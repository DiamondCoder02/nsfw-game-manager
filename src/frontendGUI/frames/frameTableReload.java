package frontendGUI.frames;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import folderHandling.initialFileLoading.loadGames;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.colors.tableColor;
import integrationCheck.defaultValues;

public class frameTableReload {
	public static void reloadTable(JTable table, String mainDirectory) {
		String[] columnNames = enabledRows();
		if (!loadGames.loadGamesFromXML(mainDirectory)) { 
			JOptionPane.showMessageDialog(null, "No games loaded. ERROR (frameTableReload.reloadTable)", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			return; 
		}
		Object[][] data = loadGames.data;
		String[] tbl = loadLanguage.tabl;

		for (int i = 0; i < columnNames.length; i++) {
			switch (columnNames[i]) {
				case "shownColumns": break;
				case "site": columnNames[i] = tbl[0]!=null?tbl[0]:"Site"; break;
				case "id": columnNames[i] = tbl[1]!=null?tbl[1]:"ID"; break;
				case "name": columnNames[i] = tbl[2]!=null?tbl[2]:"Name"; break;
				case "developer": columnNames[i] = tbl[3]!=null?tbl[3]:"Developer"; break;
				case "playedVersion": columnNames[i] = tbl[4]!=null?tbl[4]:"Played version"; break;
				case "lastTimePlayed": columnNames[i] = tbl[5]!=null?tbl[5]:"Last time play"; break;
				case "rated": columnNames[i] = tbl[6]!=null?tbl[6]:"Rated"; break;
				case "newestVersionOnline": columnNames[i] = tbl[7]!=null?tbl[7]:"Newest version"; break;
				case "lastDateTimeUpdated": columnNames[i] = tbl[8]!=null?tbl[8]:"Last update"; break;
				case "peopleOnlineRating": columnNames[i] = tbl[9]!=null?tbl[9]:"People rating"; break;
				case "localPlayerProgress": columnNames[i] = tbl[10]!=null?tbl[10]:"Player progress"; break;
				case "gameStillOnPc": columnNames[i] = tbl[11]!=null?tbl[11]:"Still on pc?"; break;
				case "gameEngine": columnNames[i] = tbl[12]!=null?tbl[12]:"Engine"; break;
				case "os": columnNames[i] = tbl[13]!=null?tbl[13]:"OS"; break;
				case "language": columnNames[i] = tbl[15]!=null?tbl[15]:"Language"; break;
				case "localPersonalNotes": columnNames[i] = tbl[14]!=null?tbl[14]:"Personal Notes"; break;
				default: break;
			}
		}
		// Double click to copy value to clipboard
		table.setModel(new DefaultTableModel(data, columnNames) { // set the table model as a subclass of DefaultTableModel
			@Override
			public boolean isCellEditable(int row, int column) { // override the isCellEditable method
				return false; // return false for all cells
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println("double click");
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);
					int col = table.columnAtPoint(p);
					Object value = table.getValueAt(row, col);
					StringSelection stringSelection = new StringSelection(value.toString());
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, stringSelection);
				}
			}
		});
		tableColor.getNewRenderedTable(table, (tbl[10]!=null?tbl[10]:"Player progress"));
	}

	private static String[] enabledRows() {
		String[] allColumnNames = defaultValues.settings[1];
		String[] enabledRows = new String[allColumnNames.length-1];
		Boolean[] enabledCol = loadSettings.shownColumns;
		int counter = 0;
		for (int i = 0; i < enabledCol.length; i++) {
			if (enabledCol[i]) {
				enabledRows[counter] = allColumnNames[i+1];
				counter++;
			} 
		}
		String[] enabledRows2 = new String[counter];
		for (int i = 0; i < counter; i++) {
			enabledRows2[i] = enabledRows[i];
		}

		return enabledRows2;
	}
}
