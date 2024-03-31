package integrationCheck;

import java.io.File;
import folderHandling.creatingDefaultDoc;

public class fileChecker {
	private static boolean success = false;

	public static boolean fileCheckingHandler(File mainDirectory) {
		for (String file : defaultValues.filesNeeded) { 
			success = checkFile(mainDirectory, file); 
			if (!success) { return false; }
		}
		for (String[] file : defaultValues.onlineFilesNeeded) {
			success = checkFile(mainDirectory, file); 
			if (!success) { return false; }
		}
		System.out.println("*** All files are present! ***");
		return true;
	}

	private static boolean checkFile(File directoryPlace, String fileName) {
		boolean doneWith = false;
		if (!new File(directoryPlace + "/" + fileName).exists()) {
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

	private static boolean checkFile(File mainDirectory, String[] file) {
		// TODO - This is stupid...
		
	}
}
