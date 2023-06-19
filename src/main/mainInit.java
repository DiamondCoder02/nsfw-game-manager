package main;

import org.w3c.dom.Document;

import xmlFolderHandle.loadGamesFromXml;
import xmlFolderHandle.loadSettingsFromXml;

import f95WebsiteHandle._initSiteFetch;

import windowJframe._initFrame;

public class mainInit {
	public static String settingsPath = checksFile.mainPath + "settings.xml";
	public static String databasePath = checksFile.mainPath + "hentai.xml";
	public static String language = loadSettingsFromXml.loadStringSettings("language")[0];

	public static Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
	public static String[] strColumnNames = loadSettingsFromXml.loadStringSettings("showncolumns");
	public static void main(String[] args) {
		Document domGame = loadGamesFromXml.loadDocsFromString(databasePath);

		checksFile.checks();

		_initFrame frame = new _initFrame();
		frame.setIconImage(frame.getToolkit().getImage(System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager\\pics\\nyaaa.png"));
		
		Object[][] data = loadGamesFromXml.loadGames(domGame, strColumnNames);

		frame.WindowCreate(data);

		if (boolSettings[1]) {
			_initSiteFetch.fetchInfoThenUpdateTable();
		}
	}
}