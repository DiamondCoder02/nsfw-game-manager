package controlls;

import java.time.LocalDate;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class add {
	public static void textEasy(Boolean flush, Boolean top, Boolean bottom, String text){
		if (flush) {System.out.print("\033[H\033[2J");System.out.flush();}
		if (top) {System.out.println("---------------");}
		System.out.println(text);
		if (bottom) {System.out.println("---------------");}
	}
	// Thanks to above code from ~140 print lines to ~20 print lines

	public static void addGameControl(Document dom, Scanner keyboardScan) {
		boolean wantToContinue = true;
		try {
			keyboardScan.nextLine();
			while (wantToContinue) {
				textEasy(true, true, false, "Enter game id: ");
				String id = keyboardScan.nextLine();
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
								if (ids.equals(id)) {
									textEasy(true, true, false, "Game with id: "+id+" already exists");
									break;
								} else {
									textEasy(false, true, false, "Enter game name: ");
									String name = keyboardScan.nextLine();
									textEasy(false, true, false, "Enter developer name:");
									String developer = keyboardScan.nextLine();
									textEasy(false, true, false, "Enter played version:");
									String played_version = keyboardScan.nextLine();
									String currentTimeAsOfRunning = LocalDate.now().toString();
									String dateof_lastupate = String.format(currentTimeAsOfRunning, "yyyy-mm-dd");

									Element newGame = dom.createElement("game");
									Element newName = dom.createElement("name");
									Element newDeveloper = dom.createElement("developer");
									Element newPlayed_version = dom.createElement("played_version");
									Element newDateof_lastupate = dom.createElement("dateof_lastupate");
									newGame.setAttribute("id", id);
									newName.setTextContent(name);
									newDeveloper.setTextContent(developer);
									newPlayed_version.setTextContent(played_version);
									newDateof_lastupate.setTextContent(dateof_lastupate);
									newGame.appendChild(newName);
									newGame.appendChild(newDeveloper);
									newGame.appendChild(newPlayed_version);
									newGame.appendChild(newDateof_lastupate);
									sourceNode.appendChild(newGame);

									textEasy(true, true, false, "Game with id: "+id+" has been added");
									break;
								}
							}
						}
					}
				}
				textEasy(false, true, false, "Do you want to add another game? (y/n)");
				String answer = keyboardScan.nextLine();
				if (answer.equals("n")) { wantToContinue = false; }
			}
			textEasy(true, true, true, "Game(s) saved");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
