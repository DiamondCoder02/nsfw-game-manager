package controlls;

import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class update {
	public static void updateGameControl(Document dom, Scanner keyboardScan) {
		boolean wantToContinue = true;
		try {
			keyboardScan.nextLine();
			NodeList source = dom.getElementsByTagName("source");
			while (wantToContinue){
				
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
