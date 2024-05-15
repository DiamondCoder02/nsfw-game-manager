package integrationCheck;

/**
 * This interface contains the default values for the program.
 */
public interface defaultValues {
	/**
	 * The link to the github page of the app
	 */
	String appGithubLink = "https://github.com/DiamondCoder02/nsfw-game-manager/releases/latest";

	/**
	 * setting and hentai files needed in the main directory
	 * @see #mainDirectory
	 */
	String[] filesNeeded = {"settings.json", "hentai.xml", "language.csv"};

	/**
	 * online files needed to be downloaded
	 */
	String[][] onlineFilesNeeded = {
		{"discord/discord_game_sdk.dll", "https://dl-game-sdk.discordapp.net/3.2.1/discord_game_sdk.zip"}
	};

	/**
	 * The default settings for the program
	 */
	String[][] settings = {
		{ "othersettings", "autoUpdateManager", "darkMode", "autoFetchNewGameInfos", "autoFetchLocalGameFolder", 
			"DiscordRPC", "showConsole", "firstTimeRunDone"},
		{ "shownColumns", "site", "id", "name", "developer", "playedVersion", "lastTimePlayed", "rated", 
			"newestVersionOnline", "lastDateTimeUpdated", "peopleOnlineRating", "localPlayerProgress", 
			"gameStillOnPc", "gameEngine", "os", "language", "localPersonalNotes"},
		{ "appVersion", "0.1.1.4"},
		// { "languageVersion", "0.1"},
		{ "appLanguage", "english" },
		{ "folderLocation", "null" },
		{ "databaseNumber", "0"},
		{ "databaseNames", "main//sfw//?//?//?"}
	};

	/**
	 * The possible values for the game information
	 */
	String[] gameInfos = {"from", "id", "name", "developer", "played_version", "dateof_lastplay", "user_rating", "newest_version", 
		"dateof_lastupate", "people_rating", "howFarUserPlayed", "stillOnPc", "engine", "OS", "language", "selfNote"};

	String[] infoSites2LOL = {"Manually added games", "F95zone", "Steam"};
	/**
	 * 	The possible values for some of the game information
	 */
	String[] infoSite = {"man", "f95", "steam"};
	String[] infoProgress = {"Not played", "In progress", "Finish", "100% Finished", "Dropped"};
	String[] infoOnPc = {"yes", "no", "unknown"};
	String[] infoEngine = {"Flash", "HTML", "Java", "QSP", "Ren'Py", "RPGM", "Unity", "Unreal Engine", "WinGit", "WolfRPG", "other/unknown"};
	String[] infoOS = {"Windows", "Linux", "Mac", "Android", "IOS", "other"};
}
