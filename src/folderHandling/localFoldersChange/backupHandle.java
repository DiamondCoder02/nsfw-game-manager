package folderHandling.localFoldersChange;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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
		for (int dbN = 0; dbN < 5; dbN++) {
			// check backup folder
			if (!new File(mainDir + "/backup/hentai"+dbN).exists()) {
				new File(mainDir + "/backup/hentai"+dbN).mkdirs();
			}

			// delete files except the latest 10
			File[] files = new File(mainDir + "/backup/hentai"+dbN).listFiles();
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
			String backupFile = mainDir + "/backup/hentai"+dbN+"/backup_"+date+".xml";
			// copy main file to backup file
			try {
				// Files.copy(new File(mainDir + "/hentai.xml").toPath(), new File(backupFile).toPath());
				if (dbN == 0) {
					Files.copy(Paths.get(mainDir + "/hentai.xml"), Paths.get(backupFile));
				} else {
					Files.copy(Paths.get(mainDir + "/hentai"+dbN+".xml"), Paths.get(backupFile));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
