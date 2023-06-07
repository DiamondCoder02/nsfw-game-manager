package windowJframe;

import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import xmlFolderHandle.saveLoadDoc;

public class _initFrame extends JFrame implements ActionListener {
	JMenuBar mb;

	JMenuItem addGame, removeGame, updateList;
	JMenuItem refreshFromAPI, refreshEverything, saveFileToDifferent;

	JCheckBoxMenuItem darkMode, autoFetchNews, autoUpdateGames;
	JCheckBoxMenuItem showID, showName, showDeveloper, showPlayedVersion, showLastTimePlayed, showRated, showNewestVersion, showDateOfLastUpdate, showPeopleRating, showhowFarUserPlayed, showDeletedFromPc;
	JCheckBoxMenuItem showEngine, showOS, ShowSelfNote;

	JMenuItem dataToShow, faq, credits;

	static JTable table;
	public void WindowCreate(String[] columnNames, Object[][] dataFromXMLFile) {
		// TODO text size small on large display - https://bugs.openjdk.org/browse/JDK-8202973
		setTitle("Hentai Game Database");
		setSize(1500, 600);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mb = new JMenuBar());

		JMenu games;
		mb.add(games = new JMenu("Games"));
		games.add(addGame = new JMenuItem("Add game"));
		games.add(removeGame = new JMenuItem("Remove game"));
		games.add(updateList = new JMenuItem("Update game"));
		games.addSeparator();
		games.add(saveFileToDifferent = new JMenuItem("Save file copy"));
		games.addSeparator();
		games.add(refreshEverything = new JMenuItem("Refresh everything"));
		games.add(refreshFromAPI = new JMenuItem("API refresh"));
		addGame.addActionListener(this);
		removeGame.addActionListener(this);
		updateList.addActionListener(this);
		saveFileToDifferent.addActionListener(this);
		refreshFromAPI.addActionListener(this);
		refreshEverything.addActionListener(this);

		boolean[] columnVisibility, otherSettings;
		columnVisibility = settingsManager.loadSettings("showncolumns");
		otherSettings = settingsManager.loadSettings("othersettings");

		JMenu settings;
		mb.add(settings = new JMenu("Settings"));
		JMenu show;
		settings.add(show = new JMenu("Shown informations"));
		show.add(showID = new JCheckBoxMenuItem("ID", columnVisibility[0]));
		show.add(showName = new JCheckBoxMenuItem("Name", columnVisibility[1]));
		show.add(showDeveloper = new JCheckBoxMenuItem("Developer", columnVisibility[2]));
		show.add(showPlayedVersion = new JCheckBoxMenuItem("Played version", columnVisibility[3]));
		show.add(showLastTimePlayed = new JCheckBoxMenuItem("Last time played", columnVisibility[4]));
		show.add(showRated = new JCheckBoxMenuItem("Rated", columnVisibility[5]));
		show.add(showNewestVersion = new JCheckBoxMenuItem("Newest version", columnVisibility[6]));
		show.add(showDateOfLastUpdate = new JCheckBoxMenuItem("Date of last update", columnVisibility[7]));
		show.add(showPeopleRating = new JCheckBoxMenuItem("People rating", columnVisibility[8]));
		show.add(showhowFarUserPlayed = new JCheckBoxMenuItem("Player prograssion", columnVisibility[9]));
		show.add(showDeletedFromPc = new JCheckBoxMenuItem("Still on pc?", columnVisibility[10]));
		show.add(showEngine = new JCheckBoxMenuItem("Engine", columnVisibility[11]));
		show.add(showOS = new JCheckBoxMenuItem("OS", columnVisibility[12]));
		show.add(ShowSelfNote = new JCheckBoxMenuItem("Personal Notes", columnVisibility[13]));

		settings.addSeparator();
		settings.add(darkMode = new JCheckBoxMenuItem("Dark mode", otherSettings[0]));
		settings.addSeparator();
		settings.add(autoFetchNews = new JCheckBoxMenuItem("Auto fetch game updates", otherSettings[1]));
		settings.add(autoUpdateGames = new JCheckBoxMenuItem("Auto update games", otherSettings[2]));

		showID.addActionListener(this);
		showName.addActionListener(this);
		showDeveloper.addActionListener(this);
		showPlayedVersion.addActionListener(this);
		showLastTimePlayed.addActionListener(this);
		showRated.addActionListener(this);
		showNewestVersion.addActionListener(this);
		showDateOfLastUpdate.addActionListener(this);
		showPeopleRating.addActionListener(this);
		showhowFarUserPlayed.addActionListener(this);
		showDeletedFromPc.addActionListener(this);
		showEngine.addActionListener(this);
		showOS.addActionListener(this);
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

		table = new JTable();
		saveLoadDoc.reloadTable(table);
		table.setBounds(30, 40, 200, 300);
		// table.setAutoCreateRowSorter(true);
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);

        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);

		JMenuItem exit;
		mb.add(exit = new JMenuItem("Exit"));
		exit.addActionListener(this);

		setVisible(true);
	}

	public static void refreshTable(){saveLoadDoc.reloadTable(table);}
	@Override
	public void actionPerformed(ActionEvent e) {
		// out.println(e);
		String gac = e.getActionCommand();
		switch (gac) {
			case "Add game": addGameToFile.addOneGameToFile(); break;
			case "Remove game": removeGameFromFile.removeOneGameFromFile(); break;
			case "Update game": updateGameFromToFile.updateOneGameFromToFile(); break;
			case "Save file copy": otherButtonsThingies.saveFileCopy();	break;
			case "Refresh everything": refreshTable(); break;
			case "API refresh":
					JOptionPane.showMessageDialog(null, "API is not implemented at all yet.\nCome back later.", "Error", JOptionPane.ERROR_MESSAGE);
					out.println("API refresh");
					break;
			case "ID": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Name": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Developer": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Played version": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Last time played": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Rated": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Newest version": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Date of last update": settingsManager.xmlSettings("showncolumns", gac); break;
			case "People rating": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Player prograssion": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Still on pc?": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Engine": settingsManager.xmlSettings("showncolumns", gac); break;
			case "OS": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Personal Notes": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Dark mode": settingsManager.xmlSettings("othersettings", gac); JOptionPane.showMessageDialog(null, "Not implemented yet.\nCome back later.", "Error", JOptionPane.ERROR_MESSAGE);break; // TODO darkmode
			case "Auto fetch game updates": settingsManager.xmlSettings("othersettings", gac);JOptionPane.showMessageDialog(null, "API is not implemented at all yet.\nCome back later.", "Error", JOptionPane.ERROR_MESSAGE); break; // TODO api1
			case "Auto update games": settingsManager.xmlSettings("othersettings", gac);JOptionPane.showMessageDialog(null, "API is not implemented at all yet.\nCome back later.", "Error", JOptionPane.ERROR_MESSAGE); break; // TODO api2
			case "FAQ": otherButtonsThingies.FACKQU(); break;
			case "Credits": otherButtonsThingies.money(); break;
			case "Exit": otherButtonsThingies.sureAboutExit(); break;
			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!!", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
	}
}
