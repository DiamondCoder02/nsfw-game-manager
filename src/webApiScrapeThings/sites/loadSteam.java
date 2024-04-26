package webApiScrapeThings.sites;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import folderHandling.initialFileLoading.loadLanguage;
import webApiScrapeThings.loadSitesBufRead;

/*
00- Steam
https://store.steampowered.com/api/appdetails?appids=10
From this api I can get:
02- Name
03- Developers
01- SteamID
13- platforms OS
14- languages

- Is free? (if not, price)

https://partner.steamgames.com/doc/store/getreviews
From this:
09- Rated (likes and dislikes)


https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=10
From this:
- Latest update time? 1702399041 (unix timestamp) => Tue Dec 12 16:37:21 2023 UTC


https://api.steamcmd.net/v1/info/10
From this:
01 - data."10".appid

02 - data."10".common.name
03 - data."10".common.associations.0.name

07 - data."10".depots.branches.public.buildid (cannot find game version so fuck it, I will use this)
08 - data."10".depots.branches.public.timeupdated (UNIX timestamp)
09 - data."10".common.review_percentage (all reviews)

13 - data."10".common.oslist (format a bit)
14 - data."10".common.supported_languages 


Locally:
4 - Played version
5 - Last time play
11 - Still on pc?

???:
6 - Rated
10 - Player progress
12 - Engine	
15 - Personal notes



order of storage:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes


{
	"data": {
		"10": {
			"appid": "10",
			"common": {
				"associations": {
				"0": {
					"name": "Valve",
					"type": "developer"
				},
				"1": {"name": "Valve", "type": "publisher" }
				},
				"gameid": "10",
				"name": "Counter-Strike",
				"oslist": "windows,macos,linux",
				"review_percentage": "97",
				"review_score": "9",
				
				"supported_languages": {"english": {"full_audio": "true", "supported": "true" }, "french": {"full_audio": "true", "supported": "true" }, "german": {"full_audio": "true", "supported": "true" }, "italian": {"full_audio": "true", "supported": "true" }, "koreana": {"full_audio": "true", "supported": "true" }, "schinese": {"full_audio": "true", "supported": "true" }, "spanish": {"full_audio": "true", "supported": "true" }, "tchinese": {"full_audio": "true", "supported": "true" } },
				"type": "game"
			},
			"depots": {
				"branches": {
				"public": {
					"buildid": "12934623",
					"timeupdated": "1702399024"
				}
				},
			},
		}
	},
	"status": "success"
}

*/

public class loadSteam {
	// TODO - steam
	static String[] lf = loadLanguage.folder, bs = loadLanguage.base;
	public static String[] getSteamUrlContents(String gameIds) {
		StringBuilder content = loadSitesBufRead.loadSite("https://store.steampowered.com/api/appdetails?appids="+gameIds, false);
		if (content == null) { return null; }

		String[] allInfo = new String[8];

		JsonObject obj = JsonParser.parseString(content.toString()).getAsJsonObject();
		obj.entrySet().forEach(entry -> {
			if (entry.getKey().equals(gameIds)) {
				JsonObject data = entry.getValue().getAsJsonObject();
				if (data.get("success").getAsBoolean()) {
					JsonObject gameData = data.getAsJsonObject("data");
					gameData.entrySet().forEach(gameEntry -> {
						switch (gameEntry.getKey().toString()) {
							/*
							case "name":
								allInfo[0] = gameEntry.getValue().getAsString();
								break;
							case "developers":
								allInfo[1] = "";
								JsonArray developers = gameEntry.getValue().getAsJsonArray();
								developers.forEach(developer -> { allInfo[1] += developer.getAsString() + ", "; });
								allInfo[1] = allInfo[1].substring(0, allInfo[1].length() - 2);
								break;
							case "platforms":
								JsonObject platforms = gameEntry.getValue().getAsJsonObject();
								allInfo[13] = 
									(platforms.get("windows").getAsBoolean() ? "Windows" : "") + 
									(platforms.get("mac").getAsBoolean() ? "Mac" : "") + 
									(platforms.get("linux").getAsBoolean() ? "Linux" : "");
								break;
							case "supported_languages":
								allInfo[14] = gameEntry.getValue().getAsString();
								break;
							*/
							

							default:
								break;
						}



					});
				}
			}
		});


		for (int i = 0; i < allInfo.length; i++) {
			System.out.println(allInfo[i]);
		}

		return null;
	}
}
