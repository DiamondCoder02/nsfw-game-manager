package xmlFolderHandle;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class _initXml {
	public static String[] allColumns(Document dom) {
		String columnNames[] = new String[30];
		Integer counter = 0;
		try{
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList showncolumns = e.getElementsByTagName("showncolumns");
				for (int i = 0; i < showncolumns.getLength(); i++) {
					Node showncolumnsNode = showncolumns.item(i);
					if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) showncolumnsNode;
						String enabled = e2.getAttribute("enabled").trim();
						if (enabled.equals("true")) {
							String column = e2.getTextContent().trim();
							columnNames[counter] = column;
							counter++;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] finalColumnNames = new String[counter];
		for (int i = 0; i < counter; i++) {
			finalColumnNames[i] = columnNames[i];
		}
		return finalColumnNames;
	}

	public static Object[][] loadGames(Document dom, String[] columnNames) {
		Object[][] allDataFromFile = null;
		Integer counter = 0;
		NodeList source = dom.getElementsByTagName("source");
		for (int i = 0; i < source.getLength(); i++) {
			Node sourceNode = source.item(i);
			if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList game = sourceNode.getChildNodes();
				allDataFromFile = new Object[game.getLength()][columnNames.length];
				for (int j = 0; j < game.getLength(); j++) {
					Node gameNode = game.item(j);
					if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) gameNode;
						for (int n = 0; n < columnNames.length; n++) {
							switch (columnNames[n]) {
								case "Site": allDataFromFile[counter][n] = e.getAttribute("from").trim(); break;
								case "ID": allDataFromFile[counter][n] = e.getAttribute("id").trim(); break;
								case "Name": allDataFromFile[counter][n] = e.getElementsByTagName("name").item(0).getTextContent().trim(); break;
								case "Developer": allDataFromFile[counter][n] = e.getElementsByTagName("developer").item(0).getTextContent().trim(); break;
								case "Played version": allDataFromFile[counter][n] = e.getElementsByTagName("played_version").item(0).getTextContent().trim(); break;
								case "Last time play": allDataFromFile[counter][n] = e.getElementsByTagName("dateof_lastplay").item(0).getTextContent().trim(); break;
								case "Rated": allDataFromFile[counter][n] = e.getElementsByTagName("user_rating").item(0).getTextContent().trim(); break;
								case "Newest version": { allDataFromFile[counter][n] = e.getElementsByTagName("newest_version").item(0).getTextContent().trim(); if (e.getAttribute("finished").trim().equals("true")){allDataFromFile[counter][n] += " (Last version)";} break;}
								case "Last update": allDataFromFile[counter][n] = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim(); break;
								case "People rating": allDataFromFile[counter][n] = e.getElementsByTagName("people_rating").item(0).getTextContent().trim(); break;
								case "Player progress": allDataFromFile[counter][n] = e.getElementsByTagName("howFarUserPlayed").item(0).getTextContent().trim(); break;
								case "Still on pc?": allDataFromFile[counter][n] = e.getElementsByTagName("stillOnPc").item(0).getTextContent().trim(); break;
								case "Engine": allDataFromFile[counter][n] = e.getElementsByTagName("engine").item(0).getTextContent().trim(); break;
								case "OS": allDataFromFile[counter][n] = e.getElementsByTagName("OS").item(0).getTextContent().trim(); break;
								case "Personal Notes": allDataFromFile[counter][n] = e.getElementsByTagName("selfNote").item(0).getTextContent().trim(); break;
								default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!! (_initFrame)", "Error", JOptionPane.ERROR_MESSAGE); break;
							}
						}
						counter++;
					}
				}
			}
		}
		for (int i = 0; i < allDataFromFile.length; i++) {
			if (allDataFromFile[i][0] == null) {
				Object[][] allDataFromFile2 = new Object[i][columnNames.length];
				for (int j = 0; j < i; j++) { for (int n = 0; n < columnNames.length; n++) { allDataFromFile2[j][n] = allDataFromFile[j][n]; } }
				allDataFromFile = allDataFromFile2;
				break;
			}
		}
		return allDataFromFile;
	}
}
