package frontendGUI.buttons;

import java.awt.Desktop;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.updateSettings;

public class firstWelcomeMessage {
	private static String br = "<br>";
	public static void welcomeMessage(String mainDir) {
		String color;
		if (loadSettings.othersettings[1]) { color = "white"; } else { color = "black"; }
		String[] logo = {"Assets/Pics/KN_Logo.png", "300" , "140"}; // 1500x672

		// "<html><img src='file:"+logos[0][0]+"' width="+logos[0][1]+"height="+logos[0][2]+"></img>"
		// <font color = #64AFFF><a href=\"***\">***</a></font>
		String textInside = "<html><p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"
			+ "Welcome to your very own Hentai database :3" + br + br
			+ "The program is very simple and easy to use but you might want to check the wiki for the first time." + br
			+ "It lists important features and info about the program." + br
			+ "<font color = #64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/wiki/Features\">(Wiki link)</a></font>" + br + br
			+ "The program works but still in the early access stage so we try to improve it." + br
			+ "Every help and feedbacks makes this program better." + br + br
			+ "If you encounter an issue please provide feedback here: <font color = #64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/issues/new/choose\">(Github link)</a></font>" + br + br
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
		updateSettings.setSetting(mainDir, "othersettings", "firstTimeRunDone", true);
		JOptionPane.showMessageDialog(null, ep, "Welcome! (1/3)", JOptionPane.INFORMATION_MESSAGE);

		String[][] logo2 = {
			{"Assets/Pics/welcome/trackedInfo.png", "850" , "45"}, // 1717x91
			{"Assets/Pics/welcome/databases.png", "200" , "155"} // 405x323
		};

		// "<html><img src='file:"+logos[0][0]+"' width="+logos[0][1]+"height="+logos[0][2]+"></img>"
		String textInside2 = "<html><p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"
			+ "Unique features that you might like:" + br + br
			+ "- Writing down how much you liked a game, how far you played it through." + br
			+ "<html><img src='file:"+logo2[0][0]+"' width="+logo2[0][1]+" height="+logo2[0][2]+"></img>" + br
			+ "- The program track Name and developer(s) of a game, online rating, and many other infos." + br + br
			+ "- Multiple tables so you can sort all your games better."
			+ "<html><img src='file:"+logo2[1][0]+"' width="+logo2[1][1]+" height="+logo2[1][2]+"></img>" + br
			+ "- You can double click on cell or a row to copy it. (No feedback to the user for now.)" + br + br
			+ "- Discord Integration (Default off)" + br
			+ "- Language support [English(+, EngwishUwU ), Hungarian] (more in the future)" + br
			+ "- Supports websites like F95zone, Steam and soon Dlsite. Later any other that is requested." + br + br
			+ "<font color = #64AFFF><a href=\"https://github.com/DiamondCoder02/nsfw-game-manager/wiki/Features\">(All features with details here)</a></font>"
			;
		ep.setText(textInside2);
		JOptionPane.showMessageDialog(null, ep, "Features! (2/3)", JOptionPane.INFORMATION_MESSAGE);

		String[][] logo3 = {
			{"Assets/Pics/welcome/menuBar.png", "661" , "35"} // 661x35
		};
		// "<html><img src='file:"+logos[0][0]+"' width="+logos[0][1]+"height="+logos[0][2]+"></img>"
		String textInside3 = "<html><p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"
			+ "Short explanation of the top menu:" + br
			+ "<html><img src='file:"+logo3[0][0]+"' width="+logo3[0][1]+" height="+logo3[0][2]+"></img>" + br
			+ "- Games: You can add, update and remove games from the table" + br
			+ "- Random: Randomly get a game( from the table) if you don't know what to play today. You can also filter if you want something specific randomly" + br
			+ "- Search: Search through the games" + br
			+ "- Settings: Change the settings of the program, like language, Dark mode, enable Discord or change tables." + br
			+ "- Show informations: Shows the informations of a game and you can disable columns you are not interested to see." + br
			+ "- Other buttons just shows this welcome messages, FAQ and credits." + br
			+ "- Exit: Close the program" + br + br

			+ "This is mostly all. If you have a question or a problem write an email to us at KnockyNekos@gmail.com or open an issue on Github." + br
			// + "<font color = #64AFFF><a href=\"KnockyNekos@gmail.com\">KnockyNekos@gmail.com</a></font>" + br
			+ "    For all our contacts, check the Credits in the program or the wiki."
			;
		ep.setText(textInside3);
		JOptionPane.showMessageDialog(null, ep, "Menu! (3/3)", JOptionPane.INFORMATION_MESSAGE);
	}
}
