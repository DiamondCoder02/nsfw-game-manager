package folderHandling.integCheck;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import folderHandling.ADocHandle;

public class creatingDefaultDoc {
	// Create settings.json
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
	// Create database.xml
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
