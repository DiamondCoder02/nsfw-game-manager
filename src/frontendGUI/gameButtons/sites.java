package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import backendThings.log;
import backendThings.integrationCheck.defaultValues;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;

public class sites {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa;
	/**
	 * This function will request the site and ID from the user.
	 * @param mainDir - The main directory of the program.
	 * @param AUR - add, update, remove
	 * @param optDialog - The dialog text.
	 * @return String[] - returns the site and ID.
	 */
	public static String[] requestSiteAndId(String mainDir, String AUR, String optDialog){
		JPanel panel = new JPanel(new GridLayout(2, 2));

		ButtonGroup webButtons = new ButtonGroup();
		JPanel webPanel = new JPanel();

		for (int i = 0; i < defaultValues.infoSite.length; i++) {
			JRadioButton button = new JRadioButton(defaultValues.infoSites2LOL[i], i==0? true : false); 
			button.setActionCommand(defaultValues.infoSite[i]);
			webButtons.add(button); webPanel.add(button);
		}
		webPanel.setLayout(new BoxLayout(webPanel, BoxLayout.X_AXIS));

		JLabel IDlabel = new JLabel(jla[0]!=null?jla[0]:"ID: (required)");
		JTextField id = new JTextField(8);

		panel.add(IDlabel); panel.add(id);
		panel.add(new JLabel("Website:"));	panel.add(webPanel);

		//  CLOSED - -1  //  Yes-OK - 0  //  No - 1  //  Cancel - 2  //
		Integer option = JOptionPane.showOptionDialog(null, 
			panel, optDialog, 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.PLAIN_MESSAGE, 
			null, null, null);
		if (option != 0) { return null; }

		if (id.getText().equals("")) { 
			JOptionPane.showMessageDialog(null, 
				basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE); 
			return null; 
		}

		String errorMessage = null;
		String finalId = id.getText();
		String finalSite = webButtons.getSelection().getActionCommand();
		if (finalSite.contains("f95zone.to")) { finalSite = "f95"; }
		if (finalSite.contains("store.steampowered.com")) { finalSite = "steam"; }
		if (finalSite.contains("dlsite.com")) { finalSite = "dls"; }

		log.print(finalId, log.WARNING);
		if (finalId.startsWith("http") || finalId.startsWith("www")) {
			switch(finalSite) {
				case "f95": 
					// https://f95zone.to/threads/summertime-saga-v21-0-0-wip-4468-kompas-productions.276/
					// OR
					// https://f95zone.to/threads/276/
					if (finalId.contains("/threads/")) {
						finalId = finalId.split("/threads/")[1];
						if (finalId.contains(".")) { finalId = finalId.split("\\.")[1]; }
						if (finalId.contains("/")) { finalId = finalId.split("/")[0]; }
					} else {
						log.print("F95 link not correct: "+finalId, log.ERROR);
						errorMessage = "F95 link not correct: "+id.getText()+" ("+finalId+")";
					}
					break;
				case "steam":
					// https://store.steampowered.com/app/553850/HELLDIVERS_2/
					// OR
					// https://store.steampowered.com/app/553850/
					if (finalId.contains("/app/")) {
						finalId = finalId.split("/app/")[1].split("/")[0];
						if (finalId.contains("/")) { finalId = finalId.split("/")[0]; }
					} else {
						log.print("Steam link not correct: "+finalId, log.ERROR);
						errorMessage = "Steam link not correct: "+id.getText()+" ("+finalId+")";
					}
					break;
				case "dls":
					// https://www.dlsite.com/maniax/work/=/product_id/RJ01153987.html
					// https://www.dlsite.com/appx/work/=/product_id/RJ01034730.html
					// https://www.dlsite.com/pro/work/=/product_id/VJ010152.html
					// OR
					// TODO - This need to be checked?
					// https://www.dlsite.com/appx/RJ01169385/?locale=en_US
					// https://www.dlsite.com/pro/VJ010152/?locale=en_US
					if (finalId.contains("/product_id/")) {
						finalId = finalId.split("/product_id/")[1].split("\\.")[0];
						finalId = finalId.split("\\.")[0];
					} else {
						log.print("DLsite link not correct: "+finalId, log.ERROR);
						errorMessage = "DLsite link not correct: "+id.getText()+" ("+finalId+")";
					}
					break;
				default:
					log.print("Unknown site: "+finalSite + " - " + finalId, log.ERROR);
					errorMessage = "Unknown site: "+finalSite + " - " + id.getText()+" ("+finalId+")";
			}
		}

		if (errorMessage != null) {
			JOptionPane.showMessageDialog(null, 
				errorMessage, 
				base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE);
			return null;
		}

		boolean isOk = checkDatabase.isInDatabase(mainDir, finalId, finalSite);
		// check if the game is already in the database
		// AUR = add, update, remove
		if (AUR.equals("add") && isOk) { errorMessage = finalId + " ("+finalId+")"+" is already in the database"; } 
		else if ((AUR.equals("update") || AUR.equals("remove")) && !isOk) { errorMessage = finalId + " ("+finalId+")"+" is not in the database"; }

		if (errorMessage != null) {
			JOptionPane.showMessageDialog(null, 
				errorMessage, 
				base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return new String[] {finalSite, finalId};
	}
}
