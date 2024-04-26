package webApiScrapeThings.sites;

import folderHandling.initialFileLoading.loadLanguage;
import webApiScrapeThings.loadSitesBufRead;

/*
https://store.steampowered.com/api/appdetails?appids=10
From this api I can get:
- If game is available
- Name
- Developers
- SteamID
- platforms OS
- languages


- Is free? (if not, price)
- reccomendations ??? 

https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=10
From this:
- Latest update time? 1702399041 (unix timestamp) => Tue Dec 12 16:37:21 2023 UTC
*/

public class loadSteam {
	// TODO - steam
	static String[] lf = loadLanguage.folder, bs = loadLanguage.base;
	public static String[] getSteamUrlContents(Integer gameIds) {
		// https://store.steampowered.com/api/appdetails?appids=620980
		StringBuilder content = loadSitesBufRead.loadSite("https://store.steampowered.com/api/appdetails?appids="+gameIds, false);
		if (content == null) { return null; }

		// Name, Free/Paid, Developer, Platforms, Language, Recommendations
		String[] allTheInfo = new String[6];
		for (int i = 0; i < allTheInfo.length; i++) { allTheInfo[i] = ""; }

		/*
		JSONObject obj = new JSONObject(content.toString());
		// System.out.println(obj);
		Boolean succ = obj.getJSONObject(gameIds.toString()).getBoolean("success");
		if (!succ) {
			JOptionPane.showMessageDialog(null,  "("+gameIds+")" + "Error as the game with this ID was not found", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		JSONObject gameData = obj.getJSONObject(gameIds.toString()).getJSONObject("data");
		allTheInfo[0] = gameData.getString("name");
		allTheInfo[1] = gameData.getBoolean("is_free") ? "Free" : "Paid";
		// loop and combine all the developers into one string separated by commas
		for (int i = 0; i < gameData.getJSONArray("developers").length(); i++) {
			allTheInfo[2] += gameData.getJSONArray("developers").getString(i) + ", ";
		}
		allTheInfo[2] = allTheInfo[2].substring(0, allTheInfo[2].length() - 2);
		// loop and only return the true platforms
		for (String key : gameData.getJSONObject("platforms").keySet()) {
			if (gameData.getJSONObject("platforms").getBoolean(key)) {
				allTheInfo[3] += key + ", ";
			}
		}
		allTheInfo[3] = allTheInfo[3].substring(0, allTheInfo[3].length() - 2);
		// Remove <strong> tags
		allTheInfo[4] = gameData.getString("supported_languages")
			.replaceAll("<strong>", "")
			.replaceAll("</strong>", "")
			.replaceAll("\\*", "");
		allTheInfo[4] = allTheInfo[4].split("<br>")[0];

		if (gameData.getJSONObject("release_date").getBoolean("coming_soon")) {
			allTheInfo[5] = "Unavailable til release";
		} else {
			Integer recomend = gameData.getJSONObject("recommendations").getInt("total");
			allTheInfo[5] = recomend.toString();
		}

		System.out.println(allTheInfo[0]);
		System.out.println(allTheInfo[1]);
		System.out.println(allTheInfo[2]);
		System.out.println(allTheInfo[3]);
		System.out.println(allTheInfo[4]);
		System.out.println(allTheInfo[5]);
		*/
		return null;
	}
}
