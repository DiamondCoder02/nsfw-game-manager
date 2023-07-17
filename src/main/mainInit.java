package main;

import org.w3c.dom.Document;

import WebsiteHandle.autoSiteFetching;
import folderHandle.checkAndBackup.backup;
import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadGamesFromXml;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;
import main.application.frameCreate;

public class mainInit {
	public static String settingsPath = checksFiles.mainPath + "settings.xml";
	public static String databasePath = checksFiles.mainPath + "hentai.xml";
	public static void main(String[] args) {
		// TODO Text size small on large display - https://bugs.openjdk.org/browse/JDK-8202973
		checksFiles.checks();

		Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
		String[] strColumnNames = loadSettingsFromXml.loadStringSettings("showncolumns");

		Document domGame = saveLoadDoc.loadDocument(databasePath);

		frameCreate frame = new frameCreate();
		frame.setIconImage(frame.getToolkit().getImage(System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager\\pics\\nyaaa.png"));
		
		Object[][] data = loadGamesFromXml.loadGames(domGame, strColumnNames);

		frame.WindowCreate(data);

		if (boolSettings[1]) {
			autoSiteFetching.fetchInfoThenUpdateTable();
		}
		backup.doBackup();
	}
}