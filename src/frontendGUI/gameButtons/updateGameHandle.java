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
import folderHandling.initialFileLoading.loadLanguage;
import frontendGUI.mainFrame;
import integrationCheck.defaultValues;

public class updateGameHandle {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa, 
		folder = loadLanguage.folder, jrb = loadLanguage.jrabu;

	static ButtonGroup howFarUserPlayed = new ButtonGroup();
	static JPanel howFarUserPlayedPanel = new JPanel();
	static ButtonGroup stillOnPc = new ButtonGroup();
	static JPanel stillOnPcPanel = new JPanel();
	static ButtonGroup engineGroup = new ButtonGroup();
	static JPanel enginePanel = new JPanel();

	static String osValue = "";
	static JPanel osPanel = new JPanel();
	
	public static boolean updateGameInDB(Document dom, String site, String[] oldInfo, String[] newInfo) {
		String[] finalInfo = new String[oldInfo.length];
		// TODO - updateGameInDB
		Element e = ADocHandle.getElementFromDB(dom, oldInfo[0]);
		if (e == null) { return false; }

		/*
		0 - ID		1 - Name	2 - Developer	3 - Played version
		4 - Last time play		5 - Rated		6 - Newest version
		7 - Last update		8 - People rating	9 - Player progress
		10 - Still on pc?			11 - Engine		12 - OS
		13 - Language			14 - Personal notes
		*/
		JPanel panel = new JPanel(new GridLayout(8*2, 2));
		JTextField[] textField = new JTextField[oldInfo.length];
		
		for (int i = 0; i < oldInfo.length; i++) {
			if (!oldInfo[i].equals(newInfo[i]) && newInfo[i] != null) {
				JLabel label = new JLabel(jla[i]!=null?jla[i]:"N/A");
				panel.add(label);
				JLabel label2 = new JLabel(oldInfo[i] + " -> " + newInfo[i]);
				panel.add(label2);
				finalInfo[i] = newInfo[i];
			} else if (oldInfo[i].equals(newInfo[i])) { 
				JLabel label = new JLabel(jla[i]!=null?jla[i]:"N/A");
				panel.add(label);
				JLabel label2 = new JLabel(oldInfo[i]);
				panel.add(label2);
				finalInfo[i] = oldInfo[i]; 
			} else if ((oldInfo[i] != null || newInfo[i] != null) && (i == 9 || i == 10 || i == 11 || i == 12)) {
				switch (i) {
					case 9:
						String[] jrb1 = {jrb[0]!=null?jrb[0]:"Not played", jrb[1]!=null?jrb[1]:"In progress", jrb[2]!=null?jrb[2]:"Finish", jrb[3]!=null?jrb[3]:"100% Finished"};
						radioButtons("progress", jrb1, defaultValues.infoProgress, true, oldInfo[i]);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(howFarUserPlayedPanel);
						break;
					case 10:
						String[] jrb2 = {jrb[4]!=null?jrb[4]:"Yes", jrb[5]!=null?jrb[5]:"No", jrb[6]!=null?jrb[6]:"Unknown"};
						radioButtons("stillOnPc", jrb2, defaultValues.infoOnPc, true, oldInfo[i]);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(stillOnPcPanel);
						break;
					case 11:
						radioButtons("engine", defaultValues.infoEngine, defaultValues.infoEngine, true, oldInfo[i]);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(enginePanel);
						break;
					case 12:
						radioButtons("os", defaultValues.infoOS, defaultValues.infoOS, false, oldInfo[i]);
						panel.add(new JLabel(jla[i]!=null?jla[i]:"N/A")); panel.add(osPanel);
						break;
				}
			} else if (!oldInfo[i].equals(newInfo[i]) && newInfo[i] == null) {
				JLabel label = new JLabel(jla[i]!=null?jla[i]:"N/A" + " ("+oldInfo[i]+")");
				panel.add(label);
				textField[i] = new JTextField();
				textField[i].setText(oldInfo[i]);
				panel.add(textField[i]);
			} else if (oldInfo[i].equals("")) {
				JLabel label = new JLabel(jla[i]!=null?jla[i]:"N/A" + " ("+oldInfo[i]+")");
				panel.add(label);
				textField[i] = new JTextField();
				panel.add(textField[i]);
			} else {
				System.out.println("Error in updateGameHandle.java");
				System.out.println("newInfo["+i+"]: "+newInfo[i]);
				System.out.println("oldInfo["+i+"]: "+oldInfo[i]);
			}
		}

		int option = JOptionPane.showConfirmDialog(null, panel, base[3]!=null?base[3]:"Update game", JOptionPane.OK_CANCEL_OPTION);
		if (option != JOptionPane.OK_OPTION) {
			JOptionPane.showMessageDialog(null, 
				oldInfo[1]+", \nId: "+oldInfo[0] +" "+ (basic[4]!=null?basic[4]:"was not updated."), 
				base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		for (int i = 0; i < textField.length; i++) {
			if (textField[i] != null) { finalInfo[i] = textField[i].getText(); }
			if (oldInfo[i] != null && newInfo[i] == null) {
				switch (i) {
					case 9: finalInfo[i] = howFarUserPlayed.getSelection().getActionCommand(); break;
					case 10: finalInfo[i] = stillOnPc.getSelection().getActionCommand(); break;
					case 11: finalInfo[i] = engineGroup.getSelection().getActionCommand(); break;
					case 12:
						for (int j = 0; j < osPanel.getComponentCount(); j++) {
							JCheckBox os = (JCheckBox) osPanel.getComponent(j);
							if (os.isSelected()) { osValue += os.getText() + " / "; }
						}
						if (osValue.endsWith(" / ")) { osValue = osValue.substring(0, osValue.length() - 3); }
						finalInfo[i] = osValue;
						break;
				}
			}
		}

		for (int i = 0; i < finalInfo.length; i++) {
			if (i == 0) { continue; }
			if (finalInfo[i] != null) {
				e.getElementsByTagName(defaultValues.games[i][0]).item(0).setTextContent(finalInfo[i]);
			}
		}

		ADocHandle.save(dom, defaultValues.mainDirectory + "/hentai.xml");
		mainFrame.refreshTable();
		JOptionPane.showMessageDialog(null, newInfo[1]+", \nId: "+newInfo[0] +" "+ (basic[4]!=null?basic[4]:"has been updated"), base[0]!=null?base[0]:"Success", JOptionPane.INFORMATION_MESSAGE);
		return true;
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
