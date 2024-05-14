package frontendGUI.buttons;

import java.awt.Desktop;

import javax.swing.ButtonGroup;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import folderHandling.initialFileLoading.loadGames;
import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.mainProgramStart;
import integrationCheck.defaultValues;

public class randomGames {
	static String[] jrb = loadLanguage.jrabu, tlc = loadLanguage.tabl, ran= loadLanguage.rand;

	public static void randoms(String choice){
		switch (choice) {
			case "RandomFully": fullyRandom(); break;
			case "RandomDev": randomFromDeveloper(); break;
			case "RandomProgress": createButtonGroup(
				ran[8]!=null?ran[8]:"Select progress to search for:", 
				ran[3]!=null?ran[3]:"Random game from progress", 
				ran[10]!=null?ran[10]:"from progress",
				10, 
				defaultValues.infoProgress
				); 
				break;
			case "RandomEngine":createButtonGroup(
				ran[9]!=null?ran[9]:"Select engine to search for:", 
				ran[4]!=null?ran[4]:"Random game with engine", 
				ran[10]!=null?ran[10]:"with engine",
				12, 
				defaultValues.infoEngine
				);
				break;
			case "RandomSite": createButtonGroup(
				ran[11]!=null?ran[11]:"Select site to search from:", 
				ran[5]!=null?ran[5]:"Random game from site", 
				ran[12]!=null?ran[12]:"from site", 
				0, 
				defaultValues.infoSite
				);
				break;
			default: break;
		}
	}

	static Object[][] allGames = loadGames.loadGamesFromXML(mainProgramStart.mainProgDir);
	private static void fullyRandom() {
		Integer amount = allGames.length;
		Integer random = (int) (Math.random() * amount);
		Object[] result = allGames[random];
		resultShow(result, ran[1]!=null?ran[1]:"Fully random game");
	}

	private static void randomFromDeveloper() {
		String wantedRandomDev = JOptionPane.showInputDialog(null, 
			ran[6]!=null?ran[6]:"Enter developer name you wish to search for:", 
			ran[2]!=null?ran[2]:"Random game from developer", 
			JOptionPane.QUESTION_MESSAGE
		);
		if (wantedRandomDev == null) { return; }
		Object[] result = randomFilter(3, ran[7]!=null?ran[7]:"from developer", wantedRandomDev);
		if (result != null) {resultShow(result, ran[2]!=null?ran[2]:"Random game from developer");}
	}

	private static void createButtonGroup(String jLabel, String messageDialog, String fromWithWhere, Integer place, String[] options){
		ButtonGroup allButtons = new ButtonGroup();
		JLabel siteLabel = new JLabel(jLabel);
		JPanel sitePanel = new JPanel(); 
		sitePanel.add(siteLabel);

		for (int i = 0; i < options.length; i++) {
			JRadioButton site = new JRadioButton(options[i], i == 0);
			site.setActionCommand(options[i]);
			allButtons.add(site);
			sitePanel.add(site);
		}

		Integer resul = JOptionPane.showConfirmDialog(null, sitePanel, messageDialog, JOptionPane.OK_CANCEL_OPTION);
		if (resul != JOptionPane.OK_OPTION) { return; }
		Object[] result = randomFilter(place, fromWithWhere, allButtons.getSelection().getActionCommand());
		if (result != null) { resultShow(result, messageDialog); }
	}

	/* Integer place:
	0 - Site		1 - ID		2 - Name	3 - Developer
	4 - Played version			5 - Last time play
	6 - Rated		7 - Newest version		8 - Last update
	9 - People rating			10 - Player progress
	11 - Still on pc?			12 - Engine	
	13 - OS			14 - Language			15 - Personal notes
	*/
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
		String br = "<br>";
		Boolean[] boolSettings = loadSettings.othersettings;
		String color;
		if (boolSettings[1]) { color = "white"; } else { color = "black"; }
		String finalLink, updateLink = (ran[14]!=null?ran[14]:"Update link here if needed");
		switch (result[0].toString()) {
			case "f95": finalLink = "<font color = 64AFFF><a href=\"https://f95zone.to/threads/"+result[1]+"/\">"+updateLink+"</a></font>"; break;
			case "steam": finalLink = "<font color = 64AFFF><a href=\"https://store.steampowered.com/app/"+result[1]+"/\">"+updateLink+"</a></font>"; break;
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
		("Is still on pc?")+" - "+(result[13].equals("yes")?"✔ :3":"❌ :/")+br+
		("Language(s)")+": "+result[14]+br+
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
		ep.setOpaque(boolSettings[1] ? false : true);
		JOptionPane.showMessageDialog(null, ep, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
