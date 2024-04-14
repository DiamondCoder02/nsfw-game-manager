package frontendGUI.frames;

import javax.swing.JTable;

import folderHandling.initialFileLoading.loadSettings;

public class frameTable {
	// private static JTable table = new JTable();
	public static JTable createTable(String mainDirectory, JTable table){
		// TODO - Don't forget to add games to the table
		frameTableReload.reloadTable(table, mainDirectory);
		table.setBounds(30, 40, 200, 300);
		// TODO - fixed? 
		setColumns(table);
		table.setAutoCreateRowSorter(true);
		return table;
	}

	public static void setColumns(JTable table){
		Boolean[] boolColumns = loadSettings.shownColumns;
		Integer counter = 0;

		Integer[] ind = new Integer[]{
			5,	// dl - dlsite / f95 - f95zone / man - manually added
			25,	// id
			235,// name
			90,	// developer
			65,	// played version
			50,	// last time play
			40,	// rated 
			70,	// newest version
			50,	// last update
			60,	// people rating
			65,	// player progress
			40,	// still on pc? 
			55,	// engine
			80, // os
			40,	// language
			80	// personal notes
		};

		for (int i = 0; i < ind.length; i++) {
			if (boolColumns[i]) { 
				// System.out.println(boolColumns[i]+"Setting column " + i + " to " + ind[i] + " with width ");
				table.getColumnModel().getColumn(counter).setPreferredWidth(ind[i]); 
				counter++;
			}
		}
	}
}
