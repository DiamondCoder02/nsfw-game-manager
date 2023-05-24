package xmlFolderHandle;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class saveLoadDoc {
	static String path = System.getenv("APPDATA")+"\\DiamondCoder\\nsfwGameManager\\hentai.xml";
	public static Document loadDocument(){
		// find file
		File file = new File(path);
		if (!file.exists()) { createFile(); }
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			return dom;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading database file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	public static void saveDocument(Document dom){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domsource = new DOMSource(dom);
			StreamResult result = new StreamResult(path);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error saving database file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void saveDocument(String path){
		try {
			Document dom = loadDocument();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domsource = new DOMSource(dom);
			StreamResult result = new StreamResult(path);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error saving database file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void reloadTable(JTable table) {
		Document dom = saveLoadDoc.loadDocument();
		String[] columnNames = _initXml.allColumns(dom);
		Object[][] data = _initXml.loadGames(dom, columnNames);
		table.setModel(new JTable(data, columnNames).getModel());
	}

	private static void createFile() {
		/* 
		Special char: 
		&		&amp;
		<		&lt; 
		>		&gt;
		""		&quot;
		'' 		&apos;
		<name>John &amp; Doe</name>

		If there is id but something goes wrong and one or more info is not available 
		Actually, while there is no api/rss, this is not needed?
		<game id="1">
				idea: some stuff can be demonstrated with styles 
				or put a star or symbol
			<name>testname</name>
			<developer>testdeveloper</developer>

				idea: if games is completed or onhold or abondoned, 
				have small thing at the online last version
			<onlineVersion>v0.1</onlineVersion>
			<played_version>v0.2</played_version>
			<dateof_lastupate>0000-00-00</dateof_lastupate>
			<onlineLastUpdate>0009-09-09</onlineLastUpdate>
			<websiteRate>10/10</websiteRate>
			<howFarUserPlayed>finished/tobecompleted/neverplayed</howFarUserPlayed>
			<deletedFromPc>true/false</deletedFromPc>
			<engine>Unreal/Renpy</engine>

			<OS>Win./Linux/Mac/Android</OS>		Choose one of these maybe?
			<OS win="y" lin="n" mac="n" and="y" other="y">randomOS</OS>

			<selfNote>This is shit games</selfNote>
		</game>

		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
		<nsfwgames>
			<settings>
				<otherSettings enabled="false">darkmode</otherSettings>
				<otherSettings enabled="false">autoFetchNews</otherSettings>
				<otherSettings enabled="false">autoUpdateGames</otherSettings>
				<showncolumns enabled="true">ID</showncolumns>
				<showncolumns enabled="true">Name</showncolumns>
				<showncolumns enabled="true">Developer</showncolumns>
				<showncolumns enabled="true">Played version</showncolumns>
				<showncolumns enabled="true">Date of last update</showncolumns>
				<showncolumns enabled="true">Player prograssion</showncolumns>
				<showncolumns enabled="true">Still on pc?</showncolumns>
				<showncolumns enabled="true">Engine</showncolumns>
					<showncolumns enabled="false">OS</showncolumns>
				<showncolumns enabled="true">Personal Notes</showncolumns>
			</settings>
			<source from="f95zone">
				<game id="19095">
					<name>2037 - Almost ready Inc.</name>
					<developer>MadAlice</developer>
					<played_version>v0.9.6</played_version>
					<dateof_lastupate>2020-06-22</dateof_lastupate>
					<howFarUserPlayed>Completed</howFarUserPlayed>
					<deletedFromPc>yes</deletedFromPc>
					<engine>HTML</engine>
						<OS and="n" ios="n" lin="n" mac="n" other="n" win="y">-</OS>
					<selfNote>-</selfNote>
				</game>
			</source>
		</nsfwgames>
		*/
		try {
			new File(System.getenv("APPDATA")+"\\DiamondCoder\\nsfwGameManager").mkdirs(); 
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating database folders", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("nsfwgames");

			Element settings = doc.createElement("settings");
			rootElement.appendChild(settings);
			Element otherSettings1 = doc.createElement("otherSettings");
			otherSettings1.setAttribute("enabled", "false");
			otherSettings1.appendChild(doc.createTextNode("darkmode"));
			settings.appendChild(otherSettings1);
			Element otherSettings2 = doc.createElement("otherSettings");
			otherSettings2.setAttribute("enabled", "false");
			otherSettings2.appendChild(doc.createTextNode("autoFetchNews"));
			settings.appendChild(otherSettings2);
			Element otherSettings3 = doc.createElement("otherSettings");
			otherSettings3.setAttribute("enabled", "false");
			otherSettings3.appendChild(doc.createTextNode("autoUpdateGames"));
			settings.appendChild(otherSettings3);
			Element showncolumns1 = doc.createElement("showncolumns");
			showncolumns1.setAttribute("enabled", "true");
			showncolumns1.appendChild(doc.createTextNode("ID"));
			settings.appendChild(showncolumns1);
			Element showncolumns2 = doc.createElement("showncolumns");
			showncolumns2.setAttribute("enabled", "true");
			showncolumns2.appendChild(doc.createTextNode("Name"));
			settings.appendChild(showncolumns2);
			Element showncolumns3 = doc.createElement("showncolumns");
			showncolumns3.setAttribute("enabled", "true");
			showncolumns3.appendChild(doc.createTextNode("Developer"));
			settings.appendChild(showncolumns3);
			Element showncolumns4 = doc.createElement("showncolumns");
			showncolumns4.setAttribute("enabled", "true");
			showncolumns4.appendChild(doc.createTextNode("Played version"));
			settings.appendChild(showncolumns4);
			Element showncolumns5 = doc.createElement("showncolumns");
			showncolumns5.setAttribute("enabled", "true");
			showncolumns5.appendChild(doc.createTextNode("Date of last update"));
			settings.appendChild(showncolumns5);
			Element showncolumns6 = doc.createElement("showncolumns");
			showncolumns6.setAttribute("enabled", "true");
			showncolumns6.appendChild(doc.createTextNode("Player prograssion"));
			settings.appendChild(showncolumns6);
			Element showncolumns7 = doc.createElement("showncolumns");
			showncolumns7.setAttribute("enabled", "true");
			showncolumns7.appendChild(doc.createTextNode("Still on pc?"));
			settings.appendChild(showncolumns7);
			Element showncolumns8 = doc.createElement("showncolumns");
			showncolumns8.setAttribute("enabled", "true");
			showncolumns8.appendChild(doc.createTextNode("Engine"));
			settings.appendChild(showncolumns8);
			Element showncolumns0 = doc.createElement("showncolumns");
			showncolumns0.setAttribute("enabled", "false");
			showncolumns0.appendChild(doc.createTextNode("OS"));
			settings.appendChild(showncolumns0);
			Element showncolumns9 = doc.createElement("showncolumns");
			showncolumns9.setAttribute("enabled", "true");
			showncolumns9.appendChild(doc.createTextNode("Personal Notes"));
			settings.appendChild(showncolumns9);

			Element source = doc.createElement("source");
			source.setAttribute("from", "f95zone");
			rootElement.appendChild(source);

			Element newGame = doc.createElement("game");
			Element newName = doc.createElement("name");
			Element newDeveloper = doc.createElement("developer");
			Element newPlayed_version = doc.createElement("played_version");
			Element newDateof_lastupate = doc.createElement("dateof_lastupate");
			Element newHowFarUserPlayed = doc.createElement("howFarUserPlayed");
			Element newDeletedFromPc = doc.createElement("deletedFromPc");
			Element newEngine = doc.createElement("engine");
			//Element newOS = doc.createElement("OS");
			Element newSelfNote = doc.createElement("selfNote");
			newGame.setAttribute("id", "000000");
			newName.appendChild(doc.createTextNode("Example game"));
			newDeveloper.appendChild(doc.createTextNode("Example developer"));
			newPlayed_version.appendChild(doc.createTextNode("v0.0.0"));
			newDateof_lastupate.appendChild(doc.createTextNode("2020-01-01"));
			newHowFarUserPlayed.appendChild(doc.createTextNode("Not played"));
			newDeletedFromPc.appendChild(doc.createTextNode("no"));
			newEngine.appendChild(doc.createTextNode("HTML"));
			//newOS.appendChild(doc.createTextNode("-"));
			newSelfNote.appendChild(doc.createTextNode("-"));
			newGame.appendChild(newName);
			newGame.appendChild(newDeveloper);
			newGame.appendChild(newPlayed_version);
			newGame.appendChild(newDateof_lastupate);
			newGame.appendChild(newHowFarUserPlayed);
			newGame.appendChild(newDeletedFromPc);
			newGame.appendChild(newEngine);
			//newGame.appendChild(newOS);
			newGame.appendChild(newSelfNote);
			source.appendChild(newGame);

			doc.appendChild(rootElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domsource = new DOMSource(doc);
			// System.out.println(System.getenv("APPDATA"));
			StreamResult result = new StreamResult(path);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating database file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
