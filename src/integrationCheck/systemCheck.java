package integrationCheck;

import java.io.File;

import javax.swing.JOptionPane;

public class systemCheck {
	// TODO - This is probably the only reason it will not work on Linux (NEED TEST)
	private static File mainDirectory = new File(System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager");
	/*
	 * Folder structure:
	 * AppData\Roaming\DiamondCoder\nsfwGameManager\
	 *  - backup\
	 *  - - backup1.xml
	 *  - discord\
	 *  - - discord_game_sdk.dll
	 * 	- pics\
	 *  - - creditLogo.png
	 *  - - nyaaa.png
	 * 	- settings.xml
	 * 	- hentai.xml
	 * 	- language.csv
	 */
	public static boolean programSystemCheck() {
		if (mainDirectoryCheck()) {
			if (foldersCheck()) {
				if (fileChecker.fileCheckingHandler(mainDirectory)) {
					return true;
				}
			}
		}
		return false;
	}

	// Check if the folders exist
	private static boolean foldersCheck() {
		File discord = new File(mainDirectory + "/discord");
		File pics = new File(mainDirectory + "/pics");
		if (!new File(mainDirectory + "/backup").exists()) { try { new File(mainDirectory + "/backup").mkdirs(); } catch (Exception e) { return false; } }
		if (!discord.exists()) { try { discord.mkdirs(); } catch (Exception e) { return false; } }
		if (!pics.exists()) { try { pics.mkdirs(); } catch (Exception e) { return false; } }
		return true;
	}

	// Check if the main folder in AppData exists
	private static boolean mainDirectoryCheck() {
		if (!mainDirectory.exists()) {
			try {
				mainDirectory.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error creating main folder!", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
}
