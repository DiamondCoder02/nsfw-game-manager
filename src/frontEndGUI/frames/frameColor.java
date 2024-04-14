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
		if (loadSettings.othersettings[2]) {
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
			frame.getContentPane().setBackground(bg);

			// TODO - Fix the colors
			

			/*mb.setBackground(bg).setForeground(fg);
			games.setBackground(bg).setForeground(fg);
			addGame.setBackground(bg).setForeground(fg);
			updateList.setBackground(bg).setForeground(fg);
			removeGame.setBackground(bg).setForeground(fg);

			addF95zone.setBackground(bg); addF95zone.setForeground(fg);
			updateF95.setBackground(bg); updateF95.setForeground(fg);
			removeF95.setBackground(bg); removeF95.setForeground(fg);

			saveFileToDifferent.setBackground(bg); saveFileToDifferent.setForeground(fg);
			refreshTable.setBackground(bg); refreshTable.setForeground(fg);
			refreshFromAPI.setBackground(bg); refreshFromAPI.setForeground(fg);

			random.setBackground(bg); random.setForeground(fg);
			fullRandom.setBackground(bg); fullRandom.setForeground(fg);
			randomDev.setBackground(bg); randomDev.setForeground(fg);
			randomProgress.setBackground(bg); randomProgress.setForeground(fg);
			randomEngine.setBackground(bg); randomEngine.setForeground(fg);
			randomSite.setBackground(bg); randomSite.setForeground(fg);

			search.setBackground(bg); search.setForeground(fg);
			searchById.setBackground(bg); searchById.setForeground(fg);
			searchByName.setBackground(bg); searchByName.setForeground(fg);
			searchByDeveloper.setBackground(bg); searchByDeveloper.setForeground(fg);

			settings.setBackground(bg);	settings.setForeground(fg);
			autoUpdateWanted.setBackground(bg); autoUpdateWanted.setForeground(fg);
			changeLanguage.setBackground(bg); changeLanguage.setForeground(fg);
			changeFolderLocation.setBackground(bg); changeFolderLocation.setForeground(fg);
			darkMode.setBackground(bg);	darkMode.setForeground(fg);
			discordrpc.setBackground(bg); discordrpc.setForeground(fg);
			autoFetchNews.setBackground(bg); autoFetchNews.setForeground(fg);
			autoFetchFolders.setBackground(bg); autoFetchFolders.setForeground(fg);

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
			*/
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
			frame.getContentPane().setBackground(null);

			/*mb.setBackground(null); mb.setForeground(null);
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

			random.setBackground(null); random.setForeground(null);
			fullRandom.setBackground(null); fullRandom.setForeground(null);
			randomDev.setBackground(null); randomDev.setForeground(null);
			randomProgress.setBackground(null); randomProgress.setForeground(null);
			randomEngine.setBackground(null); randomEngine.setForeground(null);
			randomSite.setBackground(null); randomSite.setForeground(null);

			search.setBackground(null); search.setForeground(null);
			searchById.setBackground(null); searchById.setForeground(null);
			searchByName.setBackground(null); searchByName.setForeground(null);
			searchByDeveloper.setBackground(null); searchByDeveloper.setForeground(null);

			settings.setBackground(null);	settings.setForeground(null);
			autoUpdateWanted.setBackground(null); autoUpdateWanted.setForeground(null);
			changeLanguage.setBackground(null); changeLanguage.setForeground(null);
			changeFolderLocation.setBackground(null); changeFolderLocation.setForeground(null);
			darkMode.setBackground(null);	darkMode.setForeground(null);
			discordrpc.setBackground(null); discordrpc.setForeground(null);
			autoFetchNews.setBackground(null); autoFetchNews.setForeground(null);
			autoFetchFolders.setBackground(null); autoFetchFolders.setForeground(null);

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
			*/
		}
	}
}
