package folderHandling.localFoldersChange;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class backupHandle {
	// TODO remake for multiple database
	/**
	 * Create a backup of the main data file
	 */
	public static void doBackup(String mainDir) {
		// check backup folder
		if (!new File(mainDir + "/backup").exists()) {
			new File(mainDir + "/backup").mkdirs();
		}

		// delete files except the latest 10
		File[] files = new File(mainDir + "/backup").listFiles();
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
			}
		});
		int count = files.length - 9;
		if (count<0) { count = 0; }
		for (int i=0; i<count; i++) {
			files[i].delete();
		}

		// create backup file with date and time
		String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String backupFile = mainDir + "/backup/backup_"+date+".xml";
		// copy main file to backup file
		try {
			Files.copy(new File(mainDir + "/hentai.xml").toPath(), new File(backupFile).toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
