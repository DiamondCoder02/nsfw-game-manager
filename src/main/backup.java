package main;

import java.io.File;
import java.util.Arrays;

public class backup {
	public static void doBackup() {
		String mainDataPath = mainInit.databasePath;
		String backupPath = checksFile.mainPath+"backup";
		// check backup folder
		if (!new File(backupPath).exists()) {
			new File(backupPath).mkdirs();
		}
		// create backup file with date and time
		String date = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
		String backupFile = backupPath+"/backup_"+date+".xml";
		// copy main file to backup file
		try {
			java.nio.file.Files.copy(new File(mainDataPath).toPath(), new File(backupFile).toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// keep only the last 10 backups
		File[] files = new File(backupPath).listFiles();
		Arrays.sort(files, new java.util.Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
			}
		});
	}
}
