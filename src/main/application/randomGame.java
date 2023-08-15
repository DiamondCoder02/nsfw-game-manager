package main.application;

import java.awt.Desktop;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import folderHandle.loadSaveGamesSettings.loadGamesFromXml;
import main.langLoad;

public class randomGame {
	private static String br = "<br>";
	static Object[][] allGames = loadGamesFromXml.loadGames();
	static String[] fld = langLoad.folder;
	public static void fullyRandom() {
		Integer amount = allGames.length;
		Integer random = (int) (Math.random() * amount);
		Object[] result = allGames[random];
		resultShow(result, "Fully random game");
	}

	public static void randomFromDeveloper() {

	}

	public static void randomWithEngine() {

	}

	public static void randomFromSite() {

	}

	private static void resultShow(Object[] result, String title) {
		Boolean[] boolSettings = folderHandle.loadSaveGamesSettings.loadSettingsFromXml.loadBooleanSettings("othersettings");
		String color;
		if (boolSettings[0]) { color = "white"; } else { color = "black"; }
		JEditorPane ep = new JEditorPane();
		ep.setContentType("text/html");
		// TODO language here:
		ep.setText("<p style=\"font-family: Arial\"><span style=\"color:"+color+"\">"+"Fully random game"+br+br+
		"");
		ep.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				Desktop desktop = Desktop.getDesktop();
				if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)){
					try {
						desktop.browse(e.getURL().toURI());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		ep.setEditable(false);
		ep.setOpaque(boolSettings[0] ? false : true);
		JOptionPane.showMessageDialog(null, ep, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
