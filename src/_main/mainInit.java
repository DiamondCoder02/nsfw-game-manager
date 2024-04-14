package _main;

import _folderHandle.checkAndBackup.backup;

public class mainInit {
	public static String mainPath = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager/";
	public static String settingsPath = mainPath + "settings.xml";
	public static String databasePath = mainPath + "hentai.xml";
	public static String appVersion = "1.1.1.4";
	public static void mainStart() {

		// autoSiteFetching.fetchInfoThenUpdateTable();
		backup.doBackup();
	}
}

