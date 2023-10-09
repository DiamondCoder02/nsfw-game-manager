package folderHandle.checkAndBackup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import WebsiteHandle.autoUpdateCheck;
import folderHandle.createMissing.createMissingDatabase;
import folderHandle.createMissing.createMissingSettings;
import folderHandle.loadSaveGamesSettings.settingsManager;
import main.langLoad;
import main.mainInit;

public class checksFiles {
	private static String mainPath = mainInit.mainPath;
	public static void checks() {
		File mainDirectory = new File(System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager");
		if (!mainDirectory.exists()){
			try {
				mainDirectory.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error creating main folder(s)! (checksFile.checks)", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (autoUpdateCheck.test()) { 
			System.out.println("Update needed");
			settingsManager.xmlSettings("appVersion", "appVer");
			System.exit(0); 
		} else {
			System.out.println("Update not needed");
			checkSettingsFolder();
			checkLanguage();
			langLoad.loadLanguages();
			checkMissingDatabase();
			checkPics();
		}
	}

	public static void checkMissingDatabase() {
		String path = mainPath + "hentai.xml";
		File file = new File(path);
		if (!file.exists()) {
			createMissingDatabase.createFile(path);
		}
	}

	public static void checkSettingsFolder(){
		String path = mainPath + "settings.xml";
		File file = new File(path);
		if (!file.exists()) {
			createMissingSettings.createFile(path);
		} else {
			checkMissingSetting.checkSettings();
		}
	}

	private static void checkPics() {
		File file = new File(mainPath + "/pics");
		if (!file.exists()) {
			file.mkdirs();
		}
		String[] picturesThatGets = {"creditLogo.png", "nyaaa.png"};
		String unableToDownload = "";
		for (int i = 0; i < picturesThatGets.length; i++) {
			File file2 = new File(mainPath + "pics/" + picturesThatGets[i]);
			if (!file2.exists()) {
				try{
					URL url = new URL("https://raw.githubusercontent.com/DiamondPRO02/nsfw-game-manager/master/icons_doNotTouch/" + picturesThatGets[i]);
					BufferedImage img = ImageIO.read(url);
					ImageIO.write(img, "png", file2);
				} catch (Exception e) {
					unableToDownload = unableToDownload+"\nUnable to download: " + picturesThatGets[i];
				}
			}
		}
		if (!unableToDownload.equals("")) {
			JOptionPane.showMessageDialog(null, "Error downloading image(s):\n" + unableToDownload + "\n>.< (checksFile.checkPics)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void checkLanguage(){
		File file = new File(mainPath + "language.csv");
		if (!file.exists()) {
			try{
				URL url = new URL("https://raw.githubusercontent.com/DiamondPRO02/nsfw-game-manager/master/doNotTouch/language.csv");
				// System.out.println(url);
				InputStream in = url.openStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				in.close();
				fos.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error downloading language file.\n>.< (checksFile.checkLanguage)", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
