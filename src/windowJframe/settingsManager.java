package windowJframe;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.saveLoadDoc;

public class settingsManager {
	public static void columnVisibility(String gac) {
		try{
			Document dom = saveLoadDoc.loadDocument();
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList showncolumns = e.getElementsByTagName("showncolumns");
				for (int i = 0; i < showncolumns.getLength(); i++) {
					Node showncolumnsNode = showncolumns.item(i);
					if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) showncolumnsNode;
						String column = e2.getTextContent().trim();
						if (column.equals(gac)) {
							String enabled = e2.getAttribute("enabled").trim();
							if (enabled.equals("true")) {
								e2.setAttribute("enabled", "false");
							} else {
								e2.setAttribute("enabled", "true");
							}
							saveLoadDoc.saveDocument(dom);
							_initFrame.refreshTable();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
