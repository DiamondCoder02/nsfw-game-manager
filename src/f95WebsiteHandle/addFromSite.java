package f95WebsiteHandle;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import windowJframe._initFrame;
import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.saveLoadDoc;

public class addFromSite {
	public static void addFromF95(){
		boolean repeat = true;
		while (repeat) {
			String idValue = JOptionPane.showInputDialog(null, "Enter the id of the game you want to add", "Add game", JOptionPane.PLAIN_MESSAGE);
			if (idValue == null) { JOptionPane.showMessageDialog(null, "You must enter an id", "Error", JOptionPane.ERROR_MESSAGE); break; }

			if (isIDInDatabase.isInDatabase(idValue, "f95")) {
				JOptionPane.showMessageDialog(null, "The id you entered is already in the F95zone database", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}

			String[] output = loadSite.getUrlContents(idValue);
			if (output == null) { break; }
			// Name, Developer, Newest version, Date of last update, People rating, Engine, OS
			String nameValue = output[0].toString();
			String developerValue = output[1].toString();
			String newest_versionValue = output[2].toString();
			String dateOfLastUpdateValue = output[3].toString();
			String people_ratingValue = output[4].toString();
			String engineValue = output[5].toString();
			String osValue = output[6].toString();

			JPanel panel = new JPanel(new GridLayout(6*2, 0));
			JTextField played_version = new JTextField(15);
			JTextField dateof_lastplay = new JTextField(20);
			JTextField user_rating = new JTextField(20);
			
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

			JTextField selfNote = new JTextField(50);

			howFarUserPlayedPanel.setLayout(new BoxLayout(howFarUserPlayedPanel, BoxLayout.X_AXIS));
			stillOnPcPanel.setLayout(new BoxLayout(stillOnPcPanel, BoxLayout.X_AXIS));

			JLabel played_versionlabel = new JLabel("Last version you played:");
			panel.add(played_versionlabel); panel.add(played_version);
			JLabel dateof_lastplaylabel = new JLabel("Last time you updated the game:");
			panel.add(dateof_lastplaylabel); panel.add(dateof_lastplay);
			JLabel user_ratinglabel = new JLabel("Rating by you:");
			panel.add(user_ratinglabel); panel.add(user_rating);
			JLabel howFarUserPlayedlabel = new JLabel("How far you progressed in the game:");
			panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
			JLabel stillOnPclabel = new JLabel("Is the game still on pc?");
			panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
			JLabel selfNotelabel = new JLabel("Self note:");
			panel.add(selfNotelabel); panel.add(selfNote);
			JOptionPane.showMessageDialog(null, panel, "Add game", JOptionPane.PLAIN_MESSAGE);

			String played_versionValue = played_version.getText();
			String dateof_lastplayValue = dateof_lastplay.getText();
			String user_ratingValue = user_rating.getText();
			String howFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
			String stillOnPcValue = stillOnPc.getSelection().getActionCommand();
			String selfNoteValue = selfNote.getText();
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
						Element newOS = dom.createElement("OS");
						Element newSelfNote = dom.createElement("selfNote");
						newGame.setAttribute("from", "f95");
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
