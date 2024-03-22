package folderHandle.checkAndBackup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import main.langLoad;
import main.mainInit;

public class checksFiles {
	private static String mainPath = mainInit.mainPath;
	public static void checks() {
		
		checkLanguage();
		langLoad.loadLanguages();
		checkPics();
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
					URL url = new URL("https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/" + picturesThatGets[i]);
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
				URL url = new URL("https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/doNotTouch/language.csv");
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
