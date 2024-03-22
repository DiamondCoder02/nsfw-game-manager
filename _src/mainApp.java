import WebsiteHandle.autoUpdateCheck;
import WebsiteHandle.loadSteam;
import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import main.downloadNewVersion;
import main.mainInit;

public class mainApp {
	public static void main(String[] args) {
		checksFiles.checkSettingsFolder();
		loadSteam.getSteamUrlContents(620980);
		if (autoUpdateCheck.checkUpdate() && loadSettingsFromXml.loadVersionBoolean()) {
			downloadNewVersion.getNewestGithubVersion();
		} else {
			checksFiles.checks();
			mainInit.mainStart();
		}
	}
}