package main.application;

// thank you <3 ; https://github.com/JnCrMx/discord-game-sdk4j
import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import de.jcm.discordgamesdk.activity.ActivityButton;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import folderHandle.discord.downloadDiscordSDK;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;

public class discord {
	static Integer allGames = saveLoadDoc.allGames;
	static String image = "https://i.imgur.com/lJEl4eK.png";
	// TODO - This seems to be broken, this needs fix
	static ActivityButton button = new ActivityButton("Github", "https://github.com/DiamondPRO02/nsfw-game-manager");
	static Boolean[] boolSettings;

	public static void loopDiscord() throws IOException {
		boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
		if (!boolSettings[3]) { return;}

		String name = "discord_game_sdk"; String suffix;
		String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		String arch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);
		if (osName.contains("windows")) { suffix = ".dll";
		} else if (osName.contains("linux")) { suffix = ".so";
		} else if (osName.contains("mac os")) { suffix = ".dylib";
		} else { throw new RuntimeException("cannot determine OS type: " + osName); }
		if (arch.equals("amd64")) { arch = "x86_64"; }

		File discordLibrary = downloadDiscordSDK.downloadDiscordLibrary(name, suffix, arch);
		if (discordLibrary == null) { System.err.println("Error downloading Discord SDK."); return;}

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