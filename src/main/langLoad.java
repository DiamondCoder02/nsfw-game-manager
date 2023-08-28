package main;

import java.io.FileReader;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import folderHandle.checkAndBackup.checksFiles;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;

public class langLoad {
	static String path = checksFiles.mainPath;
	private static String[] fol = {"base", "basic", "tablec", "JLabPan", "JRadBut", "buttons", "folders", "search"};
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
			reader = new CSVReaderBuilder( new FileReader(path+"language.csv")).withCSVParser( 
			new CSVParserBuilder().withSeparator(';').build()).build();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading language csv (loadLanguages)", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (language == null || reader == null) { return; }
		try {
			// first line is the language names
			String[] nextLine = reader.readNext();
			int langindex = 0;
			for (int i = 0; i < nextLine.length; i++) {
				if (nextLine[i].equals(language)) { langindex = i; break; }
			}
			if (langindex == 0) { langindex = 1; }
			System.out.println(langindex);
			System.out.println(nextLine[0]);
			System.out.println(nextLine[1]);
			System.out.println(nextLine[2]);
			System.out.println(nextLine[3]);
			System.out.println("---");
			/* 
			lang;english;engwishUwU;hungarian
			base;Success;Success OwO;Siker
			base;Error;Ewwow;Hiba
			base;Add game;Awd game;Játék hozzáadás
			*/
			// english is 1, so in every line get only the second element

			while ((nextLine = reader.readNext()) != null) {
				Integer temp = 0;
				for (int i = 0; i < nextLine.length; i++){
					switch (nextLine[0]) {
						case "base": tempBase[temp] = nextLine[langindex]; break;
						case "basic": tempBasic[temp] = tempAr; break;
						case "tablec": tempTabl[temp] = tempAr; break;
						case "JLabPan": tempjLaPa[temp] = tempAr; break;
						case "JRadBut": tempjRaBu[temp] = tempAr; break;
						case "buttons": tempbuton[temp] = tempAr; break;
						case "folders": tempFold[temp] = tempAr; break;
						case "search": tempSear[temp] = tempAr; break;
					}
				}
			}
			System.out.println("base: "+tempBase[0]);
			System.out.println("base: "+tempBase[1]);
			System.out.println("base: "+tempBase[2]);
			System.out.println("base: "+tempBase[3]);
			/*
			base = tempBase; basic = tempBasic;
			tabl = tempTabl; jlapa = tempjLaPa;
			jrabu = tempjRaBu; buton = tempbuton;
			folder = tempFold; serc = tempSear;
			*/
			return;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLanguages 2)", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/*
		try {
			NodeList langSource = dom.getElementsByTagName("lang");
			Node langSourceNode = langSource.item(0);
			if (langSourceNode.getNodeType() == Node.ELEMENT_NODE) {
				Element langSourceElement = (Element) langSourceNode;
				NodeList lang = langSourceElement.getElementsByTagName(language);
				Node langNode = lang.item(0);
				if (langNode.getNodeType() == Node.ELEMENT_NODE) {
					Element langElement = (Element) langNode;
					for (int i = 0; i < fol.length; i++) {
						String[] tempAr = new String[30];
						NodeList folList = langElement.getElementsByTagName(fol[i]);
						// System.out.println("------------"+fol[i]);
						for (int j = 0; j < folList.getLength(); j++) {
							Node folNode = folList.item(j);
							if (folNode.getNodeType() == Node.ELEMENT_NODE) {
								Element folElement = (Element) folNode;
								tempAr[j] = folElement.getTextContent();
								if (tempAr[j].contains("\\n")) {
									tempAr[j] = tempAr[j].replace("\\n", "\n");
								}
								// System.out.println(tempAr[j]);
							}
						}
						switch (fol[i]) {
							case "base": tempBase = tempAr; break;
							case "basic": tempBasic = tempAr; break;
							case "tablec": tempTabl = tempAr; break;
							case "JLabPan": tempjLaPa = tempAr; break;
							case "JRadBut": tempjRaBu = tempAr; break;
							case "buttons": tempbuton = tempAr; break;
							case "folders": tempFold = tempAr; break;
							case "search": tempSear = tempAr; break;
						}
					}
				}
			}
			base = tempBase; basic = tempBasic;
			tabl = tempTabl; jlapa = tempjLaPa;
			jrabu = tempjRaBu; buton = tempbuton;
			folder = tempFold; serc = tempSear;
			return;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading language file (loadLanguages)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		*/
	}
}
