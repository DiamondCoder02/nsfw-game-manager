package folderHandling.integCheck;

import com.google.gson.JsonObject;

import folderHandling.ADocHandle;

public class creatingMissingSettings {
	public static boolean creatingMissingSettingsHandler(String[][] allSettings, String directoryPath) {
		JsonObject settings = ADocHandle.loadSettingsJson(directoryPath + "/settings.json");
		// TODO - check the settings file and update if some values are missing
		try {
			String[][] toReduce = allSettings;
			JsonObject toTestWith = settings;

			toTestWith.entrySet().forEach(entry -> {
				if (entry.getValue().isJsonObject()) {
					entry.getValue().getAsJsonObject().entrySet().forEach(subEntry -> {
						// System.out.println(subEntry.getKey() + " - " + subEntry.getValue());
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
			/* 
			System.out.println("------------");
			System.out.println("------------");
			System.out.println("------------");
			System.out.println(toReduce[0]);
			System.out.println(toReduce[1]);
			System.out.println(toReduce[2]);
			System.out.println(toReduce[3][0]);
			System.out.println(toReduce[3][1]);
			System.out.println(toReduce[3][2]);
			System.out.println(toReduce[3][3]);
			System.out.println(toReduce[3][4]);
			System.out.println(toReduce[4][0]);
			System.out.println(toReduce[4][1]);
			System.out.println(toReduce[4][2]);
			System.out.println(toReduce[4][3]);
			System.out.println(toReduce[4][4]);
			System.out.println("------------");
			*/
			System.out.println(settings.size());
			System.out.println(allSettings.length);
			System.out.println("------------");

			for (int i = 0; i < toReduce.length; i++) {
				if (toReduce[i] == null) { continue; }
				if (toReduce[i][0] == "othersettings" || toReduce[i][0] == "shownColumns") {
					JsonObject subSettings = new JsonObject();
					for (int j = 1; j < allSettings[i].length; j++) {
						if (toReduce[i][j] == null) { continue; }
						System.out.println(allSettings[i][j] + " - " + toReduce[i][j] +" - " + settings.has(allSettings[i][j]));
						
						
						
						if (!settings.has(toReduce[i][j])) {
							subSettings.addProperty(toReduce[i][j], (toReduce[i][0] == "shownColumns" ? true : false));
						} else {
							subSettings.addProperty(toReduce[i][j], settings.get(toReduce[i][j]).getAsBoolean());
						}
					}
					settings.add(toReduce[i][0], subSettings);
				} else {
					if (!settings.has(toReduce[i][0])) {
						settings.addProperty(toReduce[i][0], toReduce[i][1]);
					} else {
						settings.addProperty(toReduce[i][0], settings.get(toReduce[i][0]).getAsString());
					}
				}
			}

			Boolean success = ADocHandle.saveSettingsJson(directoryPath + "/settings.json", settings);
			System.out.println("Settings updated: " + success);


			return false;



			/*JsonObject newSettings = new JsonObject();
			for (int i = 0; i < allSettings.length; i++) {
				if (allSettings[i][0] == "othersettings" || allSettings[i][0] == "shownColumns") {
					JsonObject subSettings = new JsonObject();
					for (int j = 1; j < allSettings[i].length; j++) {
						// System.out.println(allSettings[i][j]);
						// System.out.println(settings.has(allSettings[i][j]));
						// System.out.println(settings.get(allSettings[i][j]));
						
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

			return success;*/
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
