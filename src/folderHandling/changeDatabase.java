package folderHandling;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandling.changeDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import frontendGUI.mainFrame;
import integrationCheck.defaultValues;

public class changeDatabase {
	static String[] jla = loadLanguage.jlapa, folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	static String[] base = loadLanguage.base, basic = loadLanguage.basic;
	// TODO - optimise this
	public static boolean addNewGameIntoDatabase(String fromSite, String[] gameInfo) {
		String idValue = gameInfo[0], nameValue = gameInfo[1], developerValue = gameInfo[2], 
				played_versionValue = gameInfo[3], dateof_lastplayValue = gameInfo[4], 
				user_ratingValue = gameInfo[5], newest_versionValue = gameInfo[6], 
				dateOfLastUpdateValue = gameInfo[7], people_ratingValue = gameInfo[8], 
				howFarUserPlayedValue = gameInfo[9], stillOnPcValue = gameInfo[10], 
				engineValue = gameInfo[11], osValue = gameInfo[12], 
				languageValue = gameInfo[13], selfNoteValue = gameInfo[14];

		try{
			Document dom = ADocHandle.load(defaultValues.mainDirectory + "/hentai.xml");
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
					Element newLanguage = dom.createElement("language");
					Element newSelfNote = dom.createElement("selfNote");
					newGame.setAttribute("from", fromSite);
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
					newLanguage.setTextContent(languageValue);
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
					newGame.appendChild(newLanguage);
					newGame.appendChild(newSelfNote);
					sourceNode.appendChild(newGame);
					ADocHandle.save(dom, defaultValues.mainDirectory + "/hentai.xml");
					mainFrame.refreshTable();
					JOptionPane.showMessageDialog(null, nameValue+", \nId: "+idValue+" "+(basic[2]==null?"has been added":basic[2]), base[0]==null?"Success":base[0], JOptionPane.INFORMATION_MESSAGE);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean removeGameFromDatabase(String fromSite) {
		JOptionPane optionPane = new JOptionPane();
		JTextField id = new JTextField();
		Object[] message = { jla[0]!=null?jla[0]:"ID of the game to remove:", id };
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, base[4]!=null?base[4]:"Remove game");
		dialog.setVisible(true);
		String idValue = id.getText();
		if (idValue.equals("")) { JOptionPane.showMessageDialog(null, basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return false; }
		if (checkDatabase.isInDatabase(idValue, fromSite)) {
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
		} else {
			JOptionPane.showMessageDialog(null, "Id: "+idValue+" "+(basic[1]!=null?basic[1]:"doesn't exists"), base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
