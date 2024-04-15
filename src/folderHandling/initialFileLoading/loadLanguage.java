package folderHandling.initialFileLoading;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class loadLanguage {
	// TODO - This needs to be changed
	// TODO - The way this was done is fucking retarded

	// TODO - Idea: Literally here do if a language is missing or some words are null
	// TODO - If something is missing default to english column but keep the row

	private static String[] tempBase = new String[30], tempBasic = new String[30], tempTabl = new String[30], 
	tempjLaPa = new String[30], tempjRaBu = new String[30], tempbuton = new String[30], tempFold = new String[30],
	tempSear = new String[30], tempRand = new String[30];
	public static String[] base, basic, tabl, jlapa, jrabu, buton, folder, serc, rand;
	public static String[] langChoices, lanMeans;

	public static boolean load(String path){
		Integer temp = 0; 
		String lastLang = "", split = ";";
		String[] tempAr = new String[30];
		int langindex = 0;
		BufferedReader br = null;

		try {
			// parsing a CSV file into BufferedReader class constructor
			br = new BufferedReader(new FileReader(path+"/language.csv"));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLanguages)", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try{
			String[] languages = br.readLine().split(split);// first line is the language names
			lanMeans = new String[languages.length-1];
			langChoices = new String[languages.length-1];
			for (int i = 0; i < languages.length; i++) { if (languages[i].equals(loadSettings.language)) { langindex = i; break; } }
			if (langindex == 0) { langindex = 1; }
			for (int i = 0; i < (languages.length-1); i++) { langChoices[i] = languages[i+1]; }
			languages = br.readLine().split(split); // Second line is language in own language
			for (int i = 0; i < (languages.length-1); i++) { lanMeans[i] = languages[i+1]; }

			String line = "";
			while ((line = br.readLine()) != null) { // returns a Boolean value
				String[] nextLine = line.split(split); // use comma as separator
				if (!lastLang.equals(nextLine[0])) {
					lastLang = nextLine[0];
					temp = 0;
				}
				tempAr[temp] = nextLine[langindex];
				if (tempAr[temp].contains("\\n")) {
					tempAr[temp] = tempAr[temp].replace("\\n", "\n");
				}
				switch (nextLine[0]) {
					case "base": tempBase[temp] = tempAr[temp]; break;
					case "basic": tempBasic[temp] = tempAr[temp]; break;
					case "tablec": tempTabl[temp] = tempAr[temp]; break;
					case "JLabPan": tempjLaPa[temp] = tempAr[temp]; break;
					case "JRadBut": tempjRaBu[temp] = tempAr[temp]; break;
					case "buttons": tempbuton[temp] = tempAr[temp]; break;
					case "folders": tempFold[temp] = tempAr[temp]; break;
					case "search": tempSear[temp] = tempAr[temp]; break;
					case "random": tempRand[temp] = tempAr[temp]; break;
				}
				temp++;
			}
			base = tempBase; basic = tempBasic; tabl = tempTabl; jlapa = tempjLaPa;
			jrabu = tempjRaBu; buton = tempbuton; folder = tempFold; serc = tempSear;
			rand = tempRand;
			br.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLanguages 2)", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
