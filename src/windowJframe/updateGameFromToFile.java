package windowJframe;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.saveLoadDoc;

public class updateGameFromToFile {
	public static void updateOneGameFromToFile(){
		JOptionPane optionPane = new JOptionPane();
		JTextField id = new JTextField();
		Object[] message = {
			"ID of the game to update:", id
		};
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Update game");
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
								if (ids.equals(idValue)) {
									String oldname = e.getElementsByTagName("name").item(0).getTextContent().trim();
									String olddeveloper = e.getElementsByTagName("developer").item(0).getTextContent().trim();
									String oldplayed_version = e.getElementsByTagName("played_version").item(0).getTextContent().trim();
									String olddateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
									String[] columnNames = {"ID", "Name", "Developer", "Played version", "Date of last update"};
									Object[][] data = {{ids, oldname, olddeveloper, oldplayed_version, olddateof_lastupate}};
									JTable table = new JTable(data, columnNames);
									table.setBounds(30, 40, 200, 300);
									// TODO table lol
									// setLayout(new BorderLayout());
									// add(table.getTableHeader(), BorderLayout.PAGE_START);
									// add(table, BorderLayout.CENTER);
									JTextField newname = new JTextField();
									JTextField newdeveloper = new JTextField();
									JTextField newplayed_version = new JTextField();
									JTextField newdateof_lastupate = new JTextField();
									Object[] message2 = {
										"ID: "+ids,
										"Name: (required)", newname,
										"Developer:", newdeveloper,
										"Played version:", newplayed_version,
										"Date of last update:", newdateof_lastupate
									};
									int option = JOptionPane.showConfirmDialog(null, message2, "Update game", JOptionPane.OK_CANCEL_OPTION);
									if (option == JOptionPane.OK_OPTION) {
										String newnameValue = newname.getText();
										String newdeveloperValue = newdeveloper.getText();
										String newplayed_versionValue = newplayed_version.getText();
										String newdateof_lastupateValue = newdateof_lastupate.getText();
										if (newnameValue.equals("")) { JOptionPane.showMessageDialog(null, "name is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
										if (newdeveloperValue.equals("")) { newdeveloperValue = olddeveloper; }
										if (newplayed_versionValue.equals("")) { newplayed_versionValue = oldplayed_version; }
										if (newdateof_lastupateValue.equals("")) { newdateof_lastupateValue = olddateof_lastupate; }
										e.getElementsByTagName("name").item(0).setTextContent(newnameValue);
										e.getElementsByTagName("developer").item(0).setTextContent(newdeveloperValue);
										e.getElementsByTagName("played_version").item(0).setTextContent(newplayed_versionValue);
										e.getElementsByTagName("dateof_lastupate").item(0).setTextContent(newdateof_lastupateValue);
										saveLoadDoc.saveDocument(dom);
										JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" has been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
										break;
									} else { JOptionPane.showMessageDialog(null, "Cancelled", "Success", JOptionPane.INFORMATION_MESSAGE); break; }
								} else {
									JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" doesn't exists", "Error", JOptionPane.ERROR_MESSAGE); break;
								}
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
