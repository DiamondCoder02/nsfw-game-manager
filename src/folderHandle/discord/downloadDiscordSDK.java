package folderHandle.discord;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import folderHandle.checkAndBackup.checksFiles;

public class downloadDiscordSDK {
	public static File downloadDiscordLibrary(String name, String suffix, String arch) throws IOException {
		File discordDir = new File(checksFiles.mainPath + "discord");
		if (!discordDir.exists()) { discordDir.mkdir(); }
		File temp = new File(discordDir, name + suffix);
		if (temp.exists()) { return temp; }

		// Path of Discord's library inside the ZIP
		String zipPath = "lib/" + arch + "/" + name + suffix;
		// Open the URL as a ZipInputStream
		URL downloadUrl = new URL("https://dl-game-sdk.discordapp.net/3.2.1/discord_game_sdk.zip");
		HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
		connection.setRequestProperty("User-Agent", "discord-game-sdk4j (https://github.com/JnCrMx/discord-game-sdk4j)");
		ZipInputStream zin = new ZipInputStream(connection.getInputStream());

		// Search for the right file inside the ZIP
		ZipEntry entry;
		while ((entry = zin.getNextEntry()) != null) {
			if (entry.getName().equals(zipPath)) {
				// Copy the file in the ZIP to our temporary file
				Files.copy(zin, temp.toPath());
				zin.close();
				return temp;
			}
			// next entry
			zin.closeEntry();
		}
		zin.close();
		return null;
	}
}
