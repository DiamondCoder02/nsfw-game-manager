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
