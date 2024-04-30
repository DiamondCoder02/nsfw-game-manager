package frontendGUI.gameButtons;

import folderHandling.localFoldersLoad.getSteamFolderInfos;
import webApiScrapeThings.sites.loadF95site;
import webApiScrapeThings.sites.loadSteam;

public class getGamesInfo {
	/* infos
	0 - ID		1 - Name	2 - Developer	3 - Played version
	4 - Last time play		5 - Rated		6 - Newest version
	7 - Last update		8 - People rating	9 - Player progress
	10 - Still on pc?			11 - Engine		12 - OS
	13 - Language			14 - Personal notes
	*/
	public static String[] getF95zone(String id) {
		String[] output = loadF95site.getf95UrlContents(id);
			if (output == null) { return null; }

		String[] infos = {id, output[0], output[1], null, 
			null, null, output[2], 
			output[3], output[4], null, 
			null, output[5], output[6], 
			output[7], null };

		return infos;
	}

	public static String[] getSteam(String id) {
		String[] output = loadSteam.getSteamUrlContents(id);
		if (output == null) { return null; }
		// 0 - LastUpdated		1 - local buildid
		String[] output2;
		output2 = getSteamFolderInfos.getSteamAppInfo(id);
		Boolean isOnPc = false;
		if (output2 != null) { isOnPc = true;} 
		if (output2 == null) { 
			output2 = new String[2];
			output2[0] = "???"; 
			output2[1] = "???";}

		String[] infos = {
			output[0], output[1], output[2], output2[1], 
			output2[0], null, output[3], 
			output[4], output[5], null, 
			isOnPc?"Yes":"No", null, output[6], 
			output[7], null };

		return infos;
	}

	public static String[] getDLsite(String id) {
		// TODO - DLsite
		return null;
	}

	public static String[] getManual(String id) {
		String[] infos = {id,null,null,null,
			null,null,null,
			null,null,null,
			null,null,null,
			null,null};
		return infos;
	}
}
