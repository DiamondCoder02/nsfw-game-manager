package folderHandling.initialFileLoading;

import com.google.gson.JsonObject;

import folderHandling.ADocHandle;

public class loadSettings {
	public static Boolean[] othersettings;
	public static Boolean[] shownColumns;
	public static String language;
	public static String folderLocation;
	public static String appVersion;
	public static Boolean load(String directory) {
		JsonObject parser;
		try{ parser = ADocHandle.loadSettingsJson(directory + "/settings.json"); } 
		catch (Exception e) { return false; }

		JsonObject otherSet = parser.get("othersettings").getAsJsonObject();
		JsonObject shownSet = parser.get("shownColumns").getAsJsonObject();
		othersettings = new Boolean[otherSet.size()];
		shownColumns = new Boolean[shownSet.size()];

		// TODO - Optimize this
		othersettings[0] = otherSet.get("autoUpdateManager").getAsBoolean();
		othersettings[1] = otherSet.get("darkMode").getAsBoolean();
		othersettings[2] = otherSet.get("autoFetchNewGameInfos").getAsBoolean();
		othersettings[3] = otherSet.get("autoFetchLocalGameFolder").getAsBoolean();
		othersettings[4] = otherSet.get("DiscordRPC").getAsBoolean();


		language = parser.get("appLanguage").getAsString();
		folderLocation = parser.get("folderLocation").getAsString();
		appVersion = parser.get("appVersion").getAsString();


		// TODO - Optimize this too
		shownColumns[0] = shownSet.get("site").getAsBoolean();
		shownColumns[1] = shownSet.get("id").getAsBoolean();
		shownColumns[2] = shownSet.get("name").getAsBoolean();
		shownColumns[3] = shownSet.get("developer").getAsBoolean();
		shownColumns[4] = shownSet.get("playedVersion").getAsBoolean();
		shownColumns[5] = shownSet.get("lastTimePlayed").getAsBoolean();
		shownColumns[6] = shownSet.get("rated").getAsBoolean();
		shownColumns[7] = shownSet.get("newestVersionOnline").getAsBoolean();
		shownColumns[8] = shownSet.get("lastDateTimeUpdated").getAsBoolean();
		shownColumns[9] = shownSet.get("peopleOnlineRating").getAsBoolean();
		shownColumns[10] = shownSet.get("localPlayerProgress").getAsBoolean();
		shownColumns[11] = shownSet.get("gameStillOnPc").getAsBoolean();
		shownColumns[12] = shownSet.get("gameEngine").getAsBoolean();
		shownColumns[13] = shownSet.get("os").getAsBoolean();
		shownColumns[14] = shownSet.get("language").getAsBoolean();
		shownColumns[15] = shownSet.get("localPersonalNotes").getAsBoolean();

		return true;
	}
}
