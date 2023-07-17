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

public class createMissingDatabase {
	public static void createFile(String path) {
		/*
		 * Special char:
		 * & &amp;
		 * < &lt;
		 * > &gt;
		 * "" &quot;
		 * '' &apos;
		 * <name>John &amp; Doe</name>
		 * 
		 * If there is id but something goes wrong and one or more info is not available
		 * Actually, while there is no api/rss, this is not needed?
		 * <game id="1">
		 * idea: some stuff can be demonstrated with styles
		 * or put a star or symbol
		 * <name>testname</name>
		 * <developer>testdeveloper</developer>
		 * 
		 * idea: if games is completed or onhold or abondoned,
		 * have small thing at the online last version
		 * <newest_version>v0.1</newest_version>
		 * </game>
		 */
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("source");

			Element newGame = doc.createElement("game");
			Element newName = doc.createElement("name");
			Element newDeveloper = doc.createElement("developer");
			Element newPlayed_version = doc.createElement("played_version");
			Element newDateof_lastplay = doc.createElement("dateof_lastplay");
			Element newUser_rating = doc.createElement("user_rating");
			Element newNewest_version = doc.createElement("newest_version");
			Element newDateof_lastupate = doc.createElement("dateof_lastupate");
			Element newPeople_rating = doc.createElement("people_rating");
			Element newHowFarUserPlayed = doc.createElement("howFarUserPlayed");
			Element newstillOnPc = doc.createElement("stillOnPc");
			Element newEngine = doc.createElement("engine");
			Element newOS = doc.createElement("OS");
			Element newLanguage = doc.createElement("language");
			Element newSelfNote = doc.createElement("selfNote");
			newGame.setAttribute("from", "man");
			newGame.setAttribute("id", "000000");
			newName.appendChild(doc.createTextNode("Example game"));
			newDeveloper.appendChild(doc.createTextNode("Example developer"));
			newPlayed_version.appendChild(doc.createTextNode("v0.0.0"));
			newDateof_lastplay.appendChild(doc.createTextNode("2020-01-01"));
			newUser_rating.appendChild(doc.createTextNode("10/10"));
			newNewest_version.appendChild(doc.createTextNode("v1.0.0"));
			newDateof_lastupate.appendChild(doc.createTextNode("2020-01-03"));
			newPeople_rating.appendChild(doc.createTextNode("8/10"));
			newHowFarUserPlayed.appendChild(doc.createTextNode("Not played"));
			newstillOnPc.appendChild(doc.createTextNode("No"));
			newEngine.appendChild(doc.createTextNode("HTML"));
			newOS.appendChild(doc.createTextNode("Linux"));
			newLanguage.appendChild(doc.createTextNode("English"));
			newSelfNote.appendChild(doc.createTextNode("-"));
			newGame.appendChild(newName);
			newGame.appendChild(newDeveloper);
			newGame.appendChild(newPlayed_version);
			newGame.appendChild(newDateof_lastplay);
			newGame.appendChild(newUser_rating);
			newGame.appendChild(newNewest_version);
			newGame.appendChild(newDateof_lastupate);
			newGame.appendChild(newPeople_rating);
			newGame.appendChild(newHowFarUserPlayed);
			newGame.appendChild(newstillOnPc);
			newGame.appendChild(newEngine);
			newGame.appendChild(newOS);
			newGame.appendChild(newLanguage);
			newGame.appendChild(newSelfNote);
			rootElement.appendChild(newGame);

			doc.appendChild(rootElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domsource = new DOMSource(doc);
			StreamResult result = new StreamResult(path);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating database file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
