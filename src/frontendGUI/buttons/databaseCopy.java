package frontendGUI.buttons;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.Document;

import backendThings.log;
import folderHandling.ADocHandle;
import folderHandling.initialFileLoading.loadLanguage;
public class databaseCopy {
	static String[] btn = loadLanguage.buton, fld = loadLanguage.folder;
	public static void saveFileCopy(String mainDir){
		JFileChooser chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(fld[18]!=null?fld[18]:"Save file copy");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("XML file", "xml"));

		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().toString();
			if (!path.endsWith(".xml")) { path = path+".xml"; }
			Document doc = ADocHandle.load(mainDir);
			if (ADocHandle.save(doc, path)) {
				JOptionPane.showMessageDialog(null, "File saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			log.print("No Selection for saving file copy.");
			return;
		}
	}
}
