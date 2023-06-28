package xmlFolderHandle;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.langLoad;

public class loadSettingsFromXml {
	static String[] xf = langLoad.folder, bs = langLoad.base;
	public static Document loadSettingXml() {
		String path = main.mainInit.settingsPath;
		Document dom = saveLoadDoc.loadDocument(path);
		return dom;
	}

	public static Boolean[] loadBooleanSettings(String setting){
		Boolean[] columnNames = new Boolean[30];
		Integer counter = 0;
		Document dom = loadSettingXml();
		try {
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList showncolumns = e.getElementsByTagName(setting);
				for (int i = 0; i < showncolumns.getLength(); i++) {
					Node showncolumnsNode = showncolumns.item(i);
					if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) showncolumnsNode;
						String enabled = e2.getAttribute("enabled").trim();
						if (enabled.equals("true")) { 
							columnNames[counter] = true;
						} else { 
							columnNames[counter] = false; 
						}
						counter++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, xf[10]!=null?xf[10]:"Error loading settings file"+" (loadOther)", bs[1]!=null?bs[1]:"Error", JOptionPane.ERROR_MESSAGE);
		}
		Boolean[] columnNames2 = new Boolean[counter];
		for (int i = 0; i < counter; i++) {
			columnNames2[i] = columnNames[i];
		}
		return columnNames2;
	}
	
	public static String[] loadStringSettings(String setting) {
		String columnNames[] = new String[30];
		Integer counter = 0;
		Document dom = loadSettingXml();
		try {
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList showncolumns = e.getElementsByTagName(setting);
				if (setting.equals("language")){
					for (int i = 0; i < showncolumns.getLength(); i++) {
						Node showncolumnsNode = showncolumns.item(i);
						if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
							Element e2 = (Element) showncolumnsNode;
							String column = e2.getTextContent().trim();
							columnNames[counter] = column;
							counter++;
						}
					}
				} else {
					for (int i = 0; i < showncolumns.getLength(); i++) {
						Node showncolumnsNode = showncolumns.item(i);
						if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
							Element e2 = (Element) showncolumnsNode;
							String column = e2.getTextContent().trim();
							columnNames[counter] = column;
							counter++;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, (xf[10]!=null?xf[10]:"Error loading settings file") + " (loadOther)", bs[1]!=null?bs[1]:"Error", JOptionPane.ERROR_MESSAGE);
		}
		String[] columnNames2 = new String[counter];
		for (int i = 0; i < counter; i++) {
			columnNames2[i] = columnNames[i];
		}
		return columnNames2;
	}
}
