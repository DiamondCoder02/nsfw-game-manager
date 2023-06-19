package windowJframe;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.UIManager;

import f95WebsiteHandle.addFromSite;
import f95WebsiteHandle.updateFromSite;
import f95WebsiteHandle._initSiteFetch;
import xmlFolderHandle.loadSettingsFromXml;
import xmlFolderHandle.saveLoadDoc;

public class _initFrame extends JFrame implements ActionListener {
	JMenuBar mb;

	JMenu games;
	JMenuItem addGame, addF95zone;
	JMenuItem updateList, updateF95;
	JMenuItem removeGame, saveFileToDifferent;
	JMenuItem refreshTable, refreshFromAPI;

	JMenu search;
	JMenuItem searchById, searchByName, searchByDeveloper;

	JMenu settings;
	JCheckBoxMenuItem darkMode, autoFetchNews, autoUpdateGames;
	JMenu show;
	JCheckBoxMenuItem showSite, showID, showName, showDeveloper, showPlayedVersion, showLastTimeplay, showRated, showNewestVersion, showDateOfLastUpdate, showPeopleRating, showhowFarUserPlayed, showDeletedFromPc;
	JCheckBoxMenuItem showEngine, showOS, ShowSelfNote;

	JMenu help;
	JMenuItem dataToShow, faq, credits;

	JMenuItem exit;
	static JTable table;

	Color bg = new Color(100, 100, 100);
	Color fg = new Color(255, 255, 255);
	Color textdark = new Color(30, 30, 30);

	Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
	Boolean[] boolColumns = loadSettingsFromXml.loadBooleanSettings("showncolumns");

	private void WindowRefresh(){ // TODO make background also dark
		Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
		if (boolSettings[0]) {
			UIManager.put("OptionPane.background", bg);
			UIManager.put("OptionPane.messageForeground", fg);
			UIManager.put("Button.background", null);
			UIManager.put("Button.foreground", null);
			UIManager.put("Label.foreground", fg);
			UIManager.put("Label.background", bg);
			UIManager.put("RadioButton.background", bg);
			UIManager.put("RadioButton.foreground", fg);
			UIManager.put("CheckBox.background", bg);
			UIManager.put("CheckBox.foreground", fg);
			UIManager.put("TextField.background", textdark);
			UIManager.put("TextField.foreground", fg);
			getContentPane().setBackground(bg);
			mb.setBackground(bg); mb.setForeground(fg);
			games.setBackground(bg); games.setForeground(fg);
			settings.setBackground(bg);	settings.setForeground(fg);
			help.setBackground(bg);	help.setForeground(fg);
			show.setBackground(bg); show.setForeground(fg); // TODO why no dark work?
			addGame.setBackground(bg); addGame.setForeground(fg);
			removeGame.setBackground(bg); removeGame.setForeground(fg);
			updateList.setBackground(bg); updateList.setForeground(fg);
			updateF95.setBackground(bg); updateF95.setForeground(fg);
			saveFileToDifferent.setBackground(bg); saveFileToDifferent.setForeground(fg);
			refreshTable.setBackground(bg); refreshTable.setForeground(fg);
			addF95zone.setBackground(bg); addF95zone.setForeground(fg);
			refreshFromAPI.setBackground(bg); refreshFromAPI.setForeground(fg);
			search.setBackground(bg); search.setForeground(fg);
			searchById.setBackground(bg); searchById.setForeground(fg);
			searchByName.setBackground(bg); searchByName.setForeground(fg);
			searchByDeveloper.setBackground(bg); searchByDeveloper.setForeground(fg);
			showSite.setBackground(bg); showSite.setForeground(fg);
			showID.setBackground(bg); showID.setForeground(fg);
			showName.setBackground(bg); showName.setForeground(fg);
			showDeveloper.setBackground(bg); showDeveloper.setForeground(fg);
			showPlayedVersion.setBackground(bg); showPlayedVersion.setForeground(fg);
			showLastTimeplay.setBackground(bg); showLastTimeplay.setForeground(fg);
			showRated.setBackground(bg); showRated.setForeground(fg);
			showNewestVersion.setBackground(bg); showNewestVersion.setForeground(fg);
			showDateOfLastUpdate.setBackground(bg); showDateOfLastUpdate.setForeground(fg);
			showPeopleRating.setBackground(bg);	showPeopleRating.setForeground(fg);
			showhowFarUserPlayed.setBackground(bg);	showhowFarUserPlayed.setForeground(fg);
			showDeletedFromPc.setBackground(bg); showDeletedFromPc.setForeground(fg);
			showEngine.setBackground(bg); showEngine.setForeground(fg);
			showOS.setBackground(bg); showOS.setForeground(fg);
			ShowSelfNote.setBackground(bg);	ShowSelfNote.setForeground(fg);
			darkMode.setBackground(bg);	darkMode.setForeground(fg);
			autoFetchNews.setBackground(bg); autoFetchNews.setForeground(fg);
			// autoUpdateGames.setBackground(bg); autoUpdateGames.setForeground(fg);
			faq.setBackground(bg); faq.setForeground(fg);
			credits.setBackground(bg); credits.setForeground(fg);
			exit.setBackground(bg);	exit.setForeground(fg);
			table.setBackground(bg);
		} else {
			UIManager.put("OptionPane.background", null);
			UIManager.put("OptionPane.messageForeground", null);
			UIManager.put("Panel.background", null);
			UIManager.put("Panel.messageForeground", null);
			UIManager.put("Button.background", bg);
			UIManager.put("Button.foreground", fg);
			UIManager.put("Label.foreground", null);
			UIManager.put("Label.background", null);
			UIManager.put("RadioButton.background", null);
			UIManager.put("RadioButton.foreground", null);
			UIManager.put("CheckBox.background", null);
			UIManager.put("CheckBox.foreground", null);
			UIManager.put("TextField.background", null);
			UIManager.put("TextField.foreground", null);
			getContentPane().setBackground(null);
			mb.setBackground(null); mb.setForeground(null);
			games.setBackground(null); games.setForeground(null);
			settings.setBackground(null); settings.setForeground(null);
			help.setBackground(null); help.setForeground(null);
			show.setBackground(null); show.setForeground(null);
			addGame.setBackground(null); addGame.setForeground(null);
			removeGame.setBackground(null); removeGame.setForeground(null);
			updateList.setBackground(null); updateList.setForeground(null);
			updateF95.setBackground(null); updateF95.setForeground(null);
			saveFileToDifferent.setBackground(null); saveFileToDifferent.setForeground(null);
			refreshTable.setBackground(null); refreshTable.setForeground(null);
			addF95zone.setBackground(null); addF95zone.setForeground(null);
			refreshFromAPI.setBackground(null); refreshFromAPI.setForeground(null);
			search.setBackground(null); search.setForeground(null);
			searchById.setBackground(null); searchById.setForeground(null);
			searchByName.setBackground(null); searchByName.setForeground(null);
			searchByDeveloper.setBackground(null); searchByDeveloper.setForeground(null);
			showSite.setBackground(null); showSite.setForeground(null);
			showID.setBackground(null); showID.setForeground(null);
			showName.setBackground(null); showName.setForeground(null);
			showDeveloper.setBackground(null); showDeveloper.setForeground(null);
			showPlayedVersion.setBackground(null); showPlayedVersion.setForeground(null);
			showLastTimeplay.setBackground(null); showLastTimeplay.setForeground(null);
			showRated.setBackground(null); showRated.setForeground(null);
			showNewestVersion.setBackground(null); showNewestVersion.setForeground(null);
			showDateOfLastUpdate.setBackground(null); showDateOfLastUpdate.setForeground(null);
			showPeopleRating.setBackground(null); showPeopleRating.setForeground(null);
			showhowFarUserPlayed.setBackground(null); showhowFarUserPlayed.setForeground(null);
			showDeletedFromPc.setBackground(null); showDeletedFromPc.setForeground(null);
			showEngine.setBackground(null); showEngine.setForeground(null);
			showOS.setBackground(null); showOS.setForeground(null);
			ShowSelfNote.setBackground(null); ShowSelfNote.setForeground(null);
			darkMode.setBackground(null); darkMode.setForeground(null);
			autoFetchNews.setBackground(null); autoFetchNews.setForeground(null);
			// autoUpdateGames.setBackground(null); autoUpdateGames.setForeground(null);
			faq.setBackground(null); faq.setForeground(null);
			credits.setBackground(null); credits.setForeground(null);
			exit.setBackground(null); exit.setForeground(null);
			table.setBackground(null);
		}
	}
	public void WindowCreate(Object[][] dataFromXMLFile) {
		// TODO text size small on large display - https://bugs.openjdk.org/browse/JDK-8202973
		setTitle("Hentai Game Database");
		setSize(1500, 600);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mb = new JMenuBar());

		mb.add(games = new JMenu("Games"));
		games.add(addGame = new JMenuItem("Add game"));
		games.add(addF95zone = new JMenuItem("Add from F95zone"));
		games.addSeparator();
		games.add(updateList = new JMenuItem("Update game"));
		games.add(updateF95 = new JMenuItem("Update F95zone"));
		games.addSeparator();
		games.add(removeGame = new JMenuItem("Remove game"));
		games.add(saveFileToDifferent = new JMenuItem("Save file copy"));
		games.addSeparator();
		games.add(refreshTable = new JMenuItem("Refresh table"));
		games.add(refreshFromAPI = new JMenuItem("API refresh"));
		addGame.addActionListener(this);
		removeGame.addActionListener(this);
		updateList.addActionListener(this);
		updateF95.addActionListener(this);
		saveFileToDifferent.addActionListener(this);
		refreshTable.addActionListener(this);
		addF95zone.addActionListener(this);
		refreshFromAPI.addActionListener(this);

		mb.add(search = new JMenu("Search"));
		search.add(searchById = new JMenuItem("Search by ID"));
		search.add(searchByName = new JMenuItem("Search by name"));
		search.add(searchByDeveloper = new JMenuItem("Search by developer"));
		searchById.addActionListener(this);
		searchByName.addActionListener(this);
		searchByDeveloper.addActionListener(this);

		mb.add(settings = new JMenu("Settings"));
		settings.add(show = new JMenu("Shown informations"));
		show.add(showSite = new JCheckBoxMenuItem("Site", boolColumns[0]));
		show.add(showID = new JCheckBoxMenuItem("ID", boolColumns[1]));
		show.add(showName = new JCheckBoxMenuItem("Name", boolColumns[2]));
		show.add(showDeveloper = new JCheckBoxMenuItem("Developer", boolColumns[3]));
		show.add(showPlayedVersion = new JCheckBoxMenuItem("Played version", boolColumns[4]));
		show.add(showLastTimeplay = new JCheckBoxMenuItem("Last time play", boolColumns[5]));
		show.add(showRated = new JCheckBoxMenuItem("Rated", boolColumns[6]));
		show.add(showNewestVersion = new JCheckBoxMenuItem("Newest version", boolColumns[7]));
		show.add(showDateOfLastUpdate = new JCheckBoxMenuItem("Last update", boolColumns[8]));
		show.add(showPeopleRating = new JCheckBoxMenuItem("People rating", boolColumns[9]));
		show.add(showhowFarUserPlayed = new JCheckBoxMenuItem("Player progress", boolColumns[10]));
		show.add(showDeletedFromPc = new JCheckBoxMenuItem("Still on pc?", boolColumns[11]));
		show.add(showEngine = new JCheckBoxMenuItem("Engine", boolColumns[12]));
		show.add(showOS = new JCheckBoxMenuItem("OS", boolColumns[13]));
		show.add(ShowSelfNote = new JCheckBoxMenuItem("Personal Notes", boolColumns[14]));

		settings.addSeparator();
		settings.add(darkMode = new JCheckBoxMenuItem("Dark mode", boolSettings[0]));
		settings.addSeparator();
		settings.add(autoFetchNews = new JCheckBoxMenuItem("Auto fetch game info", boolSettings[1]));
		//settings.add(autoUpdateGames = new JCheckBoxMenuItem("Auto update games", boolSettings[2]));

		showSite.addActionListener(this);
		showID.addActionListener(this);
		showName.addActionListener(this);
		showDeveloper.addActionListener(this);
		showPlayedVersion.addActionListener(this);
		showLastTimeplay.addActionListener(this);
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
		//autoUpdateGames.addActionListener(this);
		darkMode.addActionListener(this);

		mb.add(help = new JMenu("Other"));
		help.add(faq = new JMenuItem("FAQ"));
		help.add(credits = new JMenuItem("Credits"));
		faq.addActionListener(this);
		credits.addActionListener(this);

		table = new JTable();
		refreshTable();
		table.setBounds(30, 40, 200, 300);
		setColumns();
		/*
		table.setAutoCreateRowSorter(true);
		table.getRowSorter().addRowSorterListener((RowSorterListener) new RowSorterListener() {
			@Override
			public void sorterChanged(javax.swing.event.RowSorterEvent e) {
				refreshTable();
			}
		});
		*/
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);

        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);

		mb.add(exit = new JMenuItem("Exit"));
		exit.addActionListener(this);

		WindowRefresh();
		setVisible(true);
	}

	private static void setColumns(){
		Boolean[] boolColumns = loadSettingsFromXml.loadBooleanSettings("showncolumns");
		Integer[] ind = new Integer[boolColumns.length];
		Integer counter = 0;
		for (int i = 0; i < boolColumns.length; i++) { ind[i] = -1; }
		for (int i = 0; i < boolColumns.length; i++) { 
			if (boolColumns[i]) {
				ind[i] = counter; 
				counter++;
			} else {
				ind[i] = -1;
			} 
		}

		Integer[] ind2 = new Integer[]{
			5,	// dl - dlsite / f95 - f95zone / man - manually added
			20,	// id
			240,// name
			90,	// developer
			65,	// played version
			50,	// last time play
			40,	// rated 
			70,	// newest version
			50,	// last update
			60,	// people rating
			65,	// player progress
			35,	// still on pc? 
			60,	// engine
			100,// os
			100	// personal notes
		};

		for (int i = 0; i < ind.length; i++) {
			if (boolColumns[i]) { 
				table.getColumnModel().getColumn(ind[i]).setPreferredWidth(ind2[i]); 
			}
		}
	}

	public static void refreshTable(){saveLoadDoc.reloadTable(table); setColumns();}
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e);
		String gac = e.getActionCommand();
		switch (gac) {
			case "Add game": addGameToFile.addOneGameToFile(); break;
			case "Remove game": removeGameFromFile.removeOneGameFromFile(); break;
			case "Update game": updateGameManually.updateOneGameFromToFile(); break;
			case "Update F95zone": updateFromSite.updatef95game(); break;
			case "Refresh table": refreshTable(); setColumns(); break;
			// TODO does this work?
			case "Save file copy": otherButtonsThingies.saveFileCopy();	break;
			// TODO fix this:
			case "Add from F95zone": addFromSite.addFromF95(); break;
			case "API refresh":  _initSiteFetch.fetchInfoAskConfirm(); break;
			case "Search by ID": searchButton.searchById(); break;
			case "Search by name": searchButton.searchByName(); break;
			case "Search by developer": searchButton.searchByDeveloper(); break;
			case "Site": settingsManager.xmlSettings("showncolumns", gac); break;
			case "ID": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Name": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Developer": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Played version": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Last time play": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Rated": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Newest version": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Last update": settingsManager.xmlSettings("showncolumns", gac); break;
			case "People rating": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Player progress": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Still on pc?": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Engine": settingsManager.xmlSettings("showncolumns", gac); break;
			case "OS": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Personal Notes": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Dark mode": settingsManager.xmlSettings("othersettings", gac); WindowRefresh(); refreshTable(); break;
			case "Auto fetch game info": settingsManager.xmlSettings("othersettings", gac); break;
			// case "Auto update games": settingsManager.xmlSettings("othersettings", gac);JOptionPane.showMessageDialog(null, "API is not implemented at all yet.\nCome back later.", "Error", JOptionPane.ERROR_MESSAGE); break; // TODO No api2
			case "FAQ": otherButtonsThingies.FACKQU(); break;
			case "Credits": otherButtonsThingies.money(); break;
			case "Exit": otherButtonsThingies.sureAboutExit(); break;
			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!! (_initFrame)", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
	}
}
