package main;

import java.io.IOException;

import folderHandle.autoFetchChecks.autoFolderChecks;
import folderHandle.autoFetchChecks.autoSiteFetching;
import folderHandle.checkAndBackup.backup;
import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadGamesFromXml;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import main.application.discord;
import main.application.frameCreate;

public class mainInit {
<<<<<<< HEAD
	public static String mainPath = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager/";
	public static String settingsPath = mainPath + "settings.xml";
	public static String databasePath = mainPath + "hentai.xml";
	public static void main(String[] args) {
<<<<<<< HEAD
=======
		// TODO Update autoCheck:
		autoUpdateCheck.test();

>>>>>>> e5b7719 (Why the fuck won't you work)
=======
	public static String settingsPath = checksFiles.mainPath + "settings.xml";
	public static String databasePath = checksFiles.mainPath + "hentai.xml";
	public static void mainStart() {
		// TODO Update autoCheck:
<<<<<<< HEAD
>>>>>>> c0120d7 (meow)
=======
>>>>>>> 922070941adf547b5911040ccda364716f564670
		checksFiles.checks();

		Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");

		if (boolSettings[2]) { autoFolderChecks.fetchFoldersForTable(); }

		frameCreate frame = new frameCreate();
		frame.setIconImage(frame.getToolkit().getImage(mainPath + "pics\\nyaaa.png"));

		Object[][] data = loadGamesFromXml.loadGames();

		frame.WindowCreate(data);

		if (boolSettings[1]) { autoSiteFetching.fetchInfoThenUpdateTable(); }
		backup.doBackup();

		if (boolSettings[3]) { try { discord.loopDiscord(); } catch (IOException e) { e.printStackTrace(); } }
	}
}

// TODO Link(s) (2):
/*
 * https://stackoverflow.com/questions/232347/how-should-i-implement-an-auto-updater
 * Text size small on large display - (Check Discord) https://bugs.openjdk.org/browse/JDK-8202973
 */

// TODO ERROR(s) (0):
/*
 * 1. -
 */

/* TODO order of storage:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes
 */