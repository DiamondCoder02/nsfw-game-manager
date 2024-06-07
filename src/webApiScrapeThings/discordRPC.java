package webApiScrapeThings;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import de.jcm.discordgamesdk.activity.ActivityButton;
import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.frames.frameCounter;

import java.time.Instant;

import backendThings.log;

// https://github.com/JnCrMx/discord-game-sdk4j
public class discordRPC {
	static Instant time;
	/**
	 * This function will start the Discord RPC.
	 * @param boolSettings - The settings of the program.
	 * @return Runnable - returns null.
	 */
	public static Runnable discordStarter(Boolean[] boolSettings, String mainDir) {
		time = Instant.now();
		try (CreateParams params = new CreateParams()) {
			params.setClientID(1135539276692607086L);
			params.setFlags(CreateParams.getDefaultFlags());
			// Create the Core
			try (Core core = new Core(params)) {
				try (Activity activity = new Activity()) {
					// activity.setDetails("Managing my hentai games");
					activity.setState("Currently managing " + frameCounter.gameCounts[0] + " games");
					// Setting a start time causes an "elapsed" field to appear
					activity.timestamps().setStart(time);
					// Make a "cool" image show up
					activity.assets().setLargeImage("https://cdn.discordapp.com/avatars/1135539276692607086/519c4b431278ba6326f8304da37c2848");
					activity.assets().setLargeText("Hen-Tie Gamer");
					// Custom button
					ActivityButton button = new ActivityButton("Github", "https://github.com/DiamondCoder02/nsfw-game-manager");
					activity.addButton(button);
					// Finally, update the current activity to our activity
					core.activityManager().updateActivity(activity);
					while(boolSettings[4]){
						boolSettings = loadSettings.othersettings;
						if (!boolSettings[4]) { core.activityManager().clearActivity(); core.close(); return null;}
						try {
							Thread.sleep(1000); // Sleep a bit to save CPU
						}
						catch(InterruptedException e) {
							log.print("<.<", log.ERROR); e.printStackTrace();
						}
					}
				} catch (Exception e) { 
					log.print(">.>", log.ERROR); e.printStackTrace(); 
				}
			}
		}
		return null;
	}
}
