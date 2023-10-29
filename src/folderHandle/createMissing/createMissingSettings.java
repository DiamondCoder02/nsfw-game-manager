package folderHandle.createMissing;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.mainInit;

public class createMissingSettings {
	public static void createFile(String path){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element settings = doc.createElement("settings");
			Element otherSettings1 = doc.createElement("othersettings"); otherSettings1.setAttribute("enabled", "true");
			otherSettings1.appendChild(doc.createTextNode("Dark mode")); settings.appendChild(otherSettings1);
			Element otherSettings2 = doc.createElement("othersettings"); otherSettings2.setAttribute("enabled", "false");
			otherSettings2.appendChild(doc.createTextNode("Auto fetch game info")); settings.appendChild(otherSettings2);
			Element otherSettings3 = doc.createElement("othersettings"); otherSettings3.setAttribute("enabled", "false");
			otherSettings3.appendChild(doc.createTextNode("Auto fetch folders")); settings.appendChild(otherSettings3);
			Element otherSettings4 = doc.createElement("othersettings"); otherSettings4.setAttribute("enabled", "false");
			otherSettings4.appendChild(doc.createTextNode("DiscordRPC")); settings.appendChild(otherSettings4);
			Element language = doc.createElement("language");
			language.appendChild(doc.createTextNode("english")); settings.appendChild(language);
			Element folderLoc = doc.createElement("folderLocation");
			folderLoc.appendChild(doc.createTextNode("null")); settings.appendChild(folderLoc);
			Element appVersion = doc.createElement("appVersion");
			appVersion.appendChild(doc.createTextNode(mainInit.appVersion)); settings.appendChild(appVersion);
			Element showncolumns0 = doc.createElement("showncolumns"); showncolumns0.setAttribute("enabled", "true");
			showncolumns0.appendChild(doc.createTextNode("Site")); settings.appendChild(showncolumns0);
			Element showncolumns1 = doc.createElement("showncolumns"); showncolumns1.setAttribute("enabled", "true");
			showncolumns1.appendChild(doc.createTextNode("ID")); settings.appendChild(showncolumns1);
			Element showncolumns2 = doc.createElement("showncolumns"); showncolumns2.setAttribute("enabled", "true");
			showncolumns2.appendChild(doc.createTextNode("Name")); settings.appendChild(showncolumns2);
			Element showncolumns3 = doc.createElement("showncolumns"); showncolumns3.setAttribute("enabled", "true");
			showncolumns3.appendChild(doc.createTextNode("Developer")); settings.appendChild(showncolumns3);
			Element showncolumns4 = doc.createElement("showncolumns"); showncolumns4.setAttribute("enabled", "true");
			showncolumns4.appendChild(doc.createTextNode("Played version")); settings.appendChild(showncolumns4);
			Element showncolumns5 = doc.createElement("showncolumns"); showncolumns5.setAttribute("enabled", "true");
			showncolumns5.appendChild(doc.createTextNode("Last time play")); settings.appendChild(showncolumns5);
			Element showncolumns6 = doc.createElement("showncolumns"); showncolumns6.setAttribute("enabled", "true");
			showncolumns6.appendChild(doc.createTextNode("Rated")); settings.appendChild(showncolumns6);
			Element showncolumns7 = doc.createElement("showncolumns"); showncolumns7.setAttribute("enabled", "true");
			showncolumns7.appendChild(doc.createTextNode("Newest version")); settings.appendChild(showncolumns7);
			Element showncolumns8 = doc.createElement("showncolumns"); showncolumns8.setAttribute("enabled", "true");
			showncolumns8.appendChild(doc.createTextNode("Last update")); settings.appendChild(showncolumns8);
			Element showncolumns9 = doc.createElement("showncolumns"); showncolumns9.setAttribute("enabled", "true");
			showncolumns9.appendChild(doc.createTextNode("People rating")); settings.appendChild(showncolumns9);
			Element showncolumns10 = doc.createElement("showncolumns"); showncolumns10.setAttribute("enabled", "true");
			showncolumns10.appendChild(doc.createTextNode("Player progress")); settings.appendChild(showncolumns10);
			Element showncolumns11 = doc.createElement("showncolumns"); showncolumns11.setAttribute("enabled", "true");
			showncolumns11.appendChild(doc.createTextNode("Still on pc?")); settings.appendChild(showncolumns11);
			Element showncolumns12 = doc.createElement("showncolumns"); showncolumns12.setAttribute("enabled", "true");
			showncolumns12.appendChild(doc.createTextNode("Engine")); settings.appendChild(showncolumns12);
			Element showncolumns13 = doc.createElement("showncolumns"); showncolumns13.setAttribute("enabled", "true");
			showncolumns13.appendChild(doc.createTextNode("OS")); settings.appendChild(showncolumns13);
			Element showncolumns15 = doc.createElement("showncolumns"); showncolumns15.setAttribute("enabled", "true");
			showncolumns15.appendChild(doc.createTextNode("Language")); settings.appendChild(showncolumns15);
			Element showncolumns14 = doc.createElement("showncolumns"); showncolumns14.setAttribute("enabled", "true");
			showncolumns14.appendChild(doc.createTextNode("Personal Notes")); settings.appendChild(showncolumns14);

			doc.appendChild(settings);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domsource = new DOMSource(doc);
			StreamResult result = new StreamResult(path);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating settings file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
