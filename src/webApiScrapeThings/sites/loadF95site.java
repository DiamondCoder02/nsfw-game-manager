package webApiScrapeThings.sites;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import folderHandling.initialFileLoading.loadLanguage;

public class loadF95site {
	static String[] lf = loadLanguage.folder, bs = loadLanguage.base;
	public static String[] getf95UrlContents(String gameIds) {
		String[] allTheInfo = new String[8];
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL("https://f95zone.to/threads/"+gameIds+"/");
			URLConnection urlConnection = url.openConnection();
			InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			inputStreamReader.close();
			bufferedReader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  "("+gameIds+")" + (lf[5]!=null?lf[5]:"Error while loading the site") + " (f95_getUrlContents)", bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE);
			return null;
		}

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

		// for (int i = 0; i < allTheInfo.length; i++) { System.out.println(allTheInfo[i]); }
		return allTheInfo;
	}
}