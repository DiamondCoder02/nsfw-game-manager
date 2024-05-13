package frontendGUI.gameButtons;

import javax.swing.JOptionPane;

import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.changeDatabase;

public class removeGame {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	public static void removeOneGame(String mainDir){
		boolean repeat = true;
		while (repeat) {
			String[] webAndId = sites.requestSiteAndId(base[4]!=null?base[4]:"Remove game");
			if (webAndId == null) { return; }

			if (checkDatabase.isInDatabase(mainDir, webAndId[1], webAndId[0])) {
				Boolean success = changeDatabase.removeGameFromDatabase(mainDir, webAndId[1], webAndId[0]);
				if (!success) { repeat = false; return; }
			} else {
				JOptionPane.showMessageDialog(null, "Id: "+webAndId[1]+" "+(basic[1]!=null?basic[1]:"doesn't exists"), base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int optionToRepeat = JOptionPane.showConfirmDialog(null, folder[17]!=null?folder[17]:"Do you want to delete another game?", base[4]!=null?base[4]:"Delete game", JOptionPane.YES_NO_OPTION);
			if (optionToRepeat != JOptionPane.OK_OPTION) { repeat = false; }
		}
	}
}
