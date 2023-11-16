package main;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;

public class langLoad {
	static String path = mainInit.mainPath;
	private static String[] tempBase = new String[30], tempBasic = new String[30], tempTabl = new String[30], 
	tempjLaPa = new String[30], tempjRaBu = new String[30], tempbuton = new String[30], tempFold = new String[30],
	tempSear = new String[30], tempRand = new String[30];
	public static String[] base, basic, tabl, jlapa, jrabu, buton, folder, serc, rand;
	public static String[] langChoices, lanMeans;

	public static void loadLanguages() {
		for (int i = 0; i < 30; i++) {
			tempBase[i] = null; tempBasic[i] = null; tempTabl[i] = null; tempjLaPa[i] = null;
			tempjRaBu[i] = null; tempbuton[i] = null; tempFold[i] = null; tempSear[i] = null;
			tempRand[i] = null;
		}
		Integer temp = 0; String lastLang = "";
		String[] tempAr = new String[30];

		String line = "";
		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(path+"language.csv"));

			String language = loadSettingsFromXml.loadStringSettings("language")[0];
			System.out.println(language);
			String[] languages = br.readLine().split(";");

			// first line is the language names
			int langindex = 0;
			lanMeans = new String[languages.length-1];
			langChoices = new String[languages.length-1];
			for (int i = 0; i < languages.length; i++) { if (languages[i].equals(language)) { langindex = i; break; } }
			if (langindex == 0) { langindex = 1; }
			for (int i = 0; i < (languages.length-1); i++) { langChoices[i] = languages[i+1]; }
			// Second line is language in own language
			languages = br.readLine().split(";");
			for (int i = 0; i < (languages.length-1); i++) { lanMeans[i] = languages[i+1]; }

			while ((line = br.readLine()) != null) { // returns a Boolean value
				String[] nextLine = line.split(";"); // use comma as separator
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
			return;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLanguages 2)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
