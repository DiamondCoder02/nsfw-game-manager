import folderHandling.addGame;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.backupHandle;
import folderHandling.localFoldersLoad.getSteamFolderInfos;
import folderHandling.localFoldersLoad.localFolderHandle;
import frontendGUI.mainFrame;
import frontendGUI.buttons.discord;
import frontendGUI.gameButtons.updateSteam;
import integrationCheck.defaultValues;
import integrationCheck.newVersion;
import integrationCheck.systemCheck;
import webApiScrapeThings.autoSitesFetch;

// https://stackoverflow.com/questions/7704405/how-do-i-make-my-java-application-open-a-console-terminal-window
import java.io.Console;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;
public class mainApp {
	public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException{
		Boolean consoleNeeded = false;
		if (loadSettings.load(defaultValues.mainDirectory)) { consoleNeeded = loadSettings.othersettings[5]; }
		if (consoleNeeded) { 
			Console console = System.console();
			if (console == null && !GraphicsEnvironment.isHeadless()) {
				String filename = mainApp.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
				Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
			} else{
				System.out.println("+ Console needed! +");
				mainMain();
			}
		} else { mainMain(); }
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

		backupHandle.doBackup();
		System.out.println("--- Backup started ---");
		mainFrame.createFrame(mainDirectory);
		System.out.println("--- GUI started ---");

		// TODO - steam 
		if (getSteamFolderInfos.loadSteamFolders()) {  System.out.println("--- Steam loaded ---");
		} else { System.out.println("--- Steam is not detected or not downloaded ---"); }

		// updateSteam.updateSteamGame();
		String[] test = {"12","name","dev","playVer","LastTime","Rate","NewVer","LastUpd",
			"PeopleRate","PlayProg","OnPc?","Engine","OS","Lang","Notes"};
		String[] test2 = {"26",null,null,null,null,null,null,null,
			null,null,null,null,null,null,null};
		addGame.addGameToDB("man", test );
	}
}

/* order of storage:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes
*/