package webApiScrapeThings.sites;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import folderHandling.initialFileLoading.loadLanguage;
import webApiScrapeThings.loadSitesBufRead;

public class loadSteam {
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
								/*
								01 - appid

								02 - common.name
								03 - common.associations.0.name (Must be and loop check every developer) 
								Can't get from extended.developer
								Example: https://api.steamcmd.net/v1/info/2707980

								07 - depots.branches.public.buildid (cannot find game version so fuck it, I will use this)
								08 - depots.branches.public.timeupdated (UNIX timestamp)
								Note: If game is not out these 2 is not available. 
								There is "releasestate": "prerelease" so it can be checked, 
								but easier to assume it's not out

								09 - common.review_percentage (all reviews)

								13 - common.oslist (format a bit)
								14 - common.supported_languages 
								*/
								case "appid": allGameInfos[0] = allGameInfo.getValue().getAsString(); break;
								case "common": 
									String[] temp = commonHandle(allGameInfo.getValue().getAsJsonObject());
									allGameInfos[1] = temp[0];
									allGameInfos[2] = temp[1];

									allGameInfos[5] = temp[2];
									allGameInfos[6] = temp[3];
									allGameInfos[7] = temp[4];
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
		});
		// 0 - appid		1 - name		2 - developer	3 - buildid	4 - timeupdated
		// 5 - review_percentage		6 - oslist		7 - supported_languages
		return allGameInfos;
	}

	private static String[] commonHandle(JsonObject toGetInfoFrom){
		String[] temp = new String[5];
		toGetInfoFrom.entrySet().forEach(entry1 -> {
			switch (entry1.getKey()) {
				case "name": temp[0] = entry1.getValue().getAsString(); break;
				case "associations": temp[1] = developerCommonHandle(entry1.getValue().getAsJsonObject()); break;
				case "review_percentage": temp[2] = entry1.getValue().getAsString() + "%"; break;
				case "oslist": temp[3] = entry1.getValue().getAsString(); break;
				case "supported_languages": temp[4] = supLanguagesCommonHandle(entry1.getValue().getAsJsonObject()); break;
			}
		});

		return temp;
	}

	private static String developerCommonHandle(JsonObject toGetInfoFrom){
		String[] temp = new String[1];
		toGetInfoFrom.entrySet().forEach(entry1 -> {
			JsonObject obj1 = entry1.getValue().getAsJsonObject();
			if (obj1.get("type").getAsString().equals("developer")) {
				temp[0] += obj1.get("name").getAsString()+", ";
			}
			
		});
		if (temp[0].length() > 2) { 
			temp[0] = temp[0].substring(4);
			temp[0] = temp[0].substring(0, temp[0].length()-2); 
		}
		return temp[0];
	}

	private static String supLanguagesCommonHandle(JsonObject toGetInfoFrom){
		String[] temp = new String[1];
		toGetInfoFrom.entrySet().forEach(entry1 -> {
			if (entry1.getValue().getAsJsonObject().get("supported").getAsString().equals("true")) {
				temp[0] += entry1.getKey()+", ";
			}
		});
		if (temp[0].length() > 2) { 
			temp[0] = temp[0].substring(4);
			temp[0] = temp[0].substring(0, temp[0].length()-2); 
		}
		return temp[0];
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
									// convert unix to date
									Date date = new Date(Integer.parseInt(entry3.getValue().getAsString())*1000L);
									SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
									String myDate = format.format(date);
									temp[1] = myDate;
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