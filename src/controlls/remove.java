package controlls;

import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class remove {
	public static void removeGameControl(Document dom, Scanner keyboardScan) {
		boolean wantToContinue = true;
		try {
			keyboardScan.nextLine();
			NodeList source = dom.getElementsByTagName("source");
			while (wantToContinue){
				Boolean foundThing = false;
				System.out.println("Enter game id: ");
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
									String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
									System.out.println(name+" with id: "+ids+" has been removed");
									sourceNode.removeChild(gameNode);
									foundThing = true;
									break;
								}
							}
						}
					}
				}
				if (!foundThing) {System.out.println("Game with id: "+id+" was not found");}
				System.out.println("---------------");
				System.out.println("Do you want to remove another game? (y/n)");
				String answer = keyboardScan.nextLine();
				System.out.println("---------------");
				if (answer.equals("n")) {wantToContinue = false;}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
