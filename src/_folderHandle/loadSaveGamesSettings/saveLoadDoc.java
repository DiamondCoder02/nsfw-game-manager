package _folderHandle.loadSaveGamesSettings;

import java.io.File;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import _main.langLoad;

public class saveLoadDoc {
	static String[] xf = langLoad.folder, bs = langLoad.base;
	public static Integer allGames = 0;
	public static Document loadDocument(String path) {
		File file = new File(path);
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			return dom;
		} catch (Exception e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, ("Error loading file:\n"+path) + " (saveLoadDoc.loadDocument)", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
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
}
