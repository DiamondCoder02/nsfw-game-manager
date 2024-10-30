package webApiScrapeThings;

import java.awt.Dimension;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import backendThings.integrationCheck.defaultValues;
import folderHandling.ADocHandle;
import folderHandling.initialFileLoading.loadGames;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.mainFrame;

public class autoSitesFetch {
	static boolean manualButton = false;
	static String[] lf = loadLanguage.folder, bs = loadLanguage.base;

	/**
	 * This function will fetch the info from the sites and update the table.
	 */
	public static void fetchInfoAskConfirm(String mainDire) {
		String text = lf[0]==null?"This will go through all games and check if there is new update.\nAre you sure?":lf[0];
		int option = JOptionPane.showConfirmDialog(null, text, bs[3]==null?"Update":bs[3], JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			manualButton = true;
			fetchInfoThenUpdateTable(mainDire);
		}
	}

	/**
	 * This function will fetch the info from the sites and update the table.
	 */
	public static void fetchInfoThenUpdateTable(String mainDire) {
		if (!loadGames.loadGamesFromXML(mainDire)) { 
			JOptionPane.showMessageDialog(null, "No games loaded. ERROR (autoSitesFetch.fetchInfoThenUpdateTable)", bs[3]==null?"Update":bs[3], JOptionPane.INFORMATION_MESSAGE);
			return; 
		}
		Object[][] loadedGames = loadGames.data;
		CompletableFuture.runAsync(() -> {
			JProgressBar pbar = new JProgressBar(0, loadedGames.length);
			pbar.setStringPainted(true);
			pbar.setPreferredSize( new Dimension ( 300, 20));
			JFrame frame = new JFrame(lf[1]==null?"Checking games...":lf[1]);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setContentPane(pbar);
			frame.setUndecorated(true);
			frame.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
			frame.setLocation(550, 300);
			frame.pack();
			frame.setVisible(true);

			Boolean[] otherSettings = loadSettings.othersettings;
			if (!otherSettings[1] || !manualButton) return;

			ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
			for (int i = 0; i < loadedGames.length; i++) {
				String site = loadedGames[i][0].toString();
				String[] newGame = null;
				switch (site) {
					case "f95": newGame = getGamesInfo.getF95zone(loadedGames[i][1].toString()); break;
					case "steam": newGame = getGamesInfo.getSteam(loadedGames[i][1].toString()); break;
					case "dls": newGame = getGamesInfo.getDLsite(loadedGames[i][1].toString()); break;
					default: break;
				}
				if (newGame == null) { continue; }
				// This will be future problem. To bad :3
				try {
					executorService.scheduleAtFixedRate(myRunTask(mainDire, loadedGames[i], newGame), 0, 1, TimeUnit.SECONDS);
				} catch (Exception e) {
					System.out.println(e);
				}
				frame.setTitle((lf[1]!=null?lf[1]:"Checking games...") +" "+ site + ":" + loadedGames[i][1].toString());
				pbar.setValue(i);
			}
			pbar.setValue(loadedGames.length);
			manualButton = false;
			JOptionPane.showMessageDialog(null, lf[2]==null?"All game infos got updated":lf[2], bs[3]==null?"Update":bs[3], JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
			mainFrame.refreshTable(mainDire);
		});
	}

	// Notes.md
	/**
	 * This function will check if there is a new update for the game.<p>
	 * This is a huge loop that will check every game in the table.
	 * @param mainDir - The main directory of the program.
	 * @param id - The game ID to check.
	 * @param LoadGamesLength - The length of the loaded games.
	 * @return Runnable - returns null.
	 */
	private static Runnable myRunTask(String mainDir, Object[] oldLoadGame, String[] newGame) {
		String dateOfLastUpdateValue = newGame[6].toString();
		String olddateOfLastUpdateValue = oldLoadGame[7].toString();

		if (!olddateOfLastUpdateValue.equals(dateOfLastUpdateValue)) return null;
		String[] gameInfos = new String[oldLoadGame.length];
		gameInfos[0] = oldLoadGame[0].toString();
		for (int i = 1; i < oldLoadGame.length; i++) {
			// log.print(oldLoadGame[i] + " " + i + " " + newGame[i-1]);
			if (newGame[i-1] != null) { gameInfos[i] = newGame[i-1]; } 
			else { gameInfos[i] = oldLoadGame[i].toString(); }
		}

		String[] defaultGamesInfo = defaultValues.gameInfos;
		try{
			Document dom = ADocHandle.load(mainDir);
			Element e = ADocHandle.getElementFromDB(dom, oldLoadGame[1].toString(), oldLoadGame[0].toString());
			if (e == null) return null;

			for (int i = 2; i < defaultGamesInfo.length; i++) {
				try {
					if (gameInfos[i] == null) { gameInfos[i] = "N/A"; }
					e.getElementsByTagName(defaultGamesInfo[i]).item(0).setTextContent(gameInfos[i]);
				} catch (Exception e1) {
					Element newElement = dom.createElement(defaultGamesInfo[i]);
					newElement.setTextContent(gameInfos[i]);
					e.appendChild(newElement);
				}
			}
			ADocHandle.save(dom, mainDir);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}
