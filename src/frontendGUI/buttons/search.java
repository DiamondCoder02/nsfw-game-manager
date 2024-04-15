package frontendGUI.buttons;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import folderHandling.initialFileLoading.loadGames;
import folderHandling.initialFileLoading.loadLanguage;
import integrationCheck.defaultValues;

public class search {
	static boolean found = false;
	static Integer foundNum;
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, tabl = loadLanguage.tabl, serc = loadLanguage.serc;

	static Object[][] data = loadGames.loadGamesFromXML(defaultValues.mainDirectory);
	static Object[][] foundData = new Object[data.length][6];

	public static void searcher(String todoTheAsd){
		switch (todoTheAsd) {
			case "searchId": searchById(); break;
			case "searchDev": searchByDeveloper(); break;
			case "searchName": searchByName(); break;
			default: break;
		}
	}

	private static void searchById() {
		found = false; foundNum = 0;
		String id = JOptionPane.showInputDialog(null, serc[3]!=null?serc[3]:"ID of the game to search:", serc[0]!=null?serc[0]:"Search by ID", JOptionPane.QUESTION_MESSAGE);
		if (id == null || id.equals("")) { JOptionPane.showMessageDialog(null, basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }
		JPanel panel = new JPanel();
		for (int i = 0; i < data.length; i++) {
			if (data[i][1].toString().toLowerCase().equals(id.toLowerCase())) {
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
			panel.add(new JLabel(serc[8]!=null?serc[8]:"Game was not found!!!"));
			panel.add(new JLabel(serc[9]!=null?serc[9]:"Maybe try searching by other ways?"));
			panel.add(new JLabel(serc[10]!=null?serc[10]:"You can search by ID, name and developer too!"));
		} else {
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[1]!=null?tabl[1]:"ID:")+" " + foundData[i][1].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("--------------------")); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[0]!=null?tabl[0]:"Site:")+" " + foundData[i][0].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[2]!=null?tabl[2]:"Name:")+" " + foundData[i][2].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[3]!=null?tabl[3]:"Developer:")+" " + foundData[i][3].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[4]!=null?tabl[4]:"Played version:")+" " + foundData[i][4].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[5]!=null?tabl[5]:"Last time playing:")+" " + foundData[i][5].toString())); }
			panel.add(new JLabel("---------- "+(serc[11]!=null?serc[11]:"Found game(s):")+" " + (foundNum) + "----------"));
		}
		panel.setLayout(new GridLayout(8, foundNum+1));
		JOptionPane.showMessageDialog(null, panel, serc[0]!=null?serc[0]:"Search by ID", JOptionPane.INFORMATION_MESSAGE);
	}

	private static void searchByName() {
		found = false; foundNum = 0;
		String name = JOptionPane.showInputDialog(null, serc[4]!=null?serc[4]:"Name of the game to search:", serc[1]!=null?serc[1]:"Search by Name", JOptionPane.QUESTION_MESSAGE);
		if (name == null || name.equals("")) { JOptionPane.showMessageDialog(null, serc[6]!=null?serc[6]:"Name is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }
		JPanel panel = new JPanel();
		for (int i = 0; i < data.length; i++) {
			if (data[i][2].toString().toLowerCase().contains(name.toLowerCase())) {
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
			panel.add(new JLabel(serc[8]!=null?serc[8]:"Game was not found!!!"));
			panel.add(new JLabel(serc[9]!=null?serc[9]:"Maybe try searching by other ways?"));
			panel.add(new JLabel(serc[10]!=null?serc[10]:"You can search by ID, name and developer too!"));
		} else {
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[2]!=null?tabl[2]:"Name:")+" " + foundData[i][2].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("--------------------")); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[0]!=null?tabl[0]:"Site:")+" " + foundData[i][0].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[1]!=null?tabl[1]:"ID:")+" " + foundData[i][1].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[3]!=null?tabl[3]:"Developer:")+" " + foundData[i][3].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[4]!=null?tabl[4]:"Played version:")+" " + foundData[i][4].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[5]!=null?tabl[5]:"Last time playing:")+" " + foundData[i][5].toString())); }
			panel.add(new JLabel("---------- "+(serc[11]!=null?serc[11]:"Found game(s):")+" " + (foundNum) + "----------"));
		}
		panel.setLayout(new GridLayout(8, foundNum+1));
		JOptionPane.showMessageDialog(null, panel, serc[1]!=null?serc[1]:"Search by Name", JOptionPane.INFORMATION_MESSAGE);
	}

	private static void searchByDeveloper() {
		found = false; foundNum = 0;
		String dev = JOptionPane.showInputDialog(null, serc[5]!=null?serc[5]:"Developer of the game to search:", serc[2]!=null?serc[2]:"Search by Developer", JOptionPane.QUESTION_MESSAGE);
		if (dev == null || dev.equals("")) { JOptionPane.showMessageDialog(null, serc[7]!=null?serc[7]:"Developer is required", base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }
		JPanel panel = new JPanel();
		for (int i = 0; i < data.length; i++) {
			if (data[i][3].toString().toLowerCase().contains(dev.toLowerCase())) {
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
			panel.add(new JLabel(serc[8]!=null?serc[8]:"Game was not found!!!"));
			panel.add(new JLabel(serc[9]!=null?serc[9]:"Maybe try searching by other ways?"));
			panel.add(new JLabel(serc[10]!=null?serc[10]:"You can search by ID, name and developer too!"));
		} else {
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[3]!=null?tabl[3]:"Developer:")+" " + foundData[i][3].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("--------------------")); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[0]!=null?tabl[0]:"Site:")+" " + foundData[i][0].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[1]!=null?tabl[1]:"ID:")+" " + foundData[i][1].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[2]!=null?tabl[2]:"Name:")+" " + foundData[i][2].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[4]!=null?tabl[4]:"Played version:")+" " + foundData[i][4].toString())); }
			for (int i = 0; i < foundNum; i++) { panel.add(new JLabel("|| "+(tabl[5]!=null?tabl[5]:"Last time playing:")+" " + foundData[i][5].toString())); }
			panel.add(new JLabel("---------- "+(serc[11]!=null?serc[11]:"Found game(s):")+" " + (foundNum) + "----------"));
		}
		panel.setLayout(new GridLayout(8, foundNum+1));
		JOptionPane.showMessageDialog(null, panel, serc[2]!=null?serc[2]:"Search by Developer", JOptionPane.INFORMATION_MESSAGE);
	}
}
