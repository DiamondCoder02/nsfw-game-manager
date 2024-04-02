package folderHandling;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class creatingMissingSettings {
	public static boolean creatingMissingSettingsHandler(String directoryPath, String rootElement, String[][] allSettings) {
		// TODO - check the settings file and update if some values are missing
		/*
		String[][] settings = {
			{"othersettings", "enabled-true", "Dark mode"},
			{"othersettings", "enabled-false", "Auto fetch game info"},
			{"othersettings", "enabled-false", "Auto fetch folders"},
			{"language", "english"},
			{"folderLocation", "null"},
			{"appVersion", "enabled-true", "0.1.1.4"},
			{"showncolumns", "enabled-true", "Site"},
			{"showncolumns", "enabled-true", "ID"},
			{"showncolumns", "enabled-true", "Name"},
			{"showncolumns", "enabled-true", "Developer"},
		}
		Transform the abouve into below example:
		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
		<settings>
			<othersettings enabled="true">Dark mode</othersettings>
			<othersettings enabled="false">Auto fetch game info</othersettings>
			<othersettings enabled="false">Auto fetch folders</othersettings>
			<language>english</language>
			<folderLocation>null</folderLocation>
			<appVersion enabled="true">0.1.1.4</appVersion>
			<showncolumns enabled="true">Site</showncolumns>
			<showncolumns enabled="true">ID</showncolumns>
			<showncolumns enabled="true">Name</showncolumns>
			<showncolumns enabled="true">Developer</showncolumns>
		</settings>

		 */
		Document dom = ADocHandle.load(directoryPath);
		if (dom == null) { return creatingDefaultDoc.creatingDocHandler(directoryPath, rootElement, allSettings); }
		try {
			NodeList settingSource = dom.getElementsByTagName(rootElement);
			Node settingNode = settingSource.item(0);
			if (settingNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingNode;
				System.out.println("Root element: " + e.getNodeName());
				System.out.println("Root element: " + e.getTextContent());

				for (int i = 0; i < allSettings.length; i++) {
					NodeList setting = e.getElementsByTagName(allSettings[i][0]);
					
				}
			}


			/*
				NodeList setting = e.getElementsByTagName(allSettings[i][0]);
				switch (settings[i]) {
					case "othersettings": otSe = checkings(setting, othersettings, dom, "othersettings"); break;
					case "folderLocation": foSe = singleChecks(setting, folderLocation, dom, "folderLocation"); break;
					case "language": laSe = singleChecks(setting, language, dom, "language"); break;
					case "appVersion": shCo = singleChecks(setting, appVersion, dom, "appVersion"); break;
					case "showncolumns": shCo = checkings(setting, showncolumns, dom, "showncolumns"); break;
					default: JOptionPane.showMessageDialog(null, ("Should be impossible") + "checkMissingSetting", "Error", JOptionPane.ERROR_MESSAGE); break;
				}
			
			 */
			// TODO - Pleae kill me
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// private static void trueFalseSet(NodeList setting, )

	private static void checkings(NodeList setting, String[] somethingSettings, Document dom, String settingName, String path ) {
		// Check if the setting is missing
		for (int j = 0; j < somethingSettings.length; j++) {
			for (int k = 0; k < setting.getLength(); k++) {
				if (setting.item(k).getTextContent() != null) {
					if (setting.item(k).getTextContent().contains(somethingSettings[j])) {
						break;
					} else if (k == setting.getLength() - 1) {
						Element newSetting = dom.createElement(settingName);
						newSetting.setAttribute("enabled", "false");
						newSetting.appendChild(dom.createTextNode(somethingSettings[j]));
						dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
					}
				} else {
					if (k == setting.getLength() - 1) {
						Element newSetting = dom.createElement(settingName);
						newSetting.setAttribute("enabled", "false");
						newSetting.appendChild(dom.createTextNode(somethingSettings[j]));
						dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
					}
				}
			}
		}
		ADocHandle.save(dom, path);
	}

	private static void singleChecks(NodeList setting, String somethingSettings, Document dom, String settingName, String path) {
		// Check if the setting is missing
		if (setting.item(0) != null) {
			return;
		}
		Element newSetting = dom.createElement(settingName);
		if (settingName == "appVersion") { newSetting.setAttribute("enabled", "true"); }
		newSetting.appendChild(dom.createTextNode(somethingSettings));
		dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
		ADocHandle.save(dom, path);
	}
}
