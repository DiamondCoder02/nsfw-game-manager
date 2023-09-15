package WebsiteHandle;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;

public class autoUpdateCheck {
	// TODO https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest
	public static void test() {
		int responseCode = 999;
		String onlineVersion, onlineLocation = null, path, ext;
		String currentVersion = loadSettingsFromXml.loadStringSettings("appVersion")[0];
		try{
			String url = "https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest";
			HttpURLConnection con = (HttpURLConnection) (new URL(url).openConnection());
			con.setInstanceFollowRedirects(false);
			con.connect();
			responseCode = con.getResponseCode();
			onlineLocation = con.getHeaderField("Location");
			onlineVersion = onlineLocation.substring(onlineLocation.lastIndexOf("/") + 1, onlineLocation.length());
			onlineVersion = onlineVersion.substring(0, onlineVersion.lastIndexOf("-"));
			System.out.println(onlineVersion);
			System.out.println(currentVersion);
			if (onlineVersion.equals(currentVersion)) { System.out.println("Update not needed"); return; }

			path = autoUpdateCheck.class.getProtectionDomain().getCodeSource().getLocation().toURI().toString();
			ext = path.substring(path.lastIndexOf(".") + 1, path.length());
			// path = "C:\\Users\\Diamond\\Desktop";
			// ext = "exe";
			onlineLocation = onlineLocation.replace("tag", "download").concat("/HentaiGameManager." + ext);





			System.out.println(path);
			System.out.println(onlineLocation);
			JOptionPane.showMessageDialog(null, path + "\n" + onlineLocation, "Yes?", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			String error;
			error = "Github: " + responseCode + 
			"\nOnline: " + onlineLocation;
			JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
		}

// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.exe"
// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.jar"
	}
}
