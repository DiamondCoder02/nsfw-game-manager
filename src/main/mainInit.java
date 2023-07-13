package main;

import org.w3c.dom.Document;

import xmlFolderHandle.loadGamesFromXml;
import xmlFolderHandle.loadSettingsFromXml;
import xmlFolderHandle.saveLoadDoc;
import f95WebsiteHandle._initSiteFetch;

import windowJframe._initFrame;

public class mainInit {
	// TODO test databese with my 600 line excel sheet
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