package backendThings;

import java.io.File;

import javax.swing.JFrame;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.backupHandle;
import folderHandling.localFoldersLoad.getSteamFolderInfos;
import folderHandling.localFoldersLoad.localFolderHandle;
import frontendGUI.mainFrame;
import frontendGUI.buttons.discord;
import frontendGUI.buttons.firstWelcomeMessage;
import frontendGUI.colors.frameColor;
import integrationCheck.newVersion;
import integrationCheck.systemCheck;
import webApiScrapeThings.autoSitesFetch;

public class mainProgramStart {
	private static Boolean discordStart = false;
	public static String mainProgDir;
	public static String steamDir;
	/**
	 * Changes the main menu fully
	 */
	public static void mainMenuFullChange(){
		log.print("-----------------------------");
		log.print("--- Main menu full change ---");
		log.print("-----------------------------");
		loadSettings.load(mainProgDir);
		loadLanguage.loadLangFile(mainProgDir);
		mainFrame.frame.dispose();
		mainFrame.frame = new JFrame();
		mainMain();
	}

	/**
	 * Main function to start the program
	 */
	public static void mainMain() {
		if (!systemCheck.programSystemCheck(mainProgDir)) { return; }
		log.print("--- System check passed! ---");

		if (!loadSettings.load(mainProgDir)) { return; }
		if (!loadLanguage.loadLangFile(mainProgDir)) { return; }
		log.print("--- Settings / Languages loaded ---");

		log.print("--- Checking for new version --- Enabled:" + loadSettings.othersettings[0]);
		if (!new File("../../../steamapps").exists()) {
			if (loadSettings.othersettings[0]) { 
				if (newVersion.checkNewVersion(mainProgDir)) {
					log.print("-- New Version Available --"); return;
				} 
			}
			log.print("-- No New Version --");
		} else {
			log.print("-- Steam detected --", log.WARNING);
		}

		if (loadSettings.othersettings[2]) { 
			autoSitesFetch.fetchInfoThenUpdateTable(mainProgDir); 
			log.print("- Auto fetch online done -");
		}
		if (loadSettings.othersettings[3]) { 
			localFolderHandle.fetchFoldersForTable(mainProgDir); 
			log.print("- Local fetch done -");
		}
		if (loadSettings.othersettings[4] && !discordStart) { 
			discord.loopDiscord(mainProgDir); 
			log.print("- Discord loop started -"); 
			discordStart = true;
		}

		if (getSteamFolderInfos.loadSteamFolders()) {  log.print("--- Steam loaded ---");
		} else { log.print("--- Steam is not detected or not downloaded ---", log.WARNING); }

		backupHandle.doBackup(mainProgDir);
		log.print("--- Backup started ---");
		frameColor.UIColorChangeShit();
		log.print("--- Color change done ---");
		mainFrame.createFrame(mainProgDir);
		log.print("--- GUI started ---");

		if (!loadSettings.othersettings[6]) { firstWelcomeMessage.welcomeMessage(mainProgDir); }
	}
}
