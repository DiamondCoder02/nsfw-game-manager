package frontEndGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import folderHandling.changeSettings;

public class frameMenuListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Button Clicked!");
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
			/*
			case "Add game": addManually.addOneGameToFile(); break;
			case "Update game": updateManually.updateOneGameFromToFile(); break;
			case "Remove game": removeAnyGame.removeOneGameFromFile("man"); break;
			case "Add F95zone": addFromF95site.addFromF95(); break;
			case "Update F95zone": updateFromF95site.updatef95game(); break;
			case "Remove F95zone": removeAnyGame.removeOneGameFromFile("f95"); break;

			case "Save file copy": otherButtons.saveFileCopy();	break;
			case "Refresh table": autoFolderChecks.fetchFoldersForTable(); break;
			case "API refresh":  autoSiteFetching.fetchInfoAskConfirm(); break;

			case "RandomFully": randomGame.fullyRandom(); break;
			case "RandomDev": randomGame.randomFromDeveloper(); break;
			case "RandomProgress": randomGame.randomFromProgress(); break;
			case "RandomEngine": randomGame.randomWithEngine(); break;
			case "RandomSite": randomGame.randomFromSite(); break;

			case "Search by ID": searchButton.search("id"); break;
			case "Search by name": searchButton.search("name"); break;
			case "Search by developer": searchButton.search("dev"); break;
			*/


			case "site": case "id": case "name": case "developer": case "playedVersion": case "lastTimePlayed": case "rated": 
			case "newestVersionOnline": case "lastDateTimeUpdated": case "peopleOnlineRating": case "localPlayerProgress": 
			case "gameStillOnPc": case "gameEngine": case "os": case "language": case "localPersonalNotes":
				changeSettings.changeSetting("shownColumns", e.getActionCommand()); 
				break;

			case "autoUpdateManager": case "darkMode": case "autoFetchNewGameInfos": case "autoFetchLocalGameFolder": case "DiscordRPC":
				changeSettings.changeSetting("othersettings", e.getActionCommand()); 
				break;
			
			// TODO - Why?
			// case "Dark mode": settingsManager.xmlSettings("othersettings", "Dark mode"); WindowRefresh(); refreshTable(); break;
			// case "Discord RPC": settingsManager.xmlSettings("othersettings", "DiscordRPC"); try {discord.loopDiscord();} catch (IOException e1) {e1.printStackTrace();}; break;


			/*
			case "FAQ": otherButtons.FACKQU(); break;
			case "Credits": otherButtons.money(); break;
			case "Exit": otherButtons.sureAboutExit(); break;
			*/
			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!!" + " (src/frontEndGUI/frameMenuListener_actionPerformed)", "Error", JOptionPane.ERROR_MESSAGE); break;
		}

	}
}
