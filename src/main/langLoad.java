package main;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;

public class langLoad {
	static String path = checksFiles.mainPath;
	private static String[] tempBase = new String[30], tempBasic = new String[30], tempTabl = new String[30], 
	tempjLaPa = new String[30], tempjRaBu = new String[30], tempbuton = new String[30], tempFold = new String[30],
	tempSear = new String[30];
	public static String[] base, basic, tabl, jlapa, jrabu, buton, folder, serc;
	public static CSVReader reader = null;

	public static void loadLanguages() {
		for (int i = 0; i < 30; i++) {
			tempBase[i] = null; tempBasic[i] = null;
			tempTabl[i] = null; tempjLaPa[i] = null;
			tempjRaBu[i] = null; tempbuton[i] = null;
			tempFold[i] = null; tempSear[i] = null;
		}
		base = tempBase; basic = tempBasic;
		tabl = tempTabl; jlapa = tempjLaPa;
		jrabu = tempjRaBu; buton = tempbuton;
		folder = tempFold; serc = tempSear;

		String language = loadSettingsFromXml.loadStringSettings("language")[0];
		try {
			// parsing a CSV file into CSVReader class constructor
			reader = new CSVReaderBuilder( 
			new InputStreamReader( new FileInputStream(path+"language.csv"), "utf-8")
			).withCSVParser( new CSVParserBuilder().withSeparator(';').build()).build();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading language csv (loadLanguages)", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (language == null || reader == null) { return; }
		try {
			String[] nextLine = reader.readNext();
			// first line is the language names
			int langindex = 0;
			for (int i = 0; i < nextLine.length; i++) {
				if (nextLine[i].equals(language)) { langindex = i; break; }
			}
			if (langindex == 0) { langindex = 1; }
			Integer temp = 0; String lastLang = "";
			String[] tempAr = new String[30];
			while ((nextLine = reader.readNext()) != null) {
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
				}
				temp++;
			}
			base = tempBase; basic = tempBasic;
			tabl = tempTabl; jlapa = tempjLaPa;
			jrabu = tempjRaBu; buton = tempbuton;
			folder = tempFold; serc = tempSear;
			return;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLanguages 2)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
