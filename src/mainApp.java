import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

import backendThings.log;
import backendThings.mainProgramStart;
import folderHandling.initialFileLoading.loadSettings;
public class mainApp {
	private static String tempDir;
	/**
	 * This function will start the program.
	 * @param args -
	 * @throws IOException -
	 * @throws InterruptedException -
	 * @throws URISyntaxException -
	 */
	public static void main (String [] args){
		if (!checkOS()) {
			log.print("OS not supported!", log.ERROR);
			JOptionPane.showMessageDialog(null, "OS not supported! Quiting!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		Boolean consoleNeeded = false;
		if (loadSettings.load(tempDir)) { consoleNeeded = loadSettings.othersettings[5]; }
		if (consoleNeeded) { 
			log.frameLog();
			log.print("- Console enabled! -");
		} else { 
			log.print("- No console needed! -"); 
		}
		log.print("TEST, no error level given");
		log.print("TEST", log.INFO);
		log.print("TEST", log.WARNING);
		log.print("TEST", log.ERROR);
		log.print("This is just a test for logging, the format is: [yyyy-MM-dd HH:mm:ss.SSS] [Info/Warning/Error] Message");
		log.print("Example: [2021-08-01 12:00:00.000] [Info] This is a test message");
		log.print("");

		log.print("--- Starting program ---");
		log.print("Main: "+mainProgramStart.mainProgDir);
		log.print("Steam: "+mainProgramStart.steamDir);
		log.print("--- OS checked ---");
		mainProgramStart.mainMain();
    }

	/**
	 * This function will check the OS and set the directories.
	 * @return true if the OS is known, false if unknown
	 */
	private static Boolean checkOS(){
		String sysOS = System.getProperty("os.name").toLowerCase();
		log.print("OS: "+sysOS);
		if (sysOS.contains("win")) {
			mainProgramStart.mainProgDir = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager";
			tempDir = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager";
			mainProgramStart.steamDir = System.getenv("ProgramFiles(x86)") + "/Steam/steamapps";
			return true;
		// TODO - Test needed
		} else if (sysOS.contains("nix") || sysOS.contains("nux") || sysOS.contains("aix")) {
			mainProgramStart.mainProgDir = System.getenv("HOME") + "/DiamondCoder/nsfwGameManager";
			mainProgramStart.steamDir = System.getenv("HOME") + "/.steam/steam/steamapps";
			return true;
		/*
		} else if (sysOS.contains("mac")) {
			mainProgramStart.mainProgDir = System.getenv("???") + "/DiamondCoder/nsfwGameManager";
			mainProgramStart.steamDir = System.getenv("???") + "/Steam/steamapps";
			return true;
		*/
		} else { return false; }
	}
}