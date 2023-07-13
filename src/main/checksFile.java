package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class checksFile {
	public static String mainPath = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager/";
	public static void checks() {
		try{langLoad.loadLanguages();} catch (Exception e) { System.out.println("Error loading languages! (checksFile.checks)"); }
		try {
			new File(System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager").mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating main folder(s)! (checksFile.checks)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (checkSettingsFolder()) {checkMissingSetting.checkSettings();}
		checkLanguage();
		langLoad.loadLanguages();
		checkMissingDatabase();
		checkPics();
	}

	public static void checkMissingDatabase() {
		String path = mainPath + "hentai.xml";
		File file = new File(path);
		if (!file.exists()) {
			createMissingDatabase.createFile(path);
		}
	}

	public static Boolean checkSettingsFolder(){
		String path = mainPath + "settings.xml";
		File file = new File(path);
		if (!file.exists()) {
			createMissingSettings.createFile(path);
			return false;
		}
		return true;
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
		File file = new File(mainPath + "languages/");
		String[] languages = {"english", "engwishUwU", "hungarian"};
		String unableToDownload = "";
		if (!file.exists()) {
			file.mkdirs();
		}
		for (int i = 0; i < languages.length; i++) {
			File file2 = new File(file+"/" + languages[i] + ".xml");
			if (!file2.exists()) {
				try{
					URL url = new URL("https://raw.githubusercontent.com/DiamondPRO02/nsfw-game-manager/master/languages_doNotTouch/"+languages[i]+".xml");
					System.out.println(url);
					InputStream in = url.openStream();
					FileOutputStream fos = new FileOutputStream(file+"/" + languages[i] + ".xml");
					byte[] buffer = new byte[4096];
					int length;
					while ((length = in.read(buffer)) > 0) {
						fos.write(buffer, 0, length);
					}
					in.close();
					fos.close();
				} catch (Exception e) {
					unableToDownload = unableToDownload+"\nUnable to download: " + languages[i];
				}
			}
		}
		if (!unableToDownload.equals("")) {
			JOptionPane.showMessageDialog(null, "Error downloading language(s):\n" + unableToDownload + "\n>.< (checksFile.checkLanguage)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
