package folderHandle.checkAndBackup;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import main.mainInit;

public class backup {
	public static void doBackup() {
		String mainDataPath = mainInit.databasePath;
		String backupPath = checksFiles.mainPath+"backup";
		// check backup folder
		if (!new File(backupPath).exists()) {
			new File(backupPath).mkdirs();
		}

		// delete files except the latest 10
		File[] files = new File(backupPath).listFiles();
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
			}
		});
		int count = files.length - 9;
		if (count<0) { count = 0; }
		for (int i=0; i<count; i++) {
			// System.out.println("Deleted: " + files[i].getName());
			files[i].delete();
		}

		// create backup file with date and time
		String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String backupFile = backupPath+"/backup_"+date+".xml";
		// copy main file to backup file
		try {
			Files.copy(new File(mainDataPath).toPath(), new File(backupFile).toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
