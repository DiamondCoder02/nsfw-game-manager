package webApiScrapeThings.sites;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import folderHandling.initialFileLoading.loadLanguage;
import webApiScrapeThings.loadSitesBufRead;

public class loadSteam {
	// TODO - steam
	static String[] lf = loadLanguage.folder, bs = loadLanguage.base;
	public static String[] getSteamUrlContents(String gameIds) {
		StringBuilder content = loadSitesBufRead.loadSite("https://api.steamcmd.net/v1/info/"+gameIds, false);
		if (content == null) { return null; }

		String[] allGameInfos = new String[8];

		JsonObject obj = JsonParser.parseString(content.toString()).getAsJsonObject();
		obj.entrySet().forEach(entry -> {
			if (entry.getKey().equals("data")) {
				JsonObject data = entry.getValue().getAsJsonObject();
				data.entrySet().forEach(allData -> {
					if (allData.getKey().equals(gameIds)) {
						JsonObject gameInfo = allData.getValue().getAsJsonObject();
						if (gameInfo.size() < 1) {return;}
						gameInfo.entrySet().forEach(allGameInfo -> {
							switch (allGameInfo.getKey()) {
								case "appid": allGameInfos[0] = allGameInfo.getValue().getAsString(); break;
								case "common": 
									String[] temp = commonHandle(allGameInfo.getValue().getAsJsonObject());
									/*
									allGameInfos[1] = temp[0];
									allGameInfos[2] = temp[1];

									allGameInfos[5] = temp[2];
									allGameInfos[6] = temp[3];
									allGameInfos[7] = temp[4];
									*/
									break;
								case "depots":
									String[] temp2 = depotsBranchesPublicHandle(allGameInfo.getValue().getAsJsonObject());
									allGameInfos[3] = temp2[0];
									allGameInfos[4] = temp2[1];
									break;
							}
						});
					}
				});
			}
			/*
			01 - appid

			02 - common.name
			03 - common.associations.0.name (Must be and loop check every developer) 
			Can't get from extended.developer
			Example: https://api.steamcmd.net/v1/info/2707980

			07 - depots.branches.public.buildid (cannot find game version so fuck it, I will use this)
			08 - depots.branches.public.timeupdated (UNIX timestamp)
			Note: If game is not out these 2 is not available. 
			There is "releasestate": "prerelease" so it can be checked, but easier to assume it's not out

			09 - common.review_percentage (all reviews)

			13 - common.oslist (format a bit)
			14 - common.supported_languages 
			*/
		});


		for (int i = 0; i < allGameInfos.length; i++) {
			System.out.println(allGameInfos[i]);
		}

		return null;
	}

	private static String[] commonHandle(JsonObject toGetInfoFrom){
		String[] temp = new String[5];
		toGetInfoFrom.entrySet().forEach(entry1 -> {
			switch (entry1.getKey()) {
				case "name":
					break;
				case "associations":
					break;
				case "review_percentage":
					break;
				case "oslist":
					break;
				case "supported_languages":
					break;
			}
		});

		return null;
	}

	private static String[] depotsBranchesPublicHandle(JsonObject toGetInfoFrom){
		String[] temp = new String[2];
		toGetInfoFrom.entrySet().forEach(entry1 -> {
			if (entry1.getKey().contains("branches")){
				JsonObject obj1 = entry1.getValue().getAsJsonObject();
				obj1.entrySet().forEach(entry2 -> {
					if (entry2.getKey().contains("public")){
						JsonObject obj2 = entry2.getValue().getAsJsonObject();
						obj2.entrySet().forEach(entry3 -> {
							switch (entry3.getKey()) {
								case "buildid":
									temp[0] = entry3.getValue().getAsString();
									break;
								case "timeupdated":
									temp[1] = entry3.getValue().getAsString();
									break;
							
								default:
									break;
							}
						});
					}
				});
			}
		});
		return temp;
	}
}

/*
{
	"data": {
		"365670": {
			"appid": "365670",
			"common": {
				"associations": {
					"0": {
						"name": "Blender Foundation",
						"type": "developer"
					},
					"1": {
						"name": "Blender Foundation",
						"type": "publisher"
					}
				},
				"name": "Blender",
				"oslist": "windows,macos,linux",
				"review_percentage": "96",
				"review_score": "9"
			},
			"supported_languages": {
				"english": {
					"supported": "true"
				},
				"french": {
					"supported": "true"
				},
				"german": {
					"supported": "true"
				}
			},
			"type": "Application"
		},
		"depots": {
			"branches": {
				"public": {
					"buildid": "14066810",
					"timeupdated": "1713275230"
				}
			}
		},
		"extended": {
			"developer": "Blender Foundation",
			"disableoverlay": "1",
			"gamedir": "",
			"homepage": "https://www.blender.org",
			"isfreeapp": "1",
			"publisher": "Blender Foundation"
		}
	}
}
*/