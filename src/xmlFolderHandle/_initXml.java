package xmlFolderHandle;

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
		Object[][] allDataFromFile = new Object[100][10];
		Integer counter = 0;
		NodeList source = dom.getElementsByTagName("source");
		for (int i = 0; i < source.getLength(); i++) {
			Node sourceNode = source.item(i);
			if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList game = sourceNode.getChildNodes();
				for (int j = 0; j < game.getLength(); j++) {
					Node gameNode = game.item(j);
					if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) gameNode;
						for (int n = 0; n < columnNames.length; n++) {
							switch (columnNames[n]) {
								case "ID": allDataFromFile[counter][n] = e.getAttribute("id").trim(); break;
								case "Name": allDataFromFile[counter][n] = e.getElementsByTagName("name").item(0).getTextContent().trim(); break;
								case "Developer": allDataFromFile[counter][n] = e.getElementsByTagName("developer").item(0).getTextContent().trim(); break;
								case "Played version": allDataFromFile[counter][n] = e.getElementsByTagName("played_version").item(0).getTextContent().trim(); break;
								case "Date of last update": allDataFromFile[counter][n] = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim(); break;
								case "Player prograssion": allDataFromFile[counter][n] = e.getElementsByTagName("howFarUserPlayed").item(0).getTextContent().trim(); break;
								case "Still on pc?": allDataFromFile[counter][n] = e.getElementsByTagName("deletedFromPc").item(0).getTextContent().trim(); break;
								case "Engine": allDataFromFile[counter][n] = e.getElementsByTagName("engine").item(0).getTextContent().trim(); break;
								// <OS win="y" lin="y" mac="n" and="y" ios="n" other="n">-</OS>
								// <OS win="y" lin="y" mac="n" and="y" ios="n" other="y">Random unknown os</OS>
								/* 
								case "OS": { 
									String win = e.getElementsByTagName("OS").item(0).getAttributes().getNamedItem("win").getTextContent().trim();
									String lin = e.getElementsByTagName("OS").item(0).getAttributes().getNamedItem("lin").getTextContent().trim();
									String mac = e.getElementsByTagName("OS").item(0).getAttributes().getNamedItem("mac").getTextContent().trim();
									String and = e.getElementsByTagName("OS").item(0).getAttributes().getNamedItem("and").getTextContent().trim();
									String ios = e.getElementsByTagName("OS").item(0).getAttributes().getNamedItem("ios").getTextContent().trim();
									String other = e.getElementsByTagName("OS").item(0).getAttributes().getNamedItem("other").getTextContent().trim();
									String os = "";
									if (win.equals("y")) {os += "Windows ";}
									if (lin.equals("y")) {os += "Linux ";}
									if (mac.equals("y")) {os += "Mac ";}
									if (and.equals("y")) {os += "Android ";}
									if (ios.equals("y")) {os += "Ios ";}
									if (other.equals("y")) {os += e.getElementsByTagName("OS").item(0).getTextContent().trim();}
									allDataFromFile[counter][n] = os.trim();
									break;
								}*/
								case "Personal Notes": allDataFromFile[counter][n] = e.getElementsByTagName("selfNote").item(0).getTextContent().trim(); break;
								default: break;
							}
						}
						counter++;
					}
				}
			}
		}
		return allDataFromFile;
	}
}
