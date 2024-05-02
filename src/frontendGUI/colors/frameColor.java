package frontendGUI.colors;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import folderHandling.initialFileLoading.loadSettings;

public class frameColor {
	static Color bg = new Color(100, 100, 100);
	static Color fg = new Color(255, 255, 255);
	static Color textdark = new Color(30, 30, 30);

	public static void UIColorChangeShit(){
		Boolean d = loadSettings.othersettings[1];

		// https://thebadprogrammer.com/swing-uimanager-keys/
		UIManager.put("Menu.foreground", d?fg:null);
		UIManager.put("Menu.background", d?bg:null);
		UIManager.put("MenuBar.foreground", d?fg:null);
		UIManager.put("MenuBar.background", d?bg:null);
		UIManager.put("MenuItem.foreground", d?fg:null);
		UIManager.put("MenuItem.background", d?bg:null);
		UIManager.put("CheckBoxMenuItem.foreground", d?fg:null);
		UIManager.put("CheckBoxMenuItem.background", d?bg:null);


		UIManager.put("OptionPane.background", d?bg:null);
		UIManager.put("OptionPane.messageForeground", d?fg:null);

		UIManager.put("OptionPane.background", d?bg:null);
		UIManager.put("OptionPane.messageForeground", d?fg:null);

		UIManager.put("Panel.background", d?bg:null);
		UIManager.put("Panel.messageForeground", d?fg:null);
		UIManager.put("Button.foreground", d?fg:null);
		UIManager.put("Button.background", d?bg:null);
		// UIManager.put("Button.border", d?fg:bg);

		UIManager.put("Label.foreground", d?fg:null);
		UIManager.put("Label.background", d?bg:null);
		UIManager.put("RadioButton.background", d?bg:null);
		UIManager.put("RadioButton.foreground", d?fg:null);
		UIManager.put("CheckBox.background", d?bg:null);
		UIManager.put("CheckBox.foreground", d?fg:null);
		UIManager.put("TextField.background", d?textdark:null);
		UIManager.put("TextField.foreground", d?fg:null);

		/*
		UIDefaults uiDefaults = UIManager.getDefaults();
		uiDefaults.put("activeCaption", Color.gray);
		uiDefaults.put("activeCaptionText", Color.white);
		JFrame.setDefaultLookAndFeelDecorated(true);
		*/
	}

	public static void WindowRefresh(JScrollPane pane, JTable table){
		Boolean d = loadSettings.othersettings[1];
		pane.getViewport().setBackground(d?bg:fg);
		table.getTableHeader().setBackground(d?bg:fg);
		table.getTableHeader().setForeground(d?fg:textdark);
	}
}
