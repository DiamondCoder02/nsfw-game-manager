package frontendGUI.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import folderHandling.changeSettings;
import folderHandling.localFolderHandle;
import folderHandling.initialFileLoading.loadLanguage;
import frontendGUI.mainFrame;
import frontendGUI.buttons.credits;
import frontendGUI.buttons.databaseCopy;
import frontendGUI.buttons.discord;
import frontendGUI.buttons.faq;
import frontendGUI.buttons.gameFolderLocation;
import frontendGUI.buttons.languageChoice;
import frontendGUI.buttons.randomGames;
import frontendGUI.buttons.search;
import frontendGUI.gameButtons.addF95;
import frontendGUI.gameButtons.addManual;
import frontendGUI.gameButtons.removeGame;
import frontendGUI.gameButtons.updateF95;
import frontendGUI.gameButtons.updateManual;
import webApiScrapeThings.autoSitesFetch;

public class frameMenuListener implements ActionListener {
	static String[] btn = loadLanguage.buton, fld = loadLanguage.folder;
	public void actionPerformed(ActionEvent e) {
		// System.out.println("Button Clicked! " + e.getActionCommand());
		switch (e.getActionCommand()) {
			case "Add game": addManual.addOneGameToFile(); break;
			case "Add F95zone": addF95.addFromF95(); break;
			case "Update game": updateManual.updateOneGameFromToFile(); break;
			case "Update F95zone": updateF95.updatef95game(); break;
			case "Remove game": removeGame.removeOneGameFromFile("man");
			case "Remove F95zone": removeGame.removeOneGameFromFile("f95");

			case "Save file copy": databaseCopy.saveFileCopy(); break;
			case "Refresh table": localFolderHandle.fetchFoldersForTable(); break;
			case "API refresh": autoSitesFetch.fetchInfoAskConfirm(); break;

			case "RandomFully": case "RandomDev": case "RandomProgress": case "RandomEngine": case "RandomSite": 
				randomGames.randoms(e.getActionCommand());
				break;

			case "searchId": case "searchName": case "searchDev": 
				search.searcher(e.getActionCommand());
				break;

			case "site": case "id": case "name": case "developer": case "playedVersion": case "lastTimePlayed": case "rated": 
			case "newestVersionOnline": case "lastDateTimeUpdated": case "peopleOnlineRating": case "localPlayerProgress": 
			case "gameStillOnPc": case "gameEngine": case "os": case "language": case "localPersonalNotes":
				changeSettings.changeSetting("shownColumns", e.getActionCommand()); 
				break;

			case "autoUpdateManager": case "autoFetchNewGameInfos": case "autoFetchLocalGameFolder": case "darkMode":
				changeSettings.changeSetting("othersettings", e.getActionCommand()); 
				break;

			case "DiscordRPC": 
				changeSettings.changeSetting("othersettings", e.getActionCommand()); 
				discord.loopDiscord();
				break;
			
			case "folderLocation": gameFolderLocation.gamesLocationChoose(); break;
			case "appLanguage": languageChoice.langChoose(); break;

			case "FAQ": faq.FACKQU(); break;
			case "Credits": credits.money(); break;
			case "Exit": sureAboutExit(); break;

			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!!" + " (src/frontEndGUI/frameMenuListener_actionPerformed)", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
		mainFrame.refreshTable();
	}

	private static void sureAboutExit(){
		int option = JOptionPane.showConfirmDialog(null, fld[20]!=null?fld[20]:"Are you sure you want to exit?", btn[8]!=null?btn[8]:"Exit", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}
