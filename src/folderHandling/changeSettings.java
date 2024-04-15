package folderHandling;

import com.google.gson.JsonObject;

import integrationCheck.defaultValues;

public class changeSettings {
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
