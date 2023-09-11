package WebsiteHandle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

public class autoUpdateCheck {
	// TODO https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest
	public static void test() {
		System.out.println("test");
		try{
			String url = "https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest";
			HttpURLConnection con = (HttpURLConnection) (new URL(url).openConnection());
			con.setInstanceFollowRedirects(false);
			con.connect();
			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			String location = con.getHeaderField("Location");
			System.out.println(location);

			String path = null;
			// check if application is jar or exe
			// path = System.getProperty("java.class.path");
			path = autoUpdateCheck.class.getProtectionDomain().getCodeSource().getLocation().toURI().toString();

			System.out.println(path);
			JOptionPane.showMessageDialog(null, path, "Yes?", JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(New problem)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		/*
		try {
			URL url = new URL("https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest");
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(ASd)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		*/

// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.exe"
// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.jar"
	}
}
