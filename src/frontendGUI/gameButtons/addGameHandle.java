package frontendGUI.gameButtons;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.changeDatabase;
import integrationCheck.defaultValues;

public class addGameHandle {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;

	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup stillOnPc = new ButtonGroup();
	static JPanel stillOnPcPanel = new JPanel();
	static ButtonGroup engineGroup = new ButtonGroup();
	static JPanel enginePanel = new JPanel();
	static JPanel enginePanel2 = new JPanel();

	static String osValue = "";
	static JPanel osPanel = new JPanel();

	// General method to add a game to the database
	// allKnownGameInfo is an array that has all game info we know and if 
	//  an array is empty, we ask the user to fill it in
	//  while showing the user the info we know
	public static boolean addGameToDB(String site, String[] allKnownGameInfo){
		/* allKnownGameInfo
		0 - ID		1 - Name	2 - Developer	3 - Played version
		4 - Last time play		5 - Rated		6 - Newest version
		7 - Last update		8 - People rating	9 - Player progress
		10 - Still on pc?			11 - Engine		12 - OS
		13 - Language			14 - Personal notes
		*/
		// JPanel panel = new JPanel(new GridLayout(8*2, 2));
		JTextField[] textField = new JTextField[allKnownGameInfo.length];
		String spac = "        "; //8
		JLabel[] right = new JLabel[allKnownGameInfo.length];
		JLabel[] left = new JLabel[allKnownGameInfo.length];
		JPanel[] rightP = new JPanel[allKnownGameInfo.length];

		for (int i = 0; i < allKnownGameInfo.length; i++) {
			if (allKnownGameInfo[i] == null) {
				switch (i) {
					case 9:
						String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished","Dropped"};
						radioButtons("progress", jrb1, defaultValues.infoProgress, true);
						left[i] = new JLabel((jla[i]!=null?jla[i]:"N/A") + spac, SwingConstants.RIGHT);
						rightP[i] = howFarUserPlayedPanel;
						break;
					case 10:
						String[] jrb2 = {jrb[4]!=null?jrb[4]:"Yes", jrb[5]!=null?jrb[5]:"No", jrb[6]!=null?jrb[6]:"Unknown"};
						radioButtons("stillOnPc", jrb2, defaultValues.infoOnPc, true);
						left[i] = new JLabel((jla[i]!=null?jla[i]:"N/A") + spac, SwingConstants.RIGHT); 
						rightP[i] = stillOnPcPanel;
						break;
					case 11:
						radioButtons("engine", defaultValues.infoEngine, defaultValues.infoEngine, true);
						left[i] = new JLabel((jla[i]!=null?jla[i]:"N/A") + spac, SwingConstants.RIGHT); 
						rightP[i] = enginePanel;
						break;
					case 12:
						radioButtons("os", defaultValues.infoOS, defaultValues.infoOS, false);
						left[i] = new JLabel((jla[i]!=null?jla[i]:"N/A") + spac, SwingConstants.RIGHT); 
						rightP[i] = osPanel;
						break;
					default:
						textField[i] = new JTextField(30);
						left[i] = new JLabel((jla[i]!=null?jla[i]:"N/A") + spac, SwingConstants.RIGHT);
				}
			} else {
				left[i] = new JLabel((jla[i]!=null?jla[i]:"N/A") + spac, SwingConstants.RIGHT);
				right[i] = new JLabel(allKnownGameInfo[i]);
			}
		}

		JPanel panel = dynamicPanel.frameCreate(right, left, rightP, textField);

		//  CLOSED - -1  //  Yes-OK - 0  //  No - 1  //  Cancel - 2  //
		Integer option = JOptionPane.showOptionDialog(null, panel, 
			base[2]!=null?base[2]:"Add game", 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.PLAIN_MESSAGE, 
			null, null, null);
		if (option != 0) { return false; }

		for (int i = 0; i < textField.length; i++) {
			if (allKnownGameInfo[i] == null) { 
				switch (i) {
					case 9: 
						allKnownGameInfo[i] = howFarUserPlayed.getSelection().getActionCommand();
						break;
					case 10:
						allKnownGameInfo[i] = stillOnPc.getSelection().getActionCommand();
						break;
					case 11:	
						allKnownGameInfo[i] = engineGroup.getSelection().getActionCommand();
						break;
					case 12:
						for (int j = 0; j < osPanel.getComponentCount(); j++) {
							JCheckBox os = (JCheckBox) osPanel.getComponent(j);
							if (os.isSelected()) { osValue += os.getText() + " / "; }
						}
						if (osValue.endsWith(" / ")) { osValue = osValue.substring(0, osValue.length() - 3); }
						allKnownGameInfo[i] = osValue;
						break;
					default:
						allKnownGameInfo[i] = textField[i].getText();
						break;
				}
			}
		}

		Boolean success = changeDatabase.addNewGameIntoDatabase(site, allKnownGameInfo);
		if (!success) { return false; }
		return true;
	}

	private static void radioButtons(String buttonType, String[] jrbArray, String[] action, Boolean isRB) {
		ButtonGroup allButtons = new ButtonGroup();
		JPanel buttonPanel = new JPanel();

		if (isRB) { 
			for (int i = 0; i < action.length; i++) {
				JRadioButton button = new JRadioButton(jrbArray[i], i == 0); 
				button.setActionCommand(action[i]);
				allButtons.add(button);
				buttonPanel.add(button);
			}
		}
		else { 
			for (int i = 0; i < action.length; i++) {
				JCheckBox button = new JCheckBox(jrbArray[i], false);
				button.setActionCommand(action[i]);
				buttonPanel.add(button);
			}
		}

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		switch (buttonType) {
			case "progress": howFarUserPlayedPanel = buttonPanel; howFarUserPlayed = allButtons; break;
			case "stillOnPc": stillOnPcPanel = buttonPanel; stillOnPc = allButtons; break;
			case "engine": 
				enginePanel = buttonPanel; 
				engineGroup = allButtons; 
			break;
			case "os": osPanel = buttonPanel; break;
		}
	}
}
