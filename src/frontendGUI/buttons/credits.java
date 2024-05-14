package frontendGUI.buttons;

import java.awt.Desktop;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;

public class credits {
	private static String br = "<br>";
	static String[] btn = loadLanguage.buton;
	public static void money(){
		String color;
		if (loadSettings.othersettings[1]) { color = "white"; } else { color = "black"; }
		String[][] logos = {
			{"Assets/Pics/DiamondNyanekoLogo.png", "400" , "88"}, // 2000x439
			{"Assets/Pics/KN_Logo.png", "300" , "140"}, // 1500x672
		};

		// "<html><img src='file:"+logos[0][0]+"' width="+logos[0][1]+" height="+logos[0][2]+"></img>"
		String textInside = "<html><img src='file:"+logos[0][0]+"' width="+logos[0][1]+" height="+logos[0][2]+"></img>"+br+
		"<p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+(btn[7]!=null?btn[7]:"Credits")+br+br+
		"Hi, I'm DiamondCoder or Diamond."+br+
		"This is a small project for learning Java in university."+br+
		"The main reason for this was to convert my old excel file into something more readeble and more usefull."+br+
		"Main focus is something dynamic and easy to use for hentai games."+br+
		"Thank you for using this program, it gives me smile I can make something good."+br+
		"Full open source:"+" <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager\">https://github.com/DiamondCoder02/nsfw-game-manager</a></font>"+br+br+
		"Also, huge thanks to Nyaneko who made graphic elements and helped me finish writing this program. <3"+br+
		"<html><img src='file:"+logos[1][0]+"' width="+logos[1][1]+" height="+logos[1][2]+"></img>";

		// Small popup with credits
		JEditorPane ep = new JEditorPane();
		ep.setContentType("text/html");
		Font font = new Font("Arial", Font.PLAIN, 20);
		ep.setFont(font);
		ep.setText(textInside);
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
		ep.setOpaque(loadSettings.othersettings[1] ? false : true);
		JOptionPane.showMessageDialog(null, ep, btn[7]!=null?btn[7]:"Credit", JOptionPane.INFORMATION_MESSAGE);
	}
}
