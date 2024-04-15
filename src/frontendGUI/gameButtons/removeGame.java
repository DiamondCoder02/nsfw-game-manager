package frontendGUI.gameButtons;

import javax.swing.JOptionPane;

import folderHandling.changeDatabase;
import folderHandling.initialFileLoading.loadLanguage;

public class removeGame {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	public static void removeOneGameFromFile(String fromValue){
		boolean repeat = true;
		while (repeat) {
			Boolean success = changeDatabase.removeGameFromDatabase(fromValue);
			if (!success) { repeat = false; return; }
			
			int optionToRepeat = JOptionPane.showConfirmDialog(null, folder[17]!=null?folder[17]:"Do you want to delete another game?", base[4]!=null?base[4]:"Delete game", JOptionPane.YES_NO_OPTION);
			if (optionToRepeat != JOptionPane.OK_OPTION) { repeat = false; }
		}
	}
}
