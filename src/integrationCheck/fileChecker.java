package integrationCheck;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class fileChecker {
	private static String[] filesNeeded = {"settings.xml", "hentai.xml"};
	private static String[][] onlineFilesNeeded = {
		{"language.csv", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/language.csv"},
		{"pics/creditLogo.png", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/creditLogo.png"},
		{"pics/nyaaa.png", "https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/nyaaa.png"},
		{"discord_game_sdk.dll", "https://dl-game-sdk.discordapp.net/3.2.1/discord_game_sdk.zip"}
	};
	private static boolean success = false;

	public static boolean fileCheckingHandler(File mainDirectory) {
		for (String file : filesNeeded) { checkFile(mainDirectory, file); }
		for (String[] file : onlineFilesNeeded) { checkFile(mainDirectory, file); }
		return success;
	}

	private static void checkFile(File mainDirectory, String fileName) {
		if (!new File(mainDirectory + "/" + fileName).exists()) {
			// TODO - This is stupid...
			switch (fileName) {
				case "settings.xml":
					success = fileCreator.settingsCreate();
					break;
				case "hentai.xml":
					success = fileCreator.databaseCreate();
					break;
			}
		}
	}

	private static void checkFile(File mainDirectory, String[] fileName) {
		if (!new File(mainDirectory + "/" + fileName[0]).exists()) {
			try {
				URL url = new URL(fileName[1]);
				BufferedImage img = ImageIO.read(url);
				ImageIO.write(img, "png", new File(mainDirectory + "/" + fileName[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
