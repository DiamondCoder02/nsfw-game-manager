package _main.application;

import java.awt.Desktop;

import javax.swing.ButtonGroup;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import _folderHandle.loadSaveGamesSettings.loadGamesFromXml;
import _main.langLoad;

public class randomGame {
	static String[] jrb = langLoad.jrabu, tlc = langLoad.tabl, ran= langLoad.rand;
	private static String br = "<br>";
	static Object[][] allGames = loadGamesFromXml.loadGames();
	public static void fullyRandom() {
		Integer amount = allGames.length;
		Integer random = (int) (Math.random() * amount);
		Object[] result = allGames[random];
		resultShow(result, ran[1]!=null?ran[1]:"Fully random game");
	}

	public static void randomFromDeveloper() {
		String wantedRandomDev = JOptionPane.showInputDialog(null, ran[6]!=null?ran[6]:"Enter developer name you wish to search for:", ran[2]!=null?ran[2]:"Random game from developer", JOptionPane.QUESTION_MESSAGE);
		if (wantedRandomDev == null) { return; }
		Object[] result = randomFilter(3, ran[7]!=null?ran[7]:"from developer", wantedRandomDev);
		if (result != null) {resultShow(result, ran[2]!=null?ran[2]:"Random game from developer");}
	}

	public static void randomFromProgress(){
		JRadioButton howFarUserPlayed_NotPlayed = new JRadioButton(jrb[0]!=null?jrb[0]:"Not played", true), howFarUserPlayed_Playing = new JRadioButton(jrb[1]!=null?jrb[1]:"In progress", false), howFarUserPlayed_Finished = new JRadioButton(jrb[2]!=null?jrb[2]:"Finish", false), howFarUserPlayed_100Percent = new JRadioButton(jrb[3]!=null?jrb[3]:"100% Finished", false);
			howFarUserPlayed_NotPlayed.setActionCommand("Not played"); howFarUserPlayed_Playing.setActionCommand("In progress"); howFarUserPlayed_Finished.setActionCommand("Finish"); howFarUserPlayed_100Percent.setActionCommand("100% Finished");
			ButtonGroup progressGroup = new ButtonGroup(); progressGroup.add(howFarUserPlayed_NotPlayed); progressGroup.add(howFarUserPlayed_Playing); progressGroup.add(howFarUserPlayed_Finished); progressGroup.add(howFarUserPlayed_100Percent);
			JPanel progressPanel = new JPanel(); progressPanel.add(howFarUserPlayed_NotPlayed); progressPanel.add(howFarUserPlayed_Playing); progressPanel.add(howFarUserPlayed_Finished); progressPanel.add(howFarUserPlayed_100Percent);
		JOptionPane.showMessageDialog(null, progressPanel, ran[3]!=null?ran[3]:"Random game from progress", JOptionPane.QUESTION_MESSAGE);
		Object[] result = randomFilter(10, ran[8]!=null?ran[8]:"from progress", progressGroup.getSelection().getActionCommand());
		if (result != null) {resultShow(result, ran[3]!=null?ran[3]:"Random game from progress");}
	}

	public static void randomWithEngine() {
		JRadioButton engine_Flash = new JRadioButton("Flash"), engine_HTML = new JRadioButton("HTML"), engine_Java = new JRadioButton("Java"), engine_QSP = new JRadioButton("QSP"), engine_RenPy = new JRadioButton("RenPy"), engine_RPGmaker = new JRadioButton("RPGmaker"), engine_Unity = new JRadioButton("Unity"), engine_Unreal = new JRadioButton("Unreal"), engine_WinGit = new JRadioButton("WinGit"), engine_WolfRPG = new JRadioButton("WolfRPG"), engine_other = new JRadioButton(jrb[7]!=null?jrb[7]:"other/unknown", true);
			engine_Flash.setActionCommand("Flash"); engine_HTML.setActionCommand("HTML"); engine_Java.setActionCommand("Java"); engine_QSP.setActionCommand("QSP"); engine_RenPy.setActionCommand("Ren'Py"); engine_RPGmaker.setActionCommand("RPGM"); engine_Unity.setActionCommand("Unity"); engine_Unreal.setActionCommand("Unreal Engine"); engine_WinGit.setActionCommand("WinGit"); engine_WolfRPG.setActionCommand("WolfRPG"); engine_other.setActionCommand("other/unknown");
			ButtonGroup engineGroup = new ButtonGroup(); engineGroup.add(engine_Flash); engineGroup.add(engine_HTML); engineGroup.add(engine_Java); engineGroup.add(engine_QSP); engineGroup.add(engine_RenPy); engineGroup.add(engine_RPGmaker); engineGroup.add(engine_Unity); engineGroup.add(engine_Unreal); engineGroup.add(engine_WinGit); engineGroup.add(engine_WolfRPG); engineGroup.add(engine_other);
			JLabel engineLabel = new JLabel(ran[9]!=null?ran[9]:"Select engine to search for:");
			JPanel enginePanel = new JPanel(); enginePanel.add(engineLabel); enginePanel.add(engine_Flash); enginePanel.add(engine_HTML); enginePanel.add(engine_Java); enginePanel.add(engine_QSP); enginePanel.add(engine_RenPy); enginePanel.add(engine_RPGmaker); enginePanel.add(engine_Unity); enginePanel.add(engine_Unreal); enginePanel.add(engine_WinGit); enginePanel.add(engine_WolfRPG); enginePanel.add(engine_other);
		JOptionPane.showMessageDialog(null, enginePanel, ran[4]!=null?ran[4]:"Random game with engine", JOptionPane.QUESTION_MESSAGE);
		Object[] result = randomFilter(12, ran[10]!=null?ran[10]:"with engine", engineGroup.getSelection().getActionCommand());
		if (result != null) {resultShow(result, ran[4]!=null?ran[4]:"Random game with engine");}
	}

	public static void randomFromSite() {
		JRadioButton site_F95 = new JRadioButton("F95zone"), site_man = new JRadioButton("man", true);
			site_F95.setActionCommand("f95"); site_man.setActionCommand("man");
			ButtonGroup sitesGroup = new ButtonGroup(); sitesGroup.add(site_F95); sitesGroup.add(site_man);
			JLabel siteLabel = new JLabel(ran[11]!=null?ran[11]:"Select site to search from:");
			JPanel sitePanel = new JPanel(); sitePanel.add(siteLabel) ;sitePanel.add(site_F95); sitePanel.add(site_man);
		JOptionPane.showMessageDialog(null, sitePanel, ran[5]!=null?ran[5]:"Random game from site", JOptionPane.QUESTION_MESSAGE);
		Object[] result = randomFilter(0, ran[12]!=null?ran[12]:"from site", sitesGroup.getSelection().getActionCommand());
		if (result != null) {resultShow(result, ran[5]!=null?ran[5]:"Random game from site");}
	}

	private static Object[] randomFilter(Integer place, String fromWith, String wanted){
		Integer length = 0;
		Object[] result;
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][place].toString().contains(wanted)) {
				length++;
			}
		}
		if (length == 0) { JOptionPane.showMessageDialog(null, (ran[13]!=null?ran[13]:"No games found")+" "+fromWith+": "+wanted, (ran[13]!=null?ran[13]:"No games found")+"!", JOptionPane.INFORMATION_MESSAGE); return null;}
		Object[][] gamesFromSite = new Object[length][allGames[0].length];
		Integer counter = 0;
		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][place].toString().contains(wanted)) {
				gamesFromSite[counter] = allGames[i];
				counter++;
			}
		}
		Integer random = (int) (Math.random() * length);
		result = gamesFromSite[random];
		return result;
	}

	private static void resultShow(Object[] result, String title) {
		Boolean[] boolSettings = _folderHandle.loadSaveGamesSettings.loadSettingsFromXml.loadBooleanSettings("othersettings");
		String color;
		if (boolSettings[0]) { color = "white"; } else { color = "black"; }
		String finalLink, updateLink = (ran[14]!=null?ran[14]:"Update link here if needed");
		switch (result[0].toString()) {
			case "f95": finalLink = "<font color = 64AFFF><a href=\"https://f95zone.to/threads/"+result[1]+"/\">"+updateLink+"</a></font>"; break;
		// 	case "dls": "aaaaaaaaaaaaaaaa"; break;
			default: finalLink = (ran[15]!=null?ran[15]:"No link available");
		}
		JEditorPane ep = new JEditorPane();
		ep.setContentType("text/html");
		ep.setText("<p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+(ran[16]!=null?ran[16]:"Found a game!")+br+br+
		(tlc[0]!=null?tlc[0]:"Site")+": "+result[0]+br+
		(tlc[2]!=null?tlc[2]:"Name")+": "+result[2]+br+
		(tlc[3]!=null?tlc[3]:"Developer")+": "+result[3]+br+
		(tlc[12]!=null?tlc[12]:"Engine")+": "+result[12]+br+
		(tlc[13]!=null?tlc[13]:"Is still on pc")+" - "+(result[13].equals("yes")?"✔ :3":"❌ :/")+br+
		(tlc[14]!=null?tlc[14]:"Language(s)")+": "+result[14]+br+
		"👆: "+finalLink+
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
