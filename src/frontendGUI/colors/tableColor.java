package frontendGUI.colors;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import integrationCheck.defaultValues;

public class tableColor {
	public static JTable getNewRenderedTable(final JTable table, String playProgColName) {
		Color np, ip, fi, ff, dr;
			np = new Color(251, 165, 206);
			ip = new Color(255, 255, 120);
			fi = new Color(130, 255, 130);
			ff = new Color(100, 170, 255);
			dr = new Color(255, 110, 130);
		/* 
		not played = 251, 165, 206		(Pink)
		in progress = 255, 255, 120		(Yellow)
		finished = 130, 255, 130		(Green)
		100% finished = 100, 170, 255	(Blue)
		Dropped = 255, 110, 130			(Red)
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
				} else if (status.equals(defaultValues.infoProgress[4])) { setBackground(dr);
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
