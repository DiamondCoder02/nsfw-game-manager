package controlls;

import java.time.LocalDate;
import java.util.Scanner;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class add {
	public static void addGameControl(Document dom, Scanner keyboardScan) {
		boolean wantToContinue = true;
		try {
			Element root = dom.getDocumentElement();
			Element source = (Element) root.getElementsByTagName("source").item(0);
			keyboardScan.nextLine();

			while (wantToContinue) {
				System.out.println("Enter game id: ");
				String id = keyboardScan.nextLine();
				System.out.println("---------------");
				System.out.println("Enter game name: ");
				String name = keyboardScan.nextLine();
				System.out.println("---------------");
				System.out.println("Enter developer name: ");
				String developer = keyboardScan.nextLine();
				System.out.println("---------------");
				System.out.println("Enter played version: ");
				String played_version = keyboardScan.nextLine();
				System.out.println("---------------");
				// set date as current date
				String currentTimeAsOfRunning = LocalDate.now().toString();
				String dateof_lastupate = String.format(currentTimeAsOfRunning, "yyyy-mm-dd");

				Element newGame = dom.createElement("game");
				newGame.setAttribute("id", id);
				Element newName = dom.createElement("name");
				newName.setTextContent(name);
				Element newDeveloper = dom.createElement("developer");
				newDeveloper.setTextContent(developer);
				Element newPlayed_version = dom.createElement("played_version");
				newPlayed_version.setTextContent(played_version);
				Element newDateof_lastupate = dom.createElement("dateof_lastupate");
				newDateof_lastupate.setTextContent(dateof_lastupate.toString());

				newGame.appendChild(newName);
				newGame.appendChild(newDeveloper);
				newGame.appendChild(newPlayed_version);
				newGame.appendChild(newDateof_lastupate);

				source.appendChild(newGame);

				System.out.println("Do you want to add another game? (y/n)");
				String answer = keyboardScan.nextLine();
				System.out.println("---------------");
				if (answer.equals("n")) {
					wantToContinue = false;
					System.out.print("\033[H\033[2J");
					System.out.flush();
				}
			}
			DOMSource domsource = new DOMSource(dom);
			StreamResult result = new StreamResult("hentai_out.xml");
			// StreamResult result = new StreamResult(file);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(domsource, result);

			System.out.println("---------------");
			System.out.println("Game(s) saved");
			System.out.println("---------------");
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
}
