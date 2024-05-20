package folderHandling.initialFileLoading;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandling.ADocHandle;
import integrationCheck.defaultValues;

public class loadGames {
	static String[] bac = loadLanguage.basic;
	public static Object[][] data = null;
	/**
	 * @param mainDirectory - The needed path to the XML file without the file name or extension
	 * @return - Boolean - If data was successfully loaded from the XML file into Object[][] data variable 
	 */
	public static Boolean loadGamesFromXML(String mainDirectory){
		System.out.println("loadGames.loadGamesFromXML reloaded again...");
		Document dom = ADocHandle.load(mainDirectory);
		String[] columnNames = enabledRows();
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
							// System.out.println(columnNames[n]);
							try{
								switch (columnNames[n]) {
									case "shownColumns": break;
									case "site": allDataFromFile[counter][n] = e.getAttribute("from").trim(); break;
									case "id": allDataFromFile[counter][n] = e.getAttribute("id").trim(); break;
									case "name": allDataFromFile[counter][n] = e.getElementsByTagName("name").item(0).getTextContent().trim(); break;
									case "developer": allDataFromFile[counter][n] = e.getElementsByTagName("developer").item(0).getTextContent().trim(); break;
									case "playedVersion": allDataFromFile[counter][n] = e.getElementsByTagName("played_version").item(0).getTextContent().trim(); break;
									case "lastTimePlayed": allDataFromFile[counter][n] = e.getElementsByTagName("dateof_lastplay").item(0).getTextContent().trim(); break;
									case "rated": allDataFromFile[counter][n] = e.getElementsByTagName("user_rating").item(0).getTextContent().trim(); break;
									case "newestVersionOnline": { allDataFromFile[counter][n] = e.getElementsByTagName("newest_version").item(0).getTextContent().trim(); if (e.getAttribute("finished").trim().equals("true")){allDataFromFile[counter][n] += " (Last version)";} break;}
									case "lastDateTimeUpdated": allDataFromFile[counter][n] = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim(); break;
									case "peopleOnlineRating": allDataFromFile[counter][n] = e.getElementsByTagName("people_rating").item(0).getTextContent().trim(); break;
									case "localPlayerProgress": allDataFromFile[counter][n] = e.getElementsByTagName("howFarUserPlayed").item(0).getTextContent().trim(); break;
									case "gameStillOnPc": allDataFromFile[counter][n] = e.getElementsByTagName("stillOnPc").item(0).getTextContent().trim(); break;
									case "gameEngine": allDataFromFile[counter][n] = e.getElementsByTagName("engine").item(0).getTextContent().trim(); break;
									case "os": allDataFromFile[counter][n] = e.getElementsByTagName("OS").item(0).getTextContent().trim(); break;
									case "language": allDataFromFile[counter][n] = e.getElementsByTagName("language").item(0).getTextContent().trim(); break;
									case "localPersonalNotes": allDataFromFile[counter][n] = e.getElementsByTagName("selfNote").item(0).getTextContent().trim(); break;
									default:  allDataFromFile[counter][n] = "N/A???"; System.out.println("allDataFromFile: N/A" + columnNames[n]);break;
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
		data = allDataFromFile2;
		return true;
	}

	/**
	 * Site / ID / Name / Developer / Played version / Last time play / Rated / Newest version / <p>
	 * Last update / People rating / Player progress / Still on pc? / Engine / OS / Language / Personal notes
	 * @return String[] - Returns the enabled rows from the settings
	 * @see src/integrationCheck/defaultValues.java
	 */
	private static String[] enabledRows() {
		String[] allColumnNames = defaultValues.settings[1];
		String[] enabledRows = new String[allColumnNames.length-1];
		Boolean[] enabledCol = loadSettings.shownColumns;
		int counter = 0;
		for (int i = 0; i < enabledCol.length; i++) {
			if (enabledCol[i]) {
				enabledRows[counter] = allColumnNames[i+1];
				counter++;
			} 
		}
		String[] enabledRows2 = new String[counter];
		for (int i = 0; i < counter; i++) {
			enabledRows2[i] = enabledRows[i];
		}

		return enabledRows2;
	}
}
