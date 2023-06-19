package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class checksFile {
	static String mainPath = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager/";
	public static void checks() {
		try {
			new File(System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager").mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating database folders", "Error", JOptionPane.ERROR_MESSAGE);
		}
		checkSettingsFolder();
		checkMissingDatabase();
		checkMissingSetting.checkSettings();
		checkLanguage();
		checkPics();
	}

	private static void checkMissingDatabase() {
		String path = mainPath + "hentai.xml";
		File file = new File(path);
		if (!file.exists()) {
			createMissingDatabase.createFile(path);
		}
	}

	private static void checkSettingsFolder(){
		String path = mainPath + "settings.xml";
		File file = new File(path);
		if (!file.exists()) {
			createMissingSettings.createFile(path);
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
					// e.printStackTrace();
					unableToDownload = unableToDownload+"\nUnable to download: " + picturesThatGets[i];
				}
			}
		}
		if (!unableToDownload.equals("")) {
			JOptionPane.showMessageDialog(null, "Error downloading image(s):\n" + unableToDownload, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void checkLanguage(){
		File file = new File(mainPath);
		try{
			URL url = new URL("https://raw.githubusercontent.com/DiamondPRO02/nsfw-game-manager/master/languages_doNotTouch/languages.csv");
			BufferedImage csv= ImageIO.read(url);
			ImageIO.write(csv, "csv", file);
		} catch (Exception e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to download the language files", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
