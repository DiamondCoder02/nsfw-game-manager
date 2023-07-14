package f95WebsiteHandle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import main.langLoad;

public class loadSite {
	static String[] lf = langLoad.folder, bs = langLoad.base;
	public static String[] getf95UrlContents(String gameIds) {
		String[] allTheInfo = new String[8];
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL("https://f95zone.to/threads/"+gameIds+"/");
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  "("+gameIds+")" + (lf[5]!=null?lf[5]:"Error while loading the site") + " (f95_getUrlContents)", bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Name, Developer, Newest version, Date of last update, People rating, Engine, Language, OS
		// <title>RPGM - Completed - EMPTY-66822 [v9] [Lonery-Moon] | F95zone</title>
		// <title>Unity - Slave Lord - Realms of Bondage [v0.2.9] [Pink Tea Games] | F95zone</title>
		String longTitle, asd, engi[] = new String[9];
		longTitle = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>"));
		
		try {
			allTheInfo[1] = longTitle.split(" \\[")[2].split("]")[0];
		} catch (Exception e) { allTheInfo[1] = "N/A"; }
		try {
			allTheInfo[2] = longTitle.split(" \\[")[1].split("]")[0];
		} catch (Exception e) { allTheInfo[2] = "N/A"; }

		if (allTheInfo[1] == "N/A") { allTheInfo[1] = allTheInfo[2]; allTheInfo[2] = "N/A"; }
		if (longTitle.contains("Completed")) { allTheInfo[2] = "✔ " + allTheInfo[2]; }
		if (longTitle.contains("Abandoned")) { allTheInfo[2] = "✖ " + allTheInfo[2]; }

		try {
			asd = longTitle.split(" \\[")[0];
			asd = asd.substring(asd.indexOf(" - ")).trim();
			if (asd.startsWith("- Completed") || asd.startsWith("- Abandoned")) {
				allTheInfo[0] = asd.substring(asd.indexOf(" - ")).trim();
				allTheInfo[0] = allTheInfo[0].substring(2);
				System.out.println(allTheInfo[0]);
			}
		} catch (Exception e) { allTheInfo[0] = "N/A"; }

		try {
			asd = content.substring(content.indexOf(">Release Date") +18);
			allTheInfo[3] = asd.substring(0, asd.indexOf("<")).trim();
			if (allTheInfo[3].equals("")) { allTheInfo[3] = "N/A"; }
		} catch (Exception e) { allTheInfo[3] = "N/A"; }
		try {
			allTheInfo[4] = content.substring(content.indexOf(" star(s)<") - 4, content.indexOf(" star(s)<")).trim();
		} catch (Exception e) { allTheInfo[4] = "N/A"; }
		for (int i = 0; i < longTitle.split(" - ").length; i++) { engi[i] = longTitle.split(" - ")[i]; }

		if (engi[0] == "Collection") {allTheInfo[5] = engi[0];} 
		else { 
			for (int i = 0; i < engi.length; i++) { 
				if (engi[i].contains("VN")) { allTheInfo[0] = "["+engi[i]+"] "+allTheInfo[0]; } 
				if (engi[i].contains("Collection") || engi[i].contains("VN")){} else { allTheInfo[5] = engi[i]; break; } 
			} 
		}
		try {
			Integer numId = Integer.parseInt(gameIds);
			if (numId < 10) { asd = content.substring(content.indexOf(">Platform<") + 15); } 
			else { asd = content.substring(content.indexOf(">OS") + 9); }
			allTheInfo[6] = asd.substring(0, asd.indexOf("<")).trim();
		} catch (Exception e) { allTheInfo[6] = "N/A"; }

		for (int i = 0; i < allTheInfo.length; i++) {
			if (allTheInfo[i] != null) {
				String temp = allTheInfo[i].toString();
				if (temp.contains("&#039;")) { temp = temp.replace("&#039;", "'"); }
				allTheInfo[i] = temp;
			}
		}

		try{
			asd = content.substring(content.indexOf("<b>Language</b>:") + 16);
			allTheInfo[7] = asd.substring(0, asd.indexOf("<")).trim();
		} catch (Exception e) { allTheInfo[7] = "N/A"; }

		// for (int i = 0; i < allTheInfo.length; i++) { System.out.println(allTheInfo[i]); }
		return allTheInfo;
	}
}
