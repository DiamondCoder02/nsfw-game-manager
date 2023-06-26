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
	private static String[] fol = {"base", "basic", "f95Fol", "mainFol", "winFol", "xmlFol"};
	public static String[] tempBase = new String[20], tempBasic = new String[20], tempf95Fol = new String[20], tempmainFol = new String[20], tempwinFol = new String[20], tempxmlFol = new String[20] ;
	public static String[] base, basic, f95Fol, mainFol, winFol, xmlFol;

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
						String[] tempAr = new String[20];
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
							case "f95Fol": tempf95Fol = tempAr; break;
							case "mainFol": tempmainFol = tempAr; break;
							case "winFol": tempwinFol = tempAr; break;
							case "xmlFol": tempxmlFol = tempAr; break;
						}
					}
				}
			}
			base = tempBase;
			basic = tempBasic;
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
