package frontendGUI.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;

public class frameCreate {
	static JMenuBar mb;

	static JMenu games;
	static JMenuItem addGame, updateList, removeGame;
	static JMenuItem addF95zone, updateF95, removeF95;
	static JMenuItem saveFileToDifferent, refreshTable, refreshFromAPI;

	static JMenu random;
	static JMenuItem fullRandom, randomDev, randomProgress, randomEngine, randomSite;

	static JMenu search;
	static JMenuItem searchById, searchByName, searchByDeveloper;

	static JMenu settings;
	static JMenuItem changeLanguage, changeFolderLocation;
	static JCheckBoxMenuItem darkMode, discordrpc, autoFetchNews, autoFetchFolders, autoUpdateWanted;
	static JMenu show;
	static JCheckBoxMenuItem showSite, showID, showName, showDeveloper, showPlayedVersion, showLastTimeplay, showRated, showNewestVersion;
	static JCheckBoxMenuItem showDateOfLastUpdate, showPeopleRating, showhowFarUserPlayed, showDeletedFromPc, showEngine, showOS, showLanguage, ShowSelfNote;

	static JMenu help;
	static JMenuItem faq, credits;

	static JMenuItem exit;

	static String[] wfl = loadLanguage.folder, bu = loadLanguage.buton, tlc = loadLanguage.tabl, 
		bc = loadLanguage.basic, bs = loadLanguage.base, ran = loadLanguage.rand;
	public static JFrame WindowCreate(JFrame frame, String mainPath){
		frame.setTitle(wfl[9]!=null?wfl[9]:"Hentai Game Database");
		frame.setSize(1500, 600);
		frame.setMinimumSize(new Dimension(500, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(frame.getToolkit().getImage(mainPath + "/pics/nyaaa.png"));

		mb = new JMenuBar();
		frameMenuListener buttonListener = new frameMenuListener();

		games = new JMenu(bu[0]!=null?bu[0]:"Games");
		addGame = new JMenuItem(bs[2]!=null?bs[2]:"Add games"); addGame.setActionCommand("Add game"); addGame.addActionListener(buttonListener);
		updateList = new JMenuItem(bs[3]!=null?bs[3]:"Update games"); updateList.setActionCommand("Update game"); updateList.addActionListener(buttonListener);
		removeGame = new JMenuItem(bs[4]!=null?bs[4]:"Remove games"); removeGame.setActionCommand("Remove game"); removeGame.addActionListener(buttonListener);
		addF95zone = new JMenuItem(bu[9]!=null?bu[9]:"Add from F95zone"); addF95zone.setActionCommand("Add F95zone"); addF95zone.addActionListener(buttonListener);
		updateF95 = new JMenuItem(bu[10]!=null?bu[10]:"Update F95zone"); updateF95.setActionCommand("Update F95zone"); updateF95.addActionListener(buttonListener);
		removeF95 = new JMenuItem(bu[11]!=null?bu[11]:"Remove F95zone"); removeF95.setActionCommand("Remove F95zone"); removeF95.addActionListener(buttonListener);
		saveFileToDifferent = new JMenuItem(bu[12]!=null?bu[12]:"Save file copy"); saveFileToDifferent.setActionCommand("Save file copy"); saveFileToDifferent.addActionListener(buttonListener);
		refreshTable = new JMenuItem(bu[13]!=null?bu[13]:"Refresh table"); refreshTable.setActionCommand("Refresh table"); refreshTable.addActionListener(buttonListener);
		refreshFromAPI = new JMenuItem(bu[14]!=null?bu[14]:"API refresh"); refreshFromAPI.setActionCommand("API refresh"); refreshFromAPI.addActionListener(buttonListener);

		games.add(addGame); games.add(updateList); games.add(removeGame); games.addSeparator();
		games.add(addF95zone); games.add(updateF95); games.add(removeF95); games.addSeparator();
		games.add(saveFileToDifferent); games.add(refreshTable); games.add(refreshFromAPI);
		mb.add(games);

		random = new JMenu(ran[0]!=null?ran[0]:"Random");
		fullRandom = new JMenuItem(ran[1]!=null?ran[1]:"Fully random game"); fullRandom.setActionCommand("RandomFully"); fullRandom.addActionListener(buttonListener);
		randomDev = new JMenuItem(ran[2]!=null?ran[2]:"Random game by developer"); randomDev.setActionCommand("RandomDev"); randomDev.addActionListener(buttonListener);
		randomProgress = new JMenuItem(ran[3]!=null?ran[3]:"Random by player progress"); randomProgress.setActionCommand("RandomProgress"); randomProgress.addActionListener(buttonListener);
		randomEngine = new JMenuItem(ran[4]!=null?ran[4]:"Random by engine"); randomEngine.setActionCommand("RandomEngine"); randomEngine.addActionListener(buttonListener);
		randomSite = new JMenuItem(ran[5]!=null?ran[5]:"Random by site"); randomSite.setActionCommand("RandomSite"); randomSite.addActionListener(buttonListener);

		random.add(fullRandom); random.add(randomDev); random.add(randomProgress); random.add(randomEngine); random.add(randomSite);
		mb.add(random);

		search = new JMenu(bu[1]!=null?bu[1]:"Search");
		searchById = new JMenuItem(bu[15]!=null?bu[15]:"Search by ID"); searchById.setActionCommand("searchId"); searchById.addActionListener(buttonListener);
		searchByName = new JMenuItem(bu[16]!=null?bu[16]:"Search by name"); searchByName.setActionCommand("searchName"); searchByName.addActionListener(buttonListener);
		searchByDeveloper = new JMenuItem(bu[17]!=null?bu[17]:"Search by developer"); searchByDeveloper.setActionCommand("searchDev"); searchByDeveloper.addActionListener(buttonListener);
		
		search.add(searchById); search.add(searchByName); search.add(searchByDeveloper);
		mb.add(search);

		Boolean[] boolSettings = loadSettings.othersettings;
		Boolean[] boolColumns = loadSettings.shownColumns;

		settings = new JMenu(bu[2]!=null?bu[2]:"Settings");
		show = new JMenu(bu[3]!=null?bu[3]:"Shown informations");
		showSite = new JCheckBoxMenuItem(tlc[0]!=null?tlc[0]:"Site", boolColumns[0]); showSite.setActionCommand("site"); showSite.addActionListener(buttonListener);
		showID = new JCheckBoxMenuItem(tlc[1]!=null?tlc[1]:"ID", boolColumns[1]); showID.setActionCommand("id"); showID.addActionListener(buttonListener);
		showName = new JCheckBoxMenuItem(tlc[2]!=null?tlc[2]:"Name", boolColumns[2]); showName.setActionCommand("name"); showName.addActionListener(buttonListener);
		showDeveloper = new JCheckBoxMenuItem(tlc[3]!=null?tlc[3]:"Developer", boolColumns[3]); showDeveloper.setActionCommand("developer"); showDeveloper.addActionListener(buttonListener);
		showPlayedVersion = new JCheckBoxMenuItem(tlc[4]!=null?tlc[4]:"Played version", boolColumns[4]); showPlayedVersion.setActionCommand("playedVersion"); showPlayedVersion.addActionListener(buttonListener);
		showLastTimeplay = new JCheckBoxMenuItem(tlc[5]!=null?tlc[5]:"Last time play", boolColumns[5]); showLastTimeplay.setActionCommand("lastTimePlayed"); showLastTimeplay.addActionListener(buttonListener);
		showRated = new JCheckBoxMenuItem(tlc[6]!=null?tlc[6]:"Rated", boolColumns[6]); showRated.setActionCommand("rated"); showRated.addActionListener(buttonListener);
		showNewestVersion = new JCheckBoxMenuItem(tlc[7]!=null?tlc[7]:"Newest version", boolColumns[7]); showNewestVersion.setActionCommand("newestVersionOnline"); showNewestVersion.addActionListener(buttonListener);
		showDateOfLastUpdate = new JCheckBoxMenuItem(tlc[8]!=null?tlc[8]:"Last update", boolColumns[8]); showDateOfLastUpdate.setActionCommand("lastDateTimeUpdated"); showDateOfLastUpdate.addActionListener(buttonListener);
		showPeopleRating = new JCheckBoxMenuItem(tlc[9]!=null?tlc[9]:"People rating", boolColumns[9]); showPeopleRating.setActionCommand("peopleOnlineRating"); showPeopleRating.addActionListener(buttonListener);
		showhowFarUserPlayed = new JCheckBoxMenuItem(tlc[10]!=null?tlc[10]:"Player progress", boolColumns[10]); showhowFarUserPlayed.setActionCommand("localPlayerProgress"); showhowFarUserPlayed.addActionListener(buttonListener);
		showDeletedFromPc = new JCheckBoxMenuItem(tlc[11]!=null?tlc[11]:"Still on pc?", boolColumns[11]); showDeletedFromPc.setActionCommand("gameStillOnPc"); showDeletedFromPc.addActionListener(buttonListener);
		showEngine = new JCheckBoxMenuItem(tlc[12]!=null?tlc[12]:"Engine", boolColumns[12]); showEngine.setActionCommand("gameEngine"); showEngine.addActionListener(buttonListener);
		showOS = new JCheckBoxMenuItem(tlc[13]!=null?tlc[13]:"OS", boolColumns[13]); showOS.setActionCommand("os"); showOS.addActionListener(buttonListener);
		showLanguage = new JCheckBoxMenuItem(tlc[15]!=null?tlc[15]:"Language", boolColumns[14]); showLanguage.setActionCommand("language"); showLanguage.addActionListener(buttonListener);
		ShowSelfNote = new JCheckBoxMenuItem(tlc[14]!=null?tlc[14]:"Personal Notes", boolColumns[15]); ShowSelfNote.setActionCommand("localPersonalNotes"); ShowSelfNote.addActionListener(buttonListener);

		show.add(showSite); show.add(showID); show.add(showName); show.add(showDeveloper); show.add(showPlayedVersion); 
		show.add(showLastTimeplay); show.add(showRated); show.add(showNewestVersion); show.add(showDateOfLastUpdate); 
		show.add(showPeopleRating); show.add(showhowFarUserPlayed); show.add(showDeletedFromPc); show.add(showEngine); 
		show.add(showOS); show.add(showLanguage); show.add(ShowSelfNote);

		changeLanguage  = new JMenuItem("🌐 "+(bu[4]!=null?bu[4]:"Language")); changeLanguage.setActionCommand("appLanguage"); changeLanguage.addActionListener(buttonListener);
		changeFolderLocation = new JMenuItem("📁 "+(bu[21]!=null?bu[21]:"Change folder location")); changeFolderLocation.setActionCommand("folderLocation"); changeFolderLocation.addActionListener(buttonListener);
		autoUpdateWanted = new JCheckBoxMenuItem("🔁 "+"Auto update", boolSettings[0]); autoUpdateWanted.setActionCommand("autoUpdateManager"); autoUpdateWanted.addActionListener(buttonListener);
		darkMode = new JCheckBoxMenuItem(bu[18]!=null?bu[18]:"Dark mode", boolSettings[1]); darkMode.setActionCommand("darkMode"); darkMode.addActionListener(buttonListener);
		discordrpc = new JCheckBoxMenuItem("Discord RPC", boolSettings[4]); discordrpc.setActionCommand("DiscordRPC"); discordrpc.addActionListener(buttonListener);
		autoFetchNews = new JCheckBoxMenuItem(bu[19]!=null?bu[19]:"Auto fetch game info", boolSettings[2]); autoFetchNews.setActionCommand("autoFetchNewGameInfos"); autoFetchNews.addActionListener(buttonListener);
		autoFetchFolders = new JCheckBoxMenuItem(bu[20]!=null?bu[20]:"Auto fetch folders", boolSettings[3]); autoFetchFolders.setActionCommand("autoFetchLocalGameFolder"); autoFetchFolders.addActionListener(buttonListener);

		settings.add(show); settings.add(changeLanguage); settings.add(changeFolderLocation); settings.addSeparator();
		settings.add(autoUpdateWanted); settings.add(darkMode); settings.add(discordrpc); settings.addSeparator();
		settings.add(autoFetchNews); settings.add(autoFetchFolders);
		mb.add(settings);

		help = new JMenu(bu[5]!=null?bu[5]:"Other");
		faq = new JMenuItem(bu[6]!=null?bu[6]:"FAQ"); faq.setActionCommand("FAQ"); faq.addActionListener(buttonListener);
		credits = new JMenuItem(bu[7]!=null?bu[7]:"Credits"); credits.setActionCommand("Credits"); credits.addActionListener(buttonListener);

		help.add(faq); help.add(credits);
		mb.add(help);

		exit = new JMenuItem(bu[8]!=null?bu[8]:"Exit"); exit.setActionCommand("Exit"); exit.addActionListener(buttonListener);
		mb.add(exit);

		frame.setJMenuBar(mb);
		frame.setLayout(new BorderLayout());

		return frame;
	}
}