package integrationCheck;

import java.io.File;
import folderHandling.creatingDefaultDoc;
import folderHandling.creatingMissingSettings;

public class fileChecker {
	private static boolean success = false;

	public static boolean fileCheckingHandler(File mainDirectory) {
		for (String file : defaultValues.filesNeeded) { 
			success = checkFile(mainDirectory, file); 
			// System.out.println(success + " - " + file);
			if (!success) { return false; }
		}
		for (String[] file : defaultValues.onlineFilesNeeded) {
			success = fileDownloader.downloadFile(file[1], mainDirectory + "/" + file[0]); 
			// System.out.println(success + " - " + file[0]);
			if (!success) { return false; }
		}
		// System.out.println("*** All files are present! ***");
		return true;
	}

	private static boolean checkFile(File directoryPlace, String fileName) {
		if (!new File(directoryPlace + "/" + fileName).exists()) {
			// System.out.println("Creating: " + fileName);
			// TODO - This is stupid...
			switch (fileName) {
				case "settings.xml": return creatingDefaultDoc.creatingDocHandler(directoryPlace + "/" + fileName, "settings", defaultValues.settings);
				case "hentai.xml": return creatingDefaultDoc.creatingDocHandler(directoryPlace + "/" + fileName, "source", defaultValues.games);
				default: return false;
			}
		} else if (fileName.equals("settings.xml")) {
			// TODO - check the settings file and update if some values are missing
			return creatingMissingSettings.creatingMissingSettingsHandler(directoryPlace + "/" + fileName, "settings", defaultValues.settings);
		} else {
			return false;
		}
	}
}
