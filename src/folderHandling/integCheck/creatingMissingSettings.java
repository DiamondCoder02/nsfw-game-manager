package folderHandling.integCheck;

import com.google.gson.JsonObject;

import folderHandling.ADocHandle;

public class creatingMissingSettings {
	public static boolean creatingMissingSettingsHandler(String[][] allSettings, String directoryPath) {
		// TODO - check the settings file and update if some values are missing
		try {
			JsonObject settings = ADocHandle.loadSettingsJson(directoryPath);
			JsonObject newSettings = new JsonObject();

			for (int i = 0; i < allSettings.length; i++) {
				if (allSettings[i][0] == "othersettings" || allSettings[i][0] == "shownColumns") {
					JsonObject subSettings = new JsonObject();
					for (int j = 1; j < allSettings[i].length; j++) {
						if (!settings.has(allSettings[i][j])) {
							subSettings.addProperty(allSettings[i][j], (allSettings[i][0] == "shownColumns" ? true : false));
						} else {
							subSettings.addProperty(allSettings[i][j], settings.get(allSettings[i][j]).getAsBoolean());
						}
					}
					newSettings.add(allSettings[i][0], subSettings);
				} else {
					if (!settings.has(allSettings[i][0])) {
						newSettings.addProperty(allSettings[i][0], allSettings[i][1]);
					} else {
						newSettings.addProperty(allSettings[i][0], settings.get(allSettings[i][0]).getAsString());
					}
				}
			}

			Boolean success = ADocHandle.saveSettingsJson(directoryPath, newSettings);
			System.out.println("Settings updated: " + success);

			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
