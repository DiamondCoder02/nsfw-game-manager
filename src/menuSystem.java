import java.io.File;
import java.util.Scanner;

import controlls.*;

public class menuSystem {
	public static void main(String[] args) {
		System.out.print("\033[H\033[2J");
		System.out.flush();

		File f = new File("hentai.xml");
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
					listing.listAllGamesControl(f);
					break;
				case 1:
					System.out.println("---------------");
					System.out.println("Add new game");
					System.out.println("---------------");
					add.addGameControl(f, keyboardScan);
					break;
				case 2:
					System.out.println("---------------");
					System.out.println("Remove game");
					System.out.println("---------------");
					remove.removeGameControl(f, keyboardScan);
					break;
				case 3:
					System.out.println("---------------");
					System.out.println("Update game info");
					System.out.println("---------------");
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
	}
}
