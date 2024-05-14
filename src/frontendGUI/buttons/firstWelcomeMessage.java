package frontendGUI.buttons;

import java.awt.Desktop;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.changeSettings;

public class firstWelcomeMessage {
	private static String br = "<br>";
	public static void welcomeMessage(String mainDir) {
		String color;
		if (loadSettings.othersettings[1]) { color = "white"; } else { color = "black"; }
		String[] logo = {"Assets/Pics/KN_Logo.png", "300" , "140"}; // 1500x672

		// "<html><img src='file:"+logos[0][0]+"' width="+logos[0][1]+"height="+logos[0][2]+"></img>"
		String textInside = "<html><p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"
			+ "Welcome to your very own Hentai database :3" + br + br
			+ "The program is very simple and easy to use but you might want to check the wiki for the first time." + br
			+ "It lists every important features and info about the program." + br
			+ "<font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/wiki/Features\">(Wiki link)</a></font>" + br + br
			+ "The program works but still in the early access stage so we try to improve it." + br
			+ "Every help and feedbacks makes this program better." + br + br
			+ "If you encounter an issue please provide feedback here: <font color = 64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/issues/new/choose\">(Github link)</a></font>" + br + br
			+ "Have fun :3" + br
			+ "<html><img src='file:"+logo[0]+"' width="+logo[1]+" height="+logo[2]+"></img>"+ br + br
			+ "Created by DiamondCoder and NyanekoNNK" ;

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
				if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
					try { desktop.browse(e.getURL().toURI());
					} catch (Exception ex) { ex.printStackTrace(); }
				}
			}
		});
		ep.setEditable(false);
		ep.setOpaque(loadSettings.othersettings[1] ? false : true);
		changeSettings.setSetting(mainDir, "othersettings", "firstTimeRunDone", true);
		JOptionPane.showMessageDialog(null, ep, "Welcome!", JOptionPane.INFORMATION_MESSAGE);
	}
}
