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
		String textInside = "<html><p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+
		"<img src='file:"+logos[1][0]+"' width="+logos[1][1]+" height="+logos[1][2]+"></img>"+br+

		"Originally: This was a small project to learn Java for university."+br+
		"Main focus is something dynamic and easy to use for hentai games."+br+
		"This whole program is just to convert my old excel file into a smart storage"+br+
		"Full open source:"+" <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager\">https://github.com/DiamondCoder02/nsfw-game-manager</a></font>"+br+
		"----------------------------------------------------------------"+br+br+ // 64

		"Hi, I'm DiamondCoder or Diamond."+br+
		"University student, programer, stupid gamer"+br+
		"I started the project in 2023, handling Steam, check issues, still coding and arguing with Nyaneko why the UI looks from 2008"+br+
		"Thank you for using this program, it gives me smile I can make something for others :3"+br+
		"Huge thanks to Nyaneko who made graphic elements and helped me mentally and pushed me to work more on this program."+br+

		"<img src='file:"+logos[0][0]+"' width="+logos[0][1]+" height="+logos[0][2]+"></img>"+br+ // Picture as break

		"Hi, I'm NyanekoNNK or Nyaneko."+br+
		"Graphic designer and I'm here to help Diamond with this project."+br+
		"I'm responsible some graphic elements, the Steam store, legal matters and quality control."+br+
		"I can and will break everything so I will make sure there are close to no bugs in the application"+br+br+

		"----------------------------------------------------------------"+br+ // 64
		"Contact us:"+br+
		"Support Email: KnockyNekos@gmail.com"+br+
		"Github Issues:	<font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/issues/new/choose\">Github Feedback</a></font>"+br+
		"Github Wiki: <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/wiki/\">Github Wiki</a></font>"+br

		/*
		Support Email:  	KnockyNekos@gmail.com
		Github Issues:		https://github.com/DiamondCoder02/nsfw-game-manager/issues/new/choose
		Github Wiki: 		https://github.com/DiamondCoder02/nsfw-game-manager/wiki/
		*/
		;

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
