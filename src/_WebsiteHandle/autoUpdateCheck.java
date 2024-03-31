package _WebsiteHandle;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import _folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import _main.mainInit;

public class autoUpdateCheck {
	public static String onlineVersion = null;
	public static String onlineLocation = null;
	public static Boolean checkUpdate() {
		String currentVersion = null;
		try{
			currentVersion = loadSettingsFromXml.loadStringSettings("appVersion")[0];
		} catch (Exception e) {
			if (currentVersion == null) {currentVersion = mainInit.appVersion;}
			else {JOptionPane.showMessageDialog(null, "Error getting version (checkUpdate)", "Error", JOptionPane.ERROR_MESSAGE);}
		}
		String url = "https://github.com/DiamondCoder02/nsfw-game-manager/releases/latest";

		Boolean updateNeeded = false;
		onlineLocation = getOnlineLocation(url); if (onlineLocation == null) { return updateNeeded; }
		onlineVersion = onlineLocation.substring(onlineLocation.lastIndexOf("/") + 1, onlineLocation.length());
		onlineVersion = onlineVersion.substring(0, onlineVersion.lastIndexOf("-"));
		System.out.println(onlineVersion + " -> " + currentVersion);
		if (!onlineVersion.equals(currentVersion)) { updateNeeded = true; }
		System.out.println("Update is needed: "+updateNeeded);
		return updateNeeded;
	}

	private static String getOnlineLocation(String url) {
		String onlineLocation = null;
		int responseCode = 999;
		try{
			HttpURLConnection con = (HttpURLConnection) (new URL(url).openConnection());
			con.setInstanceFollowRedirects(false);
			con.connect();
			responseCode = con.getResponseCode();
			onlineLocation = con.getHeaderField("Location");
			con.disconnect();
		} catch (Exception e) {
			if (responseCode == 999) {JOptionPane.showMessageDialog(null, "Error getting to github, you might be offline?", "Error", JOptionPane.ERROR_MESSAGE);}
			String error = "Github: " + responseCode + "\nOnline: " + onlineLocation;
			JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return onlineLocation;
	}
}
