package _main;

import java.io.IOException;

import _folderHandle.autoFetchChecks.autoSiteFetching;
import _folderHandle.checkAndBackup.backup;
import _folderHandle.loadSaveGamesSettings.loadGamesFromXml;
import _main.application.discord;
import _main.application.frameCreate;

public class mainInit {
	public static String mainPath = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager/";
	public static String settingsPath = mainPath + "settings.xml";
	public static String databasePath = mainPath + "hentai.xml";
	public static String appVersion = "1.1.1.4";
	public static void mainStart() {
		
		frameCreate frame = new frameCreate();
		frame.setIconImage(frame.getToolkit().getImage(mainPath + "pics\\nyaaa.png"));

		Object[][] data = loadGamesFromXml.loadGames();

		frame.WindowCreate(data);

		autoSiteFetching.fetchInfoThenUpdateTable();
		backup.doBackup();

		try { discord.loopDiscord(); } catch (IOException e) { e.printStackTrace(); }
	}
}

