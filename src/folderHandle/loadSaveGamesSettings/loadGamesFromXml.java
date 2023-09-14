package folderHandle.loadSaveGamesSettings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandle.xmlLoader;
import main.langLoad;
import main.mainInit;

public class loadGamesFromXml {
	static String[] bac = langLoad.basic;
	public static Object[][] loadGames() {
		Document domSetting = saveLoadDoc.loadDocument(mainInit.settingsPath);
		String[] columnNames = xmlLoader.allColumns(domSetting);
		Document dom = saveLoadDoc.loadDocument(mainInit.databasePath);
		return load(dom, columnNames);
	}

	private static Object[][] load(Document dom, String[] columnNames){
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
							try{
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
									case "Language": allDataFromFile[counter][n] = e.getElementsByTagName("language").item(0).getTextContent().trim(); break;
									case "Personal Notes": allDataFromFile[counter][n] = e.getElementsByTagName("selfNote").item(0).getTextContent().trim(); break;
									default:  allDataFromFile[counter][n] = "N/A???"; System.out.println("allDataFromFile: N/A");break;
								}
							} catch (Exception e2) {
								// allDataFromFile[counter][n] = "???N/A";
								// System.out.println("allDataFromFile: N/A");
							}
						}
						counter++;
					}
				}
			}
		}
		Object[][] allDataFromFile2 = new Object[counter][columnNames.length];
		for (int i = 0; i < allDataFromFile.length; i++) {
			for (int j = 0; j < allDataFromFile[i].length; j++) {
				if (allDataFromFile[i][j] != null) {
					allDataFromFile2[i][j] = allDataFromFile[i][j];
					if (allDataFromFile2[i][j].toString().contains("&amp;")) {
						allDataFromFile2[i][j] = allDataFromFile2[i][j].toString().replace("&amp;", "&");
					}
				}
			}
		}
		return allDataFromFile2;
	}
}
