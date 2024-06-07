package backendThings.integrationCheck;

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
		success = checkFile(mainDirectory);
		if (!success) { return false; }
		for (String[] file : defaultValues.onlineFilesNeeded) {
			if (!checkLocalBeforeOnline(mainDirectory + "/" + file[0])) {
				// log.print("Downloading: " + file[0]);
				success = fileDownloader.downloadFile(file[1], mainDirectory + "/" + file[0]); 
				// log.print(success + " - " + file[0]);
				if (!success) { return false; }
			}
		}
		// log.print("*** All files are present! ***");
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
	private static boolean checkFile(File directoryPlace) {
		String[] fileName = defaultValues.filesNeeded;
		Boolean success = false;

		for (String file : fileName) {
			if (!new File(directoryPlace + "/" + file).exists()) {
				success = creatingDefaultDoc.copyFromLocal(directoryPlace + "/" + file, "Assets/default_" + file);
			} else if (file.equals("settings.json")) {
				success = creatingMissingSettings.creatingMissingSettingsHandler(defaultValues.settings, directoryPlace.toString());
			} else {
				success = true;
			}
			if (!success) { return false; }
		}

		return success;
	}
}
