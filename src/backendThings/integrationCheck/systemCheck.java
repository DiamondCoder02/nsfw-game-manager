package backendThings.integrationCheck;

import java.io.File;

import javax.swing.JOptionPane;

public class systemCheck {
	/**
	 * This function will check if the system is ready to run the program.
	 * @param mainDir - The main directory of the program.
	 * @return boolean - returns true if the system is ready.
	 * @see #mainDirectoryCheck(File)
	 * @see #foldersCheck(String)
	 * @see fileChecker#fileCheckingHandler(File)
	 * @implSpec This function will check if the main directory exists, if the folders exist and if the files exist.
	 */
	public static boolean programSystemCheck(String mainDir) {
		File mainDirectory = new File(mainDir);
		if (mainDirectoryCheck(mainDirectory)) {
			if (foldersCheck(mainDir)) {
				if (fileChecker.fileCheckingHandler(mainDirectory)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function will check if the folders exist.
	 * @param mainDirectory - The main directory of the program. 
	 * @return boolean - returns true if the folders exist.
	 */
	private static boolean foldersCheck(String mainDirectory) {
		File discord = new File(mainDirectory + "/discord");
		if (!new File(mainDirectory + "/backup").exists()) { try { new File(mainDirectory + "/backup").mkdirs(); } catch (Exception e) { return false; } }
		if (!discord.exists()) { try { discord.mkdirs(); } catch (Exception e) { return false; } }
		return true;
	}

	/**
	 * Check if the main folder in AppData exists
	 * @param mainDirectory - The main directory of the program.
	 * @return boolean - returns true if the main directory exists.
	 */
	private static boolean mainDirectoryCheck(File mainDirectory) {
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
