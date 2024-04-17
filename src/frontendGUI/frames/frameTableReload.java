package frontendGUI.frames;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import folderHandling.initialFileLoading.loadGames;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import integrationCheck.defaultValues;

public class frameTableReload {
	public static void reloadTable(JTable table, String mainDirectory) {
		String[] columnNames = enabledRows();
		Object[][] data = loadGames.loadGamesFromXML(mainDirectory);
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
		table.setModel(new JTable(data, columnNames).getModel());
		getNewRenderedTable(table, (tbl[10]!=null?tbl[10]:"Player progress"));
	}

	private static String[] enabledRows() {
		String[] allColumnNames = defaultValues.settings[4];
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

	private static JTable getNewRenderedTable(final JTable table, String playProgColName) {
		// change row color - Not played: red, In progress: yellow, Finish: blue, 100% Finished: green
		// boolean[] otherSettings = settingsManager.loadSettings("othersettings");
		Color np, ip, fi, ff;
		// if (otherSettings[0]){
			np = new Color(255, 110, 130);
			ip = new Color(255, 255, 120);
			fi = new Color(100, 170, 255);
			ff = new Color(130, 255, 130);
		/* 
		} else {
			np = new Color(255, 0, 0);
			ip = new Color(255, 255, 0);
			fi = new Color(0, 0, 255);
			ff = new Color(0, 255, 0);
		}
		*/

		int playColumnCount = 0;
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (table.getColumnName(i).equals(playProgColName)) {
				playColumnCount = i;
				break;
			}
		}
		int playProgColumn = playColumnCount;
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(
				JTable table,
				Object value, 
				boolean isSelected, 
				boolean hasFocus, 
				int row, 
				int col
			) {
				TableModel model = table.getModel();
				int modelRow = table.getRowSorter().convertRowIndexToModel(row);
				String status = (String) model.getValueAt(modelRow, playProgColumn);

				if (status.equals(defaultValues.infoProgress[0])) { setBackground(np);
				} else if (status.equals(defaultValues.infoProgress[1])) { setBackground(ip);
				} else if (status.equals(defaultValues.infoProgress[2])) { setBackground(fi);
				} else if (status.equals(defaultValues.infoProgress[3])) { setBackground(ff);
				} else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}
				setText(value != null ? value.toString() : "");
				return this;
			}
		});
		return table;
	}
}
