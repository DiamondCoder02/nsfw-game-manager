package _main.application;


import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import de.jcm.discordgamesdk.activity.ActivityButton;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import _folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import _folderHandle.loadSaveGamesSettings.saveLoadDoc;

public class discord {
	static Integer allGames = saveLoadDoc.allGames;
	static String image = "https://i.imgur.com/lJEl4eK.png";
	// TODO - This seems to be broken, this needs fix
	static ActivityButton button = new ActivityButton("Github", "https://github.com/DiamondPRO02/nsfw-game-manager");
	static Boolean[] boolSettings;

	public static void loopDiscord() throws IOException {
		boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
		if (!boolSettings[3]) { return;}

		Executors.newSingleThreadScheduledExecutor().schedule(() -> { discordRPC(); }, 0, TimeUnit.SECONDS);
	}

	static Instant time;
	private static Runnable discordRPC() {
		time = Instant.now();
		try (CreateParams params = new CreateParams()) {
			params.setClientID(1135539276692607086L);
			params.setFlags(CreateParams.getDefaultFlags());
			// Create the Core
			try (Core core = new Core(params)) {
				try (Activity activity = new Activity()) {
					allGames = saveLoadDoc.allGames;
					// activity.setDetails("Managing my hentai games");
					activity.setState("Currently managing " + allGames + " games");
					// Setting a start time causes an "elapsed" field to appear
					activity.timestamps().setStart(time);
					// Make a "cool" image show up
					activity.assets().setLargeImage(image);
					activity.assets().setLargeText("Horny :3");
					// Custom button
					activity.addButton(button);
					// Finally, update the current activity to our activity
					core.activityManager().updateActivity(activity);
					while(boolSettings[3]){
						boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
						if (!boolSettings[3]) { core.activityManager().clearActivity(); activity.close(); core.close(); return null;}
						try {
							Thread.sleep(1000); // Sleep a bit to save CPU
						}
						catch(InterruptedException e) {
							System.out.println("<.<"); e.printStackTrace();
						}
					}
				} catch (Exception e) { 
					System.out.println(">.>"); e.printStackTrace(); 
				}
			}
		}
		return null;
	}
}