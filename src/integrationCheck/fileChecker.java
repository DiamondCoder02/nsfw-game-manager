package integrationCheck;

import java.io.File;
import folderHandling.creatingDefaultDoc;

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
		boolean doneWith = false;
		if (!new File(directoryPlace + "/" + fileName).exists()) {
			// System.out.println("Creating: " + fileName);
			// TODO - This is stupid...
			switch (fileName) {
				case "settings.xml": doneWith = creatingDefaultDoc.creatingDocHandler(directoryPlace + "/" + fileName, "settings", defaultValues.settings); break;
				case "hentai.xml": doneWith = creatingDefaultDoc.creatingDocHandler(directoryPlace + "/" + fileName, "source", defaultValues.games); break;
			}
			return doneWith;
		} else {
			return true;
		}
	}
}
