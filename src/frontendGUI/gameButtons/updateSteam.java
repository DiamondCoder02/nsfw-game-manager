package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import folderHandling.ADocHandle;
import folderHandling.checkDatabase;
import folderHandling.getSteamFolderInfos;
import folderHandling.initialFileLoading.loadLanguage;
import frontendGUI.mainFrame;
import integrationCheck.defaultValues;
import webApiScrapeThings.sites.loadSteam;

public class updateSteam {
	static String[] bc = loadLanguage.basic, bs = loadLanguage.base, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;

	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup engineGroup = new ButtonGroup();
	static JPanel enginePanel = new JPanel();

	// TODO steam
	public static void updateSteamGame(){
		JPanel panel = new JPanel(new GridLayout(10*2, 0));
		// String idValue = JOptionPane.showInputDialog(null, bc[6]!=null?bc[6]:"Enter the id of the game you want to update", bs[3]!=null?bs[3]:"Update game", JOptionPane.PLAIN_MESSAGE);
		String idValue = "105600";
		if (idValue == null) { JOptionPane.showMessageDialog(null, bc[0]!=null?bc[0]:"You must enter an id", bs[1]!=null?bs[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }

		if (!checkDatabase.isInDatabase(idValue, "steam")) {
			JOptionPane.showMessageDialog(null, 
				"Id: "+idValue+" "+(bc[5]!=null?bc[5]:"was not been updated"), 
				bs[0]!=null?bs[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try{
			Document dom = ADocHandle.load(defaultValues.mainDirectory + "/hentai.xml");
			Element e = ADocHandle.getElementsFromDB(dom);

			String ids = e.getAttribute("id").trim();
			if (!ids.equals(idValue)) { return; }

			String oldname = e.getElementsByTagName("name").item(0).getTextContent().trim();
			String olddeveloper = e.getElementsByTagName("developer").item(0).getTextContent().trim();
			String oldplayed_version = e.getElementsByTagName("played_version").item(0).getTextContent().trim();
			String olddateof_lastplay = e.getElementsByTagName("dateof_lastplay").item(0).getTextContent().trim();
			String olduser_rated = e.getElementsByTagName("user_rating").item(0).getTextContent().trim();
			String oldnewest_version = e.getElementsByTagName("newest_version").item(0).getTextContent().trim();
			String olddateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
			String oldpeople_rated = e.getElementsByTagName("people_rating").item(0).getTextContent().trim();
			String oldhowFarUserPlayed = e.getElementsByTagName("howFarUserPlayed").item(0).getTextContent().trim();
			String oldstillOnPc = e.getElementsByTagName("stillOnPc").item(0).getTextContent().trim();
			String oldengine = e.getElementsByTagName("engine").item(0).getTextContent().trim();
			String oldos = e.getElementsByTagName("OS").item(0).getTextContent().trim();
			String oldlanguage = e.getElementsByTagName("language").item(0).getTextContent().trim();
			String oldselfNote = e.getElementsByTagName("selfNote").item(0).getTextContent().trim();

			String[] output = loadSteam.getSteamUrlContents(idValue);
			String[] output2 = getSteamFolderInfos.getSteamAppInfo(idValue);

			JTextField new_user_rating = new JTextField(15);
			// Not played, In progress, Finish, 100% Finished
			String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
			radioButtons("progress", jrb1, defaultValues.infoProgress, true);
			// Flash, HTML, Java, QSP, RenPy, RPGmaker, Unity, Unreal, WinGit, WolfRPG, other/unknown
			radioButtons("engine", defaultValues.infoEngine, defaultValues.infoEngine, true);
			JTextField new_selfNote = new JTextField(50);





			/*
			e.getElementsByTagName("name").item(0).setTextContent(newnameValue);
			e.getElementsByTagName("developer").item(0).setTextContent(newdeveloperValue);
			e.getElementsByTagName("played_version").item(0).setTextContent(newplayed_versionValue);
			e.getElementsByTagName("dateof_lastplay").item(0).setTextContent(newdateof_lastplayValue);
			e.getElementsByTagName("user_rating").item(0).setTextContent(newuser_ratedValue);
			e.getElementsByTagName("newest_version").item(0).setTextContent(newnewest_versionValue);
			e.getElementsByTagName("dateof_lastupate").item(0).setTextContent(newdateof_lastupateValue);
			e.getElementsByTagName("people_rating").item(0).setTextContent(newpeople_ratedValue);
			e.getElementsByTagName("howFarUserPlayed").item(0).setTextContent(newhowFarUserPlayedValue);
			e.getElementsByTagName("stillOnPc").item(0).setTextContent(newstillOnPcValue);
			e.getElementsByTagName("engine").item(0).setTextContent(newengineValue);
			e.getElementsByTagName("OS").item(0).setTextContent(newosValue);
			e.getElementsByTagName("language").item(0).setTextContent(newlanguageValue);
			e.getElementsByTagName("selfNote").item(0).setTextContent(newselfNoteValue);
			// ADocHandle.save(dom, defaultValues.mainDirectory + "/hentai.xml");
			// mainFrame.refreshTable();
			JOptionPane.showMessageDialog(null, newnameValue+", \nId: "+idValue+" "+(bc[4]!=null?bc[4]:"has been updated"), bs[0]!=null?bs[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void radioButtons(String buttonType, String[] jrbArray, String[] action, Boolean isRB) {
		ButtonGroup allButtons = new ButtonGroup();
		JPanel buttonPanel = new JPanel();

		if (isRB) { 
			for (int i = 0; i < jrbArray.length; i++) {
				JRadioButton button = new JRadioButton(jrbArray[i], i == 0); 
				button.setActionCommand(action[i]);
				allButtons.add(button);
				buttonPanel.add(button);
			}
		}
		else { 
			for (int i = 0; i < jrbArray.length; i++) {
				JCheckBox button = new JCheckBox(jrbArray[i], false);
				button.setActionCommand(action[i]);
				buttonPanel.add(button);
			}
		}

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		switch (buttonType) {
			case "progress": howFarUserPlayedPanel = buttonPanel; howFarUserPlayed = allButtons; break;
			case "engine": enginePanel = buttonPanel; engineGroup = allButtons; break;
		}
	}
}
