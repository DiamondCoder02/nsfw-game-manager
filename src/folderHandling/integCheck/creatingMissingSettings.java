package folderHandling.integCheck;

import com.google.gson.JsonObject;

import folderHandling.ADocHandle;

public class creatingMissingSettings {
	// TODO - check the settings file and update if some values are missing
	public static boolean creatingMissingSettingsHandler(String[][] allSettings, String directoryPath) {
		JsonObject settings = ADocHandle.loadSettingsJson(directoryPath + "/settings.json");
		String[][] toReduce = new String[5][20];
		// This is retarded, but fuck me >:3
		for (int i = 0; i < allSettings.length; i++) { for (int j = 0; j < allSettings[i].length; j++) { toReduce[i][j] = allSettings[i][j]; } }

		settings.entrySet().forEach(entry -> {
			if (entry.getValue().isJsonObject()) {
				entry.getValue().getAsJsonObject().entrySet().forEach(subEntry -> {
					for (int i = 0; i < toReduce.length; i++) {
						if (toReduce[i] == null) { continue; }
						if (toReduce[i][0].equals(entry.getKey())) {
							for (int j = 1; j < toReduce[i].length; j++) {
								if (toReduce[i][j] == null) { continue; }
								if (toReduce[i][j].equals(subEntry.getKey())) {
									toReduce[i][j] = null;
									return;
								}
							}
						}
					}
				});
			} else {
				for (int i = 0; i < toReduce.length; i++) {
					if (toReduce[i] == null) { continue; }
					if (toReduce[i][0].equals(entry.getKey())) {
						toReduce[i] = null;
						return;
					}
				}
			}
		});

		System.out.println("------------");
		System.out.println("/////////////");
		System.out.println("------------");

		for (int i = 0; i < allSettings.length; i++) {
			if (toReduce[i] == null) { continue; }
			if (allSettings[i].length == 2) {
				settings.addProperty(toReduce[i][0], toReduce[i][1]);
			} else {
				for (int j = 1; j < allSettings[i].length; j++) {
					System.out.println("+++");
					if (toReduce[i][j] == null) { continue; }
					System.out.println(allSettings[i][j] + " - " + toReduce[i][j]);
					System.out.println("*****");
					
					if (!settings.has(allSettings[i][0])) {
						settings.add(allSettings[i][0], new JsonObject());
					}
					settings.getAsJsonObject().get(allSettings[i][0]).getAsJsonObject().addProperty(toReduce[i][j], (allSettings[i][0] == "shownColumns" ? true : false));
					
				}
			}
		}












		
		try {
			Boolean success = ADocHandle.saveSettingsJson(directoryPath + "/settings.json", settings);
			System.out.println("Settings updated: " + success);


			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
