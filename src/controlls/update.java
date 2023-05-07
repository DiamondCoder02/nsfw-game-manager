package controlls;

import java.time.LocalDate;
import java.util.Scanner;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class update {
	public static void updateGameControl(Document dom, Scanner keyboardScan) {
		boolean wantToContinue = true;
		try {
			keyboardScan.nextLine();
			NodeList source = dom.getElementsByTagName("source");
			while (wantToContinue){
				Boolean foundThing = false;
				System.out.println("Enter game id to update: ");
				String id = keyboardScan.nextLine();
				System.out.println("---------------");
				for (int i = 0; i < source.getLength(); i++) {
					Node sourceNode = source.item(i);
					if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
						NodeList game = sourceNode.getChildNodes();
						for (int j = 0; j < game.getLength(); j++) {
							Node gameNode = game.item(j);
							if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
								Element e = (Element) gameNode;
								String ids = e.getAttribute("id").trim();
								if (ids.equals(id)) {
									System.out.println("---------------------------------------------------------------------");
									System.out.println("|id    | developer                          | played_version        |");
									System.out.println("| LastUpdate | name                                                 |");
									System.out.println("---------------------------------------------------------------------");
									String oldname = e.getElementsByTagName("name").item(0).getTextContent().trim();
									String olddeveloper = e.getElementsByTagName("developer").item(0).getTextContent().trim();
									String oldplayed_version = e.getElementsByTagName("played_version").item(0).getTextContent().trim();
									String olddateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
									if (ids.length() == 1) {ids = "00000"+ids;}else if (ids.length() == 2) {ids = "0000"+ids;}else if (ids.length() == 3) {ids = "000"+ids;}else if (ids.length() == 4) {ids = "00"+ids;}else if (ids.length() == 5) {ids = "0"+ids;}
									System.out.println("|"+ids+"| "+olddeveloper+"| "+oldplayed_version+"|");
									System.out.println("| "+olddateof_lastupate+" | "+oldname+"|");
									System.out.println("---------------------------------------------------------------------");
									System.out.println("Enter new game name: ");
									String newname = keyboardScan.nextLine();
									System.out.println("Enter new developer name: ");
									String newdeveloper = keyboardScan.nextLine();
									System.out.println("Enter new played version: ");
									String newplayed_version = keyboardScan.nextLine();
									// set date as current date
									String currentTimeAsOfRunning = LocalDate.now().toString();
									String newdateof_lastupate = String.format(currentTimeAsOfRunning, "yyyy-mm-dd");

									System.out.print("\033[H\033[2J");
									System.out.flush();
									System.out.println("---------------------------------------------------------------------");
									System.out.println("|id    | developer                          | played_version        |");
									System.out.println("| LastUpdate | name                                                 |");
									System.out.println("---------------------------------------------------------------------");
									System.out.println("|"+ids+"| "+olddeveloper+"| "+oldplayed_version+"|");
									System.out.println("| "+olddateof_lastupate+" | "+oldname+"|");
									System.out.println("-/\\-old-----new-\\/-------------------------------------------------");
									System.out.println("|"+ids+"| "+newdeveloper+"| "+newplayed_version+"|");
									System.out.println("| "+newdateof_lastupate+" | "+newname+"|");
									System.out.println("---------------------------------------------------------------------");

									System.out.println("---------------");
									System.out.println("Do you want to update this game? (y/n)");
									String answer2 = keyboardScan.nextLine();
									System.out.println("---------------");
									if (answer2.equals("n")) {break;}
									e.getElementsByTagName("name").item(0).setTextContent(newname);
									e.getElementsByTagName("developer").item(0).setTextContent(newdeveloper);
									e.getElementsByTagName("played_version").item(0).setTextContent(newplayed_version);
									e.getElementsByTagName("dateof_lastupate").item(0).setTextContent(newdateof_lastupate);
									System.out.println("Game with id: "+ids+" has been updated");
									foundThing = true;
									break;
								}
							}
						}
					}
				}
				if (!foundThing) {System.out.println("Game with id: "+id+" was not found or terminated");}
				System.out.println("---------------");
				System.out.println("Do you want to update another game? (y/n)");
				String answer = keyboardScan.nextLine();
				System.out.println("---------------");
				if (answer.equals("n")) {wantToContinue = false;}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domsource = new DOMSource(dom);
				StreamResult result = new StreamResult("hentai_out.xml");
				transformer.transform(domsource, result);
				System.out.println("---------------");
				System.out.println("Game(s) updated");
				System.out.println("---------------");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
