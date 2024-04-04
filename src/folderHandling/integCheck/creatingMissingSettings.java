package folderHandling.integCheck;

import org.json.JSONObject;

import folderHandling.ADocHandle;

public class creatingMissingSettings {
	public static boolean creatingMissingSettingsHandler(String[][] allSettings, String directoryPath) {
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
		// TODO - check the settings file and update if some values are missing
		try {
			JSONObject test = ADocHandle.loadJson(directoryPath);
			System.out.println(test.toString());
			// TODO - Pleae kill me
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
