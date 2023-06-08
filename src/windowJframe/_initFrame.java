package windowJframe;

import static java.lang.System.out;

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

import xmlFolderHandle.saveLoadDoc;

public class _initFrame extends JFrame implements ActionListener {
	JMenuBar mb;

	JMenu games;
	JMenuItem addGame, removeGame, updateList;
	JMenuItem refreshFromAPI, refreshEverything, saveFileToDifferent;

	JMenu settings;
	JCheckBoxMenuItem darkMode, autoFetchNews, autoUpdateGames;
	JMenu show;
	JCheckBoxMenuItem showID, showName, showDeveloper, showPlayedVersion, showLastTimeplay, showRated, showNewestVersion, showDateOfLastUpdate, showPeopleRating, showhowFarUserPlayed, showDeletedFromPc;
	JCheckBoxMenuItem showEngine, showOS, ShowSelfNote;

	JMenu help;
	JMenuItem dataToShow, faq, credits;

	JMenuItem exit;
	static JTable table;

	Color bg = new Color(100, 100, 100);
	Color fg = new Color(255, 255, 255);
	Color textdark = new Color(30, 30, 30);

	boolean[] columnVisibility = settingsManager.loadSettings("showncolumns");
	boolean[] otherSettings = settingsManager.loadSettings("othersettings");

	public void WindowRefresh(){
		otherSettings = settingsManager.loadSettings("othersettings");
		if (otherSettings[0]) {
			UIManager.put("OptionPane.background", bg);
			UIManager.put("OptionPane.messageForeground", fg);
			UIManager.put("Panel.background", bg);
			UIManager.put("Panel.messageForeground", fg);
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
			
			mb.setBackground(bg); mb.setForeground(fg);
			games.setBackground(bg); games.setForeground(fg);
			settings.setBackground(bg);	settings.setForeground(fg);
			help.setBackground(bg);	help.setForeground(fg);
			show.setBackground(bg); show.setForeground(fg); // TODO why no dark work?
			addGame.setBackground(bg); addGame.setForeground(fg);
			removeGame.setBackground(bg); removeGame.setForeground(fg);
			updateList.setBackground(bg); updateList.setForeground(fg);
			saveFileToDifferent.setBackground(bg); saveFileToDifferent.setForeground(fg);
			refreshEverything.setBackground(bg); refreshEverything.setForeground(fg);
			refreshFromAPI.setBackground(bg); refreshFromAPI.setForeground(fg);
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
			autoUpdateGames.setBackground(bg); autoUpdateGames.setForeground(fg);
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

			mb.setBackground(null); mb.setForeground(null);
			games.setBackground(null); games.setForeground(null);
			settings.setBackground(null); settings.setForeground(null);
			help.setBackground(null); help.setForeground(null);
			show.setBackground(null); show.setForeground(null);
			addGame.setBackground(null); addGame.setForeground(null);
			removeGame.setBackground(null); removeGame.setForeground(null);
			updateList.setBackground(null); updateList.setForeground(null);
			saveFileToDifferent.setBackground(null); saveFileToDifferent.setForeground(null);
			refreshEverything.setBackground(null); refreshEverything.setForeground(null);
			refreshFromAPI.setBackground(null); refreshFromAPI.setForeground(null);
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
			autoUpdateGames.setBackground(null); autoUpdateGames.setForeground(null);
			faq.setBackground(null); faq.setForeground(null);
			credits.setBackground(null); credits.setForeground(null);
			exit.setBackground(null); exit.setForeground(null);
			table.setBackground(null);
		}
	}
	public void WindowCreate(String[] columnNames, Object[][] dataFromXMLFile) {
		// TODO text size small on large display - https://bugs.openjdk.org/browse/JDK-8202973
		setTitle("Hentai Game Database");
		setSize(1500, 600);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mb = new JMenuBar());

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

		mb.add(settings = new JMenu("Settings"));
		settings.add(show = new JMenu("Shown informations"));
		show.add(showID = new JCheckBoxMenuItem("ID", columnVisibility[0]));
		show.add(showName = new JCheckBoxMenuItem("Name", columnVisibility[1]));
		show.add(showDeveloper = new JCheckBoxMenuItem("Developer", columnVisibility[2]));
		show.add(showPlayedVersion = new JCheckBoxMenuItem("Played version", columnVisibility[3]));
		show.add(showLastTimeplay = new JCheckBoxMenuItem("Last time play", columnVisibility[4]));
		show.add(showRated = new JCheckBoxMenuItem("Rated", columnVisibility[5]));
		show.add(showNewestVersion = new JCheckBoxMenuItem("Newest version", columnVisibility[6]));
		show.add(showDateOfLastUpdate = new JCheckBoxMenuItem("Date of last update", columnVisibility[7]));
		show.add(showPeopleRating = new JCheckBoxMenuItem("People rating", columnVisibility[8]));
		show.add(showhowFarUserPlayed = new JCheckBoxMenuItem("Player progress", columnVisibility[9]));
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
		autoUpdateGames.addActionListener(this);
		darkMode.addActionListener(this);

		mb.add(help = new JMenu("Other"));
		help.add(faq = new JMenuItem("FAQ"));
		help.add(credits = new JMenuItem("Credits"));
		faq.addActionListener(this);
		credits.addActionListener(this);

		table = new JTable();
		refreshTable();
		table.setBounds(30, 40, 200, 300);
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
			case "Last time play": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Rated": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Newest version": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Date of last update": settingsManager.xmlSettings("showncolumns", gac); break;
			case "People rating": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Player progress": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Still on pc?": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Engine": settingsManager.xmlSettings("showncolumns", gac); break;
			case "OS": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Personal Notes": settingsManager.xmlSettings("showncolumns", gac); break;
			case "Dark mode": settingsManager.xmlSettings("othersettings", gac); WindowRefresh(); refreshTable(); break;
			case "Auto fetch game updates": settingsManager.xmlSettings("othersettings", gac);JOptionPane.showMessageDialog(null, "API is not implemented at all yet.\nCome back later.", "Error", JOptionPane.ERROR_MESSAGE); break; // TODO api1
			case "Auto update games": settingsManager.xmlSettings("othersettings", gac);JOptionPane.showMessageDialog(null, "API is not implemented at all yet.\nCome back later.", "Error", JOptionPane.ERROR_MESSAGE); break; // TODO api2
			case "FAQ": otherButtonsThingies.FACKQU(); break;
			case "Credits": otherButtonsThingies.money(); break;
			case "Exit": otherButtonsThingies.sureAboutExit(); break;
			default: JOptionPane.showMessageDialog(null, "Error, this should never happen!!!", "Error", JOptionPane.ERROR_MESSAGE); break;
		}
	}
}
