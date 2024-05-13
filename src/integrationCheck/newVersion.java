package integrationCheck;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.changeSettings;

public class newVersion {
	/**
	 * This function will check if there is a new version available.
	 * @return boolean - returns true if there is a new version available.
	 */
	public static Boolean checkNewVersion(String mainDir) {
		String onlineLocation = getOnlineLocation();
		if (onlineLocation == null) { return false; }

		String onlineVersion = onlineLocation.substring(onlineLocation.lastIndexOf("/") + 1, onlineLocation.length());
		onlineVersion = onlineVersion.substring(0, onlineVersion.lastIndexOf("-"));

		if (onlineVersion.equals(loadSettings.appVersion.toString())) { return false; }
		return getNewVersion(mainDir, onlineLocation, onlineVersion);
	}

	/**
	 * This function will download the new version from github.
	 * @param onlineLocation - The online location of the new version.
	 * @param onlineVersion - The online version of the new version.
	 * @return boolean - returns true if the new version is downloaded.
	 */
	private static Boolean getNewVersion(String mainDir, String onlineLocation, String onlineVersion){
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
			if (succ) { changeSettings.changeSetting(mainDir, "appVersion", onlineVersion); }

			return succ;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error downloading from github (getNewestGithubVersion) \nGet newest from github.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * This function will get the online location of the new version.
	 * @return String - returns the online location of the new version.
	 */
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
