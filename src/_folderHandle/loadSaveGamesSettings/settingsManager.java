package _folderHandle.loadSaveGamesSettings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import _folderHandle.loadSaveGamesSettings.choices.*;
import _main.langLoad;
import _main.mainInit;
import _main.application.frameCreate;

public class settingsManager {
	static String[] folder = langLoad.folder, butt = langLoad.buton;
	public static void xmlSettings(String TagName, String options){
		// System.out.println(options);
		try{
			Document dom = saveLoadDoc.loadDocument(mainInit.settingsPath);
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
						switch (options) {
							case "lang": languageChoices.langChoose(dom); break;
							case "gameInfoFolLoc": gamesLocationChoice.gamesLocationChoose(dom); break;
							case "appVer": {
								saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
								break;
							}
							case "appVer2": {
								String enabled = e2.getAttribute("enabled").trim();
								if (enabled.equals("true")) {
									e2.setAttribute("enabled", "false");
								} else {
									e2.setAttribute("enabled", "true");
								}
								saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
								break;
							}
							default: {
								if (option.equals(options)) {
									String enabled = e2.getAttribute("enabled").trim();
									if (enabled.equals("true")) {
										e2.setAttribute("enabled", "false");
									} else {
										e2.setAttribute("enabled", "true");
									}
									saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
									frameCreate.refreshTable();
								}
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
