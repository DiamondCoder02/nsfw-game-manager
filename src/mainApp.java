import folderHandling.backupHandle;
import folderHandling.localFolderHandle;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.mainFrame;
import frontendGUI.buttons.discord;
import integrationCheck.defaultValues;
import integrationCheck.newVersion;
import integrationCheck.systemCheck;
import webApiScrapeThings.autoSitesFetch;

// https://stackoverflow.com/questions/7704405/how-do-i-make-my-java-application-open-a-console-terminal-window
import java.io.*;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;
public class mainApp {
	public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException{
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = mainApp.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
        }else{
            maintest(new String[0]);
            System.out.println("This is bug --- Program has ended, please type 'exit' to close the console");
        }
    }

	public static void maintest (String[] args) {
		String mainDirectory = defaultValues.mainDirectory;

		if (!systemCheck.programSystemCheck(mainDirectory)) { return; }
		System.out.println("--- System check passed! ---");

		if (!loadSettings.load(mainDirectory)) { return; }
		System.out.println("--- Settings loaded ---");

		if (!loadLanguage.load(mainDirectory)) { return; }
		System.out.println("--- Languages loaded ---");

		System.out.println("--- Checking for new version --- Enabled:" + loadSettings.othersettings[0]);
		if (loadSettings.othersettings[0]) { 
			if (newVersion.checkNewVersion()) {
				System.out.println("--- New Version Available ---"); return;
			}
		}
		System.out.println("--- No New Version ---");

		if (loadSettings.othersettings[2]) { autoSitesFetch.fetchInfoThenUpdateTable(); }
		if (loadSettings.othersettings[3]) { localFolderHandle.fetchFoldersForTable(); }
		if (loadSettings.othersettings[4]) { discord.loopDiscord(); }

		backupHandle.doBackup();
		mainFrame.createFrame(mainDirectory);
	}

	// https://code-disaster.github.io/steamworks4j/getting-started.html
}

/* order of storage:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes
 */