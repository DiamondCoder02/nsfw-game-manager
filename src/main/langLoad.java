package main;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	public static void loadLanguages(){
		String language = "";
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

		language = loadSettingsFromXml.loadStringSettings("language")[0];
		Document dom = saveLoadDoc.loadDocument(path+"languages/"+language+".xml");
		if (language == null || dom == null) { return; }
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
			JOptionPane.showMessageDialog(null, "Error loading settings file (loadOther)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
