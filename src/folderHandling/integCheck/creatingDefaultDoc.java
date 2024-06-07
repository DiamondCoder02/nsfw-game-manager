package folderHandling.integCheck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class creatingDefaultDoc {
	/*
	 * Special char:
	 * & &amp;
	 * < &lt;
	 * > &gt;
	 * "" &quot;
	 * '' &apos;
	 * <name>John &amp; Doe</name>
	*/

	/**
	 * Create a database file
	 * @param directoryPath - The directory where the database file will be saved **with** the file name or extension
	 * @param assetPath - The path to the asset file
	 * @return boolean - returns true if the file was created successfully
	 */
	public static boolean copyFromLocal(String directoryPath, String assetPath) {
		try {
			// assetPath folder is next to the jar or exe
			String path = new File(".").getCanonicalPath();
			File file = new File(path +"/"+ assetPath);
			Files.copy(file.toPath(), new File(directoryPath).toPath());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
