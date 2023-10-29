import WebsiteHandle.autoUpdateCheck;
import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import main.downloadNewVersion;
import main.mainInit;

public class mainApp {
	public static void main(String[] args) {
		Boolean updateNeeded = false;
		updateNeeded = autoUpdateCheck.checkUpdate();
		checksFiles.checkSettingsFolder();
		Boolean wantsUpdate = loadSettingsFromXml.loadBooleanSettings("appVersion")[0];
		System.out.println("wantsUpdate: " + wantsUpdate);
		if (updateNeeded) { 
			downloadNewVersion.getNewestGithubVersion();
		} else { 
			checksFiles.checks();
			mainInit.mainStart();
		}
	}
}