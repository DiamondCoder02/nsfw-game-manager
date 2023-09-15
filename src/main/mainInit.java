package main;

import java.io.IOException;

import WebsiteHandle.autoUpdateCheck;
import folderHandle.autoFetchChecks.autoFolderChecks;
import folderHandle.autoFetchChecks.autoSiteFetching;
import folderHandle.checkAndBackup.backup;
import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadGamesFromXml;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import main.application.discord;
import main.application.frameCreate;

public class mainInit {
	public static String settingsPath = checksFiles.mainPath + "settings.xml";
	public static String databasePath = checksFiles.mainPath + "hentai.xml";
	public static void main(String[] args) {
		// TODO Update autoCheck:

		checksFiles.checks();

		autoUpdateCheck.test();

		Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");

		if (boolSettings[2]) { autoFolderChecks.fetchFoldersForTable(); }

		frameCreate frame = new frameCreate();
		frame.setIconImage(frame.getToolkit().getImage(System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager\\pics\\nyaaa.png"));

		Object[][] data = loadGamesFromXml.loadGames();

		frame.WindowCreate(data);

		if (boolSettings[1]) { autoSiteFetching.fetchInfoThenUpdateTable(); }
		backup.doBackup();

		if (boolSettings[3]) { try { discord.discordFirstInit(); } catch (IOException e) { e.printStackTrace(); } }
	}
}

// TODO Links (3):
/*
 * https://stackoverflow.com/questions/232347/how-should-i-implement-an-auto-updater
 * https://stackoverflow.com/questions/1881714/how-to-start-stop-restart-a-thread-in-java
 * Text size small on large display - https://bugs.openjdk.org/browse/JDK-8202973
 */

/* TODO order of storage:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes
 */