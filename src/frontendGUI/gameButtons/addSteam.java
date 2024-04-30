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

import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.changeDatabase;
import folderHandling.localFoldersLoad.getSteamFolderInfos;
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
	// TODO - steam
	static String[] bs = loadLanguage.base, bc = loadLanguage.basic, jla = loadLanguage.jlapa, 
		lf = loadLanguage.folder, jrb = loadLanguage.jrabu;

	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup engineGroup = new ButtonGroup();
	static JPanel enginePanel = new JPanel();

	public static void addFromSteam(){
		boolean repeat = true;
		while (repeat) {
			String text = "Enter the id of the game you want to add, from Steam.";
			String idValue = JOptionPane.showInputDialog(null, text, bs[2]!=null?bs[2]:"Add game", JOptionPane.PLAIN_MESSAGE);
			if (idValue == null) { JOptionPane.showMessageDialog(null, bc[0]==null?"You must enter an id":bc[0], bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE); break; }

			if (checkDatabase.isInDatabase(idValue, "steam")) {
				JOptionPane.showMessageDialog(null, lf[4]==null?"The id you entered is already in the Steam database":lf[4], bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE);
				break;
			}

			// 0 - appid		1 - name		2 - developer	3 - online buildid	4 - timeupdated
			// 5 - review_percentage		6 - oslist		7 - supported_languages
			String[] output = loadSteam.getSteamUrlContents(idValue);
			if (output == null) { break; }
			// 0 - LastUpdated		1 - local buildid
			String[] output2 = getSteamFolderInfos.getSteamAppInfo(idValue);
			Boolean isOnPc = false;
			if (output2 != null) { isOnPc = true;}

			JPanel panel = new JPanel(new GridLayout(4*2, 0));

			JTextField user_rating = new JTextField(15);
			// Not played, In progress, Finish, 100% Finished
			String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
			radioButtons("progress", jrb1, defaultValues.infoProgress, true);
			// Flash, HTML, Java, QSP, RenPy, RPGmaker, Unity, Unreal, WinGit, WolfRPG, other/unknown
			radioButtons("engine", defaultValues.infoEngine, defaultValues.infoEngine, true);
			JTextField selfNote = new JTextField(50);

			JLabel user_ratinglabel = new JLabel(jla[5]!=null?jla[5]:"Rating by you:");
			panel.add(user_ratinglabel); panel.add(user_rating);
			JLabel howFarUserPlayedlabel = new JLabel(jla[9]!=null?jla[9]:"How far you progressed in the game:");
			panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
			JLabel enginelabel = new JLabel(jla[11]!=null?jla[11]:"Engine:");
			panel.add(enginelabel); panel.add(enginePanel);
			JLabel selfNotelabel = new JLabel(jla[13]!=null?jla[13]:"Self note:");
			panel.add(selfNotelabel); panel.add(selfNote);
			JOptionPane.showMessageDialog(null, panel, bs[2]==null?"Add game":bs[2], JOptionPane.PLAIN_MESSAGE);

			String[] allGameInfos = {
				output[0], output[1], output[2], output2[1], output2[0], user_rating.getText(), output[3], 
				output[4], output[5], howFarUserPlayed.getSelection().getActionCommand(), isOnPc?"Yes":"No", 
				engineGroup.getSelection().getActionCommand(), output[6], output[7], selfNote.getText()
			};

			Boolean success = changeDatabase.addNewGameIntoDatabase("steam", allGameInfos);
			if (!success) { break; }
	
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
			case "engine": enginePanel = buttonPanel; engineGroup = allButtons; break;
		}
	}
}
