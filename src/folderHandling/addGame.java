package folderHandling;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.changeDatabase;
import integrationCheck.defaultValues;

public class addGame {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;

	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup stillOnPc = new ButtonGroup();
	static JPanel stillOnPcPanel = new JPanel();
	static ButtonGroup engineGroup = new ButtonGroup();
	static JPanel enginePanel = new JPanel();

	static String osValue = "";
	static JPanel osPanel = new JPanel();

	// General method to add a game to the database
	// allKnownGameInfo is an array that has all game info we know and if 
	//  an array is empty, we ask the user to fill it in
	//  while showing the user the info we know
	public static boolean addGameToDB(String site, String[] allKnownGameInfo){
		if (allKnownGameInfo[0] == null) { 
			JOptionPane.showMessageDialog(null, 
				basic[0]!=null?basic[0]:"ID is required", 
				base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE); 
			return false; 
		}

		JPanel panel = new JPanel(new GridLayout(8*2, 2));
		/* allKnownGameInfo
		0 - ID		1 - Name	2 - Developer	3 - Played version
		4 - Last time play		5 - Rated		6 - Newest version
		7 - Last update		8 - People rating	9 - Player progress
		10 - Still on pc?			11 - Engine		12 - OS
		13 - Language			14 - Personal notes
		*/

		for (int i = 0; i < allKnownGameInfo.length; i++) {
			if (allKnownGameInfo[i] == null) {
				switch (i) {
					case 9:
						String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
						radioButtons("progress", jrb1, defaultValues.infoProgress, true);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(howFarUserPlayedPanel);
						break;
					case 10:
						String[] jrb2 = {jrb[4]!=null?jrb[4]:"Yes", jrb[5]!=null?jrb[5]:"No", jrb[6]!=null?jrb[6]:"Unknown"};
						radioButtons("stillOnPc", jrb2, defaultValues.infoOnPc, true);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(stillOnPcPanel);
						break;
					case 11:
						radioButtons("engine", defaultValues.infoEngine, defaultValues.infoEngine, true);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(enginePanel);
						break;
					case 12:
						radioButtons("os", defaultValues.infoOS, defaultValues.infoOS, false);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(osPanel);
						break;
					default:
						JTextField textField = new JTextField(40);
						JLabel label = new JLabel(jla[i]!=null?jla[i]:"N/A");
						panel.add(label, BorderLayout.WEST); panel.add(textField);
				}
			} else {
				JLabel label = new JLabel(jla[i]!=null?jla[i]:"N/A");
				JLabel value = new JLabel(allKnownGameInfo[i]);
				panel.add(label); panel.add(value);
			}
		}

		JOptionPane.showMessageDialog(null, panel, base[2]!=null?base[2]:"Add game", JOptionPane.PLAIN_MESSAGE);

		return false;

























		/*
			JTextField id = new JTextField(6);
			JTextField name = new JTextField(40);
			JTextField developer = new JTextField(20);
			JTextField played_version = new JTextField(15);
			JTextField dateof_lastplay = new JTextField(20);
			JTextField user_rating = new JTextField(20);
			JTextField newest_version = new JTextField(12);
			JTextField dateOfLastUpdate = new JTextField(12);
			JTextField people_rating = new JTextField(20);

			// Not played, In progress, Finish, 100% Finished
			String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
			radioButtons("progress", jrb1, defaultValues.infoProgress, true);
			// Yes, No, Unknown
			String[] jrb2 = {jrb[4]!=null?jrb[4]:"Yes", jrb[5]!=null?jrb[5]:"No", jrb[6]!=null?jrb[6]:"Unknown"};
			radioButtons("stillOnPc", jrb2, defaultValues.infoOnPc, true);
			// Flash, HTML, Java, QSP, RenPy, RPGmaker, Unity, Unreal, WinGit, WolfRPG, other/unknown
			radioButtons("engine", defaultValues.infoEngine, defaultValues.infoEngine, true);
			// Windows, Linux, Mac, Android, other
			radioButtons("os", defaultValues.infoOS, defaultValues.infoOS, false);

			JTextField language = new JTextField(20);
			JTextField selfNote = new JTextField(50);

			JLabel IDlabel = new JLabel(jla[0]!=null?jla[0]:"ID: (required)");
			panel.add(IDlabel); panel.add(id);
			JLabel Namelabel = new JLabel(jla[1]!=null?jla[1]:"Name: (required)");
			panel.add(Namelabel); panel.add(name);
			JLabel developerlabel = new JLabel(jla[2]!=null?jla[2]:"Developer:");
			panel.add(developerlabel); panel.add(developer);
			JLabel played_versionlabel = new JLabel(jla[3]!=null?jla[3]:"Last version you played:");
			panel.add(played_versionlabel); panel.add(played_version);
			JLabel dateof_lastplaylabel = new JLabel(jla[4]!=null?jla[4]:"Last time you updated the game:");
			panel.add(dateof_lastplaylabel); panel.add(dateof_lastplay);
			JLabel user_ratinglabel = new JLabel(jla[5]!=null?jla[5]:"Rating by you:");
			panel.add(user_ratinglabel); panel.add(user_rating);
			JLabel newest_versionlabel = new JLabel(jla[6]!=null?jla[6]:"Online newest version:");
			panel.add(newest_versionlabel); panel.add(newest_version);
			JLabel dateOfLastUpdatelabel = new JLabel(jla[7]!=null?jla[7]:"Online date of last update:");
			panel.add(dateOfLastUpdatelabel); panel.add(dateOfLastUpdate);
			JLabel people_ratinglabel = new JLabel(jla[8]!=null?jla[8]:"Rating by online people:");
			panel.add(people_ratinglabel); panel.add(people_rating);
			JLabel howFarUserPlayedlabel = new JLabel(jla[9]!=null?jla[9]:"How far you progressed in the game:");
			panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
			JLabel stillOnPclabel = new JLabel(jla[10]!=null?jla[10]:"Is the game still on pc?");
			panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
			JLabel enginelabel = new JLabel(jla[11]!=null?jla[11]:"Engine:");
			panel.add(enginelabel); panel.add(enginePanel);
			JLabel oslabel = new JLabel(jla[12]!=null?jla[12]:"OS:");
			panel.add(oslabel); panel.add(osPanel);
			JLabel languagelabel = new JLabel(jla[14]!=null?jla[14]:"Language:");
			panel.add(languagelabel); panel.add(language);
			JLabel selfNotelabel = new JLabel(jla[13]!=null?jla[13]:"Self note:");
			panel.add(selfNotelabel, BorderLayout.WEST); panel.add(selfNote);

			JOptionPane.showMessageDialog(null, panel, base[2]!=null?base[2]:"Add game", JOptionPane.PLAIN_MESSAGE);

			for (int i = 0; i < osPanel.getComponentCount(); i++) {
				JCheckBox os = (JCheckBox) osPanel.getComponent(i);
				if (os.isSelected()) { osValue += os.getText() + " / "; }
			}
			if (osValue.endsWith(" / ")) { osValue = osValue.substring(0, osValue.length() - 3); }

			if (id.getText().equals("")) { JOptionPane.showMessageDialog(null, basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }
			if (checkDatabase.isInDatabase(id.getText(), "man")) { 
				JOptionPane.showMessageDialog(null, folder[12]!=null?folder[12]:"The id you entered is already in the *MANUAL* database", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				String[] allGameInfo = {id.getText(), name.getText(), developer.getText(), played_version.getText(), 
					dateof_lastplay.getText(), user_rating.getText(), newest_version.getText(), 
					dateOfLastUpdate.getText(), people_rating.getText(), howFarUserPlayed.getSelection().getActionCommand(), 
					stillOnPc.getSelection().getActionCommand(), engineGroup.getSelection().getActionCommand(), 
					osValue, language.getText(), selfNote.getText()};
				Boolean success = changeDatabase.addNewGameIntoDatabase("man", allGameInfo);
				if (!success) { break; }
				
			}
		*/
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
