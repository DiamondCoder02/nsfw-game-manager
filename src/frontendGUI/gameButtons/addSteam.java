package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import folderHandling.changeDatabase;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import integrationCheck.defaultValues;
import webApiScrapeThings.sites.loadSteam;

/*
00- Steam
https://api.steamcmd.net/v1/info/10
From this:
01 - data."10".appid

02 - data."10".common.name
03 - data."10".common.associations.0.name

07 - data."10".depots.branches.public.buildid (cannot find game version so fuck it, I will use this)
08 - data."10".depots.branches.public.timeupdated (UNIX timestamp)
09 - data."10".common.review_percentage (all reviews)

13 - data."10".common.oslist (format a bit)
14 - data."10".common.supported_languages 

Locally:
4 - Played version
5 - Last time play
11 - Still on pc?

???:
6 - Rated
10 - Player progress
12 - Engine	
15 - Personal notes

*/

public class addSteam {
	static String[] bs = loadLanguage.base, bc = loadLanguage.basic, jla = loadLanguage.jlapa, 
		lf = loadLanguage.folder, jrb = loadLanguage.jrabu;

	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup stillOnPc = new ButtonGroup();
	static JPanel stillOnPcPanel = new JPanel();
	static ButtonGroup engineGroup = new ButtonGroup();
	static JPanel enginePanel = new JPanel();

	static String osValue = "";
	static JPanel osPanel = new JPanel();

	public static void addFromF95(){
		boolean repeat = true;
		while (repeat) {
			String text = "Enter the id of the game you want to add, from Steam.";
			String idValue = JOptionPane.showInputDialog(null, text, bs[2]!=null?bs[2]:"Add game", JOptionPane.PLAIN_MESSAGE);
			if (idValue == null) { JOptionPane.showMessageDialog(null, bc[0]==null?"You must enter an id":bc[0], bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE); break; }

			if (checkDatabase.isInDatabase(idValue, "steam")) {
				JOptionPane.showMessageDialog(null, lf[4]==null?"The id you entered is already in the F95zone database":lf[4], bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE);
				break;
			}

			String[] output = loadSteam.getSteamUrlContents(idValue);
			if (output == null) { break; }



			
			/*
			JPanel panel = new JPanel(new GridLayout(6*2, 0));
			JTextField played_version = new JTextField(15);
			JTextField dateof_lastplay = new JTextField(20);
			JTextField user_rating = new JTextField(20);

			// Not played, In progress, Finish, 100% Finished
			String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
			radioButtons("progress", jrb1, defaultValues.infoProgress, true);
			// Yes, No, Unknown
			String[] jrb2 = {jrb[4]!=null?jrb[4]:"Yes", jrb[5]!=null?jrb[5]:"No", jrb[6]!=null?jrb[6]:"Unknown"};
			radioButtons("stillOnPc", jrb2, defaultValues.infoOnPc, true);

			JTextField selfNote = new JTextField(50);

			JLabel played_versionlabel = new JLabel(jla[3]!=null?jla[3]:"Last version you played:");
			panel.add(played_versionlabel); panel.add(played_version);
			JLabel dateof_lastplaylabel = new JLabel(jla[4]!=null?jla[4]:"Last time you updated the game:");
			panel.add(dateof_lastplaylabel); panel.add(dateof_lastplay);
			JLabel user_ratinglabel = new JLabel(jla[5]!=null?jla[5]:"Rating by you:");
			panel.add(user_ratinglabel); panel.add(user_rating);
			JLabel howFarUserPlayedlabel = new JLabel(jla[9]!=null?jla[9]:"How far you progressed in the game:");
			panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
			JLabel stillOnPclabel = new JLabel(jla[10]!=null?jla[10]:"Is the game still on pc?");
			panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
			JLabel selfNotelabel = new JLabel(jla[13]!=null?jla[13]:"Self note:");
			panel.add(selfNotelabel); panel.add(selfNote);
			JOptionPane.showMessageDialog(null, panel, bs[2]==null?"Add game":bs[2], JOptionPane.PLAIN_MESSAGE);

			// output[i] = Name, Developer, Newest version, Date of last update, People rating, Engine, OS, Language
			String[] allGameInfo = {idValue, output[0], output[1], 
				played_version.getText(), dateof_lastplay.getText(), user_rating.getText(), 
				output[2], output[3], output[4], 
				howFarUserPlayed.getSelection().getActionCommand(), stillOnPc.getSelection().getActionCommand(), 
				output[5], output[6], output[7], selfNote.getText()};
			Boolean success = changeDatabase.addNewGameIntoDatabase("steam", allGameInfo);
			if (!success) { break; }
			*/
	
			int option = JOptionPane.showConfirmDialog(null, bc[3]==null?"Do you want to add another game?":bc[3], bs[2]==null?"Add game":bs[2], JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.NO_OPTION) { repeat = false; break; } else { break; }
		}
	}

	private static void radioButtons(String buttonType, String[] jrbArray, String[] action, Boolean isRB) {
		ButtonGroup allButtons = new ButtonGroup();
		JPanel buttonPanel = new JPanel();

		if (isRB) { 
			for (int i = 0; i < jrbArray.length; i++) {
				JRadioButton button = new JRadioButton(jrbArray[i], i == 0); 
				button.setActionCommand(action[i]);
				allButtons.add(button);
				buttonPanel.add(button);
			}
		}
		else { 
			for (int i = 0; i < jrbArray.length; i++) {
				JCheckBox button = new JCheckBox(jrbArray[i], false);
				button.setActionCommand(action[i]);
				buttonPanel.add(button);
			}
		}

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		switch (buttonType) {
			case "progress": howFarUserPlayedPanel = buttonPanel; howFarUserPlayed = allButtons; break;
			case "stillOnPc": stillOnPcPanel = buttonPanel; stillOnPc = allButtons; break;
			case "engine": enginePanel = buttonPanel; engineGroup = allButtons; break;
			case "os": osPanel = buttonPanel; break;
		}
	}
}
