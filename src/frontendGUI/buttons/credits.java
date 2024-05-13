package frontendGUI.buttons;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
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
		ImageIcon icon = new ImageIcon(new File("../Assets/Pics/DiamondCoderLogo.png").getAbsolutePath());

		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(100, 300,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		Boolean[] boolSettings = loadSettings.othersettings;
		String color;
		if (boolSettings[1]) { color = "white"; } else { color = "black"; }

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
		"Full open source:"+" <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager\">https://github.com/DiamondCoder02/nsfw-game-manager</a></font>"+br);
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
		JOptionPane.showMessageDialog(null, ep, btn[7]!=null?btn[7]:"Credit", JOptionPane.INFORMATION_MESSAGE, icon);
	}
}
