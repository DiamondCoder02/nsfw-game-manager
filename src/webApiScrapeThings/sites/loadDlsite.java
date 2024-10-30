package webApiScrapeThings.sites;

import webApiScrapeThings.loadSitesBufRead;

public class loadDlsite {
	/**
	 * This function will get the content of the DLSite page.
	 * @param gameIds - The ID of the game.
	 * @return String[] - returns the name, developer, latest update date, languages and OS.
	 */
	public static String[] getDlsiteUrlContents(String gameIds) {
		/*
		I have weirdest theory, and if this works, I'm a genius.
		* RJ  - Smartphone - https://www.dlsite.com/appx/work/=/product_id/RJ413985.html/?locale=en_US
		* VJ0 - H games - https://www.dlsite.com/pro/work/=/product_id/VJ009471.html/?locale=en_US
		* RJ0 - Doujin - https://www.dlsite.com/maniax/work/=/product_id/RJ01153987.html/?locale=en_US
		*/
		String type;
		if (gameIds.startsWith("RJ0")) { type = "maniax"; }
		else if (gameIds.startsWith("VJ0")) { type = "pro"; }
		else if (gameIds.startsWith("RJ")) { type = "appx"; }
		else { return null; }
		StringBuilder content = loadSitesBufRead.loadSite("https://www.dlsite.com/"+type+"/work/=/product_id/"+gameIds+".html/?locale=en_US", true);
		if (content == null) { return null; }

		String snippingUseless = content.substring(content.indexOf("<!-- top_wrapper -->") + 20);
		String snippingUseless2 = snippingUseless.substring(0, snippingUseless.indexOf("<!-- script_footer -->"));
		String webInfos = snippingUseless2.replaceAll("(?m)^[ \t]*\r?\n", ""); // remove all empty lines

		// <h1 itemprop="name" id="work_name">
		String name = webInfos.substring(webInfos.indexOf("<h1 itemprop=\"name\" id=\"work_name\">") + 35, webInfos.indexOf("</h1>"));

		// TODO I was uneable to get star or any rating, will check back later
		// <span class="point average_count">{{ product.rate_average_2dp }}</span>
		// <span class="point average_count">4.41</span>
		// String rating = webInfos.substring(webInfos.indexOf("<span class=\"point average_count\">") + 33, 4);
		// log.print(rating);

		// <span itemprop="brand" class="maker_name">
		// <a href="https://www.dlsite.com/maniax/circle/profile/=/maker_id/RG43595.html">Crazy Nirin</a>
		String developer = webInfos.substring(webInfos.indexOf("class=\"maker_name\">") + 19);
		developer = developer.substring(developer.indexOf(">") + 1, developer.indexOf("</a>"));

		/*
		<tr>
			<th>Release date</th>
			<td> <a href="https://www.dlsite.com/appx/new/=/year/2022/mon/09/day/03/cyear/2022/cmon/09">Sep/03/2022</a> </td>  
		</tr>
		<tr>
			<th>Update information</th>
			<td> Dec/13/2022 <div class="btn_ver_up"> </div> </td>
		</tr>
		 */
		String latestUpdateDate;
		if (webInfos.contains("<th>Update information</th>")) {
			latestUpdateDate = webInfos.substring(webInfos.indexOf("<th>Update information</th>") + 28).strip();
			latestUpdateDate = latestUpdateDate.substring(latestUpdateDate.indexOf("<td>") + 4, latestUpdateDate.indexOf("<div")).strip();
		} else {
			latestUpdateDate = webInfos.substring(webInfos.indexOf("work_outline") + 12).strip();
			latestUpdateDate = latestUpdateDate.substring(latestUpdateDate.indexOf("<a"), latestUpdateDate.indexOf("</a>")).strip();
			latestUpdateDate = latestUpdateDate.substring(latestUpdateDate.indexOf(">") + 1).strip();
		}

		/*
		<th>Supported languages</th>
		<td>
		<div class="work_genre">
			<a href="https://www.dlsite.com/appx/fsr/=/options/CHI_HANS/from/icon.work"><span class="icon_CHI_HANS" title="Chinese Simplified">Chinese Simplified</span></a><a href="https://www.dlsite.com/appx/fsr/=/options/CHI_HANT/from/icon.work"><span class="icon_CHI_HANT" title="Chinese Traditional">Chinese Traditional</span></a><a href="https://www.dlsite.com/appx/fsr/=/options/KO_KR/from/icon.work"><span class="icon_KO_KR" title="Korean">Korean</span></a><a href="https://www.dlsite.com/appx/fsr/=/options/JPN/from/icon.work"><span class="icon_JPN" title="Japanese">Japanese</span></a><a href="https://www.dlsite.com/appx/fsr/=/options/ENG/from/icon.work"><span class="icon_ENG" title="English">English</span></a>
		</div>
		</td>
		*/
		/*
		 * <a href="https://www.dlsite.com/pro/fsr/=/work_category%5B0%5D/pc/options/JPN/from/icon.work"><span class="icon_JPN" title="Japanese">Japanese</span></a>
		 */
		String languages = webInfos.substring(webInfos.indexOf("<th>Supported languages</th>") + 28).strip();
		languages = languages.substring(0, languages.indexOf("</div>") + 10).strip();
		String allLanguages = "";
		do {
			languages = languages.substring(languages.indexOf("<span") + 19).strip();
			try{
				String lang = languages.substring(languages.indexOf(">") + 1, languages.indexOf("</span>")).strip();
				allLanguages += lang + ", ";
				// log.print(lang);
			} catch (Exception e) {
				break;
			}
		} while (languages.contains("<span"));
		allLanguages = allLanguages.substring(0, allLanguages.length() - 2);

		// <div class="os_popup_body">
		/*
		<tr><td>Windows</td><td>7 / 8 / 10 / 11</td></tr>
		<tr><td>Mac</td><td>-</td></tr> 
		<tr><td>iOS</td> <td>-</td></tr> 
		<tr><td>Android</td> <td>Android8.0</td></tr> 
		<tr><td>Miscellaneous</td> <td>-</td></tr>
		 */
		String osList = webInfos.substring(webInfos.indexOf("os_popup_body") + 15).strip();
		osList = osList.substring(osList.indexOf("<tbody>") + 7).strip();
		String useableOsList = osList.substring(0, osList.indexOf("</tbody>")).strip();
		String[] osArray = useableOsList.split("</tr>");
		String allOs = "";
		for (String os : osArray) {
			String osName = os.substring(os.indexOf("<td>") + 4, os.indexOf("</td>")).strip();
			String osVersion = os.substring(os.lastIndexOf("<td>") + 4, os.lastIndexOf("</td>")).strip();
			// log.print(osName + " - " + osVersion);
			if (!osVersion.equals("-")) { allOs += osName + " - " + osVersion + ", "; }
		}
		allOs = allOs.substring(0, allOs.length() - 2);

		String[] allInfos = {name, developer, latestUpdateDate, allLanguages, allOs};
		// for (String info : allInfos) { log.print(info); }

		return allInfos;
	}
}
