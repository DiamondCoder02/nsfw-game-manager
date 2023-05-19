package windowJframe;

import static java.lang.System.out;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.loadDoc;
import xmlFolderHandle.saveDoc;

public class _initFrame extends JFrame implements ActionListener {
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
		games.add(updateList = new JMenuItem("Update game"));
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
		Document dom = loadDoc.loadDocument();
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
		// TODO table init lol
		// setLayout(new BorderLayout());
		// add(table.getTableHeader(), BorderLayout.PAGE_START);
		// add(table, BorderLayout.CENTER);

		JMenu exit;
		mb.add(exit = new JMenu("Exit"));

		setVisible(true);
	}

	public static void columnVisibility(String gac) {
		try{
			Document dom = loadDoc.loadDocument();
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
							JOptionPane.showMessageDialog(null, "Changes will be visible after restart", "Success", JOptionPane.INFORMATION_MESSAGE);
							saveDoc.saveDocument(dom);
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
			case "Add game": addGameToFile.addOneGameToFile(); break;
			case "Remove game": removeGameFromFile.removeOneGameFromFile(); break;
			case "Update game": updateGameFromToFile.updateOneGameFromToFile(); break;
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
			case "Exit":
				out.println("Exit");
				break;
			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!!", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
	}
}
