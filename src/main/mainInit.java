package main;

import org.w3c.dom.Document;

import xmlFolderHandle.loadGamesFromXml;
import xmlFolderHandle.loadSettingsFromXml;
import xmlFolderHandle.saveLoadDoc;
import f95WebsiteHandle._initSiteFetch;

import windowJframe._initFrame;

public class mainInit {
	public static String settingsPath = checksFile.mainPath + "settings.xml";
	public static String databasePath = checksFile.mainPath + "hentai.xml";
	public static void main(String[] args) {
		checksFile.checks();

		Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
		String[] strColumnNames = loadSettingsFromXml.loadStringSettings("showncolumns");

		Document domGame = saveLoadDoc.loadDocument(databasePath);

		_initFrame frame = new _initFrame();
		frame.setIconImage(frame.getToolkit().getImage(System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager\\pics\\nyaaa.png"));
		
		Object[][] data = loadGamesFromXml.loadGames(domGame, strColumnNames);

		frame.WindowCreate(data);

		if (boolSettings[1]) {
			_initSiteFetch.fetchInfoThenUpdateTable();
		}
		backup.doBackup();
	}
}

// TODO Problems: 6
/* 
- OS or Engine column can bug and show "> HTML" of something
- If name has dash (-) in it, it's not showing the full name
- Add something to show if game is VR and/or abondoned for f95
- Text size small on large display - https://bugs.openjdk.org/browse/JDK-8202973
- Find ways to optimize
!!! - Offline not fixed !!!
*/