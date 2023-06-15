import org.w3c.dom.Document;

import f95WebsiteHandle._initSiteFetch;
import xmlFolderHandle._initXml;
import xmlFolderHandle.saveLoadDoc;

import windowJframe._initFrame;
import windowJframe.settingsManager;

public class mainInit {
	public static void main(String[] args) {
		Document dom = saveLoadDoc.loadDocument();
		String[] columnNames = _initXml.allColumns(dom);
		Object[][] data = _initXml.loadGames(dom, columnNames);

		_initFrame frame = new _initFrame();
		frame.setIconImage(frame.getToolkit().getImage("src/icons/icon.png"));
		frame.WindowCreate(columnNames, data);

		boolean[] otherSettings = settingsManager.loadSettings("othersettings");
		if (otherSettings[1]) {
			_initSiteFetch.fetchInfoThenUpdateTable();
		}
	}
}