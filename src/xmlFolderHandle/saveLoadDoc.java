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

import main._initChecksFile;

public class saveLoadDoc {
	static String path = System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager\\hentai.xml";

	public static Document loadDocument() {
		// find file
		File file = new File(path);
		if (!file.exists()) {
			_initChecksFile.createFile(path);
		}
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			return dom;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading database file (loadDocument)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public static void saveDocument(Document dom) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domsource = new DOMSource(dom);
			StreamResult result = new StreamResult(path);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error saving database file (saveDocument)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void saveADocument(String pathOther) {
		try {
			Document dom = loadDocument();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domsource = new DOMSource(dom);
			StreamResult result = new StreamResult(pathOther);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error saving database file (saveADocument)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void reloadTable(JTable table) {
		Document dom = saveLoadDoc.loadDocument();
		String[] columnNames = _initXml.allColumns(dom);
		Object[][] data = _initXml.loadGames(dom, columnNames);
		table.setModel(new JTable(data, columnNames).getModel());
		getNewRenderedTable(table);
	}

	private static JTable getNewRenderedTable(final JTable table) {
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
			if (table.getColumnName(i).equals("Player progress")) {
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
