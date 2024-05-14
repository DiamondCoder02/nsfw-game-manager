package frontendGUI;

import java.io.File;

import javax.swing.JFrame;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.backupHandle;
import folderHandling.localFoldersLoad.getSteamFolderInfos;
import folderHandling.localFoldersLoad.localFolderHandle;
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
		System.out.println("--- System check passed! ---");

		if (!loadSettings.load(mainProgDir)) { return; }
		if (!loadLanguage.loadLangFile(mainProgDir)) { return; }
		System.out.println("--- Settings / Languages loaded ---");

		System.out.println("--- Checking for new version --- Enabled:" + loadSettings.othersettings[0]);
		if (!new File("../../../steamapps").exists()) {
			if (loadSettings.othersettings[0]) { 
				if (newVersion.checkNewVersion(mainProgDir)) {
					System.out.println("-- New Version Available --"); return;
				} 
			}
			System.out.println("-- No New Version --");
		} else {
			System.out.println("-- Steam detected --");
		}

		if (loadSettings.othersettings[2]) { 
			autoSitesFetch.fetchInfoThenUpdateTable(mainProgDir); 
			System.out.println("- Auto fetch online done -");
		}
		if (loadSettings.othersettings[3]) { 
			localFolderHandle.fetchFoldersForTable(mainProgDir); 
			System.out.println("- Local fetch done -");
		}
		if (loadSettings.othersettings[4] && !discordStart) { 
			discord.loopDiscord(mainProgDir); 
			System.out.println("- Discord loop started -"); 
			discordStart = true;
		}

		if (getSteamFolderInfos.loadSteamFolders()) {  System.out.println("--- Steam loaded ---");
		} else { System.out.println("--- Steam is not detected or not downloaded ---"); }

		backupHandle.doBackup(mainProgDir);
		System.out.println("--- Backup started ---");
		frameColor.UIColorChangeShit();
		System.out.println("--- Color change done ---");
		mainFrame.createFrame(mainProgDir);
		System.out.println("--- GUI started ---");

		if (!loadSettings.othersettings[6]) { firstWelcomeMessage.welcomeMessage(mainProgDir); }
	}
}
