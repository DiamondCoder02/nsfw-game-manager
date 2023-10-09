package WebsiteHandle;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;

public class autoUpdateCheck {
	// TODO https://github.com/DiamondPRO02/nsfw-game-manager/releases/latest
	public static String onlineVersion = null;
	public static Boolean test() {
		int responseCode = 999;
		String currentVersion;
		String onlineLocation = null, path, ext;
		try {
			currentVersion = loadSettingsFromXml.loadStringSettings("appVersion")[0];
		} catch (Exception e) {
			currentVersion = null;
		}
		Boolean success = false;
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
			if (onlineVersion.equals(currentVersion)) { return success; }

			path = autoUpdateCheck.class.getProtectionDomain().getCodeSource().getLocation().toURI().toString();
			path = path.replace("file:/", "");
			ext = path.substring(path.lastIndexOf(".") + 1, path.length());
			// path = "C:\\Users\\Diamond\\Desktop";
			// ext = "exe";
			onlineLocation = onlineLocation.replace("tag", "download").concat("/HentaiGameManager." + ext);

			try{ path.replace("_"+currentVersion+".", "_"+onlineVersion+"."); } 
			catch (Exception e) { path = path.replace("."+ext, "_"+onlineVersion+"."+ext); }

			System.out.println(path);
			System.out.println(onlineLocation);
			try{
				success = downloadFile(onlineLocation, path);
				JOptionPane.showMessageDialog(null, "test", "Success", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				success = false;
				JOptionPane.showMessageDialog(null, "Error downloading from github (autoUpdateCheck)", "Error", JOptionPane.ERROR_MESSAGE);
			}
			return success;
		} catch (Exception e) {
			String error;
			error = "Github: " + responseCode + 
			"\nOnline: " + onlineLocation;
			JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.exe"
// <a href="/DiamondPRO02/nsfw-game-manager/releases/download/0.1.1.2-lang2/HentaiGameManager.jar"
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
