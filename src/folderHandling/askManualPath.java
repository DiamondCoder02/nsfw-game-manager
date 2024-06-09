package folderHandling;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backendThings.mainProgramStart;
import folderHandling.localFoldersChange.updateSettings;

public class askManualPath {
	/**
	 * This function will ask the user to give the path to the steam directory.
	 * @return true if the user wants to quit or not give the path, false if the path was set
	 */
	public static boolean askManual() {
		if (mainProgramStart.steamDir == null) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Sorry, the program could not find the steam directory.");
			JLabel label1 = new JLabel("Important that this is full path to the installation directory.");
			JLabel label2 = new JLabel("Enter the path to the steam directory if you wish to use that feature.");
			JLabel label3 = new JLabel("Example: C:\\Program Files (x86)\\Steam\\steamapps");
			JTextField path = new JTextField(20);
			panel.add(label); panel.add(label1); panel.add(label2); panel.add(path); panel.add(label3);
			int result = JOptionPane.showConfirmDialog(label, path, null, JOptionPane.OK_CANCEL_OPTION);
			// Returns true, because user probably wants to quit and not give path
			if (result != JOptionPane.OK_OPTION) { return true; }
			
			File file = new File(path.getText() + "/libraryfolders.vdf");
			if (!file.exists() && !file.isFile()) { return false; }
			String fullPath = path.getText();
			if (!fullPath.endsWith("/")) { fullPath = fullPath.substring(0, fullPath.length()-1); }
			mainProgramStart.steamDir = fullPath;
			updateSettings.changeSetting(mainProgramStart.mainProgDir, "steamDir", fullPath);
			return true;
		}
		return true;
	}
}
