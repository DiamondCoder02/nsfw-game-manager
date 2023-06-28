package windowJframe;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.langLoad;
import main.mainInit;
import xmlFolderHandle.saveLoadDoc;

public class settingsManager {
	static String[] folder = langLoad.folder, butt = langLoad.buton;
	public static void xmlSettings(String TagName, String options){
		// System.out.println(options);
		try{
			Document dom = saveLoadDoc.loadDocument(mainInit.settingsPath);
			NodeList settingsNode = dom.getElementsByTagName("settings");
			Node settingsNodeElement = settingsNode.item(0);
			if (settingsNodeElement.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) settingsNodeElement;
				NodeList allSettings = e.getElementsByTagName(TagName);
				for (int i = 0; i < allSettings.getLength(); i++) {
					Node otherSettingsNode = allSettings.item(i);
					if (otherSettingsNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e2 = (Element) otherSettingsNode;
						String option = e2.getTextContent().trim();
						if (option.equals(options)) {
							String enabled = e2.getAttribute("enabled").trim();
							if (enabled.equals("true")) {
								e2.setAttribute("enabled", "false");
							} else {
								e2.setAttribute("enabled", "true");
							}
							saveLoadDoc.saveDocument(dom, mainInit.settingsPath);
							_initFrame.refreshTable();
						} else if (options.equals("lang")) {
							langChoose(dom);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO https://stackoverflow.com/questions/1881714/how-to-start-stop-restart-a-thread-in-java
	private static void langChoose(Document dom) {
		String[] langButtons = {"english", "hungarian"};
		String[] langMeaining = {"English", "Hungarian / Magyar"};
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
		int result = JOptionPane.showConfirmDialog(null, panel, "Language", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
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
