package folderHandling.localFoldersChange;

import com.google.gson.JsonObject;

import folderHandling.ADocHandle;

public class updateSettings {
	/**
	 * This function will change a setting in the settings.json file.
	 * @param mainChange - The main setting to change.
	 * @param toChange - The sub setting to change.
	 * @return boolean - returns true if the setting was changed successfully.
	 */
	public static boolean changeSetting(String mainDir,String mainChange, String toChange) {
		JsonObject settings = ADocHandle.loadSettingsJson(mainDir + "/settings.json");
		settings.entrySet().forEach(entry -> {
			if (entry.getKey().startsWith(mainChange)) {
				if (entry.getValue().isJsonObject()) {
					entry.getValue().getAsJsonObject().entrySet().forEach(subEntry -> {
						if (subEntry.getKey().equals(toChange)) {
							entry.getValue().getAsJsonObject().addProperty(toChange, (subEntry.getValue().getAsBoolean() ? false : true));
						}
					});
				} else {
					settings.addProperty(mainChange, toChange);
				}
			}
		});

		return ADocHandle.saveSettingsJson(mainDir + "/settings.json", settings);
	}

	/**
	 * This function will set a setting in the settings.json file.
	 * @param mainChange - The main setting to change.
	 * @param toChange - The sub setting to change.
	 * @param value - The value to set the setting to.
	 * @return boolean - returns true if the setting was set successfully.
	 */
	public static boolean setSetting(String mainDir, String mainChange, String toChange, boolean value) {
		JsonObject settings = ADocHandle.loadSettingsJson(mainDir + "/settings.json");
		settings.entrySet().forEach(entry -> {
			if (entry.getKey().startsWith(mainChange)) {
				if (entry.getValue().isJsonObject()) {
					entry.getValue().getAsJsonObject().entrySet().forEach(subEntry -> {
						if (subEntry.getKey().equals(toChange)) {
							entry.getValue().getAsJsonObject().addProperty(toChange, value);
						}
					});
				} else {
					settings.addProperty(mainChange, toChange);
				}
			}
		});

		return ADocHandle.saveSettingsJson(mainDir + "/settings.json", settings);
	}
}
