package windowJframe;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.saveLoadDoc;

public class addGameToFile {
	public static void addOneGameToFile(){
		JOptionPane optionPane = new JOptionPane();
		JTextField id = new JTextField();
		JTextField name = new JTextField();
		JTextField developer = new JTextField();
		JTextField played_version = new JTextField();
		JTextField dateOfLastUpdate = new JTextField();
		Object[] message = {
			"ID: (required)", id,
			"Name: (required)", name,
			"Developer:", developer,
			"Played version:", played_version,
			"Date of last update:", dateOfLastUpdate
		};
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Add game");
		dialog.setVisible(true);
		String idValue = id.getText();
		String nameValue = name.getText();
		String developerValue = developer.getText();
		String played_versionValue = played_version.getText();
		String dateOfLastUpdateValue = dateOfLastUpdate.getText();
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
						NodeList game = sourceNode.getChildNodes();
						for (int j = 0; j < game.getLength(); j++) {
							Node gameNode = game.item(j);
							if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
								Element e = (Element) gameNode;
								String ids = e.getAttribute("id").trim();
								if (ids.equals(idValue)) {
									JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" already exists", "Error", JOptionPane.ERROR_MESSAGE); break;
								} else {
									Element newGame = dom.createElement("game");
									Element newName = dom.createElement("name");
									Element newDeveloper = dom.createElement("developer");
									Element newPlayed_version = dom.createElement("played_version");
									Element newDateof_lastupate = dom.createElement("dateof_lastupate");
									newGame.setAttribute("id", idValue);
									newName.setTextContent(nameValue);
									newDeveloper.setTextContent(developerValue);
									newPlayed_version.setTextContent(played_versionValue);
									newDateof_lastupate.setTextContent(dateOfLastUpdateValue);
									newGame.appendChild(newName);
									newGame.appendChild(newDeveloper);
									newGame.appendChild(newPlayed_version);
									newGame.appendChild(newDateof_lastupate);
									sourceNode.appendChild(newGame);
									saveLoadDoc.saveDocument(dom);
									JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" has been added", "Success", JOptionPane.INFORMATION_MESSAGE);
									break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
