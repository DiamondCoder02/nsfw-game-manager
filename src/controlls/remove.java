package controlls;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class remove {
	public static void removeGameControl(File file, Scanner keyboardScan) {
		boolean wantToContinue = true;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			// first get the source from f95zone
			NodeList source = dom.getElementsByTagName("source");
			while (wantToContinue){
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
								if (id.equals(ids)) {
									sourceNode.removeChild(gameNode);
									System.out.println("Game with id: "+id+" has been removed");
									break;
								}
							}
						}
					}
				}
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
