package main;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;

public class checkMissingSetting {
	public static void checkSettings() {
		// TODO check settings and update if missing
		String[] settings = {"othersettings", "language", "showncolumns"};
		String[] othersettings = {"Dark mode", "Auto fetch game info"};
		String[] language = {"english"};
		String[] showncolumns = {
			"Site", "ID", "Name", "Developer", "Played version", 
			"Last time play", "Rated", "Newest version", 
			"Last update", "People rating", "Player progress", 
			"Still on pc?", "Engine", "OS", "Personal Notes"
		};
		try{
			Boolean otSe, laSe, shCo;
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
							System.out.println("Checking " + settings[i] + "...");
							switch (settings[i]) {
								case "othersettings": otSe = checkings(setting, othersettings); break;
								case "language": laSe = checkings(setting, language); break;
								case "showncolumns": shCo = checkings(setting, showncolumns); break;
								default: JOptionPane.showMessageDialog(null, "Should be impossible checkMissingSetting", "Error", JOptionPane.ERROR_MESSAGE); break;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error checking settings. (checkMissingSetting.checkSettings_2)", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			// TODO show update dialog
			System.out.println("Done checking settings.");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error checking settings. (checkMissingSetting.checkSettings)", "Error", JOptionPane.ERROR_MESSAGE);
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

	private static Boolean checkings(NodeList setting, String[] somethingSettings){
		Boolean settingsGotUpdated = false;
		for (int j = 0; j < somethingSettings.length; j++) {
			String[] toCheckWith = new String[somethingSettings.length];
			for (int k = 0; k < setting.getLength(); k++) { toCheckWith[k] = setting.item(k).getTextContent(); }
			for (int k = 0; k < toCheckWith.length; k++) { if (toCheckWith[k] == null) { toCheckWith[k] = "-"; } }
			System.out.println(toCheckWith[j] + " -> "+somethingSettings[j]);
			if (toCheckWith[j].contains(somethingSettings[j])) {
				System.out.println("! " + somethingSettings[j]);
			} else {
				System.out.println("? " + somethingSettings[j]);
			}
		}
		return settingsGotUpdated;
	}
}
