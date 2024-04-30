package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import folderHandling.addGameHandle;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.localFoldersLoad.getSteamFolderInfos;
import webApiScrapeThings.sites.loadF95site;
import webApiScrapeThings.sites.loadSteam;

public class addGame {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	public static void addOneGame() {
		// TODO - addGame
		JPanel panel = new JPanel(new GridLayout(2, 2));

		ButtonGroup webButtons = new ButtonGroup();
		JPanel webPanel = new JPanel();
		// Buttons for possible websites (F95zone - f95, Steam - steam, Manually added - man)
		JRadioButton button = new JRadioButton("F95zone", false); 
		button.setActionCommand("f95");
		JRadioButton button2 = new JRadioButton("Steam", false);
		button2.setActionCommand("steam");
		JRadioButton button3 = new JRadioButton("Manually added", true);
		button3.setActionCommand("man");
		webButtons.add(button); webButtons.add(button2); webButtons.add(button3);
		webPanel.add(button); webPanel.add(button2); webPanel.add(button3);
		webPanel.setLayout(new BoxLayout(webPanel, BoxLayout.X_AXIS));

		JLabel IDlabel = new JLabel(jla[0]!=null?jla[0]:"ID: (required)");
		JTextField id = new JTextField(8);

		panel.add(IDlabel); panel.add(id);
		panel.add(new JLabel("Website:"));	panel.add(webPanel);

		//  CLOSED - -1  //  Yes-OK - 0  //  No - 1  //  Cancel - 2  //
		Integer option = JOptionPane.showOptionDialog(null, panel, 
			base[2]!=null?base[2]:"Add game", 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.PLAIN_MESSAGE, 
			null, null, null);
		if (option != 0) { return; }

		if (id.getText() == null) { 
			JOptionPane.showMessageDialog(null, 
				basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE); 
			return; 
		}
		String webSite = webButtons.getSelection().getActionCommand();
		if (checkDatabase.isInDatabase(id.getText(), webSite)) { 
			JOptionPane.showMessageDialog(null, 
			webSite + " with the id "+id.getText()+" is already in the database", 
				base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] infos = null;
		switch (webSite) {
			case "f95":
				infos = getF95zone(id.getText());
				break;
			case "steam":
				infos = getSteam(id.getText());
				break;
			case "dls":
				infos = getDLsite(id.getText());
				break;
			case "man":
				infos = getManual(id.getText());
				break;
		}

		if (infos == null) { return; }
		addGameHandle.addGameToDB(webSite, infos);
	}

	/* infos
	0 - ID		1 - Name	2 - Developer	3 - Played version
	4 - Last time play		5 - Rated		6 - Newest version
	7 - Last update		8 - People rating	9 - Player progress
	10 - Still on pc?			11 - Engine		12 - OS
	13 - Language			14 - Personal notes
	*/
	private static String[] getF95zone(String id) {
		String[] output = loadF95site.getf95UrlContents(id);
			if (output == null) { return null; }

		String[] infos = {id, output[0], output[1], null, 
			null, null, output[2], 
			output[3], output[4], null, 
			null, output[5], output[6], 
			output[7], null };

		return infos;
	}

	private static String[] getSteam(String id) {
		String[] output = loadSteam.getSteamUrlContents(id);
		if (output == null) { return null; }
		// 0 - LastUpdated		1 - local buildid
		String[] output2 = getSteamFolderInfos.getSteamAppInfo(id);
		Boolean isOnPc = false;
		if (output2 != null) { isOnPc = true;}

		String[] infos = {
			output[0], output[1], output[2], output2[1], 
			output2[0], null, output[3], 
			output[4], output[5], null, 
			isOnPc?"Yes":"No", null, output[6], 
			output[7], null };

		return infos;
	}

	private static String[] getDLsite(String id) {
		// TODO - DLsite
		return null;
	}

	private static String[] getManual(String id) {
		String[] infos = {id,null,null,null,
			null,null,null,
			null,null,null,
			null,null,null,
			null,null};
		return infos;
	}
}
