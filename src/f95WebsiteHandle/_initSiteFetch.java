package f95WebsiteHandle;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.mainInit;
import windowJframe._initFrame;
import xmlFolderHandle._initXml;
import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.loadGamesFromXml;
import xmlFolderHandle.loadSettingsFromXml;
import xmlFolderHandle.saveLoadDoc;

public class _initSiteFetch extends JFrame {
	static Document dom = saveLoadDoc.loadDocument(mainInit.settingsPath);
	static String[] columnNames = _initXml.allColumns(dom);
	static Document domGame = saveLoadDoc.loadDocument(mainInit.databasePath);
	static Object[][] loadedGames = loadGamesFromXml.loadGames(domGame, columnNames);
	static boolean manualButton = false;

	public static void fetchInfoAskConfirm() {
		int option = JOptionPane.showConfirmDialog(null, "This will go through all games and check if there is new update.\nAre you sure?", "Update", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			manualButton = true;
			fetchInfoThenUpdateTable();
		}
	}

	public static void fetchInfoThenUpdateTable() {
		CompletableFuture.runAsync(() -> {
			JProgressBar pbar = new JProgressBar(0, loadedGames.length);
			pbar.setStringPainted(true);
			pbar.setPreferredSize( new Dimension ( 300, 20));
			JFrame frame = new JFrame("Progress Bar");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setContentPane(pbar);
			frame.setUndecorated(true);
			frame.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
			frame.setLocation(550, 300);
			frame.pack();
			frame.setVisible(true);

			Boolean[] otherSettings = loadSettingsFromXml.loadBooleanSettings("othersettings"); // loadBooleanSettings
			if (otherSettings[1] || manualButton) {
				ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
				for (int i = 0; i < loadedGames.length; i++) {
					String id = loadedGames[i][1].toString();
					String site = loadedGames[i][0].toString();
					try {
						if (site.equals("f95")) {
							executorService.scheduleAtFixedRate(myF95Task(id, i), 0, 1, TimeUnit.SECONDS);
						}
					} catch (Exception e) { /* /ᐠ｡ꞈ｡ᐟ\ */ }
					pbar.setValue(i);
				}
				pbar.setValue(loadedGames.length);
				manualButton = false;
				JOptionPane.showMessageDialog(null, "All game infos got updated", "Update", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				_initFrame.refreshTable();
			}
		});
	}
/* 
0	case "Site" :
1	case "ID": 
2	case "Name": 
3	case "Developer": 
4	case "Played version": 
5	case "Last time play":
6	case "Rated": 
7	case "Newest version": 
8	case "Last update": 
9	case "People rating": 
10	case "Player progress": 
11	case "Still on pc?":
12	case "Engine": 
13	case "OS": 
14	case "Personal Notes": 
*/
	private static Runnable myF95Task(String id, int LoadGamesLength) {
		String[] gameInfo = loadSite.getf95UrlContents(id);
		String dateOfLastUpdateValue = gameInfo[3].toString();
		String olddateOfLastUpdateValue = loadedGames[LoadGamesLength][4].toString();
		if (!olddateOfLastUpdateValue.equals(dateOfLastUpdateValue)) {
			String oldname = loadedGames[LoadGamesLength][2].toString();
			String olddeveloper = loadedGames[LoadGamesLength][3].toString();
			String oldnewest_version = loadedGames[LoadGamesLength][5].toString();
			String olddateof_lastupate = loadedGames[LoadGamesLength][4].toString();
			String oldpeople_rated = loadedGames[LoadGamesLength][7].toString();
			String oldengine = loadedGames[LoadGamesLength][8].toString();
			String oldos = loadedGames[LoadGamesLength][9].toString();
			String newnameValue = gameInfo[0].toString();
			String newdeveloperValue = gameInfo[1].toString();
			String newnewest_versionValue = gameInfo[2].toString();
			String newdateof_lastupateValue = gameInfo[3].toString();
			String newpeople_ratedValue = gameInfo[4].toString();
			String newengineValue = gameInfo[5].toString();
			String newosValue = gameInfo[6].toString();
			if (!oldname.equals(newnameValue)) { loadedGames[LoadGamesLength][2] = newnameValue; }
			if (!olddeveloper.equals(newdeveloperValue)) { loadedGames[LoadGamesLength][3] = newdeveloperValue; }
			if (!oldnewest_version.equals(newnewest_versionValue)) { loadedGames[LoadGamesLength][5] = newnewest_versionValue; }
			if (!olddateof_lastupate.equals(newdateof_lastupateValue)) { loadedGames[LoadGamesLength][4] = newdateof_lastupateValue; }
			if (!oldpeople_rated.equals(newpeople_ratedValue)) { loadedGames[LoadGamesLength][7] = newpeople_ratedValue; }
			if (!oldengine.equals(newengineValue)) { loadedGames[LoadGamesLength][8] = newengineValue; }
			if (!oldos.equals(newosValue)) { loadedGames[LoadGamesLength][9] = newosValue; }

			if (isIDInDatabase.isInDatabase(id, "f95")) {
				try{
					Document dom = saveLoadDoc.loadDocument(mainInit.databasePath);
					NodeList source = dom.getElementsByTagName("source");
					for (int i = 0; i < source.getLength(); i++) {
						Node sourceNode = source.item(i);
						if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
							NodeList game = sourceNode.getChildNodes();
							for (int j = 0; j < game.getLength(); j++) {
								Node gameNode = game.item(j);
								if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
									Element e = (Element) gameNode;
									if (e.getAttribute("id").trim().equals(id)) {
										e.getElementsByTagName("name").item(0).setTextContent(newnameValue);
										e.getElementsByTagName("developer").item(0).setTextContent(newdeveloperValue);
										e.getElementsByTagName("newest_version").item(0).setTextContent(newnewest_versionValue);
										e.getElementsByTagName("dateof_lastupate").item(0).setTextContent(newdateof_lastupateValue);
										e.getElementsByTagName("people_rating").item(0).setTextContent(newpeople_ratedValue);
										e.getElementsByTagName("engine").item(0).setTextContent(newengineValue);
										e.getElementsByTagName("OS").item(0).setTextContent(newosValue);
										saveLoadDoc.saveDocument(dom, mainInit.databasePath);
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}