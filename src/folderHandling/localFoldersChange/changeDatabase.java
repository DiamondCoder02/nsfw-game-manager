package folderHandling.localFoldersChange;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandling.ADocHandle;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.changeDatabase;
import frontendGUI.mainFrame;
import integrationCheck.defaultValues;

public class changeDatabase {
	static String[] jla = loadLanguage.jlapa, folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	static String[] base = loadLanguage.base, basic = loadLanguage.basic;
	public static boolean addNewGameIntoDatabase(String fromSite, String[] gameInfo) {
		try{
			Document dom = ADocHandle.load(defaultValues.mainDirectory + "/hentai.xml");
			NodeList source = dom.getElementsByTagName("source");
			String[][] game = defaultValues.games;
			for (int i = 0; i < source.getLength(); i++) {
				Node sourceNode = source.item(i);
				if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
					Element newGame = dom.createElement("game");
					newGame.setAttribute("from", fromSite);
					newGame.setAttribute("id", gameInfo[0]);
					for (int j = 1; j < game.length; j++) {
						Element newElement = dom.createElement(game[j][0]);
						newElement.setTextContent(gameInfo[j]);
						newGame.appendChild(newElement);
					}
					sourceNode.appendChild(newGame);
					ADocHandle.save(dom, defaultValues.mainDirectory + "/hentai.xml");
					mainFrame.refreshTable();
					JOptionPane.showMessageDialog(null, gameInfo[1]+", \nId: "+gameInfo[0]+" "+(basic[2]==null?"has been added":basic[2]), base[0]==null?"Success":base[0], JOptionPane.INFORMATION_MESSAGE);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean removeGameFromDatabase(String idValue, String fromSite) {
		try{
			Document dom = ADocHandle.load(defaultValues.mainDirectory + "/hentai.xml");
			Node sourceNode = ADocHandle.getSourceNodeFromDB(dom);
			NodeList game = sourceNode.getChildNodes();
			for (int j = 0; j < game.getLength(); j++) {
				Node gameNode = game.item(j);
				if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) gameNode;
					String ids = e.getAttribute("id").trim();
					String from = e.getAttribute("from").trim();
					if ( ids.equals(idValue) && from.equals(fromSite)) {
						String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
						int option = JOptionPane.showConfirmDialog(null, name + ", \nId: "+ids+" "+(folder[14]!=null?folder[14]:"will be removed. Are you sure?"), base[4]!=null?base[4]:"Remove game", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							sourceNode.removeChild(gameNode);
							ADocHandle.save(dom, defaultValues.mainDirectory + "/hentai.xml");
							mainFrame.refreshTable();
							JOptionPane.showMessageDialog(null, name + ", \nId: "+ids+" "+(folder[15]!=null?folder[15]:"has been removed."), base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
						} else { JOptionPane.showMessageDialog(null, folder[16]!=null?folder[16]:"Cancelled", base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE); }
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
