package folderHandling.localFoldersChange;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandling.ADocHandle;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersChange.updateDatabase;
import frontendGUI.mainFrame;
import integrationCheck.defaultValues;

public class updateDatabase {
	static String[] jla = loadLanguage.jlapa, folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	static String[] base = loadLanguage.base, basic = loadLanguage.basic;
	/**
	 * Adds a new game into the database
	 * @param fromSite - The site where the game is from
	 * @param gameInfo - The information of the game<p>
	 * [ ID / Name / Developer / Played version / Last time play / Rated / Newest version / <p>
	 * Last update / People rating / Player progress / Still on pc? / Engine / OS / Language / Personal notes ]
	 * @return boolean - returns true if the game was added successfully
	 */
	public static boolean addNewGameIntoDatabase(String mainDir, String fromSite, String[] gameInfo) {
		try{
			Document dom = ADocHandle.load(mainDir);
			NodeList source = dom.getElementsByTagName("source");
			for (int i = 0; i < source.getLength(); i++) {
				Node sourceNode = source.item(i);
				if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
					Element newGame = dom.createElement("game");
					newGame.setAttribute("from", fromSite);
					newGame.setAttribute("id", gameInfo[0]);
					for (int j = 2; j < defaultValues.gameInfos.length; j++) {
						Element newElement = dom.createElement(defaultValues.gameInfos[j]);
						newElement.setTextContent(gameInfo[j-1]);
						newGame.appendChild(newElement);
					}
					sourceNode.appendChild(newGame);
					ADocHandle.save(dom, mainDir);
					mainFrame.refreshTable(mainDir);
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

	/**
	 * Removes a game from the database
	 * @param idValue - The ID of the game
	 * @param fromSite - The site where the game is from
	 * @return boolean - returns true if the game was removed successfully
	 */
	public static boolean removeGameFromDatabase(String mainDir, String idValue, String fromSite) {
		try{
			Document dom = ADocHandle.load(mainDir);
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
							ADocHandle.save(dom, mainDir);
							mainFrame.refreshTable(mainDir);
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
