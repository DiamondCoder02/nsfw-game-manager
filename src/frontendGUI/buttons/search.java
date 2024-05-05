package frontendGUI.buttons;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import folderHandling.initialFileLoading.loadGames;
import folderHandling.initialFileLoading.loadLanguage;
import integrationCheck.defaultValues;

public class search {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, tabl = loadLanguage.tabl, serc = loadLanguage.serc;
	public static void searcher(String todoTheAsd){
		switch (todoTheAsd) {
			case "searchId": searchDynamic(
				serc[3]!=null?serc[3]:"ID of the game to search:", 
				serc[0]!=null?serc[0]:"Search by ID", 
				basic[0]!=null?basic[0]:"ID is required", 
				1
			); break;
			case "searchName": searchDynamic(
				serc[4]!=null?serc[4]:"Name of the game to search:",
				serc[1]!=null?serc[1]:"Search by Name",
				serc[6]!=null?serc[6]:"Name is required",
				2
			); break;
			case "searchDev": searchDynamic(
				serc[5]!=null?serc[5]:"Developer of the game to search:", 
				serc[2]!=null?serc[2]:"Search by Developer",
				serc[7]!=null?serc[7]:"Developer is required",
				3
			); break;
			default: break;
		}
	}

	private static void searchDynamic(String inDialog1, String inDialog2, String errorDialog, Integer seatchNum){
		String searchFor = JOptionPane.showInputDialog(null, inDialog1, inDialog2, JOptionPane.QUESTION_MESSAGE);
		if (searchFor == null || searchFor.equals("")) { JOptionPane.showMessageDialog(null, errorDialog, base[1]!=null?base[1]:"Error", JOptionPane.ERROR_MESSAGE); return; }

		String[][] foundData = searchDB(searchFor, seatchNum);

		if (foundData == null ) {
			JPanel panel = new JPanel();
			panel.add(new JLabel(serc[8]!=null?serc[8]:"Game was not found!!!"));
			panel.add(new JLabel(serc[9]!=null?serc[9]:"Maybe try searching by other ways?"));
			panel.add(new JLabel(serc[10]!=null?serc[10]:"You can search by ID, name and developer too!"));
			panel.setLayout(new GridLayout(3, 1));
			JOptionPane.showMessageDialog(null, panel, inDialog2, JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int counter = 0;
		int amountPerPage = 3; // 3 games per page
		do {
			String[][] tempData = new String[amountPerPage][foundData[0].length];
			for (int i = 0; i < amountPerPage; i++) { 
				for (int j = 0; j < foundData[0].length; j++) { 
					if (counter >= foundData.length) { break; }
					if (foundData[counter][j] == null) { continue; }
					tempData[i][j] = foundData[counter][j]; 
				} 
				counter++; 
			}
			counter = pagination(tempData, counter, inDialog2, foundData.length); 
			System.out.println("Counter: " + counter);
			if (counter >= foundData.length) { return; }
			System.out.println("Counter: " + counter);
		} while (counter > 0);
		return; 
	}

	private static int pagination(String[][] data, int counter, String inDialog2, int allDataLength){
		int isThree = allDataLength%3;

		JPanel panel = new JPanel();
		for (int i = 0; i < data.length; i++) { if (data[i][0] != null) {panel.add(new JLabel("--------------------")); }}
		for (int i = 0; i < data.length; i++) { if (data[i][0] != null) {panel.add(new JLabel("|| "+(tabl[0]!=null?tabl[0]:"Site:")+" " + data[i][0].toString())); }}
		for (int i = 0; i < data.length; i++) { if (data[i][1] != null) {panel.add(new JLabel("|| "+(tabl[1]!=null?tabl[1]:"ID:")+" " + data[i][1].toString())); }}
		for (int i = 0; i < data.length; i++) { if (data[i][2] != null) {panel.add(new JLabel("|| "+(tabl[2]!=null?tabl[2]:"Name:")+" " + data[i][2].toString())); }}
		for (int i = 0; i < data.length; i++) { if (data[i][3] != null) {panel.add(new JLabel("|| "+(tabl[3]!=null?tabl[3]:"Developer:")+" " + data[i][3].toString())); }}
		for (int i = 0; i < data.length; i++) { if (data[i][4] != null) {panel.add(new JLabel("|| "+(tabl[4]!=null?tabl[4]:"Played version:")+" " + data[i][4].toString())); }}
		for (int i = 0; i < data.length; i++) { if (data[i][5] != null) {panel.add(new JLabel("|| "+(tabl[5]!=null?tabl[5]:"Last time playing:")+" " + data[i][5].toString())); }}
		panel.add(new JLabel("---------- "+(serc[11]!=null?serc[11]:"Found game(s):")+" " + (allDataLength) + " ---------- " + "Page " + (counter/3) + " / " + (isThree==0 ? allDataLength/3:allDataLength/3+1) + "----------"));
		panel.setLayout(new GridLayout(8, isThree==0 ? data.length+1:data.length+2));
		JOptionPane.showMessageDialog(null, panel, inDialog2, JOptionPane.INFORMATION_MESSAGE);

		return counter;
	}

	private static String[][] searchDB(String searchFor, Integer seatchNum){
		Object[][] data = loadGames.loadGamesFromXML(defaultValues.mainDirectory);
		Integer foundNum = 0;
		String[][] foundData = new String[data.length][data[0].length];
		for (int i = 0; i < data.length; i++) {
			if (data[i][seatchNum].toString().toLowerCase().contains(searchFor.toLowerCase())) {
				for (int j = 0; j < data[i].length; j++) { if (data[i][j] == null) { continue; } foundData[foundNum][j] = data[i][j].toString(); }
				foundNum++;
			}
		}
		if (foundNum == 0) { return null; }
		String[][] foundData2 = new String[foundNum][data[0].length];
		for (int i = 0; i < foundNum; i++) { for (int j = 0; j < data[0].length; j++) { if (foundData[i][j] == null) { continue; } foundData2[i][j] = foundData[i][j]; } }
		return foundData2;
	}
}
