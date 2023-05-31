package windowJframe;

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
			JTextField dateOfLastUpdate = new JTextField(12);

			JRadioButton howFarUserPlayed_NotPlayed = new JRadioButton("Not played", true), howFarUserPlayed_Playing = new JRadioButton("In progress", false), howFarUserPlayed_Finished = new JRadioButton("Finish", false), howFarUserPlayed_100Percent = new JRadioButton("100% Finished", false);
			howFarUserPlayed_NotPlayed.setActionCommand("Not played"); howFarUserPlayed_Playing.setActionCommand("In progress"); howFarUserPlayed_Finished.setActionCommand("Finish"); howFarUserPlayed_100Percent.setActionCommand("100% Finished");
			ButtonGroup howFarUserPlayed = new ButtonGroup(); howFarUserPlayed.add(howFarUserPlayed_NotPlayed); howFarUserPlayed.add(howFarUserPlayed_Playing); howFarUserPlayed.add(howFarUserPlayed_Finished); howFarUserPlayed.add(howFarUserPlayed_100Percent);
			JPanel howFarUserPlayedPanel = new JPanel(); howFarUserPlayedPanel.add(howFarUserPlayed_NotPlayed); howFarUserPlayedPanel.add(howFarUserPlayed_Playing); howFarUserPlayedPanel.add(howFarUserPlayed_Finished); howFarUserPlayedPanel.add(howFarUserPlayed_100Percent);

			JRadioButton stillOnPc_yes = new JRadioButton("Yes", true), stillOnPc_no = new JRadioButton("No", false), stillOnPc_unknown = new JRadioButton("Unknown", false);
			stillOnPc_yes.setActionCommand("yes"); stillOnPc_no.setActionCommand("no"); stillOnPc_unknown.setActionCommand("unknown");
			ButtonGroup stillOnPc = new ButtonGroup(); stillOnPc.add(stillOnPc_yes); stillOnPc.add(stillOnPc_no); stillOnPc.add(stillOnPc_unknown);
			JPanel stillOnPcPanel = new JPanel(); stillOnPcPanel.add(stillOnPc_yes); stillOnPcPanel.add(stillOnPc_no); stillOnPcPanel.add(stillOnPc_unknown);

			JTextField engine = new JTextField(10);
			//<OS win="y" lin="n" mac="n" and="y" other="y">randomOS</OS>
			// JCheckBox os = new JCheckBox();
			JTextField selfNote = new JTextField(50);

			JLabel IDlabel = new JLabel("ID: (required)");
			panel.add(IDlabel); panel.add(id);
			JLabel Namelabel = new JLabel("Name: (required)");
			panel.add(Namelabel); panel.add(name);
			JLabel developerlabel = new JLabel("Developer:");
			panel.add(developerlabel); panel.add(developer);
			JLabel played_versionlabel = new JLabel("Played version:");
			panel.add(played_versionlabel); panel.add(played_version);
			JLabel dateOfLastUpdatelabel = new JLabel("Date of last update:");
			panel.add(dateOfLastUpdatelabel); panel.add(dateOfLastUpdate);
			JLabel howFarUserPlayedlabel = new JLabel("Player prograssion:");
			panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
			JLabel stillOnPclabel = new JLabel("Is the game still on pc?");
			panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
			JLabel enginelabel = new JLabel("Engine:");
			panel.add(enginelabel); panel.add(engine);
			// JLabel oslabel = new JLabel("OS:");
			// panel.add(oslabel);
			// panel.add(os);
			JLabel selfNotelabel = new JLabel("Self note:");
			panel.add(selfNotelabel); panel.add(selfNote);

			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			JOptionPane.showMessageDialog(null, panel, "Add game", JOptionPane.PLAIN_MESSAGE);

			String idValue = id.getText();
			String nameValue = name.getText();
			String developerValue = developer.getText();
			String played_versionValue = played_version.getText();
			String dateOfLastUpdateValue = dateOfLastUpdate.getText();
			String howFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
			String stillOnPcValue = stillOnPc.getSelection().getActionCommand();
			String engineValue = engine.getText();
			// String osValue = os.getText();
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
							Element newDateof_lastupate = dom.createElement("dateof_lastupate");
							Element newHowFarUserPlayed = dom.createElement("howFarUserPlayed");
							Element newstillOnPc = dom.createElement("stillOnPc");
							Element newEngine = dom.createElement("engine");
							//Element newOS = dom.createElement("OS");
							Element newSelfNote = dom.createElement("selfNote");
							newGame.setAttribute("id", idValue);
							newName.setTextContent(nameValue);
							newDeveloper.setTextContent(developerValue);
							newPlayed_version.setTextContent(played_versionValue);
							newDateof_lastupate.setTextContent(dateOfLastUpdateValue);
							newHowFarUserPlayed.setTextContent(howFarUserPlayedValue);
							newstillOnPc.setTextContent(stillOnPcValue);
							newEngine.setTextContent(engineValue);
							//newOS.setTextContent(osValue);
							newSelfNote.setTextContent(selfNoteValue);
							newGame.appendChild(newName);
							newGame.appendChild(newDeveloper);
							newGame.appendChild(newPlayed_version);
							newGame.appendChild(newDateof_lastupate);
							newGame.appendChild(newHowFarUserPlayed);
							newGame.appendChild(newstillOnPc);
							newGame.appendChild(newEngine);
							//newGame.appendChild(newOS);
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
