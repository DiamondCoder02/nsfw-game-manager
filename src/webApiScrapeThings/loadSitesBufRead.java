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
			// https://mkyong.com/java/java-httpurlconnection-follow-redirect-example/
			String newUrl = null;
			/*
			URL obj = new URL(webSite);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");
			System.out.println("Request URL ... " + webSite);

			boolean redirect = false;
			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}
			System.out.println("Response Code ... " + status);

			if (redirect) {
				// get redirect url from "location" header field
				newUrl = conn.getHeaderField("Location");
				// get the cookie if need, for login
				String cookies = conn.getHeaderField("Set-Cookie");
				// open the new connnection again
				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestProperty("Cookie", cookies);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");
				System.out.println("Redirect to URL : " + newUrl);
			}
			*/

			URL url = new URL(newUrl!=null?newUrl:webSite);
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
