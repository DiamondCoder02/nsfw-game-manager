package frontendGUI.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.updateSettings;
import folderHandling.localFoldersLoad.localFolderHandle;
import frontendGUI.mainFrame;
import frontendGUI.mainProgramStart;
import frontendGUI.buttons.credits;
import frontendGUI.buttons.databaseChange;
import frontendGUI.buttons.databaseCopy;
import frontendGUI.buttons.discord;
import frontendGUI.buttons.faq;
import frontendGUI.buttons.firstWelcomeMessage;
import frontendGUI.buttons.gameFolderLocation;
import frontendGUI.buttons.languageChoice;
import frontendGUI.buttons.randomGames;
import frontendGUI.buttons.search;
import frontendGUI.gameButtons.addGame;
import frontendGUI.gameButtons.removeGame;
import frontendGUI.gameButtons.updateGame;
import webApiScrapeThings.autoSitesFetch;

public class frameMenuListener implements ActionListener {
	static String[] btn = loadLanguage.buton, fld = loadLanguage.folder;
	private static String mainDir = mainProgramStart.mainProgDir;
	public void actionPerformed(ActionEvent e) {
		// System.out.println("Button Clicked! " + e.getActionCommand());
		switch (e.getActionCommand()) {
			// frontendGUI/frames/frameCreate.java
			case "Add game": addGame.addOneGame(mainDir); break;
			case "Update game": updateGame.updateOneGame(mainDir); break;
			case "Remove game": removeGame.removeOneGame(mainDir); break;

			case "Save file copy": databaseCopy.saveFileCopy(mainDir); break;
			case "Refresh table": localFolderHandle.fetchFoldersForTable(mainDir); break;
			case "API refresh": autoSitesFetch.fetchInfoAskConfirm(mainDir); break;

			case "RandomFully": case "RandomDev": case "RandomProgress": case "RandomEngine": case "RandomSite": 
				randomGames.randoms(e.getActionCommand());
				break;

			case "searchId": case "searchName": case "searchDev": 
				search.searcher(e.getActionCommand());
				break;

			case "site": case "id": case "name": case "developer": case "playedVersion": case "lastTimePlayed": case "rated": 
			case "newestVersionOnline": case "lastDateTimeUpdated": case "peopleOnlineRating": case "localPlayerProgress": 
			case "gameStillOnPc": case "gameEngine": case "os": case "language": case "localPersonalNotes":
				updateSettings.changeSetting(mainDir, "shownColumns", e.getActionCommand()); 
				break;

			case "autoUpdateManager": case "autoFetchNewGameInfos": case "autoFetchLocalGameFolder":
				updateSettings.changeSetting(mainDir, "othersettings", e.getActionCommand()); 
				break;
			case "darkMode":
				updateSettings.changeSetting(mainDir, "othersettings", e.getActionCommand()); 
				mainProgramStart.mainMenuFullChange();
				break;


			case "DiscordRPC": 
				updateSettings.changeSetting(mainDir, "othersettings", e.getActionCommand()); 
				discord.loopDiscord(mainDir);
				break;
			
			case "dbChange": 
				databaseChange.changeDatabase(mainDir); 
				mainProgramStart.mainMenuFullChange(); 
				break;
			case "folderLocation": gameFolderLocation.gamesLocationChoose(mainDir); break;
			case "appLanguage": 
				languageChoice.langChoose(mainDir); 
				mainProgramStart.mainMenuFullChange(); 
				break;

			case "welcomeMessage": firstWelcomeMessage.welcomeMessage(mainDir); break;
			case "FAQ": faq.FACKQU(); break;
			case "Credits": credits.money(); break;
			case "Exit": sureAboutExit(); break;

			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!!" + " (src/frontEndGUI/frameMenuListener_actionPerformed)", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
		mainFrame.refreshTable(mainDir);
	}

	private static void sureAboutExit(){
		int option = JOptionPane.showConfirmDialog(null, fld[20]!=null?fld[20]:"Are you sure you want to exit?", btn[8]!=null?btn[8]:"Exit", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}
