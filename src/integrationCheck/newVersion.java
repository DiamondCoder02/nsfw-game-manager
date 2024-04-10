package integrationCheck;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import folderHandling.initialFileLoading.loadSettings;

public class newVersion {
	public static boolean checkNewVersion() {
		String onlineLocation = getOnlineLocation();
		if (onlineLocation == null) { return false; }

		String onlineVersion = onlineLocation.substring(onlineLocation.lastIndexOf("/") + 1, onlineLocation.length());
		onlineVersion = onlineVersion.substring(0, onlineVersion.lastIndexOf("-"));
		
		if (!onlineVersion.equals(loadSettings.appVersion.toString())) { return true; }
		return false;
	}

	private static String getOnlineLocation() {
		String onlineLocation = null;
		int responseCode = 999;
		try{
			HttpURLConnection con = (HttpURLConnection) (new URL(defaultValues.appGithubLink).openConnection());
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
