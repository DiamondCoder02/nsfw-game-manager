package main;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JOptionPane;

import WebsiteHandle.autoUpdateCheck;
import folderHandle.loadSaveGamesSettings.settingsManager;

public class downloadNewVersion {
	public static void getNewestGithubVersion() {
		System.out.println("Getting new update");
		String onlineVersion = autoUpdateCheck.onlineVersion;
		String onlineLocation = autoUpdateCheck.onlineLocation;
		String path, ext;
		try{
			path = mainInit.class.getProtectionDomain().getCodeSource().getLocation().toString();
			System.out.println(path);
			path = path.replace("file:/", "");
			System.out.println(path);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error getting path (getNewestGithubVersion)", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ext = path.substring(path.lastIndexOf(".") + 1, path.length());
		onlineLocation = onlineLocation.replace("tag", "download").concat("/HentaiGameManager." + ext);

		System.out.println(path);
		System.out.println(onlineLocation);
		path = (System.getProperty("user.dir") + "/HentaiGameManager_"+onlineVersion+"."+ext);
		System.out.println(path);
		try{
			boolean succ = downloadFile(onlineLocation, path);
			if (succ) { settingsManager.xmlSettings("appVersion", "appVer"); }
			JOptionPane.showMessageDialog(null, "Succesfully updated! ("+onlineVersion+")", "Success", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error downloading from github (getNewestGithubVersion) \nGet newest from github.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static Boolean downloadFile(String url, String path) throws Exception {
		URL gotUrl = new URL(url);
		// System.out.println(gotUrl);
		InputStream in = gotUrl.openStream();
		FileOutputStream fos = new FileOutputStream(path);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0) {
			fos.write(buffer, 0, length);
		}
		in.close();
		fos.close();
		return true;
	}
}
