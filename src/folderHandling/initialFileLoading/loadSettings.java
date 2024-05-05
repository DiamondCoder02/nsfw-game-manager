package folderHandling.initialFileLoading;

import com.google.gson.JsonObject;

import folderHandling.ADocHandle;
import integrationCheck.defaultValues;

public class loadSettings {
	public static Boolean[] othersettings;
	public static Boolean[] shownColumns;
	public static String language;
	public static String folderLocation;
	public static String appVersion;
	/**
	 * @param directory - The directory where the settings file is located without the file name or extension
	 * @return boolean - returns true if the settings file was loaded successfully
	 */
	public static Boolean load(String directory) {
		JsonObject parser;
		try{ parser = ADocHandle.loadSettingsJson(directory + "/settings.json"); } 
		catch (Exception e) { return false; }

		try {
			JsonObject otherSet = parser.get("othersettings").getAsJsonObject();
			JsonObject shownSet = parser.get("shownColumns").getAsJsonObject();
			othersettings = new Boolean[otherSet.size()];
			shownColumns = new Boolean[shownSet.size()];

			for (int i = 0; i < defaultValues.settings[3].length-1; i++) {
				othersettings[i] = otherSet.get(defaultValues.settings[3][i+1]).getAsBoolean();
			}

			language = parser.get("appLanguage").getAsString();
			folderLocation = parser.get("folderLocation").getAsString();
			appVersion = parser.get("appVersion").getAsString();

			for (int i = 0; i < defaultValues.settings[4].length-1; i++) {
				shownColumns[i] = shownSet.get(defaultValues.settings[4][i+1]).getAsBoolean();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
