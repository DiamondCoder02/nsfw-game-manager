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
		if (boolSettings[1]) { color = "white"; } else { color = "black"; }
		JEditorPane ep = new JEditorPane();
		ep.setContentType("text/html");
		ep.setText("<p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+(fld[19]!=null?fld[19]:"FAQ")+br+br+
		"Q: What is this?"+br+"A: A simple excel like hentai game manager."+br+br+
		"Q: Why this exist?"+br+"A: Because I had enough managing my games in an excel table and wanted something better."+br+br+
		"Q: What can this program do?"+br+" Check out the wiki for all the possibilities: <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/wiki\">nsfw game manager wiki</a></font>"+br+br+
		"Q: Where the data is stored?"+br+"A: Everything is saved at:"+" C:\\Users\\{name}\\AppData\\Roaming\\DiamondCoder\\nsfwGameManager\\hentai.xml"+br+br+
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
		ep.setOpaque(boolSettings[1] ? false : true);
		JOptionPane.showMessageDialog(null, ep, fld[19]!=null?fld[19]:"Frequently Asked Questions", JOptionPane.INFORMATION_MESSAGE);
	}
}
