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
	public static String[][] tempf95Fol = new String[10][50], tempmainFol = new String[10][50], tempwinFol = new String[10][50], tempxmlFol = new String[10][50];
	public static String[][] f95Fol, mainFol, winFol, xmlFol;
	private static String[] tempBase = new String[10], fol = {"base", "f95Fol", "mainFol", "winFol", "xmlFol"};
	public static String[] base;

	public static void loadLanguages(){
		Document dom = saveLoadDoc.loadDocument(path);
		try {
			Integer fC = 0, pC = 0;
			NodeList langSource = dom.getElementsByTagName("lang");
			Node langSourceNode = langSource.item(0);
			if (langSourceNode.getNodeType() == Node.ELEMENT_NODE) {
				Element langSourceElement = (Element) langSourceNode;
				NodeList lang = langSourceElement.getElementsByTagName(language);
				Node langNode = lang.item(0);
				if (langNode.getNodeType() == Node.ELEMENT_NODE) {
					Element langElement = (Element) langNode;
					for (int i = 0; i < fol.length; i++) {
						if (fol[i].equals("base")){
							String[] tempAr = new String[10];
							NodeList baseList = langElement.getElementsByTagName(fol[i]);
							for (int j = 0; j < baseList.getLength(); j++) {
								Node baseNode = baseList.item(j);
								if (baseNode.getNodeType() == Node.ELEMENT_NODE) {
									Element baseElement = (Element) baseNode;
									tempAr[j] = baseElement.getTextContent();
								}
							}
							tempBase = tempAr;
						} else {
							fC = 0; pC = 0;
							NodeList folList = langElement.getElementsByTagName(fol[i]);
							String[][] tempArray = new String[10][50];
							for (int j = 0; j < folList.getLength(); j++) {
								Node folNode = folList.item(j);
								if (folNode.getNodeType() == Node.ELEMENT_NODE) {
									Element folElement = (Element) folNode;
									fC = Integer.parseInt(folElement.getAttribute("f"));
									pC = Integer.parseInt(folElement.getAttribute("p"));
									tempArray[fC][pC] = folElement.getTextContent();
									if (tempArray[fC][pC].contains("\\n")) {
										tempArray[fC][pC] = tempArray[fC][pC].replace("\\n", "\n");
									}
									// System.out.println(fol[i] + " " + fC + " " + pC + " " + tempArray[fC][pC]);
								}
							}
							switch (fol[i]) {
								case "f95Fol": tempf95Fol = tempArray; break;
								case "mainFol": tempmainFol = tempArray; break;
								case "winFol": tempwinFol = tempArray; break;
								case "xmlFol": tempxmlFol = tempArray; break;
							}
						}
					}
				}
			}
			base = tempBase;
			f95Fol = tempf95Fol;
			mainFol = tempmainFol;
			winFol = tempwinFol;
			xmlFol = tempxmlFol;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading settings file (loadOther)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
