package folderHandling;

import com.google.gson.JsonObject;

public class changeSettings {
	private static String mainDirectory;
	// TODO - This is temp till I make this other OS compatible or just make the main directory a public
	public static void retard(String lol) {
		mainDirectory = lol;
	}

	public static boolean changeSetting(String mainChange, String toChange) {
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
