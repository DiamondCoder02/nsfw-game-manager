package frontendGUI.gameButtons;

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

import folderHandling.changeDatabase;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;

public class addManual {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	public static void addOneGameToFile(){
		boolean repeat = true;
		while (repeat) {
			JPanel panel = new JPanel(new GridLayout(8*2, 2));

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
			JRadioButton howFarUserPlayed_NotPlayed = new JRadioButton(jrb[0]!=null?jrb[0]:"Not played", true), howFarUserPlayed_Playing = new JRadioButton(jrb[1]!=null?jrb[1]:"In progress", false), howFarUserPlayed_Finished = new JRadioButton(jrb[2]!=null?jrb[2]:"Finish", false), howFarUserPlayed_100Percent = new JRadioButton(jrb[3]!=null?jrb[3]:"100% Finished", false);
			howFarUserPlayed_NotPlayed.setActionCommand("Not played"); howFarUserPlayed_Playing.setActionCommand("In progress"); howFarUserPlayed_Finished.setActionCommand("Finish"); howFarUserPlayed_100Percent.setActionCommand("100% Finished");
			ButtonGroup howFarUserPlayed = new ButtonGroup(); howFarUserPlayed.add(howFarUserPlayed_NotPlayed); howFarUserPlayed.add(howFarUserPlayed_Playing); howFarUserPlayed.add(howFarUserPlayed_Finished); howFarUserPlayed.add(howFarUserPlayed_100Percent);
			JPanel howFarUserPlayedPanel = new JPanel(); howFarUserPlayedPanel.add(howFarUserPlayed_NotPlayed); howFarUserPlayedPanel.add(howFarUserPlayed_Playing); howFarUserPlayedPanel.add(howFarUserPlayed_Finished); howFarUserPlayedPanel.add(howFarUserPlayed_100Percent);
			// Yes, No, Unknown
			JRadioButton stillOnPc_yes = new JRadioButton(jrb[4]!=null?jrb[4]:"Yes", true), stillOnPc_no = new JRadioButton(jrb[5]!=null?jrb[5]:"No", false), stillOnPc_unknown = new JRadioButton(jrb[6]!=null?jrb[6]:"Unknown", false);
			stillOnPc_yes.setActionCommand("yes"); stillOnPc_no.setActionCommand("no"); stillOnPc_unknown.setActionCommand("unknown");
			ButtonGroup stillOnPc = new ButtonGroup(); stillOnPc.add(stillOnPc_yes); stillOnPc.add(stillOnPc_no); stillOnPc.add(stillOnPc_unknown);
			JPanel stillOnPcPanel = new JPanel(); stillOnPcPanel.add(stillOnPc_yes); stillOnPcPanel.add(stillOnPc_no); stillOnPcPanel.add(stillOnPc_unknown);
			// Flash, HTML, Java, QSP, RenPy, RPGmaker, Unity, Unreal, WinGit, WolfRPG, other/unknown
			JRadioButton engine_Flash = new JRadioButton("Flash"), engine_HTML = new JRadioButton("HTML"), engine_Java = new JRadioButton("Java"), engine_QSP = new JRadioButton("QSP"), engine_RenPy = new JRadioButton("RenPy"), engine_RPGmaker = new JRadioButton("RPGmaker"), engine_Unity = new JRadioButton("Unity"), engine_Unreal = new JRadioButton("Unreal"), engine_WinGit = new JRadioButton("WinGit"), engine_WolfRPG = new JRadioButton("WolfRPG"), engine_other = new JRadioButton(jrb[7]!=null?jrb[7]:"other/unknown", true);
			engine_Flash.setActionCommand("Flash"); engine_HTML.setActionCommand("HTML"); engine_Java.setActionCommand("Java"); engine_QSP.setActionCommand("QSP"); engine_RenPy.setActionCommand("RenPy"); engine_RPGmaker.setActionCommand("RPGmaker"); engine_Unity.setActionCommand("Unity"); engine_Unreal.setActionCommand("Unreal"); engine_WinGit.setActionCommand("WinGit"); engine_WolfRPG.setActionCommand("WolfRPG"); engine_other.setActionCommand("other/unknown");
			ButtonGroup engineGroup = new ButtonGroup(); engineGroup.add(engine_Flash); engineGroup.add(engine_HTML); engineGroup.add(engine_Java); engineGroup.add(engine_QSP); engineGroup.add(engine_RenPy); engineGroup.add(engine_RPGmaker); engineGroup.add(engine_Unity); engineGroup.add(engine_Unreal); engineGroup.add(engine_WinGit); engineGroup.add(engine_WolfRPG); engineGroup.add(engine_other);
			JPanel enginePanel = new JPanel(); enginePanel.add(engine_Flash); enginePanel.add(engine_HTML); enginePanel.add(engine_Java); enginePanel.add(engine_QSP); enginePanel.add(engine_RenPy); enginePanel.add(engine_RPGmaker); enginePanel.add(engine_Unity); enginePanel.add(engine_Unreal); enginePanel.add(engine_WinGit); enginePanel.add(engine_WolfRPG); enginePanel.add(engine_other);
			// Windows, Linux, Mac, Android, other
			JCheckBox os_win = new JCheckBox("Windows"), os_lin = new JCheckBox("Linux"), os_mac = new JCheckBox("Mac"), os_and = new JCheckBox("Android"), os_other = new JCheckBox(jrb[7]!=null?jrb[7]:"other");
			os_win.setActionCommand("windows"); os_lin.setActionCommand("linux"); os_mac.setActionCommand("mac"); os_and.setActionCommand("android"); os_other.setActionCommand("other");
			JPanel osPanel = new JPanel(); osPanel.add(os_win); osPanel.add(os_lin); osPanel.add(os_mac); osPanel.add(os_and); osPanel.add(os_other);

			JTextField language = new JTextField(20);
			JTextField selfNote = new JTextField(50);

			howFarUserPlayedPanel.setLayout(new BoxLayout(howFarUserPlayedPanel, BoxLayout.X_AXIS));
			stillOnPcPanel.setLayout(new BoxLayout(stillOnPcPanel, BoxLayout.X_AXIS));
			enginePanel.setLayout(new BoxLayout(enginePanel, BoxLayout.X_AXIS));
			osPanel.setLayout(new BoxLayout(osPanel, BoxLayout.X_AXIS));

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

			String idValue = id.getText();
			String nameValue = name.getText();
			String developerValue = developer.getText();
			String played_versionValue = played_version.getText();
			String dateof_lastplayValue = dateof_lastplay.getText();
			String user_ratingValue = user_rating.getText();
			String newest_versionValue = newest_version.getText();
			String dateOfLastUpdateValue = dateOfLastUpdate.getText();
			String people_ratingValue = people_rating.getText();
			String howFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
			String stillOnPcValue = stillOnPc.getSelection().getActionCommand();
			String engineValue = engineGroup.getSelection().getActionCommand();
			String osValue = "";
			if (os_win.isSelected()) { osValue += "Windows / "; }
			if (os_lin.isSelected()) { osValue += "Linux / "; }
			if (os_mac.isSelected()) { osValue += "Mac / "; }
			if (os_and.isSelected()) { osValue += "Android / "; }
			if (os_other.isSelected()) { osValue += "other"; }
			if (osValue.endsWith(" / ")) { osValue = osValue.substring(0, osValue.length() - 3); }
			String languageValue = language.getText();
			String selfNoteValue = selfNote.getText();

			if (idValue.equals("")) { JOptionPane.showMessageDialog(null, basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }
			if (checkDatabase.isInDatabase(idValue, "man")) { 
				JOptionPane.showMessageDialog(null, folder[12]!=null?folder[12]:"The id you entered is already in the *MANUAL* database", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				String[] allGameInfo = {idValue, nameValue, developerValue, played_versionValue, 
					dateof_lastplayValue, user_ratingValue, newest_versionValue, 
					dateOfLastUpdateValue, people_ratingValue, howFarUserPlayedValue, 
					stillOnPcValue, engineValue, osValue, languageValue, selfNoteValue};
				Boolean success = changeDatabase.addNewGameIntoDatabase("man", allGameInfo);
				if (!success) { break; }

				int option = JOptionPane.showConfirmDialog(null, basic[3]!=null?basic[3]:"Do you want to add another game?", base[2]!=null?base[2]:"Add game", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.NO_OPTION) { repeat = false; break; } else { break; }
			}
		}
	}
}
