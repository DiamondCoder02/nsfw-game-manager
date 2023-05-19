import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.lang.System.out;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JCheckBoxMenuItem;

public class mainInit {
	public static void main(String[] args) {
		try {
			File file = new File("hentai.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			Window window = new Window();
			
			String[] columnNames = allColumns(dom);
			Object[][] data = loadGames(dom, columnNames);
			window.WindowCreate(columnNames, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] allColumns(Document dom) {
		String columnNames[] = new String[30];
		Integer counter = 0;
		try{
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList showncolumns = e.getElementsByTagName("showncolumns");
				for (int i = 0; i < showncolumns.getLength(); i++) {
					Node showncolumnsNode = showncolumns.item(i);
					if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) showncolumnsNode;
						String enabled = e2.getAttribute("enabled").trim();
						if (enabled.equals("true")) {
							String column = e2.getTextContent().trim();
							columnNames[counter] = column;
							counter++;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] finalColumnNames = new String[counter];
		for (int i = 0; i < counter; i++) {
			finalColumnNames[i] = columnNames[i];
		}
		return finalColumnNames;
	}

	public static Object[][] loadGames(Document dom, String[] columnNames) {
		Object[][] allDataFromFile = new Object[100][5];
		Integer counter = 0;
		NodeList source = dom.getElementsByTagName("source");
		for (int i = 0; i < source.getLength(); i++) {
			Node sourceNode = source.item(i);
			if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList game = sourceNode.getChildNodes();
				for (int j = 0; j < game.getLength(); j++) {
					Node gameNode = game.item(j);
					if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) gameNode;
						for (int n = 0; n < columnNames.length; n++) {
							switch (columnNames[n]) {
								case "ID": allDataFromFile[counter][n] = e.getAttribute("id").trim(); break;
								case "Name": allDataFromFile[counter][n] = e.getElementsByTagName("name").item(0).getTextContent().trim(); break;
								case "Developer": allDataFromFile[counter][n] = e.getElementsByTagName("developer").item(0).getTextContent().trim(); break;
								case "Played version": allDataFromFile[counter][n] = e.getElementsByTagName("played_version").item(0).getTextContent().trim(); break;
								case "Date of last update": allDataFromFile[counter][n] = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim(); break;
								default: break;
							}
						}
						counter++;
					}
				}
			}
		}
		return allDataFromFile;
	}
}

class Window extends JFrame implements ActionListener{
	JMenuBar mb;

	JMenuItem addGame, removeGame, updateList;
	JMenuItem refreshFromAPI, refreshFile, saveFileToDifferent;

	JCheckBoxMenuItem darkMode, autoFetchNews, autoUpdateGames;
	JCheckBoxMenuItem showID, showName, showDeveloper, showPlayedVersion, showDateOfLastUpdate;

	JMenuItem dataToShow, faq, credits;

	JTable table;

	public void WindowCreate(String[] columnNames, Object[][] dataFromXMLFile) {
		// TODO - https://stackoverflow.com/questions/7628121/how-can-i-refresh-or-reload-the-jframe
		setTitle("Hentai Game Database");
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mb = new JMenuBar());

		JMenu games;
		mb.add(games = new JMenu("Games"));
		games.add(addGame = new JMenuItem("Add game"));
		games.add(removeGame = new JMenuItem("Remove game"));
		games.add(updateList = new JMenuItem("Update list"));
		games.addSeparator();
		games.add(saveFileToDifferent = new JMenuItem("Save file to different"));
		games.addSeparator();
		games.add(refreshFile = new JMenuItem("File refresh"));
		games.add(refreshFromAPI = new JMenuItem("API refresh"));
		addGame.addActionListener(this);
		removeGame.addActionListener(this);
		updateList.addActionListener(this);
		saveFileToDifferent.addActionListener(this);
		refreshFromAPI.addActionListener(this);
		refreshFile.addActionListener(this);

		boolean[] columnVisibility, otherSettings;
		Document dom = loadDoc();
		NodeList settingsNode = dom.getElementsByTagName("settings");
		Node settingsNodeElement = settingsNode.item(0);
		if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) settingsNodeElement;
			NodeList showncolumns = e.getElementsByTagName("showncolumns");
			columnVisibility = new boolean[showncolumns.getLength()];
			for (int i = 0; i < showncolumns.getLength(); i++) {
				Node showncolumnsNode = showncolumns.item(i);
				if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e2 = (Element) showncolumnsNode;
					String enabled = e2.getAttribute("enabled").trim();
					if (enabled.equals("true")) {
						columnVisibility[i] = true;
					} else {
						columnVisibility[i] = false;
					}
				}
			}
			NodeList otherSettingsNode = e.getElementsByTagName("otherSettings");
			otherSettings = new boolean[otherSettingsNode.getLength()];
			for (int i = 0; i < otherSettingsNode.getLength(); i++) {
				Node otherSettingsNodeElement = otherSettingsNode.item(i);
				if (otherSettingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
					Element e2 = (Element) otherSettingsNodeElement;
					String enabled = e2.getAttribute("enabled").trim();
					if (enabled.equals("true")) {
						otherSettings[i] = true;
					} else {
						otherSettings[i] = false;
					}
				}
			}
		} else {
			columnVisibility = new boolean[5];
			otherSettings = new boolean[3];
		}

		JMenu settings;
		mb.add(settings = new JMenu("Settings"));
		JMenu show;
		settings.add(show = new JMenu("Shown informations"));
		show.add(showID = new JCheckBoxMenuItem("ID", columnVisibility[0]));
		show.add(showName = new JCheckBoxMenuItem("Name", columnVisibility[1]));
		show.add(showDeveloper = new JCheckBoxMenuItem("Developer", columnVisibility[2]));
		show.add(showPlayedVersion = new JCheckBoxMenuItem("Played version", columnVisibility[3]));
		show.add(showDateOfLastUpdate = new JCheckBoxMenuItem("Date of last update", columnVisibility[4]));
		settings.addSeparator();
		settings.add(darkMode = new JCheckBoxMenuItem("Dark mode", otherSettings[0]));
		settings.addSeparator();
		settings.add(autoFetchNews = new JCheckBoxMenuItem("Auto fetch game updates", otherSettings[1]));
		settings.add(autoUpdateGames = new JCheckBoxMenuItem("Auto update games", otherSettings[2]));
		showID.addActionListener(this);
		showName.addActionListener(this);
		showDeveloper.addActionListener(this);
		showPlayedVersion.addActionListener(this);
		showDateOfLastUpdate.addActionListener(this);
		autoFetchNews.addActionListener(this);
		autoUpdateGames.addActionListener(this);
		darkMode.addActionListener(this);

		JMenu help;
		mb.add(help = new JMenu("Other"));
		help.add(faq = new JMenuItem("FAQ"));
		help.add(credits = new JMenuItem("Credits"));
		faq.addActionListener(this);
		credits.addActionListener(this);

		table = new JTable(dataFromXMLFile, columnNames);
		table.setBounds(30, 40, 200, 300);
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void columnVisibility(String gac) {
		try{
			Document dom = loadDoc();
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList showncolumns = e.getElementsByTagName("showncolumns");
				for (int i = 0; i < showncolumns.getLength(); i++) {
					Node showncolumnsNode = showncolumns.item(i);
					if (showncolumnsNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) showncolumnsNode;
						String column = e2.getTextContent().trim();
						if (column.equals(gac)) {
							String enabled = e2.getAttribute("enabled").trim();
							if (enabled.equals("true")) {
								e2.setAttribute("enabled", "false");
							} else {
								e2.setAttribute("enabled", "true");
							}
							out.println("changed!");
							saveDoc(dom);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// out.println(e);
		String gac = e.getActionCommand();
		switch (gac) {
			case "Add game":
				out.println("Add game");
				break;
			case "Remove game":
				out.println("Remove game");
				break;
			case "Update list":
				out.println("Update list");
				break;
			case "Save file to different":
				out.println("Save file to different");
				break;
			case "File refresh":
				out.println("File refresh");
				break;
			case "API refresh":
				out.println("API refresh");
				break;
			case "ID": columnVisibility(gac); break;
			case "Name": columnVisibility(gac); break;
			case "Developer": columnVisibility(gac); break;
			case "Played version": columnVisibility(gac); break;
			case "Date of last update": columnVisibility(gac); break;
			case "Dark mode":
				out.println("Dark mode");
				break;
			case "Auto fetch game updates":
				out.println("Auto fetch game updates");
				break;
			case "Auto update games":
				out.println("Auto update games");
				break;
			case "FAQ":
				out.println("FAQ");
				break;
			case "Credits":
				out.println("Credits");
				break;
			default:
				out.println("Unknown settings interaction");
				break;
		}
	}

	public static Document loadDoc(){
		try {
			File file = new File("hentai.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			return dom;
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error loading file");
		}
		return null;
	}
	public static void saveDoc(Document dom){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domsource = new DOMSource(dom);
			StreamResult result = new StreamResult("hentai.xml");
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error saving file");
		}
	}
}