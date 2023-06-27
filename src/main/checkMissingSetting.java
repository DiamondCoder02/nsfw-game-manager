package main;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;

public class checkMissingSetting {
	static String[] mf = langLoad.folder, bc = langLoad.basic, bs = langLoad.base;
	public static void checkSettings() {
		String[] settings = {"othersettings", "language", "showncolumns"};
		String[] othersettings = {"Dark mode", "Auto fetch game info"};
		String[] language = {"english"};
		String[] showncolumns = {
			"Site", "ID", "Name", "Developer", "Played version", 
			"Last time play", "Rated", "Newest version", 
			"Last update", "People rating", "Player progress", 
			"Still on pc?", "Engine", "OS", "Personal Notes"
		};
		Boolean otSe = false, laSe = false, shCo = false;
		Document dom = xmlFolderHandle.saveLoadDoc.loadDocument(mainInit.settingsPath);
		if (dom == null) {
			createMissingSettings.createFile(mainInit.settingsPath);
		} else {
			try{
				NodeList settingSource = dom.getElementsByTagName("settings");
				if (settingSource.getLength() == 0) { deleteThenCreateBroken(); }
				Node settingNode = settingSource.item(0);
				if (settingNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) settingNode;
					for (int i = 0; i < settings.length; i++) {
						NodeList setting = e.getElementsByTagName(settings[i]);
						switch (settings[i]) {
							case "othersettings": otSe = checkings(setting, othersettings, dom, "othersettings"); break;
							case "language": laSe = checkings(setting, language, dom, "language"); break;
							case "showncolumns": shCo = checkings(setting, showncolumns, dom, "showncolumns"); break;
							default: JOptionPane.showMessageDialog(null, bc[7]!=null?bc[7]:"Should be impossible" + "checkMissingSetting", bs[1]!=null?bs[1]:"Error", JOptionPane.ERROR_MESSAGE); break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, mf[6]!=null?mf[6]:"Error checking settings." + "(checkMissingSetting.checkSettings)", bs[1]!=null?bs[1]:"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (otSe || laSe || shCo) {
			JOptionPane.showMessageDialog(null, mf[7]!=null?mf[7]:"Settings got updated. \nPlease restart the program to make sure everything is correct.", mf[8]!=null?mf[8]:"Settings updated", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static void deleteThenCreateBroken(){
		String path = mainInit.settingsPath;
		File file = new File(path);
		if(file.exists()){
			file.delete();
			createMissingSettings.createFile(path);
		} else {
			createMissingSettings.createFile(path);
		}
	}

	private static Boolean checkings(NodeList setting, String[] somethingSettings, Document dom, String settingName){
		Boolean settingsGotUpdated = false;

		// Check if the setting is missing
		for (int j = 0; j < somethingSettings.length; j++) {
			for (int k = 0; k < setting.getLength(); k++) {
				if (setting.item(k).getTextContent() != null) {
					if (setting.item(k).getTextContent().contains(somethingSettings[j])) {
						break;
					} else if (k == setting.getLength() - 1) {
						if (settingName == "language") {
							break;
						}
						Element newSetting = dom.createElement(settingName);
						if (somethingSettings[j] == "Auto fetch game info") { newSetting.setAttribute("enabled", "false"); } 
						else { newSetting.setAttribute("enabled", "true"); }
						newSetting.appendChild(dom.createTextNode(somethingSettings[j]));
						dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
						settingsGotUpdated = true;
					}
				} else { 
					if (k == setting.getLength() - 1) {
						if (settingName == "language") {
							break;
						}
						Element newSetting = dom.createElement(settingName);
						if (somethingSettings[j] == "Auto fetch game info") { newSetting.setAttribute("enabled", "false"); } 
						else { newSetting.setAttribute("enabled", "true"); }
						newSetting.appendChild(dom.createTextNode(somethingSettings[j]));
						dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
						settingsGotUpdated = true;
					}
				}
			}
		}
		xmlFolderHandle.saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
		return settingsGotUpdated;
	}
}
