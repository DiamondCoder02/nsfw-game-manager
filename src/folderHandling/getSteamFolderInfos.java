package folderHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import integrationCheck.defaultValues;

public class getSteamFolderInfos {
	private static String[][] steamFolders;
	public static Boolean steamAvailable = false;

	public static String[] getSteamAppInfo(String appId){
		// LastUpdated - buildid
		String[] appInfo = new String[5];
		try {
			BufferedReader reader = readGameFile(appId);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("LastUpdated")) {
					// convert unix to date
					Date date = new Date(Integer.parseInt(line.split("\"")[3])*1000L);
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					String myDate = format.format(date);
					appInfo[0] = myDate;
				}
				if (line.contains("buildid")) {
					appInfo[1] = line.split("\"")[3];
				}
			}
			reader.close();
			return appInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
"AppState"
{
	"appid"		"105600"
	"universe"		"1"
	"LauncherPath"		"C:\\Program Files (x86)\\Steam\\steam.exe"
	"name"		"Terraria"
	"StateFlags"		"4"
	"installdir"		"Terraria"
	"LastUpdated"		"1700826683"
	"SizeOnDisk"		"687925078"
	"StagingSize"		"0"
	"buildid"		"9965506"
	"LastOwner"		"76561198365394948"
	"AutoUpdateBehavior"		"0"
	"AllowOtherDownloadsWhileRunning"		"0"
	"ScheduledAutoUpdate"		"0"
}
*/

	private static BufferedReader readGameFile(String appId){
		for (int i = 0; i < steamFolders.length; i++) {
			for (int j = 0; j < steamFolders[i].length; j++) {
				if (steamFolders[i][j] != null) {
					if (j != 0) {
						if (steamFolders[i][j].equals(appId)) {
							try {
								return Files.newBufferedReader(Paths.get(steamFolders[i][0] + "/steamapps/appmanifest_" + appId + ".acf"));
							} catch (IOException e) {
								e.printStackTrace();
								return null;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static Boolean loadSteamFolders() {
		try {
			// libraryfolders.vdf
			BufferedReader reader = Files.newBufferedReader(Paths.get(defaultValues.steamDirectory + "/libraryfolders.vdf"));
			String line;
			String[][] mainSteamFolder = new String[10][50];
			Boolean apps = false;
			Integer folderIndex = -1, appIndex = 0;
			while ((line = reader.readLine()) != null) {
				if (line.contains("path")) {
					// 		"path"		"C:\\Program Files (x86)\\Steam"
					folderIndex++;
					mainSteamFolder[folderIndex][0] = line.split("\"")[3];
				}
				if (line.contains("}")) { apps = false; appIndex = 0; }
				if (apps) {
					if (line.contains("\"")) {
						// 		"228980"		"281442308"
						appIndex++;
						mainSteamFolder[folderIndex][appIndex] = line.split("\"")[1];
					}
				}
				if (line.contains("apps")) { apps = true; }
			}
			reader.close();
			steamAvailable = true;
			steamFolders = mainSteamFolder;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}

/* how do I read this?

"libraryfolders"
{
	"0"
	{
		"path"		"C:\\Program Files (x86)\\Steam"
		"label"		""
		"apps"
		{
			"228980"		"281442308"
			"365670"		"1271238204"
		}
	}
	"1"
	{
		"path"		"D:\\SteamLibrary"
		"label"		""
		"apps"
		{
			"105600"		"687925078"
			"230270"		"1025481560"
			"371970"		"867275641"
		}
	}
}
*/