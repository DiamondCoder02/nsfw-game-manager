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
		}

		System.out.println(content.toString());
		// TODO API NEEDED
		// Name, Developer, Newest version, Date of last update, People rating, Engine, OS
		allTheInfo[0] = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>"));



		for (int i = 0; i < allTheInfo.length; i++) {
			System.out.println(allTheInfo[i]);
		}
		allTheInfo = null;
		return allTheInfo;
	}
}
