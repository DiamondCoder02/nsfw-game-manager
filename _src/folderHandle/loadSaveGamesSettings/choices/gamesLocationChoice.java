package folderHandle.loadSaveGamesSettings.choices;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;

import main.mainInit;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;

public class gamesLocationChoice {
	public static void gamesLocationChoose(Document dom) {
		JFileChooser chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select game info folder location");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().toString();
			if (!path.endsWith("\\")) { path = path+"\\"; }
			dom.getElementsByTagName("folderLocation").item(0).setTextContent(path);
			saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
			JOptionPane.showMessageDialog(null, "Changes saved!" + "\n"+path, "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		else { return; }
	}
}
