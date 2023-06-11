package windowJframe;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import xmlFolderHandle.saveLoadDoc;

public class otherButtonsThingies {
	public static void saveFileCopy(){
		JFileChooser chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Save file copy");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

			String path = chooser.getSelectedFile().toString();
			if (!path.endsWith(".xml")) { path = path+".xml"; }
			saveLoadDoc.saveADocument(path);
		}
		else {
			System.out.println("No Selection ");
			return;
		}
	}


	public static void FACKQU(){ // TODO faq
		JOptionPane.showMessageDialog(null, "FAQ\n\n"+
		"Q: What is this?\nA: A simple excel like hentai game manager.\n\n"+
		"Q: Why this exist?\nA: Because I had enough managing my games in an excel table and wanted something better.\n\n"+
		"Q: Where the data is stored?\nA: Everything is saved at: C:\\Users\\{name}\\AppData\\Roaming\\DiamondCoder\\nsfwGameManager\\hentai.xml\n"+
		"You can also save a copy under \"Games\" => \"Save file copy\" button. (This does not change where it continues to save.)\n\n"+
		"Q: Features?\nA: -----*Currently:*-----\n"+
		"- Add, store, update, remove game infos manually\n"+
		"- DarkMode\n"+
		"- Colorful table\n"+
		"- F95zone, so only ID needed to add game\n"+
		"-----*Plans:*-----\n"+
		"- Dlsite so only ID needed\n"+
		"- (Far future) If I can, I will also add to download/detect what games are downloaded\n"+
		"\n"+
		"Q: Support?\nA: Patreon: https://www.patreon.com/DiamondCoder\n\n"+
		"Q: Can I help code / Error in the program?\nA: All isssue and help is accepted on github: https://github.com/DiamondPRO02/nsfw-game-manager\n\n"+
		"Q: placeholder?\nA: placeholder!\n\n",
		"Frequently Asked Questions", 
		JOptionPane.INFORMATION_MESSAGE);
	}

	public static void money(){
		JOptionPane.showMessageDialog(null, 
		"Credits\n\n"+
		"Hi, I'm Diamond.\n"+
		"This is a small project after learning Java in university.\n"+
		"The main of this was to convert my old excel file into something more readeble.\n"+
		"Main focus is something dynamic and easy to use for hentai games.\n\n"+
		"Thank you for using this program.\n\n"+
		"Full open source: https://github.com/DiamondPRO02/nsfw-game-manager\n"+
		"Support me on Patreon: https://www.patreon.com/DiamondCoder",
		"Credit", 
		JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sureAboutExit(){
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}
