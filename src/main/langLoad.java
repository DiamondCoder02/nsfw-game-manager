package main;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.loadSettingsFromXml;
import xmlFolderHandle.saveLoadDoc;

public class langLoad {
	static String path = checksFile.mainPath + "languages.xml";
	static String language = loadSettingsFromXml.loadStringSettings("language")[0];
	private static String[] fol = {"base", "basic", "tablec", "JLabPan", "JRadBut", "buttons", "folders"};
	private static String[] tempBase = new String[30], tempBasic = new String[30], tempTabl = new String[30], 
	tempjLaPa = new String[30], tempjRaBu = new String[30], tempbuton = new String[30], tempFold = new String[30];
	public static String[] base, basic, tabl, jlapa, jrabu, buton, folder;

	public static void loadLanguages(){
		Document dom = saveLoadDoc.loadDocument(path);
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
						System.out.println("------------"+fol[i]);
						for (int j = 0; j < folList.getLength(); j++) {
							Node folNode = folList.item(j);
							if (folNode.getNodeType() == Node.ELEMENT_NODE) {
								Element folElement = (Element) folNode;
								tempAr[j] = folElement.getTextContent();
								if (tempAr[j].contains("\\n")) {
									tempAr[j] = tempAr[j].replace("\\n", "\n");
								}
								System.out.println(tempAr[j]);
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
						}
					}
				}
			}
			base = tempBase;
			basic = tempBasic;
			tabl = tempTabl;
			jlapa = tempjLaPa;
			jrabu = tempjRaBu;
			buton = tempbuton;
			folder = tempFold;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading settings file (loadOther)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
