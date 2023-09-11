package main.application;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Locale;

import org.w3c.dom.Document;

import folderHandle.checkAndBackup.checksFiles;
import folderHandle.discord.downloadDiscordSDK;
import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;
import main.mainInit;

public class discord {
	static Integer doNotRunAlways = 20*1;
	static Boolean[] boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
	static Document domGame = saveLoadDoc.loadDocument(mainInit.databasePath);
	static Integer allGames= domGame.getElementsByTagName("game").getLength();
	public static void discordFirstInit() throws IOException {
		String name = "discord_game_sdk"; String suffix;
		String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		String arch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);
		String image = "https://cdn.discordapp.com/app-assets/1135539276692607086/1135541050702831778.png";
		Instant time = Instant.now();

		if (osName.contains("windows")) { suffix = ".dll";
		} else if (osName.contains("linux")) { suffix = ".so";
		} else if (osName.contains("mac os")) { suffix = ".dylib";
		} else { throw new RuntimeException("cannot determine OS type: " + osName); }
		if (arch.equals("amd64")) { arch = "x86_64"; }

		File discordLibrary = downloadDiscordSDK.downloadDiscordLibrary(name, suffix, arch);
		if (discordLibrary == null) {
			System.err.println("Error downloading Discord SDK.");
		} else {
			// Initialize the Core
			File discordLibLibs = new File(checksFiles.mainPath + "discord/" + name + suffix);
			Core.init(discordLibLibs);
			// Set parameters for the Core
			try (CreateParams params = new CreateParams()) {
				params.setClientID(1135539276692607086L);
				// params.setFlags(CreateParams.getDefaultFlags());
				// Create the Core
				try (Core core = new Core(params)) {
					// Run callbacks forever
					domGame = saveLoadDoc.loadDocument(mainInit.databasePath);
					if (domGame == null) { allGames = 0; } else { allGames= domGame.getElementsByTagName("game").getLength(); }
					// Create the Activity
					try (Activity activity = new Activity()) {
						activity.setDetails("Managing my hentai games");
						activity.setState("Currently have " + allGames + " games");
						// Setting a start time causes an "elapsed" field to appear
						activity.timestamps().setStart(time);
						// Make a "cool" image show up
						activity.assets().setLargeImage(image);
						activity.assets().setLargeText("https://github.com/DiamondPRO02/nsfw-game-manager");
						// Finally, update the current activity to our activity
						core.activityManager().updateActivity(activity);
					} catch (Exception e) { 
						System.out.println(">.>"); e.printStackTrace(); 
					}
					while(true){
						boolSettings = loadSettingsFromXml.loadBooleanSettings("othersettings");
						if (!boolSettings[3]) { core.close(); break; }
						core.runCallbacks();
						try {
							Thread.sleep(100); // Sleep a bit to save CPU
						}
						catch(InterruptedException e) {
							System.out.println("<.<");
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}