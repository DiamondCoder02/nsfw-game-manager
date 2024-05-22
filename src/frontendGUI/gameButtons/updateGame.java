package frontendGUI.gameButtons;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import backendThings.integrationCheck.defaultValues;
import folderHandling.ADocHandle;
import folderHandling.checkDatabase;
import folderHandling.initialFileLoading.loadLanguage;

public class updateGame {
	static String[] bc = loadLanguage.basic, base = loadLanguage.base;
	public static void updateOneGame(String mainDir) {
		String[] webAndId = sites.requestSiteAndId(base[3]!=null?base[3]:"Update game");
		if (webAndId == null) { return; }

		if (!checkDatabase.isInDatabase(mainDir, webAndId[1], webAndId[0])) { 
			JOptionPane.showMessageDialog(null, 
				"Id: " + webAndId[1] + " ("+webAndId[1]+")"+(bc[5]!=null?bc[5]:"was not been updated"), 
				base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] newInfos = null;
		switch (webAndId[0]) {
			case "f95":
				newInfos = getGamesInfo.getF95zone(webAndId[1]);
				break;
			case "steam":
				newInfos = getGamesInfo.getSteam(webAndId[1]);
				break;
			case "dls":
				newInfos = getGamesInfo.getDLsite(webAndId[1]);
				break;
			case "man":
				newInfos = getGamesInfo.getManual(webAndId[1]);
				break;
		}
		if (newInfos == null) { return; }

		Document dom = ADocHandle.load(mainDir);
		Element e = ADocHandle.getElementFromDB(dom, webAndId[1], webAndId[0]);
		if (e == null) { return; }

		String[] oldInfos = new String[defaultValues.gameInfos.length-1];
		oldInfos[0] = e.getAttribute("id").trim();
		for (int i = 1; i < defaultValues.gameInfos.length-1; i++) {
			oldInfos[i] = e.getElementsByTagName(defaultValues.gameInfos[i+1]).item(0).getTextContent().trim();
		}
		// Funny IDs check because why not
		if (oldInfos[0].equals(newInfos[0])) {
			if (!updateGameHandle.updateGameInDB(mainDir, dom, webAndId[0], oldInfos, newInfos)) {
				JOptionPane.showMessageDialog(null, 
					"Id: " + webAndId[1] + " ("+webAndId[1]+")"+(bc[5]!=null?bc[5]:"was not been updated"), 
					base[1]!=null?base[1]:"Error", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
