package folderHandling.integCheck;

import javax.swing.JOptionPane;
import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import folderHandling.ADocHandle;

public class creatingDefaultDoc {
	/*
	 * Special char:
	 * & &amp;
	 * < &lt;
	 * > &gt;
	 * "" &quot;
	 * '' &apos;
	 * <name>John &amp; Doe</name>
	 */
	public static boolean creatingDocHandler(String directoryPath, String rootCreateElement, String[][] everythingNeeded) {
		try {
			Document doc = ADocHandle.create();
			Element rootElement = doc.createElement(rootCreateElement);

			switch (rootCreateElement) {
				// case "settings": rootElement = createSettings(doc, rootElement, everythingNeeded); break;
				case "source": rootElement = createDatabase(doc, rootElement, everythingNeeded); break;
				default: break;
			}

			doc.appendChild(rootElement);
			return ADocHandle.save(doc, directoryPath);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating "+directoryPath, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean createJsonSettings(String[][] allSettings, String directoryPath) {
		/*
		[ … ] represents an array, so library will parse it to JSONArray
		{ … } represents an object, so library will parse it to JSONObject

		String jsonString = ... ; //assign your JSON String here
		JSONObject obj = new JSONObject(jsonString);
		String pageName = obj.getJSONObject("pageInfo").getString("pageName");

		JSONArray arr = obj.getJSONArray("posts"); // notice that `"posts": [...]`
		for (int i = 0; i < arr.length(); i++) {
			String post_id = arr.getJSONObject(i).getString("post_id");
		}
		 */

		System.out.println("Creating settings.json");
		JSONObject obj = new JSONObject();
		for (int i = 0; i < allSettings.length; i++) {
			if (allSettings[i][0] == "othersettings") {
				JSONObject subObj = new JSONObject();
				for (int j = 1; j < allSettings[i].length; j++) {
					subObj.put(allSettings[i][j], false);
				}
				obj.put(allSettings[i][0], subObj);
			} else if (allSettings[i][0] == "shownColumns") {
				JSONObject subObj = new JSONObject();
				for (int j = 1; j < allSettings[i].length; j++) {
					subObj.put(allSettings[i][j], true);
				}
				obj.put(allSettings[i][0], subObj);
			} else {
				obj.put(allSettings[i][0], allSettings[i][1]);
			}
		}
		System.out.println(obj.toString());
		if (ADocHandle.saveJson(obj, directoryPath)) {
			System.out.println("File JSON win. YAY :3 ");
			return true;
		}
		return false;
	}

	// Create database.xml
	private static Element createDatabase(Document doc, Element rootElement, String[][] everythingNeeded) {
		Element newGame = doc.createElement(everythingNeeded[0][0]);
		newGame.setAttribute(everythingNeeded[0][1].split("/")[0].split("-")[0], everythingNeeded[0][1].split("/")[0].split("-")[1]);
		newGame.setAttribute(everythingNeeded[0][1].split("/")[1].split("-")[0], everythingNeeded[0][1].split("/")[1].split("-")[1]);

		for (int i = 1; i < everythingNeeded.length; i++) {
			Element newElement = doc.createElement(everythingNeeded[i][0]);
			newElement.appendChild(doc.createTextNode(everythingNeeded[i][1]));
			newGame.appendChild(newElement);
		}
		rootElement.appendChild(newGame);
		return rootElement;
	}
}
