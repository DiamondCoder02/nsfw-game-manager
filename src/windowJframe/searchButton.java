package windowJframe;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.Document;

import xmlFolderHandle._initXml;
import xmlFolderHandle.saveLoadDoc;

public class searchButton {
	static Document dom = saveLoadDoc.loadDocument();
	static String[] columnNames = _initXml.allColumns(dom);
	static Object[][] data = _initXml.loadGames(dom, columnNames);
	static Object[][] foundData = new Object[data.length][6];
	static boolean found = false;
	static Integer foundNum;
	public static void searchById() {
		found = false; foundNum = 0;
		String id = JOptionPane.showInputDialog(null, "ID of the game to search:", "Search by ID", JOptionPane.QUESTION_MESSAGE);
		if (id == null || id.equals("")) { JOptionPane.showMessageDialog(null, "ID is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
		JPanel panel = new JPanel();
		for (int i = 0; i < data.length; i++) {
			if (data[i][1].toString().toLowerCase().equals(id.toLowerCase())) {
				panel.setName("Game ID found");
				foundData[foundNum][0] = data[i][0].toString();
				foundData[foundNum][1] = data[i][1].toString();
				foundData[foundNum][2] = data[i][2].toString();
				foundData[foundNum][3] = data[i][3].toString();
				foundData[foundNum][4] = data[i][4].toString();
				foundData[foundNum][5] = data[i][5].toString();
				found = true; foundNum++;
			}
		}
		if (!found) {
			panel.setName("Game ID not found");
			panel.add(new JLabel("Game ID not found!!!"));
			panel.add(new JLabel("Maybe try searching by other ways?"));
			panel.add(new JLabel("You can search by name and developer too!"));
		} else {
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| ID: " + foundData[i][1].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("--------------------")); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Site: " + foundData[i][0].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Name: " + foundData[i][2].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Developer: " + foundData[i][3].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Played version: " + foundData[i][4].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Last time playing: " + foundData[i][5].toString())); }
			panel.add(new JLabel("---------- Found game(s): " + (foundNum) + "----------"));
		}
		panel.setLayout(new GridLayout(8, foundNum+1));
		JOptionPane.showMessageDialog(null, panel, "Search by ID", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void searchByName() {
		found = false; foundNum = 0;
		String name = JOptionPane.showInputDialog(null, "Name of the game to search:", "Search by Name", JOptionPane.QUESTION_MESSAGE);
		if (name == null || name.equals("")) { JOptionPane.showMessageDialog(null, "Name is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
		JPanel panel = new JPanel();
		for (int i = 0; i < data.length; i++) {
			if (data[i][2].toString().toLowerCase().contains(name.toLowerCase())) {
				panel.setName("Game name found");
				foundData[foundNum][0] = data[i][0].toString();
				foundData[foundNum][1] = data[i][1].toString();
				foundData[foundNum][2] = data[i][2].toString();
				foundData[foundNum][3] = data[i][3].toString();
				foundData[foundNum][4] = data[i][4].toString();
				foundData[foundNum][5] = data[i][5].toString();
				found = true; foundNum++;
			}
		}
		if (!found) {
			panel.setName("Game name not found");
			panel.add(new JLabel("Game name not found!!!"));
			panel.add(new JLabel("Maybe try searching by other ways?"));
			panel.add(new JLabel("You can search by ID and developer too!"));
		} else {
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Name: " + foundData[i][2].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("--------------------")); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Site: " + foundData[i][0].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| ID: " + foundData[i][1].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Developer: " + foundData[i][3].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Played version: " + foundData[i][4].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Last time playing: " + foundData[i][5].toString())); }
			panel.add(new JLabel("---------- Found game(s): " + (foundNum) + "----------"));
		}
		panel.setLayout(new GridLayout(8, foundNum+1));
		JOptionPane.showMessageDialog(null, panel, "Search by Name", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void searchByDeveloper() {
		found = false; foundNum = 0;
		String dev = JOptionPane.showInputDialog(null, "Developer of the game to search:", "Search by Developer", JOptionPane.QUESTION_MESSAGE);
		if (dev == null || dev.equals("")) { JOptionPane.showMessageDialog(null, "Developer is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
		JPanel panel = new JPanel();
		for (int i = 0; i < data.length; i++) {
			if (data[i][3].toString().toLowerCase().contains(dev.toLowerCase())) {
				panel.setName("Game developer found");
				foundData[foundNum][0] = data[i][0].toString();
				foundData[foundNum][1] = data[i][1].toString();
				foundData[foundNum][2] = data[i][2].toString();
				foundData[foundNum][3] = data[i][3].toString();
				foundData[foundNum][4] = data[i][4].toString();
				foundData[foundNum][5] = data[i][5].toString();
				found = true; foundNum++;
			}
		}
		if (!found) {
			panel.setName("Game developer not found");
			panel.add(new JLabel("Game developer not found!!!"));
			panel.add(new JLabel("Maybe try searching by other ways?"));
			panel.add(new JLabel("You can search by ID and name too!"));
		} else {
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Developer: " + foundData[i][3].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("--------------------")); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Site: " + foundData[i][0].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| ID: " + foundData[i][1].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Name: " + foundData[i][2].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Played version: " + foundData[i][4].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| Last time playing: " + foundData[i][5].toString())); }
			panel.add(new JLabel("---------- Found game(s): " + (foundNum) + "----------"));
		}
		panel.setLayout(new GridLayout(8, foundNum+1));
		JOptionPane.showMessageDialog(null, panel, "Search by Developer", JOptionPane.INFORMATION_MESSAGE);
	}
}
