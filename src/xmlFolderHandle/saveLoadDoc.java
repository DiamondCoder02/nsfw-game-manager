package xmlFolderHandle;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class saveLoadDoc {
	public static Document loadDocument(){
		try {
			File file = new File("hentai.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			return dom;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading database file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	public static void saveDocument(Document dom){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domsource = new DOMSource(dom);
			StreamResult result = new StreamResult("hentai.xml");
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error saving database file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void reloadTable(JTable table) {
		Document dom = saveLoadDoc.loadDocument();
		String[] columnNames = _initXml.allColumns(dom);
		Object[][] data = _initXml.loadGames(dom, columnNames);
		table.setModel(new JTable(data, columnNames).getModel());
	}
}
