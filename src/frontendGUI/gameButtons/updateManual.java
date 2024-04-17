package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import folderHandling.ADocHandle;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;
import frontendGUI.mainFrame;
import integrationCheck.defaultValues;

public class updateManual {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;
	
	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup stillOnPc = new ButtonGroup();
	static JPanel stillOnPcPanel = new JPanel();
	static ButtonGroup engineGroup = new ButtonGroup();
	static JPanel enginePanel = new JPanel();

	static JPanel osPanel = new JPanel();

	public static void updateOneGameFromToFile(){
		JOptionPane optionPane = new JOptionPane();
		JPanel panel = new JPanel(new GridLayout(8*2, 2));
		JTextField id = new JTextField();
		Object[] message = {
			jla[0]!=null?jla[0]:"ID of the game to update:", id
		};
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, base[3]!=null?base[3]:"Update game");
		dialog.setVisible(true);
		String idValue = id.getText();
		if (idValue.equals("")) { JOptionPane.showMessageDialog(null, basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }
		
		if (!checkDatabase.isInDatabase(idValue, "man")) { 
			JOptionPane.showMessageDialog(null, 
				idValue+" "+folder[13]!=null?folder[13]:"ID is not found between the manually given games", 
				base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); 
			return;
		}
		try{
			Document dom = ADocHandle.load(defaultValues.mainDirectory + "/hentai.xml");
			Element e = ADocHandle.getElementsFromDB(dom);

			String ids = e.getAttribute("id").trim();
			if (ids.equals(idValue)) {
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

				JTextField newname = new JTextField();
				JTextField newdeveloper = new JTextField();
				JTextField newplayed_version = new JTextField();
				JTextField newdateof_lastplay = new JTextField();
				JTextField newuser_rated = new JTextField();
				JTextField newnewest_version = new JTextField();
				JTextField newdateof_lastupate = new JTextField();
				JTextField newpeople_rated = new JTextField();

				// Not played, In progress, Finish, 100% Finished
				String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
				radioButtons("progress", jrb1, defaultValues.infoProgress, true, oldhowFarUserPlayed);
				// Yes, No, Unknown
				String[] jrb2 = {jrb[4]!=null?jrb[4]:"Yes", jrb[5]!=null?jrb[5]:"No", jrb[6]!=null?jrb[6]:"Unknown"};
				radioButtons("stillOnPc", jrb2, defaultValues.infoOnPc, true, oldstillOnPc);
				// Flash, HTML, Java, QSP, RenPy, RPGmaker, Unity, Unreal, WinGit, WolfRPG, other/unknown
				radioButtons("engine", defaultValues.infoEngine, defaultValues.infoEngine, true, oldengine);
				// Windows, Linux, Mac, Android, other
				radioButtons("os", defaultValues.infoOS, defaultValues.infoOS, false, oldos);
				
				JTextField newlanguage = new JTextField();
				JTextField newselfNote = new JTextField();

				JLabel Namelabel = new JLabel(jla[1]!=null?jla[1]:"Name:" + " ("+(base[5]!=null?base[5]:"old:")+" "+oldname+")");
				panel.add(Namelabel); panel.add(newname);
				JLabel developerlabel = new JLabel(jla[2]!=null?jla[2]:"Developer:" + " ("+(base[5]!=null?base[5]:"old:")+" "+olddeveloper+")");
				panel.add(developerlabel); panel.add(newdeveloper);
				JLabel played_versionlabel = new JLabel(jla[3]!=null?jla[3]:"Played version:" + " ("+(base[5]!=null?base[5]:"old:")+" "+oldplayed_version+")");
				panel.add(played_versionlabel); panel.add(newplayed_version);
				JLabel dateof_lastplaylabel = new JLabel(jla[4]!=null?jla[4]:"Date of last time play:" + " ("+(base[5]!=null?base[5]:"old:")+" "+olddateof_lastplay+")");
				panel.add(dateof_lastplaylabel); panel.add(newdateof_lastplay);
				JLabel user_ratedlabel = new JLabel(jla[5]!=null?jla[5]:"Rated:" + " ("+(base[5]!=null?base[5]:"old:")+" "+olduser_rated+")");
				panel.add(user_ratedlabel); panel.add(newuser_rated);
				JLabel newest_versionlabel = new JLabel(jla[6]!=null?jla[6]:"Newest version:" + " ("+(base[5]!=null?base[5]:"old:")+" "+oldnewest_version+")");
				panel.add(newest_versionlabel); panel.add(newnewest_version);
				JLabel dateOfLastUpdatelabel = new JLabel(jla[7]!=null?jla[7]:"Date of last update:" + " ("+(base[5]!=null?base[5]:"old:")+" "+olddateof_lastupate+")");
				panel.add(dateOfLastUpdatelabel); panel.add(newdateof_lastupate);
				JLabel people_ratedlabel = new JLabel(jla[8]!=null?jla[8]:"People rating:" + " ("+(base[5]!=null?base[5]:"old:")+" "+oldpeople_rated+")");
				panel.add(people_ratedlabel); panel.add(newpeople_rated);
				JLabel howFarUserPlayedlabel = new JLabel(jla[9]!=null?jla[9]:"Player progress:");
				panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
				JLabel stillOnPclabel = new JLabel(jla[10]!=null?jla[10]:"Deleted from pc:");
				panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
				JLabel enginelabel = new JLabel(jla[11]!=null?jla[11]:"Engine:");
				panel.add(enginelabel); panel.add(enginePanel);
				JLabel oslabel = new JLabel(jla[12]!=null?jla[12]:"OS:");
				panel.add(oslabel); panel.add(osPanel);
				JLabel languagelabel = new JLabel(jla[14]!=null?jla[14]:"Language:" + " ("+(base[5]!=null?base[5]:"old:")+" "+oldlanguage+")");
				panel.add(languagelabel); panel.add(newlanguage);
				JLabel selfNotelabel = new JLabel(jla[13]!=null?jla[13]:"Self note:" + " ("+(base[5]!=null?base[5]:"old:")+" "+oldselfNote+")");
				panel.add(selfNotelabel); panel.add(newselfNote);

				int option = JOptionPane.showConfirmDialog(null, panel, base[3]!=null?base[3]:"Update game", JOptionPane.OK_CANCEL_OPTION);
				if (option != JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, 
						oldname+", \nId: "+idValue +" "+ (basic[5]!=null?basic[5]:"was not updated."), 
						base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String newnameValue = newname.getText();
				String newdeveloperValue = newdeveloper.getText();
				String newplayed_versionValue = newplayed_version.getText();
				String newdateof_lastplayValue = newdateof_lastplay.getText();
				String newuser_ratedValue = newuser_rated.getText();
				String newnewest_versionValue = newnewest_version.getText();
				String newdateof_lastupateValue = newdateof_lastupate.getText();
				String newpeople_ratedValue = newpeople_rated.getText();
				String newhowFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
				String newstillOnPcValue = stillOnPc.getSelection().getActionCommand();
				String newengineValue = engineGroup.getSelection().getActionCommand();
				String newosValue = "";
				for (int ik = 0; ik < osPanel.getComponentCount(); ik++) {
					JCheckBox os = (JCheckBox) osPanel.getComponent(ik);
					if (os.isSelected()) { newosValue += os.getText() + " / "; }
				}
				if (newosValue.endsWith(" / ")) { newosValue = newosValue.substring(0, newosValue.length() - 3); }

				String newlanguageValue = newlanguage.getText();
				String newselfNoteValue = newselfNote.getText();
				if (newnameValue.equals("")) { newnameValue = oldname; }
				if (newdeveloperValue.equals("")) { newdeveloperValue = olddeveloper; }
				if (newplayed_versionValue.equals("")) { newplayed_versionValue = oldplayed_version; }
				if (newdateof_lastplayValue.equals("")) { newdateof_lastplayValue = olddateof_lastplay; }
				if (newuser_ratedValue.equals("")) { newuser_ratedValue = olduser_rated; }
				if (newnewest_versionValue.equals("")) { newnewest_versionValue = oldnewest_version; }
				if (newdateof_lastupateValue.equals("")) { newdateof_lastupateValue = olddateof_lastupate; }
				if (newpeople_ratedValue.equals("")) { newpeople_ratedValue = oldpeople_rated; }
				if (newlanguageValue.equals("")) { newlanguageValue = oldlanguage; }
				if (newselfNoteValue.equals("")) { newselfNoteValue = oldselfNote; }
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
				ADocHandle.save(dom, defaultValues.mainDirectory + "/hentai.xml");
				mainFrame.refreshTable();
				JOptionPane.showMessageDialog(null, newnameValue+", \nId: "+idValue +" "+ (basic[4]!=null?basic[4]:"has been updated"), base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void radioButtons(String buttonType, String[] jrbArray, String[] action, Boolean isRB, String selected) {
		ButtonGroup allButtons = new ButtonGroup();
		JPanel buttonPanel = new JPanel();

		if (isRB) { 
			for (int i = 0; i < jrbArray.length; i++) {
				JRadioButton button = new JRadioButton(jrbArray[i], i == 0); 
				button.setActionCommand(action[i]);
				allButtons.add(button);
				buttonPanel.add(button);
				if (action[i].equals(selected)) { button.setSelected(true); }
			}
		}
		else { 
			for (int i = 0; i < jrbArray.length; i++) {
				JCheckBox button = new JCheckBox(jrbArray[i], false);
				button.setActionCommand(action[i]);
				buttonPanel.add(button);
				if (action[i].equals(selected)) { button.setSelected(true); }
			}
		}

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		switch (buttonType) {
			case "progress": howFarUserPlayedPanel = buttonPanel; howFarUserPlayed = allButtons; break;
			case "stillOnPc": stillOnPcPanel = buttonPanel; stillOnPc = allButtons; break;
			case "engine": enginePanel = buttonPanel; engineGroup = allButtons; break;
			case "os": osPanel = buttonPanel; break;
		}
	}
}
