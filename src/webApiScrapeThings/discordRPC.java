package webApiScrapeThings;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import de.jcm.discordgamesdk.activity.ActivityButton;
import folderHandling.initialFileLoading.loadGames;
import folderHandling.initialFileLoading.loadSettings;
import integrationCheck.defaultValues;

import java.time.Instant;

public class discordRPC {
	static String image = "https://i.imgur.com/lJEl4eK.png";
	static ActivityButton button = new ActivityButton("Github", "https://github.com/DiamondCoder02/nsfw-game-manager");
	static Instant time;
	public static Runnable discordStarter(Boolean[] boolSettings) {
		time = Instant.now();
		try (CreateParams params = new CreateParams()) {
			params.setClientID(1135539276692607086L);
			params.setFlags(CreateParams.getDefaultFlags());
			// Create the Core
			try (Core core = new Core(params)) {
				try (Activity activity = new Activity()) {
					Integer allGames = loadGames.loadGamesFromXML(defaultValues.mainDirectory).length;
					// activity.setDetails("Managing my hentai games");
					activity.setState("Currently managing " + allGames + " games");
					// Setting a start time causes an "elapsed" field to appear
					activity.timestamps().setStart(time);
					// Make a "cool" image show up
					activity.assets().setLargeImage(image);
					activity.assets().setLargeText("Horny :3");
					// TODO - Discord custom button broken, this needs fix
					// Custom button
					activity.addButton(button);
					// Finally, update the current activity to our activity
					core.activityManager().updateActivity(activity);
					while(boolSettings[4]){
						boolSettings = loadSettings.othersettings;
						if (!boolSettings[4]) { core.activityManager().clearActivity(); activity.close(); core.close(); return null;}
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
