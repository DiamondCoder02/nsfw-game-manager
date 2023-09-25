package WebsiteHandle;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import main.langLoad;

public class loadDlsite {
	static String[] lf = langLoad.folder, bs = langLoad.base;
	public static String[] getDlsUrlContents(String gameIds) {
		String[] allTheInfo = new String[8];
		StringBuilder content = new StringBuilder();
		/* All the infos:
		 * 0. Name
		 * 1. Developer
		 * 2. Newest version
		 * 3. Date of last update
		 * 4. People rating
		 * 5. Engine
		 * 6. OS
		 * 7. Language
		 */
		try {
			URL url = new URL("https://www.dlsite.com/pro-touch/work/=/product_id/VJ"+gameIds+".html");
			System.out.println(url);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  "("+gameIds+")" + (lf[5]!=null?lf[5]:"Error while loading the site") + " (loadDlsite.getDlsUrlContents)", bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Name, Developer, Newest version, Date of last update, People rating, Engine, OS, Language
		// String longTitle = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>"));


		System.out.println("yay");

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
