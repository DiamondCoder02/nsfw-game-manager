package frontendGUI.buttons;

import java.awt.Desktop;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;

public class faq {
	private static String br = "<br>";
	static String[] fld = loadLanguage.folder;
	public static void FACKQU(){
		Boolean[] boolSettings = loadSettings.othersettings;
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
		"Q: Support the project?"+br+"A: Currently not available"+br+br+
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
}
