package integrationCheck;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import folderHandling.changeSettings;
import folderHandling.initialFileLoading.loadSettings;

public class newVersion {
	public static Boolean checkNewVersion() {
		String onlineLocation = getOnlineLocation();
		if (onlineLocation == null) { return false; }

		String onlineVersion = onlineLocation.substring(onlineLocation.lastIndexOf("/") + 1, onlineLocation.length());
		onlineVersion = onlineVersion.substring(0, onlineVersion.lastIndexOf("-"));

		if (onlineVersion.equals(loadSettings.appVersion.toString())) { return false; }
		return getNewVersion(onlineLocation, onlineVersion);
	}

	private static Boolean getNewVersion(String onlineLocation, String onlineVersion){
		String path, ext;
		try{
			path = newVersion.class.getProtectionDomain().getCodeSource().getLocation().toString();
			path = path.replace("file:/", "");
			System.out.println(path);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error getting path (getNewestGithubVersion)", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		ext = path.substring(path.lastIndexOf(".") + 1, path.length());
		onlineLocation = onlineLocation.replace("tag", "download").concat("/HentaiGameManager." + ext);

		path = (System.getProperty("user.dir") + "/HentaiGameManager_"+onlineVersion+"."+ext);
		System.out.println(path);
		try{
			boolean succ = fileDownloader.downloadFile(onlineLocation, path);
			System.out.println(succ);
			// TODO - change settings manager is still needed
			if (succ) { changeSettings.changeSetting("appVersion", onlineVersion); }

			return succ;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error downloading from github (getNewestGithubVersion) \nGet newest from github.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
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
