package frontendGUI.gameButtons;

import javax.swing.JOptionPane;

import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;

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
		addGameHandle.addGameToDB(webAndId[0], infos);
	}
}
