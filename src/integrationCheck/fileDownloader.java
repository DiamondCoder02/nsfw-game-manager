package integrationCheck;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

public class fileDownloader {
	public static boolean downloadFile(String url, String fullPath) {
		// TODO - This is stupid...
		String fileType = fullPath.substring(fullPath.lastIndexOf(".") + 1, fullPath.length());
		System.out.println("Downloading: " + fileType);
		switch (fileType) {
			case "xml": case "csv": case "jar": case "exe": 
				return fileDownloading(url, fullPath);
			case "png":	case "jpg":
				return imageDownloader(url, fullPath, fileType);
			case "dll":
				// TODO - Discord donwload, this is stupid in future, but for now it's fine
				return discordSdkDownload(url, fullPath.split("\\.")[0]);
			default:
				return false;
		}
	}

	private static boolean imageDownloader(String url, String endFullDirectoryPath, String fileType) {
		try{
			BufferedImage img = ImageIO.read(new URL(url));
			ImageIO.write(img, fileType, new File(endFullDirectoryPath));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static boolean fileDownloading(String url, String endFullDirectoryPath) {
		try {
			InputStream in = new URL(url).openStream();
			FileOutputStream fos = new FileOutputStream(new File(endFullDirectoryPath));
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			in.close(); fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// thank you <3 ; https://github.com/JnCrMx/discord-game-sdk4j
	private static boolean discordSdkDownload(String url, String directoryWithoutExtension){
		System.out.println("Downloading: " + url);
		String suffix;
		String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		String arch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);
		if (osName.contains("windows")) { suffix = ".dll";
		} else if (osName.contains("linux")) { suffix = ".so";
		} else if (osName.contains("mac os")) { suffix = ".dylib";
		} else { throw new RuntimeException("cannot determine OS type: " + osName); }
		if (arch.equals("amd64")) { arch = "x86_64"; }

		try {
			// Path of Discord's library inside the ZIP
			String zipPath = "lib/" + arch + "/discord_game_sdk" + suffix;
			// Open the URL as a ZipInputStream
			HttpURLConnection connection = (HttpURLConnection) new URL("https://dl-game-sdk.discordapp.net/3.2.1/discord_game_sdk.zip").openConnection();
			connection.setRequestProperty("User-Agent", "discord-game-sdk4j (https://github.com/JnCrMx/discord-game-sdk4j)");
			ZipInputStream zin = new ZipInputStream(connection.getInputStream());

			// Search for the right file inside the ZIP
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.getName().equals(zipPath)) {
					// Copy the file in the ZIP to our temporary file
					Files.copy(zin, new File(directoryWithoutExtension + suffix).toPath());
					zin.close();
					return true;
				}
				zin.closeEntry(); // next entry
			}
			zin.close();
			connection.disconnect();
			return true;
		} catch (Exception e) {
			System.err.println("Error downloading Discord SDK.\n" + e);
			return false;
		}
	}
}
