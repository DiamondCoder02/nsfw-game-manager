package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import folderHandling.changeDatabase;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import webApiScrapeThings.sites.loadF95site;
import integrationCheck.defaultValues;

public class addF95 {
	static String[] bs = loadLanguage.base, bc = loadLanguage.basic, jla = loadLanguage.jlapa, 
		lf = loadLanguage.folder, jrb = loadLanguage.jrabu;

	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup stillOnPc = new ButtonGroup();
	static JPanel stillOnPcPanel = new JPanel();
	public static void addFromF95(){
		boolean repeat = true;
		while (repeat) {
			String text = lf[3]==null?"Enter the id of the game you want to add, from f95zone website.":lf[3];
			String idValue = JOptionPane.showInputDialog(null, text, bs[2]!=null?bs[2]:"Add game", JOptionPane.PLAIN_MESSAGE);
			if (idValue == null) { JOptionPane.showMessageDialog(null, bc[0]==null?"You must enter an id":bc[0], bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE); break; }

			if (checkDatabase.isInDatabase(idValue, "f95")) {
				JOptionPane.showMessageDialog(null, lf[4]==null?"The id you entered is already in the F95zone database":lf[4], bs[1]==null?"Error":bs[1], JOptionPane.ERROR_MESSAGE);
				break;
			}

			String[] output = loadF95site.getf95UrlContents(idValue);
			if (output == null) { break; }

			JPanel panel = new JPanel(new GridLayout(6*2, 0));
			JTextField played_version = new JTextField(15);
			JTextField dateof_lastplay = new JTextField(20);
			JTextField user_rating = new JTextField(20);

			// Not played, In progress, Finish, 100% Finished
			String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
			radioButtons("progress", jrb1, defaultValues.infoProgress);
			// Yes, No, Unknown
			String[] jrb2 = {jrb[4]!=null?jrb[4]:"Yes", jrb[5]!=null?jrb[5]:"No", jrb[6]!=null?jrb[6]:"Unknown"};
			radioButtons("stillOnPc", jrb2, defaultValues.infoOnPc);

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
			Boolean success = changeDatabase.addNewGameIntoDatabase("f95", allGameInfo);
			if (!success) { break; }

			int option = JOptionPane.showConfirmDialog(null, bc[3]==null?"Do you want to add another game?":bc[3], bs[2]==null?"Add game":bs[2], JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.NO_OPTION) { repeat = false; break; } else { break; }
		}
	}

	private static void radioButtons(String buttonType, String[] jrbArray, String[] action) {
		ButtonGroup allButtons = new ButtonGroup();
		JPanel buttonPanel = new JPanel();

		for (int i = 0; i < jrbArray.length; i++) {
			JRadioButton button = new JRadioButton(jrbArray[i], i == 0); 
			button.setActionCommand(action[i]);
			allButtons.add(button);
			buttonPanel.add(button);
		}

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		switch (buttonType) {
			case "progress": howFarUserPlayedPanel = buttonPanel; howFarUserPlayed = allButtons; break;
			case "stillOnPc": stillOnPcPanel = buttonPanel; stillOnPc = allButtons; break;
		}
	}
}
