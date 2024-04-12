package frontEndGUI;

import javax.swing.JTable;

import folderHandling.initialFileLoading.loadSettings;

public class frameTable {
	public static JTable createTable(){
		JTable table = new JTable();
		table.setBounds(30, 40, 200, 300);
		// setColumns(table);
		table.setAutoCreateRowSorter(true);
		// TODO - Don't forget to add games to the table
		// public static void refreshTable(){
		//	saveLoadDoc.reloadTable(table); 
		// }

		return table;
	}

	private static void setColumns(JTable table){
		Boolean[] boolColumns = loadSettings.shownColumns;
		Integer[] ind = new Integer[boolColumns.length];
		Integer counter = 0;
		for (int i = 0; i < boolColumns.length; i++) { ind[i] = -1; }
		for (int i = 0; i < boolColumns.length; i++) { 
			if (boolColumns[i]) {
				ind[i] = counter; 
				counter++;
			} else {
				ind[i] = -1;
			} 
		}

		Integer[] ind2 = new Integer[]{
			5,	// dl - dlsite / f95 - f95zone / man - manually added
			20,	// id
			240,// name
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
			System.out.println(i);
			System.out.println(ind[i]);
			System.out.println(ind2[i]);
			if (boolColumns[i]) { 
				System.out.println("Setting column " + i + " to " + ind[i] + " with width " + ind2[i]);
				System.out.println(boolColumns[i]);
				table.getColumnModel().getColumn(ind[i]).setPreferredWidth(ind2[i]); 
			}
		}
	}
}
