package folderHandle.loadSaveGamesSettings.choices;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandle.loadSaveGamesSettings.saveLoadDoc;
import main.langLoad;
import main.mainInit;

public class languageChoices {
	static String[] folder = langLoad.folder, butt = langLoad.buton;
	static String[] langs = langLoad.langChoices, langMeanings = langLoad.lanMeans;
	// TODO https://stackoverflow.com/questions/1881714/how-to-start-stop-restart-a-thread-in-java
	public static void langChoose(Document dom) {
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
					NodeList settingsNode = dom.getElementsByTagName("settings");
					Node settingsNodeElement = settingsNode.item(0);
					if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) settingsNodeElement;
						NodeList allSettings = e.getElementsByTagName("language");
						for (int j = 0; j < allSettings.getLength(); j++) {
							Node otherSettingsNode = allSettings.item(j);
							if (otherSettingsNode.getNodeType() == Node.ELEMENT_NODE) {
								Element e2 = (Element) otherSettingsNode;
								e2.setTextContent(buttons[i].getActionCommand());
								saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
								JOptionPane.showMessageDialog(null, folder[7]!=null?folder[7]:"Please restart the program to apply the changes!", butt[2]!=null?butt[2]:"Restart", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
			}
		}
	}
}
