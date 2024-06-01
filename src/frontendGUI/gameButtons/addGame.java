package frontendGUI.gameButtons;

import javax.swing.JOptionPane;

import folderHandling.initialFileLoading.loadLanguage;
import webApiScrapeThings.getGamesInfo;

public class addGame {
	static String[] base = loadLanguage.base;
	public static void addOneGame(String mainDir) {
		String[] webAndId = sites.requestSiteAndId(mainDir, "add", base[2]!=null?base[2]:"Add game");
		if (webAndId == null) { return; }

		String[] infos = null;
		switch (webAndId[0]) {
			case "f95":
				infos = getGamesInfo.getF95zone(webAndId[1]);
				break;
			case "steam":
				infos = getGamesInfo.getSteam(webAndId[1]);
				break;
			case "dls":
				infos = getGamesInfo.getDLsite(webAndId[1]);
				break;
			case "man":
				infos = getGamesInfo.getManual(webAndId[1]);
				break;
		}

		if (infos == null) { return; }
		if (!addGameHandle.addGameToDB(mainDir, webAndId[0], infos)) {
			JOptionPane.showMessageDialog(null, 
				"Id: " + webAndId[1] + "Game Not added! "+"(addGame.addOneGame)", 
				base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE);
		}
	}
}
