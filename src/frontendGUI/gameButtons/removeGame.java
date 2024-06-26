package frontendGUI.gameButtons;

import javax.swing.JOptionPane;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.updateDatabase;

public class removeGame {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	public static void removeOneGame(String mainDir){
		boolean repeat = true;
		while (repeat) {
			String[] webAndId = sites.requestSiteAndId(mainDir, "remove", base[4]!=null?base[4]:"Remove game");
			if (webAndId == null) { return; }

			Boolean success = updateDatabase.removeGameFromDatabase(mainDir, webAndId[1], webAndId[0]);
			if (!success) { repeat = false; return; }
			
			int optionToRepeat = JOptionPane.showConfirmDialog(null, folder[17]!=null?folder[17]:"Do you want to delete another game?", base[4]!=null?base[4]:"Delete game", JOptionPane.YES_NO_OPTION);
			if (optionToRepeat != JOptionPane.OK_OPTION) { repeat = false; }
		}
	}
}
