package xmlFolderHandle;

import java.io.File;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class saveLoadDoc {
	static String path = System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager\\hentai.xml";

	public static Document loadDocument() {
		// find file
		File file = new File(path);
		if (!file.exists()) {
			createFile();
		}
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

	public static void saveDocument(Document dom) {
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

	public static void saveADocument(String path) {
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
		getNewRenderedTable(table);
	}

	private static JTable getNewRenderedTable(final JTable table) {
		// change row color - Not played: red, In progress: yellow, Finish: blue, 100% Finished: green
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String status = (String) table.getModel().getValueAt(row, 9);
				if ("Not played".equals(status)) {
					setBackground(Color.RED);
					setForeground(Color.BLACK);
				} else if ("In progress".equals(status)) {
					setBackground(Color.YELLOW);
					setForeground(Color.BLACK);
				} else if ("Finish".equals(status)) {
					setBackground(Color.BLUE);
					setForeground(Color.WHITE);
				} else if ("100% Finished".equals(status)) {
					setBackground(Color.GREEN);
					setForeground(Color.BLACK);
				} else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}
				return this;
			}
		});
		return table;
	}

	private static void createFile() {
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
			new File(System.getenv("APPDATA") + "\\DiamondCoder\\nsfwGameManager").mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating database folders", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("nsfwgames");

			Element settings = doc.createElement("settings");
			rootElement.appendChild(settings);
			Element otherSettings1 = doc.createElement("othersettings"); otherSettings1.setAttribute("enabled", "false");
			otherSettings1.appendChild(doc.createTextNode("Dark mode")); settings.appendChild(otherSettings1);
			Element otherSettings2 = doc.createElement("othersettings"); otherSettings2.setAttribute("enabled", "false");
			otherSettings2.appendChild(doc.createTextNode("Auto fetch game updates")); settings.appendChild(otherSettings2);
			Element otherSettings3 = doc.createElement("othersettings"); otherSettings3.setAttribute("enabled", "false");
			otherSettings3.appendChild(doc.createTextNode("Auto update games")); settings.appendChild(otherSettings3);
			Element showncolumns1 = doc.createElement("showncolumns"); showncolumns1.setAttribute("enabled", "true");
			showncolumns1.appendChild(doc.createTextNode("ID")); settings.appendChild(showncolumns1);
			Element showncolumns2 = doc.createElement("showncolumns"); showncolumns2.setAttribute("enabled", "true");
			showncolumns2.appendChild(doc.createTextNode("Name")); settings.appendChild(showncolumns2);
			Element showncolumns3 = doc.createElement("showncolumns"); showncolumns3.setAttribute("enabled", "true");
			showncolumns3.appendChild(doc.createTextNode("Developer")); settings.appendChild(showncolumns3);
			Element showncolumns4 = doc.createElement("showncolumns"); showncolumns4.setAttribute("enabled", "true");
			showncolumns4.appendChild(doc.createTextNode("Played version")); settings.appendChild(showncolumns4);
			Element showncolumns41 = doc.createElement("showncolumns"); showncolumns41.setAttribute("enabled", "true");
			showncolumns41.appendChild(doc.createTextNode("Last time played")); settings.appendChild(showncolumns41);
			Element showncolumns42 = doc.createElement("showncolumns"); showncolumns42.setAttribute("enabled", "true");
			showncolumns42.appendChild(doc.createTextNode("Rated")); settings.appendChild(showncolumns42);
			Element showncolumns43 = doc.createElement("showncolumns"); showncolumns43.setAttribute("enabled", "true");
			showncolumns43.appendChild(doc.createTextNode("Newest version")); settings.appendChild(showncolumns43);
			Element showncolumns5 = doc.createElement("showncolumns"); showncolumns5.setAttribute("enabled", "true");
			showncolumns5.appendChild(doc.createTextNode("Date of last update")); settings.appendChild(showncolumns5);
			Element showncolumns51 = doc.createElement("showncolumns"); showncolumns51.setAttribute("enabled", "true");
			showncolumns51.appendChild(doc.createTextNode("People rating")); settings.appendChild(showncolumns51);
			Element showncolumns6 = doc.createElement("showncolumns"); showncolumns6.setAttribute("enabled", "true");
			showncolumns6.appendChild(doc.createTextNode("Player prograssion")); settings.appendChild(showncolumns6);
			Element showncolumns7 = doc.createElement("showncolumns"); showncolumns7.setAttribute("enabled", "true");
			showncolumns7.appendChild(doc.createTextNode("Still on pc?")); settings.appendChild(showncolumns7);
			Element showncolumns8 = doc.createElement("showncolumns"); showncolumns8.setAttribute("enabled", "true");
			showncolumns8.appendChild(doc.createTextNode("Engine")); settings.appendChild(showncolumns8);
			Element showncolumns0 = doc.createElement("showncolumns"); showncolumns0.setAttribute("enabled", "true");
			showncolumns0.appendChild(doc.createTextNode("OS")); settings.appendChild(showncolumns0);
			Element showncolumns9 = doc.createElement("showncolumns"); showncolumns9.setAttribute("enabled", "true");
			showncolumns9.appendChild(doc.createTextNode("Personal Notes")); settings.appendChild(showncolumns9);

			Element source = doc.createElement("source");
			source.setAttribute("from", "f95zone");
			rootElement.appendChild(source);

			Element newGame = doc.createElement("game");
			Element newName = doc.createElement("name");
			Element newDeveloper = doc.createElement("developer");
			Element newPlayed_version = doc.createElement("played_version");
			Element newDateof_lastplayed = doc.createElement("dateof_lastplayed");
			Element newUser_rating = doc.createElement("user_rating");
			Element newNewest_version = doc.createElement("newest_version");
			Element newDateof_lastupate = doc.createElement("dateof_lastupate");
			Element newPeople_rating = doc.createElement("people_rating");
			Element newHowFarUserPlayed = doc.createElement("howFarUserPlayed");
			Element newstillOnPc = doc.createElement("stillOnPc");
			Element newEngine = doc.createElement("engine");
			Element newOS = doc.createElement("OS");
			Element newSelfNote = doc.createElement("selfNote");
			newGame.setAttribute("id", "000000");
			newName.appendChild(doc.createTextNode("Example game"));
			newDeveloper.appendChild(doc.createTextNode("Example developer"));
			newPlayed_version.appendChild(doc.createTextNode("v0.0.0"));
			newDateof_lastplayed.appendChild(doc.createTextNode("2020-01-01"));
			newUser_rating.appendChild(doc.createTextNode("10/10"));
			newNewest_version.appendChild(doc.createTextNode("v1.0.0"));
			// newNewest_version.setAttribute("finished", "true");
			newDateof_lastupate.appendChild(doc.createTextNode("2020-01-03"));
			newPeople_rating.appendChild(doc.createTextNode("8/10"));
			newHowFarUserPlayed.appendChild(doc.createTextNode("Not played"));
			newstillOnPc.appendChild(doc.createTextNode("No"));
			newEngine.appendChild(doc.createTextNode("HTML"));
			newOS.appendChild(doc.createTextNode("Linux"));
			newSelfNote.appendChild(doc.createTextNode("-"));
			newGame.appendChild(newName);
			newGame.appendChild(newDeveloper);
			newGame.appendChild(newPlayed_version);
			newGame.appendChild(newDateof_lastplayed);
			newGame.appendChild(newUser_rating);
			newGame.appendChild(newNewest_version);
			newGame.appendChild(newDateof_lastupate);
			newGame.appendChild(newPeople_rating);
			newGame.appendChild(newHowFarUserPlayed);
			newGame.appendChild(newstillOnPc);
			newGame.appendChild(newEngine);
			newGame.appendChild(newOS);
			newGame.appendChild(newSelfNote);
			source.appendChild(newGame);

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
