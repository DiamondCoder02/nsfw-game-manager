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
		{"discord/discord_game_sdk.dll", "https://dl-game-sdk.discordapp.net/3.2.1/discord_game_sdk.zip"}
	};
	private static boolean success = false;

	public static boolean fileCheckingHandler(File mainDirectory) {
		for (String file : filesNeeded) { 
			System.out.println("Checking for file: " + file + "...");
			success = checkFile(mainDirectory, file); 
			if (!success) { return false; }
		}
		for (String[] file : onlineFilesNeeded) { 
			System.out.println("Checking for file: " + file[0] + "...");
			success = checkFile(mainDirectory, file); 
			if (!success) { return false; }
		}
		System.out.println("*** All files are present! ***");
		return true;
	}

	private static boolean checkFile(File mainDirectory, String fileName) {
		boolean doneWith = false;
		if (!new File(mainDirectory + "/" + fileName).exists()) {
			// TODO - This is stupid...
			switch (fileName) {
				case "settings.xml": doneWith = fileCreator.settingsCreate(mainDirectory); break;
				case "hentai.xml": doneWith = fileCreator.databaseCreate(mainDirectory); break;
			}
			return doneWith;
		} else {
			return true;
		}
	}

	private static boolean checkFile(File mainDirectory, String[] fileName) {
		// TODO - This need fix
		
		/*if (!new File(mainDirectory + "/" + fileName[0]).exists()) {
			try {
				URL url = new URL(fileName[1]);
				BufferedImage img = ImageIO.read(url);
				ImageIO.write(img, "png", new File(mainDirectory + "/" + fileName[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		*/
		return true;
	}
}
