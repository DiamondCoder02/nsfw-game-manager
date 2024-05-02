package frontendGUI;

import javax.swing.JFrame;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.backupHandle;
import folderHandling.localFoldersLoad.getSteamFolderInfos;
import folderHandling.localFoldersLoad.localFolderHandle;
import frontendGUI.buttons.discord;
import frontendGUI.frames.frameColor;
import integrationCheck.defaultValues;
import integrationCheck.newVersion;
import integrationCheck.systemCheck;
import webApiScrapeThings.autoSitesFetch;

public class mainProgramStart {
	public static void mainMenuFullChange(){
		loadSettings.load(defaultValues.mainDirectory);
		loadLanguage.loadLangFile();
		mainFrame.frame.dispose();
		mainFrame.frame = new JFrame();
		mainMain();
	}

	public static void mainMain() {
		String mainDirectory = defaultValues.mainDirectory;

		if (!systemCheck.programSystemCheck(mainDirectory)) { return; }
		System.out.println("--- System check passed! ---");

		if (!loadSettings.load(mainDirectory)) { return; }
		if (!loadLanguage.loadLangFile()) { return; }
		System.out.println("--- Settings / Languages loaded ---");

		System.out.println("--- Checking for new version --- Enabled:" + loadSettings.othersettings[0]);
		if (loadSettings.othersettings[0]) { 
		if (newVersion.checkNewVersion()) {
			System.out.println("-- New Version Available --"); return;
		} }
		System.out.println("-- No New Version --");

		if (loadSettings.othersettings[2]) { 
			autoSitesFetch.fetchInfoThenUpdateTable(); 
			System.out.println("- Auto fetch online done -");
		}
		if (loadSettings.othersettings[3]) { 
			localFolderHandle.fetchFoldersForTable(); 
			System.out.println("- Local fetch done -");
		}
		if (loadSettings.othersettings[4]) { 
			discord.loopDiscord(); 
			System.out.println("- Discord loop started -"); 
		}

		if (getSteamFolderInfos.loadSteamFolders()) {  System.out.println("--- Steam loaded ---");
		} else { System.out.println("--- Steam is not detected or not downloaded ---"); }

		backupHandle.doBackup();
		System.out.println("--- Backup started ---");
		frameColor.UIColorChangeShit();
		System.out.println("--- Color change done ---");
		mainFrame.createFrame(mainDirectory);
		System.out.println("--- GUI started ---");
	}
}
