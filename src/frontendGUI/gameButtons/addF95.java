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

public class addF95 {
	static String[] bs = loadLanguage.base, bc = loadLanguage.basic, jla = loadLanguage.jlapa, 
		lf = loadLanguage.folder, jrb = loadLanguage.jrabu;
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
			// Name, Developer, Newest version, Date of last update, People rating, Engine, OS
			String nameValue = output[0].toString();
			String developerValue = output[1].toString();
			String newest_versionValue = output[2].toString();
			String dateOfLastUpdateValue = output[3].toString();
			String people_ratingValue = output[4].toString();
			String engineValue = output[5].toString();
			String osValue = output[6].toString();
			String languageValue = output[7].toString();

			JPanel panel = new JPanel(new GridLayout(6*2, 0));
			JTextField played_version = new JTextField(15);
			JTextField dateof_lastplay = new JTextField(20);
			JTextField user_rating = new JTextField(20);
			
			// Not played, In progress, Finish, 100% Finished
			JRadioButton howFarUserPlayed_NotPlayed = new JRadioButton(jrb[0]!=null?jrb[0]:"Not played", true), howFarUserPlayed_Playing = new JRadioButton(jrb[1]!=null?jrb[1]:"In progress", false), howFarUserPlayed_Finished = new JRadioButton(jrb[2]!=null?jrb[2]:"Finish", false), howFarUserPlayed_100Percent = new JRadioButton(jrb[3]!=null?jrb[3]:"100% Finished", false);
			howFarUserPlayed_NotPlayed.setActionCommand("Not played"); howFarUserPlayed_Playing.setActionCommand("In progress"); howFarUserPlayed_Finished.setActionCommand("Finish"); howFarUserPlayed_100Percent.setActionCommand("100% Finished");
			ButtonGroup howFarUserPlayed = new ButtonGroup(); howFarUserPlayed.add(howFarUserPlayed_NotPlayed); howFarUserPlayed.add(howFarUserPlayed_Playing); howFarUserPlayed.add(howFarUserPlayed_Finished); howFarUserPlayed.add(howFarUserPlayed_100Percent);
			JPanel howFarUserPlayedPanel = new JPanel(); howFarUserPlayedPanel.add(howFarUserPlayed_NotPlayed); howFarUserPlayedPanel.add(howFarUserPlayed_Playing); howFarUserPlayedPanel.add(howFarUserPlayed_Finished); howFarUserPlayedPanel.add(howFarUserPlayed_100Percent);
			// Yes, No, Unknown
			JRadioButton stillOnPc_yes = new JRadioButton(jrb[4]!=null?jrb[4]:"Yes", true), stillOnPc_no = new JRadioButton(jrb[5]!=null?jrb[5]:"No", false), stillOnPc_unknown = new JRadioButton(jrb[6]!=null?jrb[6]:"Unknown", false);
			stillOnPc_yes.setActionCommand("yes"); stillOnPc_no.setActionCommand("no"); stillOnPc_unknown.setActionCommand("unknown");
			ButtonGroup stillOnPc = new ButtonGroup(); stillOnPc.add(stillOnPc_yes); stillOnPc.add(stillOnPc_no); stillOnPc.add(stillOnPc_unknown);
			JPanel stillOnPcPanel = new JPanel(); stillOnPcPanel.add(stillOnPc_yes); stillOnPcPanel.add(stillOnPc_no); stillOnPcPanel.add(stillOnPc_unknown);

			JTextField selfNote = new JTextField(50);

			howFarUserPlayedPanel.setLayout(new BoxLayout(howFarUserPlayedPanel, BoxLayout.X_AXIS));
			stillOnPcPanel.setLayout(new BoxLayout(stillOnPcPanel, BoxLayout.X_AXIS));

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

			String played_versionValue = played_version.getText();
			String dateof_lastplayValue = dateof_lastplay.getText();
			String user_ratingValue = user_rating.getText();
			String howFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
			String stillOnPcValue = stillOnPc.getSelection().getActionCommand();
			String selfNoteValue = selfNote.getText();

			String[] allGameInfo = {idValue, nameValue, developerValue, played_versionValue, 
					dateof_lastplayValue, user_ratingValue, newest_versionValue, dateOfLastUpdateValue, 
					people_ratingValue, howFarUserPlayedValue, stillOnPcValue, engineValue, 
					osValue, languageValue, selfNoteValue};
			Boolean success = changeDatabase.addNewGameIntoDatabase("f95", allGameInfo);
			if (!success) { break; }

			int option = JOptionPane.showConfirmDialog(null, bc[3]==null?"Do you want to add another game?":bc[3], bs[2]==null?"Add game":bs[2], JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.NO_OPTION) { repeat = false; break; } else { break; }
		}
	}
}
