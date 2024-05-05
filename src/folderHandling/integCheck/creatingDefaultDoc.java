package folderHandling.integCheck;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import folderHandling.ADocHandle;

public class creatingDefaultDoc {
	/**
	 * Create settings.json
	 * @param allSettings - The settings that will be saved in the settings.json file
	 * @param directoryPath - The directory where the settings file will be saved **with** the file name or extension
	 * @return boolean - returns true if the settings file was created successfully
	 */
	public static boolean createJsonSettings(String[][] allSettings, String directoryPath) {
		System.out.println("Creating settings.json");
		Map<String, Object> someSettings = new HashMap<>();

		for (int i = 0; i < allSettings.length; i++) {
			if (allSettings[i].length == 2) {
				someSettings.put(allSettings[i][0], allSettings[i][1]);
			} else {
				Map<String, Boolean> subSettings = new HashMap<>();
				for (int j = 1; j < allSettings[i].length; j++) {
					subSettings.put(allSettings[i][j], (allSettings[i][0] == "shownColumns" ? true : false));
				}
				someSettings.put(allSettings[i][0], subSettings);
			}
		}
		return ADocHandle.saveSettingsJson(directoryPath, someSettings);
	}

	/*
	 * Special char:
	 * & &amp;
	 * < &lt;
	 * > &gt;
	 * "" &quot;
	 * '' &apos;
	 * <name>John &amp; Doe</name>
	*/
	/**
	 * Create a new database
	 * @param directoryPath - The directory where the XML file will be saved **with** the file name or extension
	 * @param rootCreateElement - The root element of the XML file
	 * @param everythingNeeded - The elements that will be saved in the XML file
	 * @return boolean - returns true if the XML file was created successfully
	 */
	public static boolean createDatabase(String directoryPath, String rootCreateElement, String[][] everythingNeeded) {
		Document doc = ADocHandle.create();
		Element rootElement = doc.createElement(rootCreateElement);
		Element newGame = doc.createElement(everythingNeeded[0][0]);
		newGame.setAttribute(everythingNeeded[0][1].split("/")[0].split("-")[0], everythingNeeded[0][1].split("/")[0].split("-")[1]);
		newGame.setAttribute(everythingNeeded[0][1].split("/")[1].split("-")[0], everythingNeeded[0][1].split("/")[1].split("-")[1]);

		for (int i = 1; i < everythingNeeded.length; i++) {
			Element newElement = doc.createElement(everythingNeeded[i][0]);
			newElement.appendChild(doc.createTextNode(everythingNeeded[i][1]));
			newGame.appendChild(newElement);
		}
		rootElement.appendChild(newGame);
		doc.appendChild(rootElement);
		return ADocHandle.save(doc, directoryPath);
	}
}
