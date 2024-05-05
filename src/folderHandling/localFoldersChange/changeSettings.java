package folderHandling.localFoldersChange;

import com.google.gson.JsonObject;

import folderHandling.ADocHandle;
import integrationCheck.defaultValues;

public class changeSettings {
	/**
	 * This function will change a setting in the settings.json file.
	 * @param mainChange - The main setting to change.
	 * @param toChange - The sub setting to change.
	 * @return boolean - returns true if the setting was changed successfully.
	 */
	public static boolean changeSetting(String mainChange, String toChange) {
		String mainDirectory = defaultValues.mainDirectory;
		JsonObject settings = ADocHandle.loadSettingsJson(mainDirectory + "/settings.json");
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

		return ADocHandle.saveSettingsJson(mainDirectory + "/settings.json", settings);
	}
}
