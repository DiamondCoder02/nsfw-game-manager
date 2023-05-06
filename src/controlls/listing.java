package controlls;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class listing {
	public static void listAllGamesControl(File f) {
		try {
			/* 
			<nsfwgames>
				<source from="f95zone">
					<game id="19095">
						<name>2037 - Almost ready Inc.</name>
						<developer>MadAlice</developer>
						<played_version>v0.9.6</played_version>
						<dateof_lastupate>2020-06-22</dateof_lastupate> 
					</game>
				</source>
			</nsfwgames>
			*/
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(f);
			dom.normalize();
			// first get the source from f95zone
			NodeList source = dom.getElementsByTagName("source");
			// then loop through the games from source

			/* Print to console that looks like this:
			----------------------------------------------------------------- // 65 characters
			|id    | developer                      | played_version        | // 6|32|23 6+32+23=61+4=65
			| LastUpdate | name                                             | // 12|50 12+50=62+3=65
			-----------------------------------------------------------------
			|999999| asd     				  		| v0.1.1something		|
			| 6969-69-69  | asdasdasdasdasdasda								|
			-----------------------------------------------------------------
			*/
			System.out.println("---------------------------------------------------------------------");
			System.out.println("|id    | developer                          | played_version        |");
			System.out.println("| LastUpdate | name                                                 |");
			System.out.println("---------------------------------------------------------------------");
			for (int i = 0; i < source.getLength(); i++) {
				Node sourceNode = source.item(i);
				if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
					NodeList game = sourceNode.getChildNodes();
					for (int j = 0; j < game.getLength(); j++) {
						Node gameNode = game.item(j);
						if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
							Element e = (Element) gameNode;
							String id = e.getAttribute("id").trim();
							// For some reason the for loop below doesn't work
							if (id.length() == 1) {id = "00000"+id;}
							else if (id.length() == 2) {id = "0000"+id;}
							else if (id.length() == 3) {id = "000"+id;}
							else if (id.length() == 4) {id = "00"+id;}
							else if (id.length() == 5) {id = "0"+id;}
							String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
							for (int k = 0; k < 50 - name.length(); k++) {name += " ";}
							//if (name.length() < 49) {System.out.println("Fuck");}
							String developer = e.getElementsByTagName("developer").item(0).getTextContent().trim();
							for (int k = 0; k < 32 - developer.length(); k++) {developer += " ";}
							//if (developer.length() < 31) {System.out.println("Fuck2");}
							String played_version = e.getElementsByTagName("played_version").item(0).getTextContent().trim();
							for (int k = 0; k < 23 - played_version.length(); k++) {played_version += " ";}
							//if (played_version.length() < 22) {System.out.println("Fuck3");}
							String dateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
							System.out.println("|" + id + "| " + developer + " | " + played_version + " |");
							System.out.println("| " + dateof_lastupate + " | " + name + " |");
							System.out.println("---------------------------------------------------------------------");
						}
					}
				}
			}
			/* 
			for (int i = 0; i < studentNodes.getLength(); i++) {
				Node n = studentNodes.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
					String major = e.getElementsByTagName("major").item(0).getTextContent().trim();
					int year = Integer.parseInt(e.getElementsByTagName("year").item(0).getTextContent().trim());
				}
			}
			*/

			/* 
			for (int i = 0; i < source.getLength(); i++) {
				NodeList game = source.item(i).getChildNodes();
				for (int j = 0; j < game.getLength(); j++) {
					System.out.println(game.item(j).getNodeName());
					System.out.println(game.item(j).getTextContent());
				}
			}
			*/
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
