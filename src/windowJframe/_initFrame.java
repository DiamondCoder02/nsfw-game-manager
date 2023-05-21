package windowJframe;

import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.saveLoadDoc;

public class _initFrame extends JFrame implements ActionListener {
	JMenuBar mb;

	JMenuItem addGame, removeGame, updateList;
	JMenuItem refreshFromAPI, refreshFile, saveFileToDifferent;

	JCheckBoxMenuItem darkMode, autoFetchNews, autoUpdateGames;
	JCheckBoxMenuItem showID, showName, showDeveloper, showPlayedVersion, showDateOfLastUpdate, showhowFarUserPlayed, showDeletedFromPc;
	JCheckBoxMenuItem showEngine, showOS, ShowSelfNote;

	JMenuItem dataToShow, faq, credits;

	JTable table;
	public void WindowCreate(String[] columnNames, Object[][] dataFromXMLFile) {
		// TODO - reload table after setting change
		setTitle("Hentai Game Database");
		setSize(1500, 1000);
		// TODO have small minimum size
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
		Document dom = saveLoadDoc.loadDocument();
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
			columnVisibility = new boolean[10];
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
		show.add(showhowFarUserPlayed = new JCheckBoxMenuItem("Player prograssion", columnVisibility[5]));
		show.add(showDeletedFromPc = new JCheckBoxMenuItem("Deleted from pc", columnVisibility[6]));
		show.add(showEngine = new JCheckBoxMenuItem("Engine", columnVisibility[7]));
		// show.add(showOS = new JCheckBoxMenuItem("OS", columnVisibility[8]));
		show.add(ShowSelfNote = new JCheckBoxMenuItem("Self note", columnVisibility[9]));

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
		showhowFarUserPlayed.addActionListener(this);
		showDeletedFromPc.addActionListener(this);
		showEngine.addActionListener(this);
		// showOS.addActionListener(this);
		ShowSelfNote.addActionListener(this);
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

		// TODO exit
		JMenuItem exit;
		mb.add(exit = new JMenu("Exit"));
		exit.addActionListener(this);

		setVisible(true);
	}

	public void updateGameFromToFile(){
		JOptionPane optionPane = new JOptionPane();
		JTextField id = new JTextField();
		Object[] message = {
			"ID of the game to update:", id
		};
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Update game");
		dialog.setVisible(true);
		String idValue = id.getText();
		if (idValue.equals("")) { JOptionPane.showMessageDialog(null, "ID is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
		if (isIDInDatabase.isInDatabase(idValue)) {
			try{
				Document dom = saveLoadDoc.loadDocument();
				NodeList source = dom.getElementsByTagName("source");
				for (int i = 0; i < source.getLength(); i++) {
					Node sourceNode = source.item(i);
					if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
						NodeList game = sourceNode.getChildNodes();
						for (int j = 0; j < game.getLength(); j++) {
							Node gameNode = game.item(j);
							if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
								Element e = (Element) gameNode;
								String ids = e.getAttribute("id").trim();
								if (ids.equals(idValue)) {
									String oldname = e.getElementsByTagName("name").item(0).getTextContent().trim();
									String olddeveloper = e.getElementsByTagName("developer").item(0).getTextContent().trim();
									String oldplayed_version = e.getElementsByTagName("played_version").item(0).getTextContent().trim();
									String olddateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
									String oldhowFarUserPlayed = e.getElementsByTagName("howFarUserPlayed").item(0).getTextContent().trim();
									String olddeletedFromPc = e.getElementsByTagName("deletedFromPc").item(0).getTextContent().trim();
									String oldengine = e.getElementsByTagName("engine").item(0).getTextContent().trim();
									// String oldos = e.getElementsByTagName("os").item(0).getTextContent().trim();
									String oldselfNote = e.getElementsByTagName("selfNote").item(0).getTextContent().trim();
									String[] columnNames = {"ID", "Name", "Developer", "Played version", "Date of last update", "Player prograssion", "Deleted from pc", "Engine", "Self note"};
									Object[][] data = {{ids, oldname, olddeveloper, oldplayed_version, olddateof_lastupate, oldhowFarUserPlayed, olddeletedFromPc, oldengine, oldselfNote}};
									JTable table = new JTable(data, columnNames);
									table.setBounds(30, 40, 200, 300);
									setLayout(new BorderLayout());
									add(table.getTableHeader(), BorderLayout.PAGE_START);
									add(table, BorderLayout.CENTER);
									JTextField newname = new JTextField();
									JTextField newdeveloper = new JTextField();
									JTextField newplayed_version = new JTextField();
									JTextField newdateof_lastupate = new JTextField();
									JTextField newhowFarUserPlayed = new JTextField();
									JTextField newdeletedFromPc = new JTextField();
									JTextField newengine = new JTextField();
									// JTextField newos = new JTextField();
									JTextField newselfNote = new JTextField();
									Object[] message2 = {
										"ID: "+ids,
										"Name: (required)", newname,
										"Developer:", newdeveloper,
										"Played version:", newplayed_version,
										"Date of last update:", newdateof_lastupate,
										"Player prograssion:", newhowFarUserPlayed,
										"Deleted from pc:", newdeletedFromPc,
										"Engine:", newengine,
										// "OS:", newos,
										"Self note:", newselfNote
									};
									int option = JOptionPane.showConfirmDialog(null, message2, "Update game", JOptionPane.OK_CANCEL_OPTION);
									if (option == JOptionPane.OK_OPTION) {
										String newnameValue = newname.getText();
										String newdeveloperValue = newdeveloper.getText();
										String newplayed_versionValue = newplayed_version.getText();
										String newdateof_lastupateValue = newdateof_lastupate.getText();
										String newhowFarUserPlayedValue = newhowFarUserPlayed.getText();
										String newdeletedFromPcValue = newdeletedFromPc.getText();
										String newengineValue = newengine.getText();
										// String newosValue = newos.getText();
										String newselfNoteValue = newselfNote.getText();
										if (newnameValue.equals("")) { JOptionPane.showMessageDialog(null, "name is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
										if (newdeveloperValue.equals("")) { newdeveloperValue = olddeveloper; }
										if (newplayed_versionValue.equals("")) { newplayed_versionValue = oldplayed_version; }
										if (newdateof_lastupateValue.equals("")) { newdateof_lastupateValue = olddateof_lastupate; }
										if (newhowFarUserPlayedValue.equals("")) { newhowFarUserPlayedValue = oldhowFarUserPlayed; }
										if (newdeletedFromPcValue.equals("")) { newdeletedFromPcValue = olddeletedFromPc; }
										if (newengineValue.equals("")) { newengineValue = oldengine; }
										// if (newosValue.equals("")) { newosValue = oldos; }
										if (newselfNoteValue.equals("")) { newselfNoteValue = oldselfNote; }
										e.getElementsByTagName("name").item(0).setTextContent(newnameValue);
										e.getElementsByTagName("developer").item(0).setTextContent(newdeveloperValue);
										e.getElementsByTagName("played_version").item(0).setTextContent(newplayed_versionValue);
										e.getElementsByTagName("dateof_lastupate").item(0).setTextContent(newdateof_lastupateValue);
										e.getElementsByTagName("howFarUserPlayed").item(0).setTextContent(newhowFarUserPlayedValue);
										e.getElementsByTagName("deletedFromPc").item(0).setTextContent(newdeletedFromPcValue);
										e.getElementsByTagName("engine").item(0).setTextContent(newengineValue);
										// e.getElementsByTagName("os").item(0).setTextContent(newosValue);
										e.getElementsByTagName("selfNote").item(0).setTextContent(newselfNoteValue);
										saveLoadDoc.saveDocument(dom);
										JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" has been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
										break;
									} else {
										JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" has not been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
										break;
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" doesn't exists", "Error", JOptionPane.ERROR_MESSAGE); return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// out.println(e);
		String gac = e.getActionCommand();
		switch (gac) {
			case "Add game": addGameToFile.addOneGameToFile(); break;
			case "Remove game": removeGameFromFile.removeOneGameFromFile(); break;
			case "Update game": updateGameFromToFile(); break;
			case "Save file to different":
				out.println("Save file to different");
				break;
			case "File refresh":
				out.println("File refresh");
				break;
			case "API refresh":
				out.println("API refresh");
				break;
			case "ID": settingsManager.columnVisibility(gac); break;
			case "Name": settingsManager.columnVisibility(gac); break;
			case "Developer": settingsManager.columnVisibility(gac); break;
			case "Played version": settingsManager.columnVisibility(gac); break;
			case "Date of last update": settingsManager.columnVisibility(gac); break;
			case "Player prograssion": settingsManager.columnVisibility(gac); break;
			case "Deleted from pc": settingsManager.columnVisibility(gac); break;
			case "Engine": settingsManager.columnVisibility(gac); break;
			// case "OS": settingsManager.columnVisibility(gac); break;
			case "Self note": settingsManager.columnVisibility(gac); break;
			case "Dark mode":
				out.println("Dark mode");
				break;
			case "Auto fetch game updates":
				out.println("Auto fetch game updates");
				break;
			case "Auto update games":
				out.println("Auto update games");
				break;
			case "FAQ": otherButtonsThingies.FACKQU(); break;
			case "Credits": otherButtonsThingies.money(); break;
			case "Exit":
				out.println("Exit");
				break;
			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!!", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
	}
}
