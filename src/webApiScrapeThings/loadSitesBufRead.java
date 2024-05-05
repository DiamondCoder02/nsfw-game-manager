package webApiScrapeThings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import folderHandling.initialFileLoading.loadLanguage;

public class loadSitesBufRead {
	static String[] lf = loadLanguage.folder, bs = loadLanguage.base;
	/**
	 * This function will load the site and return the content.
	 * @param webSite - The site to load.
	 * @param slashN - If the content should have a new line at the end.
	 * @return StringBuilder - returns the content of the site.
	 */
	public static StringBuilder loadSite(String webSite, Boolean slashN) {
		StringBuilder content = new StringBuilder();
		String line;
		try {
			URL url = new URL(webSite);
			URLConnection urlConnection = url.openConnection();
			InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			if (slashN) {
				while ((line = bufferedReader.readLine()) != null) {
					content.append(line + "\n");
				}
			} else {
				content.append(bufferedReader.readLine());
			}

			inputStreamReader.close();
			bufferedReader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  
				" *-* "+webSite+" *-* \n" + (lf[5]!=null?lf[5]:"Error while loading the site") + " (loadSitesBufRead.loadSite)", 
				bs[1]==null?"Error":bs[1], 
				JOptionPane.ERROR_MESSAGE
			);
			return null;
		}
		return content;
	}
}
