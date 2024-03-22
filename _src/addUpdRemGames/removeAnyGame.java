package addUpdRemGames;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandle.isIDInDatabase;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;
import main.langLoad;
import main.mainInit;
import main.application.frameCreate;

public class removeAnyGame {
	static String[] base = langLoad.base, basic = langLoad.basic, jla = langLoad.jlapa, folder = langLoad.folder, jrb = langLoad.jrabu;
	public static void removeOneGameFromFile(String fromValue){
		boolean repeat = true;
		while (repeat) {
			JOptionPane optionPane = new JOptionPane();
			JTextField id = new JTextField();
			Object[] message = {
				jla[0]!=null?jla[0]:"ID of the game to remove:", id
			};
			optionPane.setMessage(message);
			optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
			JDialog dialog = optionPane.createDialog(null, base[4]!=null?base[4]:"Remove game");
			dialog.setVisible(true);
			String idValue = id.getText();
			if (idValue.equals("")) { JOptionPane.showMessageDialog(null, basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }
			if (isIDInDatabase.isInDatabase(idValue, fromValue)) {
				try{
					Document dom = saveLoadDoc.loadDocument(mainInit.databasePath);
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
									String from = e.getAttribute("from").trim();
									if ( ids.equals(idValue) && from.equals(fromValue)) {
										String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
										int option = JOptionPane.showConfirmDialog(null, name + ", \nId: "+ids+" "+(folder[14]!=null?folder[14]:"will be removed. Are you sure?"), base[4]!=null?base[4]:"Remove game", JOptionPane.OK_CANCEL_OPTION);
										if (option == JOptionPane.OK_OPTION) {
											sourceNode.removeChild(gameNode);
											saveLoadDoc.saveDocument(dom, mainInit.databasePath);
											frameCreate.refreshTable();
											JOptionPane.showMessageDialog(null, name + ", \nId: "+ids+" "+(folder[15]!=null?folder[15]:"has been removed."), base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
										} else { JOptionPane.showMessageDialog(null, folder[16]!=null?folder[16]:"Cancelled", base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE); }
										break;
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Id: "+idValue+" "+(basic[1]!=null?basic[1]:"doesn't exists"), base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int optionToRepeat = JOptionPane.showConfirmDialog(null, folder[17]!=null?folder[17]:"Do you want to delete another game?", base[4]!=null?base[4]:"Delete game", JOptionPane.YES_NO_OPTION);
			if (optionToRepeat != JOptionPane.OK_OPTION) { repeat = false; }
		}
	}
}
