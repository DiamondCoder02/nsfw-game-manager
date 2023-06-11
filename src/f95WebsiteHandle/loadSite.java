package f95WebsiteHandle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

public class loadSite {
	static String[] allTheInfo = new String[7];
	public static String[] getUrlContents(String gameIds) {
		StringBuilder content = new StringBuilder();
		// Use try and catch to avoid the exceptions
		try {
			URL url = new URL("https://f95zone.to/threads/"+gameIds+"/"); // creating a url object
			URLConnection urlConnection = url.openConnection(); // creating a urlconnection object
			// wrapping the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// reading from the urlconnection using the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error while loading the site (f95_getUrlContents)", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Name, Developer, Newest version, Date of last update, People rating, Engine, OS
		String longTitle, asd, engi[] = new String[9];
		longTitle = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>"));
		
		try{
			asd = longTitle.split(" \\[")[0];
			allTheInfo[0] = asd.split(" - ")[asd.split(" - ").length - 1];
		} catch (Exception e) { allTheInfo[0] = "N/A"; }
		try {
			allTheInfo[1] = longTitle.split(" \\[")[2].split("]")[0];
		} catch (Exception e) { allTheInfo[1] = "N/A"; }
		try {
			allTheInfo[2] = longTitle.split(" \\[")[1].split("]")[0];
		} catch (Exception e) { allTheInfo[2] = "N/A"; }
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
		else { for (int i = 0; i < engi.length; i++) { if (engi[i] != "Collection" || engi[i] != "VN") { allTheInfo[5] = engi[i]; break; } } }
		try {
			Integer numId = Integer.parseInt(gameIds);
			if (numId < 10) { asd = content.substring(content.indexOf(">Platform<") + 15); } 
			else { asd = content.substring(content.indexOf(">OS") + 9); }
			allTheInfo[6] = asd.substring(0, asd.indexOf("<")).trim();
		} catch (Exception e) { allTheInfo[6] = "N/A"; }

		if (allTheInfo[1] == "N/A") { allTheInfo[1] = allTheInfo[2]; allTheInfo[2] = "N/A"; }
		// for (int i = 0; i < allTheInfo.length; i++) { System.out.println(allTheInfo[i]); }
		return allTheInfo;
	}
}
