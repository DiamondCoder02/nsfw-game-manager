package frontendGUI.frames;

import folderHandling.initialFileLoading.loadGames;
import frontendGUI.mainFrame;
import integrationCheck.defaultValues;

public class frameCounter {
	/**
	 * This function will get the number of games from the XML file. <p>
	 * [0] = All games <p>
	 * [1] = Manually added <p>
	 * [2] = F95zone <p>
	 * [3] = Steam <p>
	 */
	public static Integer[] gameCounts;

	public static void getNumberOfGames(String mainProgDir){
		gameCounts = new Integer[defaultValues.infoSite.length+1];
		Object[][] allGames = loadGames.loadGamesFromXML(mainProgDir);
		gameCounts[0] = allGames.length;
		gameCounts[1] = 0;
		gameCounts[2] = 0;
		gameCounts[3] = 0;

		for (int i = 0; i < allGames.length; i++) {
			if (allGames[i][0].equals(defaultValues.infoSite[0])) { gameCounts[1]++; }
			if (allGames[i][0].equals(defaultValues.infoSite[1])) { gameCounts[2]++; }
			if (allGames[i][0].equals(defaultValues.infoSite[2])) { gameCounts[3]++; }
		}

		// "<html> asd <font color='red'>red</font></html>"
		mainFrame.label.setText(
			"<html>  <font color='#FF99FF'>Game List: "+gameCounts[0]+"</font>  / / / / /  "+
			"<font color='#88FF88'>Manually added: "+gameCounts[1]+"</font> // "+
			"<font color='#FF6666'>F95zone: "+gameCounts[2]+"</font> // "+
			"<font color='#88FFFF'>Steam: "+gameCounts[3]+"</font> "+
			"</html>"
		);
		// dlsite #FF5500
	}
}
