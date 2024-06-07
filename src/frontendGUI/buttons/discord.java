package frontendGUI.buttons;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import folderHandling.initialFileLoading.loadSettings;
import webApiScrapeThings.discordRPC;

public class discord {
	private static boolean discordRunning = false;
	public static void loopDiscord(String mainDire) {
		loadSettings.load(mainDire);
		Boolean [] boolSettings = loadSettings.othersettings;
		if (!boolSettings[4]) { discordRunning = false; return;}
		if (discordRunning) { return; }

		try {
			Executors.newSingleThreadScheduledExecutor().schedule(() -> { discordRPC.discordStarter(boolSettings, mainDire); }, 0, TimeUnit.SECONDS);
			discordRunning = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
