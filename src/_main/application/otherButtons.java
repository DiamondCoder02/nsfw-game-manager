package _main.application;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import _folderHandle.loadSaveGamesSettings.saveLoadDoc;
import _main.langLoad;
import _main.mainInit;

public class otherButtons {
	private static String br = "<br>";
	static String[] btn = langLoad.buton, fld = langLoad.folder;
	public static void saveFileCopy(){
		JFileChooser chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(fld[18]!=null?fld[18]:"Save file copy");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().toString();
			if (!path.endsWith(".xml")) { path = path+".xml"; }
			saveLoadDoc.saveDocument(saveLoadDoc.loadDocument(mainInit.databasePath), path);
		}
		else {
			System.out.println("No Selection.");
			return;
		}
	}
	
	public static void FACKQU(){
		Boolean[] boolSettings = _folderHandle.loadSaveGamesSettings.loadSettingsFromXml.loadBooleanSettings("othersettings");
		String color;
		if (boolSettings[0]) { color = "white"; } else { color = "black"; }
		JEditorPane ep = new JEditorPane();
		ep.setContentType("text/html");
		ep.setText("<p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+(fld[19]!=null?fld[19]:"FAQ")+br+br+
		"Q: What is this?"+br+"A: A simple excel like hentai game manager."+br+br+
		"Q: Why this exist?"+br+"A: Because I had enough managing my games in an excel table and wanted something better."+br+br+
		"Q: Where the data is stored?"+br+"A: Everything is saved at:"+" C:\\Users\\{name}\\AppData\\Roaming\\DiamondCoder\\nsfwGameManager\\hentai.xml"+br+
		"You can also save a copy under \"Games\" => \"Save file copy\" button. (This does not change where it continues to save.)"+br+br+
		"Q: Features?"+br+"A: -----*Currently:*-----"+br+
		"- Add, store, update, remove game infos manually"+br+
		"- DarkMode"+br+
		"- Colorful table"+br+
		"- Search function"+br+
		"- Sorting"+br+
		"- Random game chooser"+br+
		"- After selecting a folder it will check for games and automatically update table( details on Github)"+br+
		"- F95zone, so only ID needed to add, update, remove game"+br+
		"-----*Plans:*-----"+br+
		"- Dlsite so only ID needed"+br+
		"- (Far future) If I can, I will also add to download / autoupdate games"+br+br+
		"Q: Support the project?"+br+"A: Patreon:"+" <font color = 64AFFF><a href=\"https://www.patreon.com/DiamondCoder\">https://www.patreon.com/DiamondCoder</a></font>"+br+
		"or on Github:"+" <font color = 64AFFF><a href=\"https://github.com/sponsors/DiamondCoder02\">https://github.com/sponsors/DiamondCoder02</a></font>"+br+br+
		"Q: Can I help code / Error in the program?"+br+"A: All isssue and help is accepted on GitHub:"+" <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager\">https://github.com/DiamondCoder02/nsfw-game-manager</a></font>"+br+br+
		"Q: placeholder?"+br+"A: placeholder!"+
		"</span></p>");
		ep.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				Desktop desktop = Desktop.getDesktop();
				if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)){
					try {
						desktop.browse(e.getURL().toURI());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		ep.setEditable(false);
		ep.setOpaque(boolSettings[0] ? false : true);
		JOptionPane.showMessageDialog(null, ep, fld[19]!=null?fld[19]:"Frequently Asked Questions", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void money(){
		String icons = (System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager\\pics\\creditLogo.png");
		ImageIcon icon = new ImageIcon(icons);
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(43*4, 120*4,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		Boolean[] boolSettings = _folderHandle.loadSaveGamesSettings.loadSettingsFromXml.loadBooleanSettings("othersettings");
		String color;
		if (boolSettings[0]) { color = "white"; } else { color = "black"; }

		JEditorPane ep = new JEditorPane();
		ep.setContentType("text/html");
		Font font = new Font("Arial", Font.PLAIN, 20);
		ep.setFont(font);
		ep.setText("<p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+(btn[7]!=null?btn[7]:"Credits")+br+br+
		"Hi, I'm DiamondCoder or Diamond."+br+
		"This is a small project for learning Java in university."+br+
		"The main reason for this was to convert my old excel file into something more readeble and more usefull."+br+
		"Main focus is something dynamic and easy to use for hentai games."+br+br+
		"Thank you for using this program, it gives me smile I can make something good."+br+br+
		"Full open source:"+" <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager\">https://github.com/DiamondCoder02/nsfw-game-manager</a></font>"+br+
		"Support me on Github or on Patreon:"+" <font color = 64AFFF><a href=\"https://www.patreon.com/DiamondCoder\">https://www.patreon.com/DiamondCoder</a></font></span></p>"+br);
		ep.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				Desktop desktop = Desktop.getDesktop();
				if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)){
					try {
						desktop.browse(e.getURL().toURI());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		ep.setEditable(false);
		ep.setOpaque(boolSettings[0] ? false : true);
		JOptionPane.showMessageDialog(null, ep, btn[7]!=null?btn[7]:"Credit", JOptionPane.INFORMATION_MESSAGE, icon);
	}

	public static void sureAboutExit(){
		int option = JOptionPane.showConfirmDialog(null, fld[20]!=null?fld[20]:"Are you sure you want to exit?", btn[8]!=null?btn[8]:"Exit", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}
