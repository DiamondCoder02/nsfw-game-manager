package frontendGUI.buttons;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import folderHandling.localFoldersChange.changeSettings;

public class gameFolderLocation {
	public static void gamesLocationChoose() {
		JFileChooser chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select game info folder location");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().toString();
			if (!path.endsWith("\\")) { path = path+"\\"; }
			changeSettings.changeSetting("folderLocation", path);
			JOptionPane.showMessageDialog(null, "Changes saved!" + "\n"+path, "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		else { return; }
	}
}
