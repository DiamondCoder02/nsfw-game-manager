package frontendGUI.buttons;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import folderHandling.initialFileLoading.loadLanguage;
import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.updateSettings;

public class languageChoice {
	static String[] folder = loadLanguage.folder, butt = loadLanguage.buton;
	public static boolean langChoose(String mainDir) {
		String[][] languages = loadLanguage.loadLangChoices();
		JPanel panel = new JPanel();
		JRadioButton[] buttons = new JRadioButton[languages[0].length];
		panel.setLayout(new GridLayout(2, 1));
		ButtonGroup allTheLanguage = new ButtonGroup();
		for (int i = 0; i < languages[0].length; i++) {
			buttons[i] = new JRadioButton(languages[1][i]);
			buttons[i].setActionCommand(languages[0][i]);
			allTheLanguage.add(buttons[i]);
			panel.add(buttons[i]);
			if (loadSettings.language.equals(languages[0][i])) { buttons[i].setSelected(true); }
		}
		int result = JOptionPane.showConfirmDialog(null, panel, "🌐 "+(butt[4]!=null?butt[4]:"Language"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].isSelected()) {
					updateSettings.changeSetting(mainDir, "appLanguage", buttons[i].getActionCommand());
					return true;
				}
			}
		}
		return false;
	}
}
