package frontendGUI.buttons;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import folderHandling.initialFileLoading.loadSettings;
import webApiScrapeThings.discordRPC;

public class discord {
	public static void loopDiscord() {
		Boolean [] boolSettings = loadSettings.othersettings;
		if (!boolSettings[4]) { return;}

		try {
			Executors.newSingleThreadScheduledExecutor().schedule(() -> { discordRPC.discordStarter(boolSettings); }, 0, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
