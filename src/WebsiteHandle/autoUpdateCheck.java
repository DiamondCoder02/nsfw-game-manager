package WebsiteHandle;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;

public class autoUpdateCheck {
	// TODO https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest
	public static String onlineVersion = null;
	public static void test() {
		int responseCode = 999;
		String onlineLocation = null;
		String currentVersion = loadSettingsFromXml.loadStringSettings("appVersion")[0];
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
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
			if (onlineVersion.equals(currentVersion)) { 
				System.out.println("Update not needed"); return; 
			} else {
				System.out.println("Updating..."); 
				executorService.scheduleAtFixedRate(autoUpdate.updating(currentVersion, onlineVersion, onlineLocation), 0, 1, TimeUnit.SECONDS);
				System.exit(0); return;
			}
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
