package hgm_backend.src.fileHandler;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class checkSteamOS {
	/**	
	 * @return Type of OS the app runs on
	 * @example Windows, Linux, Mac
	 */ 
	public static String osType = null;
	/**	
	 * @return location of the dafault Steam libraryfolders.vdf
	 * @example C:\Program Files (x86)\Steam\steamapps
	 */ 
	public static String steamappLocation = null;

	/**
	 * This function will check the OS and set the directories.
	 * @return true if the OS is known, false if unknown
	 * @see steamappLocation
	 */
	public static Boolean checkForOS(){
		String sysOS = System.getProperty("os.name").toLowerCase();
		if (sysOS.contains("win")) {
			steamappLocation = System.getenv("ProgramFiles(x86)") + "/Steam/steamapps";
			osType = "Windows";
			return true;
		} else if (sysOS.contains("nix") || sysOS.contains("nux") || sysOS.contains("aix")) {
			steamappLocation = System.getenv("HOME") + "/.steam/steam/steamapps";
			osType = "Linux";
			return true;
		// TODO Mac support for storage (problem: no idea how path works)
		// osType = "Linux";
		} else { return false; }
	}

	/**
	 * If there is a missing path, ask for it.
	 * @return true if saved
	 */
	public static Boolean askForPath(Boolean steamLocate){
		JPanel panel = new JPanel(new GridLayout(3,1));
		JLabel warningLabel = new JLabel(":3");

		JTextField steamPath = null;

		if (steamLocate) {
			JLabel label = new JLabel("Enter the path to the steam directory:");
			JLabel label1 = new JLabel("Example: C:\\Program Files (x86)\\Steam\\steamapps");
			JLabel label2 = new JLabel("Important that the folder includes the \"libraryfolders.vdf\" file");
			steamPath = new JTextField(20);
			panel.add(label); panel.add(steamPath); panel.add(label1); panel.add(label2);
		}
		int result = JOptionPane.showConfirmDialog(warningLabel, panel, "Change path locations", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			saveConfig.setConfig("steamDir", steamPath.getText(), saveConfig.str);
			// TODO check checking the checked path
			return true;
		} else return false;
	}
}
