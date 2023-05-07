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
		try{
			File file = new File("hentai.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();

			System.out.print("\033[H\033[2J");
			System.out.flush();

			Scanner keyboardScan = new Scanner(System.in);
			System.out.println("---------------");
			System.out.println("Welcome to a game database\nPress enter to continue:\n(Note: nsfw possible)");
			System.out.println("---------------");
			keyboardScan.nextLine();
			boolean stayInMenu = true;
			while (stayInMenu){
				System.out.println("0. List all games");
				System.out.println("1. Add new game");
				System.out.println("2. Remove game");
				System.out.println("3. Update game info");
				System.out.println("4. Exit");
				System.out.print("Choose an option: ");
				
				int option = keyboardScan.nextInt();
				switch (option){
					case 0:
						System.out.println("---------------");
						System.out.println("List all games");
						System.out.println("---------------");
						listing.listAllGamesControl(dom);
						break;
					case 1:
						System.out.println("---------------");
						System.out.println("Add new game");
						System.out.println("---------------");
						add.addGameControl(dom, keyboardScan);
						break;
					case 2:
						System.out.println("---------------");
						System.out.println("Remove game");
						System.out.println("---------------");
						remove.removeGameControl(dom, keyboardScan);
						break;
					case 3:
						System.out.println("---------------");
						System.out.println("Update game info");
						System.out.println("---------------");
						update.updateGameControl(dom, keyboardScan);
						break;
					case 4:
						System.out.println("---------------");
						System.out.println("Exit");
						System.out.println("---------------");
						stayInMenu = false;
						keyboardScan.close();
						break;
					default:
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("---------------");
						System.out.println("Invalid option");
						System.out.println("---------------");
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
}
