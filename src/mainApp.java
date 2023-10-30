import WebsiteHandle.autoUpdateCheck;
import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import main.downloadNewVersion;
import main.mainInit;

public class mainApp {
	public static void main(String[] args) {
		checksFiles.checkSettingsFolder();
		if (autoUpdateCheck.checkUpdate() && loadSettingsFromXml.loadVersionBoolean()) { 
			downloadNewVersion.getNewestGithubVersion();
		} else { 
			checksFiles.checks();
			mainInit.mainStart();
		}
	}
}