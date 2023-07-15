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

import main.langLoad;
import f95WebsiteHandle.addFromSite;
import f95WebsiteHandle.updateFromSite;
import f95WebsiteHandle._initSiteFetch;
import xmlFolderHandle.loadSettingsFromXml;
import xmlFolderHandle.saveLoadDoc;

public class _initFrame extends JFrame implements ActionListener {
	JMenuBar mb;

	JMenu games;
	JMenuItem addGame, updateList, removeGame;
	JMenuItem addF95zone, updateF95, removeF95;
	JMenuItem saveFileToDifferent, refreshTable, refreshFromAPI;

	JMenu search;
	JMenuItem searchById, searchByName, searchByDeveloper;

	JMenu settings;
	JMenuItem changeLanguage;
	JCheckBoxMenuItem darkMode, autoFetchNews;
	JMenu show;
	JCheckBoxMenuItem showSite, showID, showName, showDeveloper, showPlayedVersion, showLastTimeplay, showRated, showNewestVersion;
	JCheckBoxMenuItem showDateOfLastUpdate, showPeopleRating, showhowFarUserPlayed, showDeletedFromPc, showEngine, showOS, showLanguage, ShowSelfNote;

	JMenu help;
	JMenuItem faq, credits;

	JMenuItem exit;
	static JTable table;
	JScrollPane pane;

	Color bg = new Color(100, 100, 100);
	Color fg = new Color(255, 255, 255);
	Color textdark = new Color(30, 30, 30);

	Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
	Boolean[] boolColumns = loadSettingsFromXml.loadBooleanSettings("showncolumns");

	static String[] wfl = langLoad.folder, bu = langLoad.buton, tlc = langLoad.tabl, bc = langLoad.basic, bs = langLoad.base;

	public void WindowCreate(Object[][] dataFromXMLFile) {
		setTitle(wfl[9]!=null?wfl[9]:"Hentai Game Database");
		setSize(1500, 600);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mb = new JMenuBar());

		mb.add(games = new JMenu(bu[0]!=null?bu[0]:"Games")); 
		games.add(addGame = new JMenuItem(bs[2]!=null?bs[2]:"Add games")); addGame.setActionCommand("Add game");
		games.add(updateList = new JMenuItem(bs[3]!=null?bs[3]:"Update games")); updateList.setActionCommand("Update game");
		games.add(removeGame = new JMenuItem(bs[4]!=null?bs[4]:"Remove games")); removeGame.setActionCommand("Remove game");
		games.addSeparator();
		games.add(addF95zone = new JMenuItem(bu[9]!=null?bu[9]:"Add from F95zone")); addF95zone.setActionCommand("Add F95zone");
		games.add(updateF95 = new JMenuItem(bu[10]!=null?bu[10]:"Update F95zone")); updateF95.setActionCommand("Update F95zone");
		games.add(removeF95 = new JMenuItem(bu[11]!=null?bu[11]:"Remove F95zone")); removeF95.setActionCommand("Remove F95zone");
		games.addSeparator(); 
		games.add(saveFileToDifferent = new JMenuItem(bu[12]!=null?bu[12]:"Save file copy")); saveFileToDifferent.setActionCommand("Save file copy");
		games.add(refreshTable = new JMenuItem(bu[13]!=null?bu[13]:"Refresh table")); refreshTable.setActionCommand("Refresh table");
		games.add(refreshFromAPI = new JMenuItem(bu[14]!=null?bu[14]:"API refresh")); refreshFromAPI.setActionCommand("API refresh");
		

		mb.add(search = new JMenu(bu[1]!=null?bu[1]:"Search"));
		search.add(searchById = new JMenuItem(bu[15]!=null?bu[15]:"Search by ID")); searchById.setActionCommand("Search by ID");
		search.add(searchByName = new JMenuItem(bu[16]!=null?bu[16]:"Search by name")); searchByName.setActionCommand("Search by name");
		search.add(searchByDeveloper = new JMenuItem(bu[17]!=null?bu[17]:"Search by developer")); searchByDeveloper.setActionCommand("Search by developer");
		searchById.addActionListener(this);
		searchByName.addActionListener(this);
		searchByDeveloper.addActionListener(this);

		mb.add(settings = new JMenu(bu[2]!=null?bu[2]:"Settings"));
		settings.add(show = new JMenu(bu[3]!=null?bu[3]:"Shown informations"));
			show.add(showSite = new JCheckBoxMenuItem(tlc[0]!=null?tlc[0]:"Site", boolColumns[0])); showSite.setActionCommand("Site");
			show.add(showID = new JCheckBoxMenuItem(tlc[1]!=null?tlc[1]:"ID", boolColumns[1])); showID.setActionCommand("ID");
			show.add(showName = new JCheckBoxMenuItem(tlc[2]!=null?tlc[2]:"Name", boolColumns[2])); showName.setActionCommand("Name");
			show.add(showDeveloper = new JCheckBoxMenuItem(tlc[3]!=null?tlc[3]:"Developer", boolColumns[3])); showDeveloper.setActionCommand("Developer");
			show.add(showPlayedVersion = new JCheckBoxMenuItem(tlc[4]!=null?tlc[4]:"Played version", boolColumns[4])); showPlayedVersion.setActionCommand("Played version");
			show.add(showLastTimeplay = new JCheckBoxMenuItem(tlc[5]!=null?tlc[5]:"Last time play", boolColumns[5])); showLastTimeplay.setActionCommand("Last time play");
			show.add(showRated = new JCheckBoxMenuItem(tlc[6]!=null?tlc[6]:"Rated", boolColumns[6])); showRated.setActionCommand("Rated");
			show.add(showNewestVersion = new JCheckBoxMenuItem(tlc[7]!=null?tlc[7]:"Newest version", boolColumns[7])); showNewestVersion.setActionCommand("Newest version");
			show.add(showDateOfLastUpdate = new JCheckBoxMenuItem(tlc[8]!=null?tlc[8]:"Last update", boolColumns[8])); showDateOfLastUpdate.setActionCommand("Last update");
			show.add(showPeopleRating = new JCheckBoxMenuItem(tlc[9]!=null?tlc[9]:"People rating", boolColumns[9])); showPeopleRating.setActionCommand("People rating");
			show.add(showhowFarUserPlayed = new JCheckBoxMenuItem(tlc[10]!=null?tlc[10]:"Player progress", boolColumns[10])); showhowFarUserPlayed.setActionCommand("Player progress");
			show.add(showDeletedFromPc = new JCheckBoxMenuItem(tlc[11]!=null?tlc[11]:"Still on pc?", boolColumns[11])); showDeletedFromPc.setActionCommand("Still on pc?");
			show.add(showEngine = new JCheckBoxMenuItem(tlc[12]!=null?tlc[12]:"Engine", boolColumns[12])); showEngine.setActionCommand("Engine");
			show.add(showOS = new JCheckBoxMenuItem(tlc[13]!=null?tlc[13]:"OS", boolColumns[13])); showOS.setActionCommand("OS");
			show.add(showLanguage = new JCheckBoxMenuItem(tlc[15]!=null?tlc[15]:"Language", boolColumns[14])); showLanguage.setActionCommand("Language");
			show.add(ShowSelfNote = new JCheckBoxMenuItem(tlc[14]!=null?tlc[14]:"Personal Notes", boolColumns[15])); ShowSelfNote.setActionCommand("Personal Notes");
		settings.add(changeLanguage  = new JMenuItem("🌐 "+(bu[4]!=null?bu[4]:"Language"))); changeLanguage.setActionCommand("chanLan");
		settings.addSeparator();
		settings.add(darkMode = new JCheckBoxMenuItem(bu[18]!=null?bu[18]:"Dark mode", boolSettings[0])); darkMode.setActionCommand("Dark mode");
		settings.addSeparator();
		settings.add(autoFetchNews = new JCheckBoxMenuItem(bu[19]!=null?bu[19]:"Auto fetch game info", boolSettings[1])); autoFetchNews.setActionCommand("Auto fetch game info");

		mb.add(help = new JMenu(bu[5]!=null?bu[5]:"Other")); 
		help.add(faq = new JMenuItem(bu[6]!=null?bu[6]:"FAQ")); faq.setActionCommand("FAQ");
		help.add(credits = new JMenuItem(bu[7]!=null?bu[7]:"Credits")); credits.setActionCommand("Credits");
		mb.add(exit = new JMenuItem(bu[8]!=null?bu[8]:"Exit")); exit.setActionCommand("Exit");

		addGame.addActionListener(this); updateList.addActionListener(this);
		removeGame.addActionListener(this); addF95zone.addActionListener(this);
		updateF95.addActionListener(this); removeF95.addActionListener(this);
		saveFileToDifferent.addActionListener(this); refreshTable.addActionListener(this);
		refreshFromAPI.addActionListener(this);

		showSite.addActionListener(this); showID.addActionListener(this);
		showName.addActionListener(this); showDeveloper.addActionListener(this);
		showPlayedVersion.addActionListener(this); showLastTimeplay.addActionListener(this);
		showRated.addActionListener(this); showNewestVersion.addActionListener(this);
		showDateOfLastUpdate.addActionListener(this); showPeopleRating.addActionListener(this);
		showhowFarUserPlayed.addActionListener(this); showDeletedFromPc.addActionListener(this);
		showEngine.addActionListener(this); showOS.addActionListener(this);
		showLanguage.addActionListener(this);; ShowSelfNote.addActionListener(this); 

		changeLanguage.addActionListener(this);
		darkMode.addActionListener(this); autoFetchNews.addActionListener(this);

		faq.addActionListener(this); credits.addActionListener(this);
		exit.addActionListener(this);

		table = new JTable();
		refreshTable();
		table.setBounds(30, 40, 200, 300);
		setColumns();
		table.setAutoCreateRowSorter(true);
		/*
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

		pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);

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
			80,// os
			40,	// language
			80	// personal notes
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
		String gac = e.getActionCommand();
		main.checksFile.checkMissingDatabase();
		main.checksFile.checkSettingsFolder();
		// System.out.println(e); System.out.println(gac);
		switch (gac) {
			case "Add game": addGameToFile.addOneGameToFile(); break;
			case "Update game": updateGameManually.updateOneGameFromToFile(); break;
			case "Remove game": removeGameFromFile.removeOneGameFromFile("man"); break;
			case "Add F95zone": addFromSite.addFromF95(); break;
			case "Update F95zone": updateFromSite.updatef95game(); break;
			case "Remove F95zone": removeGameFromFile.removeOneGameFromFile("f95"); break;

			case "Save file copy": otherButtonsThingies.saveFileCopy();	break;
			case "Refresh table": refreshTable(); setColumns(); break;
			case "API refresh":  _initSiteFetch.fetchInfoAskConfirm(); break;

			case "Search by ID": searchButton.search("id"); break;
			case "Search by name": searchButton.search("name"); break;
			case "Search by developer": searchButton.search("dev"); break;

			case "Site": settingsManager.xmlSettings("showncolumns", "Site"); break;
			case "ID": settingsManager.xmlSettings("showncolumns", "ID"); break;
			case "Name": settingsManager.xmlSettings("showncolumns", "Name"); break;
			case "Developer": settingsManager.xmlSettings("showncolumns", "Developer"); break;
			case "Played version": settingsManager.xmlSettings("showncolumns", "Played version"); break;
			case "Last time play": settingsManager.xmlSettings("showncolumns", "Last time play"); break;
			case "Rated": settingsManager.xmlSettings("showncolumns", "Rated"); break;
			case "Newest version": settingsManager.xmlSettings("showncolumns", "Newest version"); break;
			case "Last update": settingsManager.xmlSettings("showncolumns", "Last update"); break;
			case "People rating": settingsManager.xmlSettings("showncolumns", "People rating"); break;
			case "Player progress": settingsManager.xmlSettings("showncolumns", "Player progress"); break;
			case "Still on pc?": settingsManager.xmlSettings("showncolumns", "Still on pc?"); break;
			case "Engine": settingsManager.xmlSettings("showncolumns", "Engine"); break;
			case "OS": settingsManager.xmlSettings("showncolumns", "OS"); break;
			case "Language": settingsManager.xmlSettings("showncolumns", "Language"); break;
			case "Personal Notes": settingsManager.xmlSettings("showncolumns", "Personal Notes"); break;

			case "chanLan": settingsManager.xmlSettings("language", "lang"); break;
			case "Dark mode": settingsManager.xmlSettings("othersettings", "Dark mode"); WindowRefresh(); refreshTable(); break;
			case "Auto fetch game info": settingsManager.xmlSettings("othersettings", "Auto fetch game info"); break;

			case "FAQ": otherButtonsThingies.FACKQU(); break;
			case "Credits": otherButtonsThingies.money(); break;
			case "Exit": otherButtonsThingies.sureAboutExit(); break;
			default: JOptionPane.showMessageDialog(null, (bc[7]!=null?bc[7]:"Error, this should never happen!!!") + " (_initFrame)", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
	}

	private void WindowRefresh(){
		Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
		if (boolSettings[0]) {
			pane.getViewport().setBackground(bg);
			table.setBackground(bg);
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
			UIManager.put("Panel.background", bg);
			UIManager.put("Panel.messageForeground", fg);
			getContentPane().setBackground(bg);
			mb.setBackground(bg); mb.setForeground(fg);
			games.setBackground(bg); games.setForeground(fg);
			addGame.setBackground(bg); addGame.setForeground(fg);
			updateList.setBackground(bg); updateList.setForeground(fg);
			removeGame.setBackground(bg); removeGame.setForeground(fg);

			addF95zone.setBackground(bg); addF95zone.setForeground(fg);
			updateF95.setBackground(bg); updateF95.setForeground(fg);
			removeF95.setBackground(bg); removeF95.setForeground(fg);

			saveFileToDifferent.setBackground(bg); saveFileToDifferent.setForeground(fg);
			refreshTable.setBackground(bg); refreshTable.setForeground(fg);
			refreshFromAPI.setBackground(bg); refreshFromAPI.setForeground(fg);

			search.setBackground(bg); search.setForeground(fg);
			searchById.setBackground(bg); searchById.setForeground(fg);
			searchByName.setBackground(bg); searchByName.setForeground(fg);
			searchByDeveloper.setBackground(bg); searchByDeveloper.setForeground(fg);

			settings.setBackground(bg);	settings.setForeground(fg);
			changeLanguage.setBackground(bg); changeLanguage.setForeground(fg);
			darkMode.setBackground(bg);	darkMode.setForeground(fg);
			autoFetchNews.setBackground(bg); autoFetchNews.setForeground(fg);

			show.setBackground(bg); show.setForeground(fg);	show.setOpaque(true);
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
			showLanguage.setBackground(bg); showLanguage.setForeground(fg);
			ShowSelfNote.setBackground(bg);	ShowSelfNote.setForeground(fg);

			help.setBackground(bg);	help.setForeground(fg);
			faq.setBackground(bg); faq.setForeground(fg);
			credits.setBackground(bg); credits.setForeground(fg);
			exit.setBackground(bg);	exit.setForeground(fg);
		} else {
			pane.getViewport().setBackground(null);
			table.setBackground(null);
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
			UIManager.put("Panel.background", null);
			UIManager.put("Panel.messageForeground", null);
			getContentPane().setBackground(null);
			mb.setBackground(null); mb.setForeground(null);
			games.setBackground(null); games.setForeground(null);
			addGame.setBackground(null); addGame.setForeground(null);
			updateList.setBackground(null); updateList.setForeground(null);
			removeGame.setBackground(null); removeGame.setForeground(null);

			addF95zone.setBackground(null); addF95zone.setForeground(null);
			updateF95.setBackground(null); updateF95.setForeground(null);
			removeF95.setBackground(null); removeF95.setForeground(null);

			saveFileToDifferent.setBackground(null); saveFileToDifferent.setForeground(null);
			refreshTable.setBackground(null); refreshTable.setForeground(null);
			refreshFromAPI.setBackground(null); refreshFromAPI.setForeground(null);

			search.setBackground(null); search.setForeground(null);
			searchById.setBackground(null); searchById.setForeground(null);
			searchByName.setBackground(null); searchByName.setForeground(null);
			searchByDeveloper.setBackground(null); searchByDeveloper.setForeground(null);

			settings.setBackground(null);	settings.setForeground(null);
			changeLanguage.setBackground(null); changeLanguage.setForeground(null);
			darkMode.setBackground(null);	darkMode.setForeground(null);
			autoFetchNews.setBackground(null); autoFetchNews.setForeground(null);

			show.setBackground(null); show.setForeground(null);	show.setOpaque(false);
			showSite.setBackground(null); showSite.setForeground(null);
			showID.setBackground(null); showID.setForeground(null);
			showName.setBackground(null); showName.setForeground(null);
			showDeveloper.setBackground(null); showDeveloper.setForeground(null);
			showPlayedVersion.setBackground(null); showPlayedVersion.setForeground(null);
			showLastTimeplay.setBackground(null); showLastTimeplay.setForeground(null);
			showRated.setBackground(null); showRated.setForeground(null);
			showNewestVersion.setBackground(null); showNewestVersion.setForeground(null);
			showDateOfLastUpdate.setBackground(null); showDateOfLastUpdate.setForeground(null);
			showPeopleRating.setBackground(null);	showPeopleRating.setForeground(null);
			showhowFarUserPlayed.setBackground(null);	showhowFarUserPlayed.setForeground(null);
			showDeletedFromPc.setBackground(null); showDeletedFromPc.setForeground(null);
			showEngine.setBackground(null); showEngine.setForeground(null);
			showOS.setBackground(null); showOS.setForeground(null);
			showLanguage.setBackground(null); showLanguage.setForeground(null);
			ShowSelfNote.setBackground(null);	ShowSelfNote.setForeground(null);

			help.setBackground(null);	help.setForeground(null);
			faq.setBackground(null); faq.setForeground(null);
			credits.setBackground(null); credits.setForeground(null);
			exit.setBackground(null);	exit.setForeground(null);
		}
	}
}
