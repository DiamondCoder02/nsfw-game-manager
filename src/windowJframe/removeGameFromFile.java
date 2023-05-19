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

public class removeGameFromFile {
	public static void removeOneGameFromFile(){
		JOptionPane optionPane = new JOptionPane();
		JTextField id = new JTextField();
		Object[] message = {
			"ID of the game to remove:", id
		};
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Remove game");
		dialog.setVisible(true);
		String idValue = id.getText();
		if (idValue.equals("")) { JOptionPane.showMessageDialog(null, "ID is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
		if (isIDInDatabase.isInDatabase(idValue)) {
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
								String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
								int option = JOptionPane.showConfirmDialog(null, "Game with id: "+ids+" and name: "+name+" will be removed. Are you sure?", "Remove game", JOptionPane.OK_CANCEL_OPTION);
								if (option == JOptionPane.OK_OPTION) {
									sourceNode.removeChild(gameNode);
									saveLoadDoc.saveDocument(dom);
									JOptionPane.showMessageDialog(null, "Game with id: "+ids+" and name: "+name+" has been removed", "Success", JOptionPane.INFORMATION_MESSAGE);
									break;
								} else { JOptionPane.showMessageDialog(null, "Cancelled", "Success", JOptionPane.INFORMATION_MESSAGE); break; }
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" doesn't exists", "Error", JOptionPane.ERROR_MESSAGE); return;
		}
	}
}
