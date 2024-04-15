package frontendGUI.buttons;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import folderHandling.changeSettings;
import folderHandling.initialFileLoading.loadLanguage;

public class languageChoice {
	static String[] folder = loadLanguage.folder, butt = loadLanguage.buton;
	static String[] langs = loadLanguage.langChoices, langMeanings = loadLanguage.lanMeans;
	public static void langChoose() {
		String[] langButtons = langs;
		String[] langMeaining = langMeanings;
		JPanel panel = new JPanel();
		JRadioButton[] buttons = new JRadioButton[langButtons.length];
		panel.setLayout(new GridLayout(2, 1));
		ButtonGroup allTheLanguage = new ButtonGroup();
		for (int i = 0; i < langButtons.length; i++) {
			buttons[i] = new JRadioButton(langMeaining[i]);
			buttons[i].setActionCommand(langButtons[i]);
			allTheLanguage.add(buttons[i]);
			panel.add(buttons[i]);
		}
		buttons[0].setSelected(true);
		int result = JOptionPane.showConfirmDialog(null, panel, "🌐 "+(butt[4]!=null?butt[4]:"Language"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].isSelected()) {
					changeSettings.changeSetting("appLanguage", buttons[i].getActionCommand());
				}
			}
		}
	}
}
