package integrationCheck;

public interface defaultValues {
	// TODO - This is probably the only reason it will not work on Linux (NEED TEST)
	String mainDirectory = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager";
	String steamDirectory = System.getenv("ProgramFiles(x86)") + "/Steam/steamapps";


	String appGithubLink = "https://github.com/DiamondCoder02/nsfw-game-manager/releases/latest";

	String[] filesNeeded = {"settings.json", "hentai.xml"};

	String[][] onlineFilesNeeded = {
		{"language.csv", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/doNotTouch/language.csv"},
		{"pics/creditLogo.png", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/creditLogo.png"},
		{"pics/nyaaa.png", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/nyaaa.png"},
		{"discord/discord_game_sdk.dll", "https://dl-game-sdk.discordapp.net/3.2.1/discord_game_sdk.zip"}
	};

	String[][] settings = {
		{ "appVersion", "0.1.1.4"},
		{ "appLanguage", "english" },
		{ "folderLocation", "null" },
		{ "othersettings", "autoUpdateManager", "darkMode", "autoFetchNewGameInfos", "autoFetchLocalGameFolder", 
			"DiscordRPC", "showConsole"},
		{ "shownColumns", "site", "id", "name", "developer", "playedVersion", "lastTimePlayed", "rated", 
			"newestVersionOnline", "lastDateTimeUpdated", "peopleOnlineRating", "localPlayerProgress", 
			"gameStillOnPc", "gameEngine", "os", "language", "localPersonalNotes"}
	};

	String[][] games = {
		{"game", "from-f95/id-19095"},
		{"name", "2037 - Almost ready, Inc."},
		{"developer", "MadAlice"},
		{"played_version", "0.9.6"},
		{"dateof_lastplay", "2020-06-22"},
		{"user_rating", ""},
		{"newest_version", "✖ v0.9.6"},
		{"dateof_lastupate", "N/A"},
		{"people_rating", "N/A"},
		{"howFarUserPlayed", "Finish"},
		{"stillOnPc", "no"},
		{"engine", "HTML"},
		{"OS", "Windows"},
		{"language", "English"},
		{"selfNote", "something"}
	};

	String[] infoSite = {"man", "f95"};
	String[] infoProgress = {"Not played", "In progress", "Finish", "100% Finished", "Dropped"};
	String[] infoOnPc = {"yes", "no", "unknown"};
	String[] infoEngine = {"Flash", "HTML", "Java", "QSP", "Ren'Py", "RPGM", "Unity", "Unreal Engine", "WinGit", "WolfRPG", "other/unknown"};
	String[] infoOS = {"Windows", "Linux", "Mac", "Android", "IOS", "other"};
}
