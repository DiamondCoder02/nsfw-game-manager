package WebsiteHandle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

public class autoUpdateCheck {
	// TODO https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest
	public static void test() {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL("https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest");
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(ASd)", "Error", JOptionPane.ERROR_MESSAGE);
		}

		String toSoonBeDownload;
		toSoonBeDownload = content.substring(content.indexOf("<ul"));
		toSoonBeDownload = toSoonBeDownload.substring(0,toSoonBeDownload.indexOf("Source code"));
		
// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.exe"
// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.jar"
		System.out.println(toSoonBeDownload);
	}
}
