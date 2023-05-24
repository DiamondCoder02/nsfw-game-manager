package windowJframe;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.saveLoadDoc;

public class settingsManager {
	public static void xmlSettings(String TagName, String options){
		System.out.println(options);
		try{
			Document dom = saveLoadDoc.loadDocument();
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList allSettings = e.getElementsByTagName(TagName);
				for (int i = 0; i < allSettings.getLength(); i++) {
					Node otherSettingsNode = allSettings.item(i);
					if (otherSettingsNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) otherSettingsNode;
						String option = e2.getTextContent().trim();
						if (option.equals(options)) {
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
