package windowJframe;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.saveLoadDoc;

public class addGameToFile {
	public static void addOneGameToFile(){
		boolean repeat = true;
		while (repeat) {
			JPanel panel = new JPanel();

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
			JRadioButton howFarUserPlayed_NotPlayed = new JRadioButton("Not played", true), howFarUserPlayed_Playing = new JRadioButton("In progress", false), howFarUserPlayed_Finished = new JRadioButton("Finish", false), howFarUserPlayed_100Percent = new JRadioButton("100% Finished", false);
			howFarUserPlayed_NotPlayed.setActionCommand("Not played"); howFarUserPlayed_Playing.setActionCommand("In progress"); howFarUserPlayed_Finished.setActionCommand("Finish"); howFarUserPlayed_100Percent.setActionCommand("100% Finished");
			ButtonGroup howFarUserPlayed = new ButtonGroup(); howFarUserPlayed.add(howFarUserPlayed_NotPlayed); howFarUserPlayed.add(howFarUserPlayed_Playing); howFarUserPlayed.add(howFarUserPlayed_Finished); howFarUserPlayed.add(howFarUserPlayed_100Percent);
			JPanel howFarUserPlayedPanel = new JPanel(); howFarUserPlayedPanel.add(howFarUserPlayed_NotPlayed); howFarUserPlayedPanel.add(howFarUserPlayed_Playing); howFarUserPlayedPanel.add(howFarUserPlayed_Finished); howFarUserPlayedPanel.add(howFarUserPlayed_100Percent);
			// Yes, No, Unknown
			JRadioButton stillOnPc_yes = new JRadioButton("Yes", true), stillOnPc_no = new JRadioButton("No", false), stillOnPc_unknown = new JRadioButton("Unknown", false);
			stillOnPc_yes.setActionCommand("yes"); stillOnPc_no.setActionCommand("no"); stillOnPc_unknown.setActionCommand("unknown");
			ButtonGroup stillOnPc = new ButtonGroup(); stillOnPc.add(stillOnPc_yes); stillOnPc.add(stillOnPc_no); stillOnPc.add(stillOnPc_unknown);
			JPanel stillOnPcPanel = new JPanel(); stillOnPcPanel.add(stillOnPc_yes); stillOnPcPanel.add(stillOnPc_no); stillOnPcPanel.add(stillOnPc_unknown);
			// Flash, HTML, Java, QSP, RenPy, RPGmaker, Unity, Unreal, WinGit, WolfRPG, other/unknown
			JRadioButton engine_Flash = new JRadioButton("Flash"), engine_HTML = new JRadioButton("HTML"), engine_Java = new JRadioButton("Java"), engine_QSP = new JRadioButton("QSP"), engine_RenPy = new JRadioButton("RenPy"), engine_RPGmaker = new JRadioButton("RPGmaker"), engine_Unity = new JRadioButton("Unity"), engine_Unreal = new JRadioButton("Unreal"), engine_WinGit = new JRadioButton("WinGit"), engine_WolfRPG = new JRadioButton("WolfRPG"), engine_other = new JRadioButton("other/unknown", true);
			engine_Flash.setActionCommand("Flash"); engine_HTML.setActionCommand("HTML"); engine_Java.setActionCommand("Java"); engine_QSP.setActionCommand("QSP"); engine_RenPy.setActionCommand("RenPy"); engine_RPGmaker.setActionCommand("RPGmaker"); engine_Unity.setActionCommand("Unity"); engine_Unreal.setActionCommand("Unreal"); engine_WinGit.setActionCommand("WinGit"); engine_WolfRPG.setActionCommand("WolfRPG"); engine_other.setActionCommand("other/unknown");
			ButtonGroup engineGroup = new ButtonGroup(); engineGroup.add(engine_Flash); engineGroup.add(engine_HTML); engineGroup.add(engine_Java); engineGroup.add(engine_QSP); engineGroup.add(engine_RenPy); engineGroup.add(engine_RPGmaker); engineGroup.add(engine_Unity); engineGroup.add(engine_Unreal); engineGroup.add(engine_WinGit); engineGroup.add(engine_WolfRPG); engineGroup.add(engine_other);
			JPanel enginePanel = new JPanel(); enginePanel.add(engine_Flash); enginePanel.add(engine_HTML); enginePanel.add(engine_Java); enginePanel.add(engine_QSP); enginePanel.add(engine_RenPy); enginePanel.add(engine_RPGmaker); enginePanel.add(engine_Unity); enginePanel.add(engine_Unreal); enginePanel.add(engine_WinGit); enginePanel.add(engine_WolfRPG); enginePanel.add(engine_other);
			// Windows, Linux, Mac, Android, other
			JCheckBox os_win = new JCheckBox("Windows"), os_lin = new JCheckBox("Linux"), os_mac = new JCheckBox("Mac"), os_and = new JCheckBox("Android"), os_other = new JCheckBox("other");
			os_win.setActionCommand("windows"); os_lin.setActionCommand("linux"); os_mac.setActionCommand("mac"); os_and.setActionCommand("android"); os_other.setActionCommand("other");
			JPanel osPanel = new JPanel(); osPanel.add(os_win); osPanel.add(os_lin); osPanel.add(os_mac); osPanel.add(os_and); osPanel.add(os_other);

			JTextField selfNote = new JTextField(50);

			howFarUserPlayedPanel.setLayout(new BoxLayout(howFarUserPlayedPanel, BoxLayout.X_AXIS));
			stillOnPcPanel.setLayout(new BoxLayout(stillOnPcPanel, BoxLayout.X_AXIS));
			enginePanel.setLayout(new BoxLayout(enginePanel, BoxLayout.X_AXIS));

			JLabel IDlabel = new JLabel("ID: (required)");
			panel.add(IDlabel); panel.add(id);
			JLabel Namelabel = new JLabel("Name: (required)");
			panel.add(Namelabel); panel.add(name);
			JLabel developerlabel = new JLabel("Developer:");
			panel.add(developerlabel); panel.add(developer);
			JLabel played_versionlabel = new JLabel("Last version you played:");
			panel.add(played_versionlabel); panel.add(played_version);
			JLabel dateof_lastplaylabel = new JLabel("Last time you updated the game:");
			panel.add(dateof_lastplaylabel); panel.add(dateof_lastplay);
			JLabel user_ratinglabel = new JLabel("Rating by you:");
			panel.add(user_ratinglabel); panel.add(user_rating);
			JLabel newest_versionlabel = new JLabel("Online newest version:");
			panel.add(newest_versionlabel); panel.add(newest_version);
			JLabel dateOfLastUpdatelabel = new JLabel("Online date of last update:");
			panel.add(dateOfLastUpdatelabel); panel.add(dateOfLastUpdate);
			JLabel people_ratinglabel = new JLabel("Rating by online people:");
			panel.add(people_ratinglabel); panel.add(people_rating);
			JLabel howFarUserPlayedlabel = new JLabel("How far you progressed in the game:");
			panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
			JLabel stillOnPclabel = new JLabel("Is the game still on pc?");
			panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
			JLabel enginelabel = new JLabel("Engine:");
			panel.add(enginelabel); panel.add(enginePanel);
			JLabel oslabel = new JLabel("OS:");
			panel.add(oslabel); panel.add(osPanel);
			JLabel selfNotelabel = new JLabel("Self note:");
			panel.add(selfNotelabel); panel.add(selfNote);

			// TODO move everything to the left
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			//panel.setLayout(new GridLayout(15, 1, 1, 1));
			JOptionPane.showMessageDialog(null, panel, "Add game", JOptionPane.PLAIN_MESSAGE);

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
			String selfNoteValue = selfNote.getText();

			if (idValue.equals("")) { JOptionPane.showMessageDialog(null, "ID is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
			if (nameValue.equals("")) { JOptionPane.showMessageDialog(null, "name is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
			if (isIDInDatabase.isInDatabase(idValue)) {
				JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" already exists", "Error", JOptionPane.ERROR_MESSAGE); return;
			} else {
				try{
					Document dom = saveLoadDoc.loadDocument();
					NodeList source = dom.getElementsByTagName("source");
					for (int i = 0; i < source.getLength(); i++) {
						Node sourceNode = source.item(i);
						if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
							Element newGame = dom.createElement("game");
							Element newName = dom.createElement("name");
							Element newDeveloper = dom.createElement("developer");
							Element newPlayed_version = dom.createElement("played_version");
							Element newDateof_lastplay = dom.createElement("dateof_lastplay");
							Element newUser_rating = dom.createElement("user_rating");
							Element newNewest_version = dom.createElement("newest_version");
							Element newDateof_lastupate = dom.createElement("dateof_lastupate");
							Element newPeople_rating = dom.createElement("people_rating");
							Element newHowFarUserPlayed = dom.createElement("howFarUserPlayed");
							Element newstillOnPc = dom.createElement("stillOnPc");
							Element newEngine = dom.createElement("engine");
							Element newOS = dom.createElement("os");
							Element newSelfNote = dom.createElement("selfNote");
							newGame.setAttribute("id", idValue);
							newName.setTextContent(nameValue);
							newDeveloper.setTextContent(developerValue);
							newPlayed_version.setTextContent(played_versionValue);
							newDateof_lastplay.setTextContent(dateof_lastplayValue);
							newUser_rating.setTextContent(user_ratingValue);
							newNewest_version.setTextContent(newest_versionValue);
							newDateof_lastupate.setTextContent(dateOfLastUpdateValue);
							newPeople_rating.setTextContent(people_ratingValue);
							newHowFarUserPlayed.setTextContent(howFarUserPlayedValue);
							newstillOnPc.setTextContent(stillOnPcValue);
							newEngine.setTextContent(engineValue);
							newOS.setTextContent(osValue);
							newSelfNote.setTextContent(selfNoteValue);
							newGame.appendChild(newName);
							newGame.appendChild(newDeveloper);
							newGame.appendChild(newPlayed_version);
							newGame.appendChild(newDateof_lastplay);
							newGame.appendChild(newUser_rating);
							newGame.appendChild(newNewest_version);
							newGame.appendChild(newDateof_lastupate);
							newGame.appendChild(newPeople_rating);
							newGame.appendChild(newHowFarUserPlayed);
							newGame.appendChild(newstillOnPc);
							newGame.appendChild(newEngine);
							newGame.appendChild(newOS);
							newGame.appendChild(newSelfNote);
							sourceNode.appendChild(newGame);
							saveLoadDoc.saveDocument(dom);
							_initFrame.refreshTable();
							JOptionPane.showMessageDialog(null, nameValue+" with id: "+idValue+" has been added", "Success", JOptionPane.INFORMATION_MESSAGE);

							int option = JOptionPane.showConfirmDialog(null, "Do you want to add another game?", "Add game", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.NO_OPTION) { repeat = false; break; } else { break; }
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
