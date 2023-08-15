package main.application;

import java.awt.Desktop;

import javax.swing.ButtonGroup;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import folderHandle.loadSaveGamesSettings.loadGamesFromXml;
import main.langLoad;

public class randomGame {
	private static String br = "<br>";
	static Object[][] allGames = loadGamesFromXml.loadGames();
	static String[] fld = langLoad.folder;
	public static void fullyRandom() {
		Integer amount = allGames.length;
		Integer random = (int) (Math.random() * amount);
		Object[] result = allGames[random];
		resultShow(result, "Fully random game");
	}

	public static void randomFromDeveloper() {
		Integer devLegth = 0; String wantedRandomDev;
		wantedRandomDev = JOptionPane.showInputDialog(null, "Enter developer name you wish to search for:", "Random game from developer", JOptionPane.QUESTION_MESSAGE);
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][3].equals(wantedRandomDev)) {
				devLegth++;
			}
		}
		if (devLegth == 0) { JOptionPane.showMessageDialog(null, "No games found from developer: "+wantedRandomDev, "Random game from developer", JOptionPane.INFORMATION_MESSAGE); return;}
		Object[][] gamesFromDevs = new Object[devLegth][allGames[0].length];
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][3].equals(wantedRandomDev)) {
				gamesFromDevs[i] = allGames[i];
			}
		}
		Integer random = (int) (Math.random() * devLegth);
		Object[] result = gamesFromDevs[random];
		resultShow(result, "Random game from developer");
	}

	public static void randomWithEngine() {
		Integer engineLegth = 0; String wantedRandomEngine;
		JRadioButton engine_Flash = new JRadioButton("Flash"), engine_HTML = new JRadioButton("HTML"), engine_Java = new JRadioButton("Java"), engine_QSP = new JRadioButton("QSP"), engine_RenPy = new JRadioButton("RenPy"), engine_RPGmaker = new JRadioButton("RPGmaker"), engine_Unity = new JRadioButton("Unity"), engine_Unreal = new JRadioButton("Unreal"), engine_WinGit = new JRadioButton("WinGit"), engine_WolfRPG = new JRadioButton("WolfRPG"), engine_other = new JRadioButton("other/unknown", true);
			engine_Flash.setActionCommand("Flash"); engine_HTML.setActionCommand("HTML"); engine_Java.setActionCommand("Java"); engine_QSP.setActionCommand("QSP"); engine_RenPy.setActionCommand("RenPy"); engine_RPGmaker.setActionCommand("RPGmaker"); engine_Unity.setActionCommand("Unity"); engine_Unreal.setActionCommand("Unreal"); engine_WinGit.setActionCommand("WinGit"); engine_WolfRPG.setActionCommand("WolfRPG"); engine_other.setActionCommand("other/unknown");
			ButtonGroup engineGroup = new ButtonGroup(); engineGroup.add(engine_Flash); engineGroup.add(engine_HTML); engineGroup.add(engine_Java); engineGroup.add(engine_QSP); engineGroup.add(engine_RenPy); engineGroup.add(engine_RPGmaker); engineGroup.add(engine_Unity); engineGroup.add(engine_Unreal); engineGroup.add(engine_WinGit); engineGroup.add(engine_WolfRPG); engineGroup.add(engine_other);
			JLabel engineLabel = new JLabel("Select engine to search for:");
			JPanel enginePanel = new JPanel(); enginePanel.add(engineLabel); enginePanel.add(engine_Flash); enginePanel.add(engine_HTML); enginePanel.add(engine_Java); enginePanel.add(engine_QSP); enginePanel.add(engine_RenPy); enginePanel.add(engine_RPGmaker); enginePanel.add(engine_Unity); enginePanel.add(engine_Unreal); enginePanel.add(engine_WinGit); enginePanel.add(engine_WolfRPG); enginePanel.add(engine_other);
		JOptionPane.showMessageDialog(null, enginePanel, "Random game with engine", JOptionPane.QUESTION_MESSAGE);
		wantedRandomEngine = engineGroup.getSelection().getActionCommand();
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][12].equals(wantedRandomEngine)) {
				engineLegth++;
			}
		}
		if (engineLegth == 0) { JOptionPane.showMessageDialog(null, "No games found with engine: "+wantedRandomEngine, "Random game with engine", JOptionPane.INFORMATION_MESSAGE); return;}
		Object[][] gamesWithEngine = new Object[engineLegth][allGames[0].length];
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][12].equals(wantedRandomEngine)) {
				gamesWithEngine[i] = allGames[i];
			}
		}
		Integer random = (int) (Math.random() * engineLegth);
		Object[] result = gamesWithEngine[random];
		resultShow(result, "Random game with engine");
	}

	public static void randomFromSite() {
		Integer siteLegth = 0; String wantedRandomSite;
		JRadioButton site_F95 = new JRadioButton("F95zone"), site_man = new JRadioButton("Manual", true);
			site_F95.setActionCommand("f95"); site_man.setActionCommand("man");
			ButtonGroup sitesGroup = new ButtonGroup(); sitesGroup.add(site_F95); sitesGroup.add(site_man);
			JLabel siteLabel = new JLabel("Select site to search from:");
			JPanel sitePanel = new JPanel(); sitePanel.add(siteLabel) ;sitePanel.add(site_F95); sitePanel.add(site_man);
		JOptionPane.showMessageDialog(null, sitePanel, "Random game from site", JOptionPane.QUESTION_MESSAGE);
		wantedRandomSite = sitesGroup.getSelection().getActionCommand();
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][0].equals(wantedRandomSite)) {
				siteLegth++;
			}
		}
		if (siteLegth == 0) { JOptionPane.showMessageDialog(null, "No games found from site: "+wantedRandomSite, "Random game from site", JOptionPane.INFORMATION_MESSAGE); return;}
		Object[][] gamesFromSite = new Object[siteLegth][allGames[0].length];
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][0].equals(wantedRandomSite)) {
				gamesFromSite[i] = allGames[i];
			}
		}
		Integer random = (int) (Math.random() * siteLegth);
		Object[] result = gamesFromSite[random];
		resultShow(result, "Random game from site");
	}

	private static void resultShow(Object[] result, String title) {
		Boolean[] boolSettings = folderHandle.loadSaveGamesSettings.loadSettingsFromXml.loadBooleanSettings("othersettings");
		String color;
		if (boolSettings[0]) { color = "white"; } else { color = "black"; }
		String finalLink;
		switch (result[0].toString()) {
			case "f95": finalLink = "<font color = 64AFFF><a href=\"https://f95zone.to/threads/"+result[1]+"/\">Update here if needed</a></font>"; break;
			default: finalLink = "No link available";
		}
		JEditorPane ep = new JEditorPane();
		ep.setContentType("text/html");
		// TODO language here:
		ep.setText("<p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+"Found a game!!!"+br+br+
		"From site: "+result[0]+br+
		"Game name: "+result[2]+br+
		"Developer: "+result[3]+br+
		"Engine: "+result[12]+br+
		"Is the game still on pc: " +(result[13].equals("yes")?"Yes :3":"No :/")+br+
		"Language(s): "+result[14]+br+
		"Link: " + finalLink + 
		"</span></p>");
		ep.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				Desktop desktop = Desktop.getDesktop();
				if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)){
					try {
						desktop.browse(e.getURL().toURI());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		ep.setEditable(false);
		ep.setOpaque(boolSettings[0] ? false : true);
		JOptionPane.showMessageDialog(null, ep, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
