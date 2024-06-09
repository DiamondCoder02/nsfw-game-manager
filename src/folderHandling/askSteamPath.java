package folderHandling;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backendThings.mainProgramStart;
import folderHandling.localFoldersChange.updateSettings;

// This needs to be rewritten later, but for now it's alright
public class askSteamPath {
	/**
	 * This function will ask the user to give the path to the steam directory.
	 * @return true if the user wants to quit or not give the path, false if the path was set
	 */
	public static boolean askBeforeInit() {
		if (mainProgramStart.steamDir == null) {
			JPanel panel = new JPanel(new GridLayout(5,1));
			JLabel label = new JLabel("Sorry, the program could not find the steam directory.");
			JLabel label1 = new JLabel("Important that this is full path to the installation directory.");
			JLabel label2 = new JLabel("Enter the path to the steam directory if you wish to use that feature.");
			JLabel label3 = new JLabel("Example: C:\\Program Files (x86)\\Steam\\steamapps");
			JTextField path = new JTextField(20);
			panel.add(label); panel.add(label1); panel.add(label2); panel.add(path); panel.add(label3);
			int result = JOptionPane.showConfirmDialog(label, path, "Add Steam location", JOptionPane.OK_CANCEL_OPTION);
			// Returns true, because user probably wants to quit and not give path
			if (result != JOptionPane.OK_OPTION) { return true; }
			if (path.getText().equals("") || path.getText().isBlank()) { return true; }
			return updateSteamInfoAboutFolder(path.getText());
		}
		return true;
	}

	/**
	 * This function will ask the user to change the path to the steam directory.
	 * No return value
	 */
	public static void changeSetting() {
		JPanel panel = new JPanel(new GridLayout(3,1));
		JLabel label = new JLabel("Enter the path to the steam directory:");
		JLabel label1 = new JLabel("Example: C:\\Program Files (x86)\\Steam\\steamapps");
		JTextField path = new JTextField(20);
		panel.add(label); panel.add(path); panel.add(label1);
		int result = JOptionPane.showConfirmDialog(label, panel, "Change Steam location", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			updateSteamInfoAboutFolder(path.getText());
		}
	}

	private static String testSteamFolder(String path){
		File file = new File(path + "/libraryfolders.vdf");
		if (!file.exists() && !file.isFile()) { return null; }
		if (!path.endsWith("/")) { path = path.substring(0, path.length()-1); }
		return path;
	}

	private static boolean updateSteamInfoAboutFolder(String path){
		String fullPath = testSteamFolder(path);
		mainProgramStart.steamDir = fullPath;
		return updateSettings.changeSetting(mainProgramStart.mainProgDir, "steamDir", fullPath);
	}
}
