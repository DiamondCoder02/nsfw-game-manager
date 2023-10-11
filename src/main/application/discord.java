package main.application;

// thank you <3 ; https://github.com/JnCrMx/discord-game-sdk4j
import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import de.jcm.discordgamesdk.activity.ActivityButton;
import de.jcm.discordgamesdk.activity.ActivityButtonsMode;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Locale;

import folderHandle.discord.downloadDiscordSDK;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;

public class discord {
	static Integer allGames = saveLoadDoc.allGames;
	public static void discordFirstInit() throws IOException {
		String name = "discord_game_sdk"; String suffix;
		String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		String arch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);
		String image = "https://cdn.discordapp.com/app-assets/1135539276692607086/1135541050702831778.png";
		Instant time = Instant.now();

		Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
		if (!boolSettings[3]) { return;}

		if (osName.contains("windows")) { suffix = ".dll";
		} else if (osName.contains("linux")) { suffix = ".so";
		} else if (osName.contains("mac os")) { suffix = ".dylib";
		} else { throw new RuntimeException("cannot determine OS type: " + osName); }
		if (arch.equals("amd64")) { arch = "x86_64"; }

		File discordLibrary = downloadDiscordSDK.downloadDiscordLibrary(name, suffix, arch);
		if (discordLibrary == null) { System.err.println("Error downloading Discord SDK."); return;}
		ActivityButton button = new ActivityButton("Github", "https://github.com/DiamondPRO02/nsfw-game-manager");
		// Initialize the Core
		// File discordLibLibs = new File(mainInit.mainPath + "discord/" + name + suffix);
		// Set parameters for the Core
		try (CreateParams params = new CreateParams()) {
			params.setClientID(1135539276692607086L);
			params.setFlags(CreateParams.getDefaultFlags());
			// Create the Core
			try (Core core = new Core(params)) {
				try (Activity activity = new Activity()) {
					allGames = saveLoadDoc.allGames;
					activity.setDetails("Managing my hentai games");
					activity.setState("Currently have " + allGames + " games");
					// Setting a start time causes an "elapsed" field to appear
					activity.timestamps().setStart(time);
					// Make a "cool" image show up
					activity.assets().setLargeImage(image);
					activity.assets().setLargeText("Horny :3");
					// Custom button
					activity.addButton(button);
					activity.setActivityButtonsMode(ActivityButtonsMode.BUTTONS);
					// Finally, update the current activity to our activity
					core.activityManager().updateActivity(activity);
				} catch (Exception e) { 
					System.out.println(">.>"); e.printStackTrace(); 
				}
				// because of this, you cannot change setting without freezing
				// And without this, discord does not start
				// so just change setting and need manual restart
				while(true){
					try {
						Thread.sleep(500); // Sleep a bit to save CPU
						boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
					}
					catch(InterruptedException e) {
						System.out.println("<.<");
						e.printStackTrace();
					}
					if (!boolSettings[3]) { core.close(); return;}
				}
			}
		}
	}
}