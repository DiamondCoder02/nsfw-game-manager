import org.w3c.dom.Document;

import xmlFolderHandle._initXml;
import xmlFolderHandle.saveLoadDoc;

import windowJframe._initFrame;

public class mainInit {
	public static void main(String[] args) {
		Document dom = saveLoadDoc.loadDocument();
		String[] columnNames = _initXml.allColumns(dom);
		Object[][] data = _initXml.loadGames(dom, columnNames);

		_initFrame frame = new _initFrame();
		frame.WindowCreate(columnNames, data);
	}
}