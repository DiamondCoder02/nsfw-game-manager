package folderHandle.checkAndBackup;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandle.createMissing.createMissingSettings;
import main.langLoad;
import main.mainInit;

import org.w3c.dom.Element;

import java.io.File;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;

public class checkMissingSetting {
	static String[] mf = langLoad.folder, bc = langLoad.basic, bs = langLoad.base;
	public static void checkSettings() {
		String[] settings = {"othersettings", "folderLocation", "language", "appVersion", "showncolumns"};
		String[] othersettings = {"Dark mode", "Auto fetch game info", "Auto fetch folders", "DiscordRPC"};
		String folderLocation = "null", language = "english", appVersion = "0.1.1.1";
		String[] showncolumns = {
			"Site", "ID", "Name", "Developer", "Played version", 
			"Last time play", "Rated", "Newest version", 
			"Last update", "People rating", "Player progress", 
			"Still on pc?", "Engine", "OS", "Language", 
			"Personal Notes"
		};
		Boolean otSe = false, foSe = false, laSe = false, shCo = false;
		Document dom = folderHandle.loadSaveGamesSettings.saveLoadDoc.loadDocument(mainInit.settingsPath);
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
							case "folderLocation": foSe = singleChecks(setting, folderLocation, dom, "folderLocation"); break;
							case "language": laSe = singleChecks(setting, language, dom, "language"); break;
							case "appVersion": shCo = singleChecks(setting, appVersion, dom, "appVersion"); break;
							case "showncolumns": shCo = checkings(setting, showncolumns, dom, "showncolumns"); break;
							default: JOptionPane.showMessageDialog(null, ("Should be impossible") + "checkMissingSetting", "Error", JOptionPane.ERROR_MESSAGE); break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, ("Error checking settings.") + "(checkMissingSetting.checkSettings)", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (otSe || foSe || laSe || shCo) {
			JOptionPane.showMessageDialog(null, "Settings got updated. \nPlease restart the program to make sure everything is correct.", "Settings updated", JOptionPane.INFORMATION_MESSAGE);
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
						Element newSetting = dom.createElement(settingName);
						if (somethingSettings[j] == "Dark mode") { newSetting.setAttribute("enabled", "true"); }
						else { newSetting.setAttribute("enabled", "false"); }
						newSetting.appendChild(dom.createTextNode(somethingSettings[j]));
						dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
						settingsGotUpdated = true;
					}
				} else { 
					if (k == setting.getLength() - 1) {
						Element newSetting = dom.createElement(settingName);
						if (somethingSettings[j] == "Dark mode") { newSetting.setAttribute("enabled", "true"); }
						else { newSetting.setAttribute("enabled", "false"); }
						newSetting.appendChild(dom.createTextNode(somethingSettings[j]));
						dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
						settingsGotUpdated = true;
					}
				}
			}
		}
		folderHandle.loadSaveGamesSettings.saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
		return settingsGotUpdated;
	}

	private static Boolean singleChecks(NodeList setting, String somethingSettings, Document dom, String settingName){
		Boolean settingsGotUpdated = false;
		// Check if the setting is missing
		if (setting.item(0) != null) {return false;}
		Element newSetting = dom.createElement(settingName);
		newSetting.appendChild(dom.createTextNode(somethingSettings));
		dom.getElementsByTagName("settings").item(0).appendChild(newSetting);
		settingsGotUpdated = true;
		folderHandle.loadSaveGamesSettings.saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
		return settingsGotUpdated;
	}
}
