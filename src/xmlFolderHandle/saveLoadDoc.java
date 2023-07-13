package xmlFolderHandle;

import java.io.File;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import main.mainInit;
import main.langLoad;

public class saveLoadDoc {
	static String[] xf = langLoad.folder, bs = langLoad.base;
	public static Document loadDocument(String path) {
		File file = new File(path);
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			return dom;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, (xf[11]!=null?xf[11]:"Error loading database file") + " (saveLoadDoc.loadDocument)", bs[1]!=null?bs[1]:"Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public static void saveDocument(Document dom, String path) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(dom), new StreamResult(path));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, (xf[11]!=null?xf[11]:"Error saving database file") + " (saveLoadDoc.saveDocument)", bs[1]!=null?bs[1]:"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void reloadTable(JTable table) {
		Document dom = saveLoadDoc.loadDocument(mainInit.databasePath);
		Document domSettings = saveLoadDoc.loadDocument(mainInit.settingsPath);
		String[] columnNames = _initXml.allColumns(domSettings);
		Object[][] data = loadGamesFromXml.loadGames(dom, columnNames);
		String[] tbl = langLoad.tabl;
		for (int i = 0; i < columnNames.length; i++) {
			switch (columnNames[i]) {
				case "Site": columnNames[i] = tbl[0]!=null?tbl[0]:"Site"; break;
				case "ID": columnNames[i] = tbl[1]!=null?tbl[1]:"ID"; break;
				case "Name": columnNames[i] = tbl[2]!=null?tbl[2]:"Name"; break;
				case "Developer": columnNames[i] = tbl[3]!=null?tbl[3]:"Developer"; break;
				case "Played version": columnNames[i] = tbl[4]!=null?tbl[4]:"Played version"; break;
				case "Last time play": columnNames[i] = tbl[5]!=null?tbl[5]:"Last time play"; break;
				case "Rated": columnNames[i] = tbl[6]!=null?tbl[6]:"Rated"; break;
				case "Newest version": columnNames[i] = tbl[7]!=null?tbl[7]:"Newest version"; break;
				case "Last update": columnNames[i] = tbl[8]!=null?tbl[8]:"Last update"; break;
				case "People rating": columnNames[i] = tbl[9]!=null?tbl[9]:"People rating"; break;
				case "Player progress": columnNames[i] = tbl[10]!=null?tbl[10]:"Player progress"; break;
				case "Still on pc?": columnNames[i] = tbl[11]!=null?tbl[11]:"Still on pc?"; break;
				case "Engine": columnNames[i] = tbl[12]!=null?tbl[12]:"Engine"; break;
				case "OS": columnNames[i] = tbl[13]!=null?tbl[13]:"OS"; break;
				case "Language": columnNames[i] = tbl[15]!=null?tbl[15]:"Language"; break;
				case "Personal Notes": columnNames[i] = tbl[14]!=null?tbl[14]:"Personal Notes"; break;
			}
		}
		table.setModel(new JTable(data, columnNames).getModel());
		getNewRenderedTable(table, (tbl[10]!=null?tbl[10]:"Player progress"));
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
		final int playProgColumn = playColumnCount;
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String status = (String) table.getModel().getValueAt(row, playProgColumn);
				if ("Not played".equals(status)) { setBackground(np);
				} else if ("In progress".equals(status)) { setBackground(ip);
				} else if ("Finish".equals(status)) { setBackground(fi);
				} else if ("100% Finished".equals(status)) { setBackground(ff);
				} else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}
				return this;
			}
		});
		return table;
	}
}
