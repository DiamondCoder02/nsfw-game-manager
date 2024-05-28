package webApiScrapeThings.sites;

import folderHandling.initialFileLoading.loadLanguage;
import webApiScrapeThings.loadSitesBufRead;

public class loadF95site {
	static String[] bs = loadLanguage.base;
	/**
	 * This function will get the content of the f95zone.to site.
	 * @param gameIds - The game ID to get the content from.
	 * @return String[] - returns the content of the site.
	 */
	public static String[] getf95UrlContents(String gameIds) {
		// TODO, if not id is given, check if it's a full url
		// https://f95zone.to/threads/123456/
		StringBuilder content = loadSitesBufRead.loadSite("https://f95zone.to/threads/"+gameIds+"/", true);
		if (content == null) { return null; }
		String[] allTheInfo = new String[8];

		// Name, Developer, Newest version, Date of last update, People rating, Engine, OS, Language
		String longTitle, asd, engi[] = new String[9];
		longTitle = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>"));
		
		try { // Developer
			allTheInfo[1] = longTitle.split(" \\[")[2].split("]")[0];
		} catch (Exception e) { allTheInfo[1] = "N/A"; }
		try { // Newest version
			allTheInfo[2] = longTitle.split(" \\[")[1].split("]")[0];
		} catch (Exception e) { allTheInfo[2] = "N/A"; }

		if (allTheInfo[1] == "N/A") { allTheInfo[1] = allTheInfo[2]; allTheInfo[2] = "N/A"; }
		if (longTitle.contains("Completed - ")) { allTheInfo[2] = "✔ " + allTheInfo[2]; }
		if (longTitle.contains("Abandoned - ")) { allTheInfo[2] = "✖ " + allTheInfo[2]; }

		try { // Name
			asd = longTitle.split(" \\[")[0];
			allTheInfo[0] = asd.substring(asd.indexOf(" - ")).trim();
			if (allTheInfo[0].contains("- Completed") || allTheInfo[0].contains("- Abandoned")) {
				allTheInfo[0] = allTheInfo[0].substring(allTheInfo[0].indexOf(" - ")).trim();
				allTheInfo[0] = allTheInfo[0].substring(2);
			}
			if (allTheInfo[0].contains("Completed - ") || allTheInfo[0].contains("Abandoned - ")) {
				allTheInfo[0] = allTheInfo[0].substring(allTheInfo[0].indexOf(" - ")).trim();
				allTheInfo[0] = allTheInfo[0].substring(2);
			}
			if (allTheInfo[0].startsWith("- ")) { allTheInfo[0] = allTheInfo[0].substring(2); }
		} catch (Exception e) { allTheInfo[0] = "N/A"; }

		try { // Date of last update
			asd = content.substring(content.indexOf(">Release Date") +18);
			allTheInfo[3] = asd.substring(0, asd.indexOf("<")).trim();
			if (allTheInfo[3].equals("")) { allTheInfo[3] = "N/A"; }
			if (allTheInfo[3].startsWith(">")) { allTheInfo[3] = allTheInfo[3].substring(1); }
		} catch (Exception e) { allTheInfo[3] = "N/A"; }
		try { // People rating
			allTheInfo[4] = content.substring(content.indexOf(" star(s)<") - 4, content.indexOf(" star(s)<")).trim();
		} catch (Exception e) { allTheInfo[4] = "N/A"; }
		for (int i = 0; i < longTitle.split(" - ").length; i++) { engi[i] = longTitle.split(" - ")[i]; }

		// Engine
		if (engi[0] == "Collection") {allTheInfo[5] = engi[0];}
		else {
			for (int i = 0; i < engi.length; i++) {
				if (engi[i].contains("VN")) { allTheInfo[0] = "["+engi[i]+"] "+allTheInfo[0]; } 
				if (engi[i].contains("Collection") || engi[i].contains("VN")){} else { allTheInfo[5] = engi[i]; break; } 
			}
		}
		try { // OS
			if (content.toString().contains(">Platform<")) { asd = content.substring(content.indexOf(">Platform<") + 15); } 
			else { asd = content.substring(content.indexOf(">OS") + 9); }
			allTheInfo[6] = asd.substring(0, asd.indexOf("<")).trim();
			if (allTheInfo[6].contains("html>")) { allTheInfo[6] = "N/A"; }
		} catch (Exception e) { allTheInfo[6] = "N/A"; }

		try{ // Language
			asd = content.substring(content.indexOf("<b>Language</b>:") + 16);
			allTheInfo[7] = asd.substring(0, asd.indexOf("<")).trim();
		} catch (Exception e) { allTheInfo[7] = "N/A"; }

		if (content.toString().contains("/tags/virtual-reality/")) {
			allTheInfo[5] = "[VR] "+allTheInfo[5];
		}

		for (int i = 0; i < allTheInfo.length; i++) {
			if (allTheInfo[i] != null) {
				String temp = allTheInfo[i].toString();
				if (temp.contains("&#039;")) { temp = temp.replace("&#039;", "'"); }
				allTheInfo[i] = temp;
			}
		}

		// for (int i = 0; i < allTheInfo.length; i++) { log.print(allTheInfo[i]); }
		return allTheInfo;
	}
}
