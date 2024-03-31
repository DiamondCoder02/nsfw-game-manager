package integrationCheck;

public interface defaultValues {
	String[] filesNeeded = {"settings.xml", "hentai.xml"};
	
	String[][] onlineFilesNeeded = {
		{"language.csv", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/language.csv"},
		{"pics/creditLogo.png", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/creditLogo.png"},
		{"pics/nyaaa.png", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/nyaaa.png"},
		{"discord/discord_game_sdk.dll", "https://dl-game-sdk.discordapp.net/3.2.1/discord_game_sdk.zip"}
	};

	String[][] settings = {
		{"othersettings", "enabled-true", "Dark mode"},
		{"othersettings", "enabled-false", "Auto fetch game info"},
		{"othersettings", "enabled-false", "Auto fetch folders"},
		{"othersettings", "enabled-false", "DiscordRPC"},
		{"language", "english"},
		{"folderLocation", "null"},
		{"appVersion", "enabled-true", "0.1.1.4"},
		{"showncolumns", "enabled-true", "Site"},
		{"showncolumns", "enabled-true", "ID"},
		{"showncolumns", "enabled-true", "Name"},
		{"showncolumns", "enabled-true", "Developer"},
		{"showncolumns", "enabled-true", "Played version"},
		{"showncolumns", "enabled-true", "Last time play"},
		{"showncolumns", "enabled-true", "Rated"},
		{"showncolumns", "enabled-true", "Newest version"},
		{"showncolumns", "enabled-true", "Last update"},
		{"showncolumns", "enabled-true", "People rating"},
		{"showncolumns", "enabled-true", "Player progress"},
		{"showncolumns", "enabled-true", "Still on pc?"},
		{"showncolumns", "enabled-true", "Engine"},
		{"showncolumns", "enabled-true", "OS"},
		{"showncolumns", "enabled-true", "Language"},
		{"showncolumns", "enabled-true", "Personal Notes"}
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
		{"selfNote", "something"},
		{"language", "English"}
	};
}
