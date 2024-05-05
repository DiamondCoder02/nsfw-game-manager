package integrationCheck;

import java.io.File;

import folderHandling.integCheck.creatingDefaultDoc;
import folderHandling.integCheck.creatingMissingSettings;

public class fileChecker {
	private static boolean success = false;

	/**
	 * This function will check if all the needed files are present in the main directory.
	 * @param mainDirectory - The main directory of the program.
	 * @return boolean - returns true if all the files are present.
	 */
	public static boolean fileCheckingHandler(File mainDirectory) {
		for (String file : defaultValues.filesNeeded) { 
			success = checkFile(mainDirectory, file); 
			// System.out.println(success + " - " + file);
			if (!success) { return false; }
		}
		for (String[] file : defaultValues.onlineFilesNeeded) {
			if (!checkLocalBeforeOnline(mainDirectory + "/" + file[0])) {
				// System.out.println("Downloading: " + file[0]);
				success = fileDownloader.downloadFile(file[1], mainDirectory + "/" + file[0]); 
				// System.out.println(success + " - " + file[0]);
				if (!success) { return false; }
			}
		}
		// System.out.println("*** All files are present! ***");
		return true;
	}

	/**
	 * This function will check if the local directory is present before downloading the online file.
	 * @param localDir - The local directory to check.
	 * @return boolean - returns true if the local directory is present.
	 */
	private static boolean checkLocalBeforeOnline(String localDir) {
		if (!new File(localDir).exists()) {
			return false;
		}
		return true;
	}

	/**
	 * This function will check if the file is present in the directory and if not, create it.
	 * @param directoryPlace - The directory to check.
	 * @param fileName - The file name to check.
	 * @return boolean - returns true if the file is present.
	 */
	private static boolean checkFile(File directoryPlace, String fileName) {
		if (!new File(directoryPlace + "/" + fileName).exists()) {
			switch (fileName) {
				case "settings.json": return creatingDefaultDoc.createJsonSettings(defaultValues.settings, directoryPlace + "/" + fileName);
				case "hentai.xml": return creatingDefaultDoc.createDatabase(directoryPlace + "/" + fileName, "source", defaultValues.games);
				default: return false;
			}
		} else if (fileName.equals("settings.json")) {
			return creatingMissingSettings.creatingMissingSettingsHandler(defaultValues.settings, directoryPlace.toString());
		} else {
			return true;
		}
	}
}
