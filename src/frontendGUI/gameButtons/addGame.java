package frontendGUI.gameButtons;

import javax.swing.JOptionPane;

import folderHandling.addGameHandle;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersLoad.getSteamFolderInfos;
import webApiScrapeThings.sites.loadF95site;
import webApiScrapeThings.sites.loadSteam;

public class addGame {
	static String[] base = loadLanguage.base;
	public static void addOneGame() {
		String[] webAndId = sites.requestSiteAndId(base[2]!=null?base[2]:"Add game");
		if (webAndId == null) { return; }

		if (checkDatabase.isInDatabase(webAndId[1], webAndId[0])) { 
			JOptionPane.showMessageDialog(null, 
			webAndId[0] + " with the id "+webAndId[1]+" is already in the database", 
				base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] infos = null;
		switch (webAndId[0]) {
			case "f95":
				infos = getF95zone(webAndId[1]);
				break;
			case "steam":
				infos = getSteam(webAndId[1]);
				break;
			case "dls":
				infos = getDLsite(webAndId[1]);
				break;
			case "man":
				infos = getManual(webAndId[1]);
				break;
		}

		if (infos == null) { return; }
		addGameHandle.addGameToDB(webAndId[0], infos);
	}

	/* infos
	0 - ID		1 - Name	2 - Developer	3 - Played version
	4 - Last time play		5 - Rated		6 - Newest version
	7 - Last update		8 - People rating	9 - Player progress
	10 - Still on pc?			11 - Engine		12 - OS
	13 - Language			14 - Personal notes
	*/
	private static String[] getF95zone(String id) {
		String[] output = loadF95site.getf95UrlContents(id);
			if (output == null) { return null; }

		String[] infos = {id, output[0], output[1], null, 
			null, null, output[2], 
			output[3], output[4], null, 
			null, output[5], output[6], 
			output[7], null };

		return infos;
	}

	private static String[] getSteam(String id) {
		String[] output = loadSteam.getSteamUrlContents(id);
		if (output == null) { return null; }
		// 0 - LastUpdated		1 - local buildid
		String[] output2 = getSteamFolderInfos.getSteamAppInfo(id);
		Boolean isOnPc = false;
		if (output2 != null) { isOnPc = true;}

		String[] infos = {
			output[0], output[1], output[2], output2[1], 
			output2[0], null, output[3], 
			output[4], output[5], null, 
			isOnPc?"Yes":"No", null, output[6], 
			output[7], null };

		return infos;
	}

	private static String[] getDLsite(String id) {
		// TODO - DLsite
		return null;
	}

	private static String[] getManual(String id) {
		String[] infos = {id,null,null,null,
			null,null,null,
			null,null,null,
			null,null,null,
			null,null};
		return infos;
	}
}
