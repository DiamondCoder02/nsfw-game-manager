package frontendGUI.frames;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import folderHandling.initialFileLoading.loadSettings;

public class frameColor {
	public static void WindowRefresh(JFrame frame, JScrollPane pane, JTable table){
		Color bg = new Color(100, 100, 100);
		Color fg = new Color(255, 255, 255);
		Color textdark = new Color(30, 30, 30);

		Boolean d = loadSettings.othersettings[1];

		pane.getViewport().setBackground(d?bg:fg);
		table.setBackground(d?bg:fg);
		table.getTableHeader().setBackground(d?bg:fg);
		table.getTableHeader().setForeground(d?fg:bg);
		UIManager.put("OptionPane.background", d?bg:null);
		UIManager.put("OptionPane.messageForeground", d?fg:null);
		UIManager.put("Panel.background", d?bg:null);
		UIManager.put("Panel.messageForeground", d?fg:null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
		UIManager.put("Label.foreground", d?fg:null);
		UIManager.put("Label.background", d?bg:null);
		UIManager.put("RadioButton.background", d?bg:null);
		UIManager.put("RadioButton.foreground", d?fg:null);
		UIManager.put("CheckBox.background", d?bg:null);
		UIManager.put("CheckBox.foreground", d?fg:null);
		UIManager.put("TextField.background", d?textdark:null);
		UIManager.put("TextField.foreground", d?fg:null);
		UIManager.put("Panel.background", d?bg:null);
		UIManager.put("Panel.messageForeground", d?fg:null);
		frame.getContentPane().setBackground(d?bg:null);
		
		frameCreate.mb.setBackground(d?bg:null); frameCreate.mb.setForeground(d?fg:null);
		frameCreate.games.setBackground(d?bg:null); frameCreate.games.setForeground(d?fg:null);
		frameCreate.addGame.setBackground(d?bg:null); frameCreate.addGame.setForeground(d?fg:null);
		frameCreate.updateList.setBackground(d?bg:null); frameCreate.updateList.setForeground(d?fg:null);
		frameCreate.removeGame.setBackground(d?bg:null); frameCreate.removeGame.setForeground(d?fg:null);

		frameCreate.updateF95.setBackground(d?bg:null); frameCreate.updateF95.setForeground(d?fg:null);
		frameCreate.removeF95.setBackground(d?bg:null); frameCreate.removeF95.setForeground(d?fg:null);

		frameCreate.saveFileToDifferent.setBackground(d?bg:null); frameCreate.saveFileToDifferent.setForeground(d?fg:null);
		frameCreate.refreshTable.setBackground(d?bg:null); frameCreate.refreshTable.setForeground(d?fg:null);
		frameCreate.refreshFromAPI.setBackground(d?bg:null); frameCreate.refreshFromAPI.setForeground(d?fg:null);

		frameCreate.random.setBackground(d?bg:null); frameCreate.random.setForeground(d?fg:null);
		frameCreate.fullRandom.setBackground(d?bg:null); frameCreate.fullRandom.setForeground(d?fg:null);
		frameCreate.randomDev.setBackground(d?bg:null); frameCreate.randomDev.setForeground(d?fg:null);
		frameCreate.randomProgress.setBackground(d?bg:null); frameCreate.randomProgress.setForeground(d?fg:null);
		frameCreate.randomEngine.setBackground(d?bg:null); frameCreate.randomEngine.setForeground(d?fg:null);
		frameCreate.randomSite.setBackground(d?bg:null); frameCreate.randomSite.setForeground(d?fg:null);

		frameCreate.search.setBackground(d?bg:null); frameCreate.search.setForeground(d?fg:null);
		frameCreate.searchById.setBackground(d?bg:null); frameCreate.searchById.setForeground(d?fg:null);
		frameCreate.searchByName.setBackground(d?bg:null); frameCreate.searchByName.setForeground(d?fg:null);
		frameCreate.searchByDeveloper.setBackground(d?bg:null); frameCreate.searchByDeveloper.setForeground(d?fg:null);

		frameCreate.settings.setBackground(d?bg:null);	frameCreate.settings.setForeground(d?fg:null);
		frameCreate.autoUpdateWanted.setBackground(d?bg:null); frameCreate.autoUpdateWanted.setForeground(d?fg:null);
		frameCreate.changeLanguage.setBackground(d?bg:null); frameCreate.changeLanguage.setForeground(d?fg:null);
		frameCreate.changeFolderLocation.setBackground(d?bg:null); frameCreate.changeFolderLocation.setForeground(d?fg:null);
		frameCreate.darkMode.setBackground(d?bg:null);	frameCreate.darkMode.setForeground(d?fg:null);
		frameCreate.discordrpc.setBackground(d?bg:null); frameCreate.discordrpc.setForeground(d?fg:null);
		frameCreate.autoFetchNews.setBackground(d?bg:null); frameCreate.autoFetchNews.setForeground(d?fg:null);
		frameCreate.autoFetchFolders.setBackground(d?bg:null); frameCreate.autoFetchFolders.setForeground(d?fg:null);

		// TODO - why no work?
		// frameCreate.show.setBackground(d?bg:null); frameCreate.show.setForeground(d?fg:null);
		frameCreate.showSite.setBackground(d?bg:null); frameCreate.showSite.setForeground(d?fg:null);
		frameCreate.showID.setBackground(d?bg:null); frameCreate.showID.setForeground(d?fg:null);
		frameCreate.showName.setBackground(d?bg:null); frameCreate.showName.setForeground(d?fg:null);
		frameCreate.showDeveloper.setBackground(d?bg:null); frameCreate.showDeveloper.setForeground(d?fg:null);
		frameCreate.showPlayedVersion.setBackground(d?bg:null); frameCreate.showPlayedVersion.setForeground(d?fg:null);
		frameCreate.showLastTimeplay.setBackground(d?bg:null); frameCreate.showLastTimeplay.setForeground(d?fg:null);
		frameCreate.showRated.setBackground(d?bg:null); frameCreate.showRated.setForeground(d?fg:null);
		frameCreate.showNewestVersion.setBackground(d?bg:null); frameCreate.showNewestVersion.setForeground(d?fg:null);
		frameCreate.showDateOfLastUpdate.setBackground(d?bg:null); frameCreate.showDateOfLastUpdate.setForeground(d?fg:null);
		frameCreate.showPeopleRating.setBackground(d?bg:null);	frameCreate.showPeopleRating.setForeground(d?fg:null);
		frameCreate.showhowFarUserPlayed.setBackground(d?bg:null); frameCreate.showhowFarUserPlayed.setForeground(d?fg:null);
		frameCreate.showDeletedFromPc.setBackground(d?bg:null); frameCreate.showDeletedFromPc.setForeground(d?fg:null);
		frameCreate.showEngine.setBackground(d?bg:null); frameCreate.showEngine.setForeground(d?fg:null);
		frameCreate.showOS.setBackground(d?bg:null); frameCreate.showOS.setForeground(d?fg:null);
		frameCreate.showLanguage.setBackground(d?bg:null); frameCreate.showLanguage.setForeground(d?fg:null);
		frameCreate.ShowSelfNote.setBackground(d?bg:null); frameCreate.ShowSelfNote.setForeground(d?fg:null);

		frameCreate.help.setBackground(d?bg:null); frameCreate.help.setForeground(d?fg:null);
		frameCreate.faq.setBackground(d?bg:null); frameCreate.faq.setForeground(d?fg:null);
		frameCreate.credits.setBackground(d?bg:null); frameCreate.credits.setForeground(d?fg:null);
		frameCreate.exit.setBackground(d?bg:null); frameCreate.exit.setForeground(d?fg:null);
	}
}
