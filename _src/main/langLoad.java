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
		Integer temp = 0; 
		String lastLang = "", split = ";", langFromConfig = "english";
		String[] tempAr = new String[30];
		int langindex = 0;
		BufferedReader br = null;

		try {
			// parsing a CSV file into BufferedReader class constructor
			br = new BufferedReader(new FileReader(path+"language.csv"));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLanguages)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (br == null) { return; }
		try{
			langFromConfig = loadSettingsFromXml.loadStringSettings("language")[0];	
			String[] languages = br.readLine().split(split);// first line is the language names
			lanMeans = new String[languages.length-1];
			langChoices = new String[languages.length-1];
			for (int i = 0; i < languages.length; i++) { if (languages[i].equals(langFromConfig)) { langindex = i; break; } }
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
			return;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLanguages 2)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
