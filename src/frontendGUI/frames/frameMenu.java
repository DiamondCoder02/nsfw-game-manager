package frontendGUI.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;

public class frameMenu {
	static JMenuBar mb;

	static JMenu games;
	static JMenuItem addGame, updateList, removeGame;
	static JMenuItem saveFileToDifferent, refreshTable, refreshFromAPI;

	static JMenu random;
	static JMenuItem fullRandom, randomDev, randomProgress, randomEngine, randomSite;

	static JMenu search;
	static JMenuItem searchById, searchByName, searchByDeveloper;

	static JMenu settings;
	static JMenuItem changeDB, changeLanguage, changeFolderLocation, changeSteamFolderLoc;
	static JCheckBoxMenuItem darkMode, discordrpc, autoFetchNews, autoFetchFolders, autoUpdateWanted;
	static JMenu show;
	static JCheckBoxMenuItem showSite, showID, showName, showDeveloper, showPlayedVersion, showLastTimeplay, showRated, showNewestVersion;
	static JCheckBoxMenuItem showDateOfLastUpdate, showPeopleRating, showhowFarUserPlayed, showDeletedFromPc, showEngine, showOS, showLanguage, ShowSelfNote;

	static JMenu help;
	static JMenuItem faq, credits, welcome, wiki;

	static JMenuItem exit;

	static String[] wfl = loadLanguage.folder, bu = loadLanguage.buton, tlc = loadLanguage.tabl, 
		bc = loadLanguage.basic, bs = loadLanguage.base, ran = loadLanguage.rand;
	public static JFrame WindowCreate(JFrame frame, String mainPath){
		frame.setTitle(wfl[9]!=null?wfl[9]:"Hentai Game Manager");
		frame.setSize(1500, 600);
		frame.setMinimumSize(new Dimension(500, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(frame.getToolkit().getImage(new File("Assets/Pics/HGM_logo.png").getAbsolutePath()));

		mb = new JMenuBar();
		frameMenuListener buttonListener = new frameMenuListener();

		games = new JMenu(bu[0]!=null?bu[0]:"Games");
		addGame = new JMenuItem(bs[2]!=null?bs[2]:"Add games"); addGame.setActionCommand("Add game"); addGame.addActionListener(buttonListener);
		updateList = new JMenuItem(bs[3]!=null?bs[3]:"Update games"); updateList.setActionCommand("Update game"); updateList.addActionListener(buttonListener);
		removeGame = new JMenuItem(bs[4]!=null?bs[4]:"Remove games"); removeGame.setActionCommand("Remove game"); removeGame.addActionListener(buttonListener);
		saveFileToDifferent = new JMenuItem("Save a copy of current table"); saveFileToDifferent.setActionCommand("Save file copy"); saveFileToDifferent.addActionListener(buttonListener);
		refreshTable = new JMenuItem("Refresh Local Games"); refreshTable.setActionCommand("Refresh table"); refreshTable.addActionListener(buttonListener);
		refreshFromAPI = new JMenuItem("Check Online Updates"); refreshFromAPI.setActionCommand("API refresh"); refreshFromAPI.addActionListener(buttonListener);

		games.add(addGame); games.add(updateList); games.add(removeGame); games.addSeparator();
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

		Boolean[] boolColumns = loadSettings.shownColumns;
		Boolean[] boolSettings = loadSettings.othersettings;

		settings = new JMenu(bu[2]!=null?bu[2]:"Settings");
		changeDB  = new JMenuItem("📚 Save slots"); changeDB.setActionCommand("dbChange"); changeDB.addActionListener(buttonListener);
		changeLanguage  = new JMenuItem("🌐 "+(bu[4]!=null?bu[4]:"Language")); changeLanguage.setActionCommand("appLanguage"); changeLanguage.addActionListener(buttonListener);
		changeFolderLocation = new JMenuItem("📁 "+("Change hentai folder location")); changeFolderLocation.setActionCommand("folderLocation"); changeFolderLocation.addActionListener(buttonListener);
		changeSteamFolderLoc = new JMenuItem("📁 Change Steam location"); changeSteamFolderLoc.setActionCommand("changeSteamDir"); changeSteamFolderLoc.addActionListener(buttonListener);
		if (!new File("../../../steamapps").exists()) {
			autoUpdateWanted = new JCheckBoxMenuItem("🔁 "+"Auto update application", boolSettings[0]); autoUpdateWanted.setActionCommand("autoUpdateManager"); autoUpdateWanted.addActionListener(buttonListener);
		}
		darkMode = new JCheckBoxMenuItem(bu[18]!=null?bu[18]:"Dark mode", boolSettings[1]); darkMode.setActionCommand("darkMode"); darkMode.addActionListener(buttonListener);
		discordrpc = new JCheckBoxMenuItem("Discord RPC", boolSettings[4]); discordrpc.setActionCommand("DiscordRPC"); discordrpc.addActionListener(buttonListener);
		autoFetchNews = new JCheckBoxMenuItem("Auto fetch online games info", boolSettings[2]); autoFetchNews.setActionCommand("autoFetchNewGameInfos"); autoFetchNews.addActionListener(buttonListener);
		autoFetchFolders = new JCheckBoxMenuItem("Auto fetch hentai folders", boolSettings[3]); autoFetchFolders.setActionCommand("autoFetchLocalGameFolder"); autoFetchFolders.addActionListener(buttonListener);
		
		settings.add(changeDB); settings.add(changeLanguage); settings.add(changeFolderLocation); settings.add(changeSteamFolderLoc); settings.addSeparator();
		if (!new File("../../../steamapps").exists()) {settings.add(autoUpdateWanted); }
		settings.add(darkMode); settings.add(discordrpc); settings.addSeparator();
		settings.add(autoFetchNews); settings.add(autoFetchFolders);
		mb.add(settings);

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
		mb.add(show); 

		help = new JMenu(bu[5]!=null?bu[5]:"Other");
		welcome = new JMenuItem("Welcome"); welcome.setActionCommand("welcomeMessage"); welcome.addActionListener(buttonListener);
		faq = new JMenuItem(bu[6]!=null?bu[6]:"FAQ"); faq.setActionCommand("FAQ"); faq.addActionListener(buttonListener);
		credits = new JMenuItem(bu[7]!=null?bu[7]:"Credits"); credits.setActionCommand("Credits"); credits.addActionListener(buttonListener);
		wiki = new JMenuItem("Wiki"); wiki.setActionCommand("wiki"); wiki.addActionListener(buttonListener);

		help.add(welcome); help.add(faq); help.add(credits); help.add(wiki);
		mb.add(help);

		exit = new JMenuItem(bu[8]!=null?bu[8]:"Exit"); exit.setActionCommand("Exit"); exit.addActionListener(buttonListener);
		mb.add(exit);

		frame.setJMenuBar(mb);
		frame.setLayout(new BorderLayout());

		return frame;
	}
}