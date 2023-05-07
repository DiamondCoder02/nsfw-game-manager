import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import controlls.*;

public class menuSystem {
	public static void main(String[] args) {
		try {
			File file = new File("test.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			Scanner keyboardScan = new Scanner(System.in);

			textEasy("Welcome to a game database\nPress enter to continue:");
			keyboardScan.nextLine();
			boolean stayInMenu = true;
			while (stayInMenu) {
				System.out.println("0. List all games");
				System.out.println("1. Add new game");
				System.out.println("2. Remove game");
				System.out.println("3. Update game info");
				System.out.println("4. Exit");
				System.out.print("Choose an option: ");

				int option = keyboardScan.nextInt();
				switch (option) {
					case 0:
						textEasy("List all games");
						listing.listAllGamesControl(dom);
						break;
					case 1:
						textEasy("Add new game");
						add.addGameControl(dom, keyboardScan);
						break;
					case 2:
						textEasy("Remove game");
						remove.removeGameControl(dom, keyboardScan);
						break;
					case 3:
						textEasy("Update game info");
						update.updateGameControl(dom, keyboardScan);
						break;
					case 4:
						textEasy("Thank you for using this program!");
						stayInMenu = false;
						keyboardScan.close();
						break;
					default:
						textEasy("Invalid option");
						break;
				}
			}
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void textEasy(String message) {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("---------------");
		System.out.println(message);
		System.out.println("---------------");
	}
}
