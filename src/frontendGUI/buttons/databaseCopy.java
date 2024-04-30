package frontendGUI.buttons;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;

import folderHandling.ADocHandle;
import folderHandling.initialFileLoading.loadLanguage;
import integrationCheck.defaultValues;

public class databaseCopy {
	static String[] btn = loadLanguage.buton, fld = loadLanguage.folder;
	public static void saveFileCopy(){
		JFileChooser chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(fld[18]!=null?fld[18]:"Save file copy");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		// TODO - rewrite here everything
		
		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().toString();
			if (!path.endsWith(".xml")) { path = path+".xml"; }
			Document doc = ADocHandle.load(defaultValues.mainDirectory + "/hentai.xml");
			if (ADocHandle.save(doc, path)) {
				JOptionPane.showMessageDialog(null, "File saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			System.out.println("No Selection.");
			return;
		}
	}
}
