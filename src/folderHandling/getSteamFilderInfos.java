package folderHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.stream.JsonReader;

import integrationCheck.defaultValues;
import webApiScrapeThings.loadSitesBufRead;

public class getSteamFilderInfos {
	private static String[] getSteamFoldersLocation() {
		try {
			// libraryfolders.vdf
			// StringBuilder content = (defaultValues.steamDirectory + "/libraryfolders.vdf", true);
			BufferedReader reader = Files.newBufferedReader(Paths.get(defaultValues.steamDirectory + "/libraryfolders.vdf"));
			String line;
			String[][] steamFoldersInfo = new String[10][50];
			Boolean apps = false;
			Integer folderIndex = -1;
			Integer appIndex = 0;
			while ((line = reader.readLine()) != null) {
				System.out.println("["+folderIndex+"]["+appIndex+"]");
				if (line.contains("path")) {
					// 		"path"		"C:\\Program Files (x86)\\Steam"
					folderIndex++;
					System.out.println(steamFoldersInfo[folderIndex][0]);
					steamFoldersInfo[folderIndex][0] = line.split("\"")[3];
					System.out.println(steamFoldersInfo[folderIndex][0]);
					
				}
				if (line.contains("}")) {
					apps = false;
					appIndex = 0;
				}
				if (apps) {
					if (line.contains("\"")) {
						// 			"228980"		"281442308"
						appIndex++;
						System.out.println(steamFoldersInfo[folderIndex][appIndex]);
						steamFoldersInfo[folderIndex][appIndex] = line.split("\"")[1];
						System.out.println(steamFoldersInfo[folderIndex][appIndex]);
						
					}
				}
				if (line.contains("apps")) {
					apps = true;
				}
				System.out.println("--------------------");
			}

			for (int i = 0; i < steamFoldersInfo.length; i++) {
				for (int j = 0; j < steamFoldersInfo[i].length; j++) {
					if (steamFoldersInfo[i][j] != null) {
						// System.out.println(steamFoldersInfo[i][j]);
					}
				}
			}
			System.out.println("Done");
			System.out.println(steamFoldersInfo[0][0]);
			System.out.println(steamFoldersInfo[0][1]);
			System.out.println(steamFoldersInfo[0][2]);
			System.out.println(steamFoldersInfo[1][0]);
			System.out.println(steamFoldersInfo[1][1]);
			System.out.println(steamFoldersInfo[1][2]);
			System.out.println(steamFoldersInfo[1][3]);
			System.out.println(steamFoldersInfo[1][4]);
			System.out.println(steamFoldersInfo[1][5]);
			System.out.println(steamFoldersInfo[1][6]);

			
			
			reader.close();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void getSteamFolders() {
		String[] steamFolders = getSteamFoldersLocation();
		JsonObject steamFoldersJson = convertVDFtoJson(defaultValues.steamDirectory + "/libraryfolders.vdf");
		System.out.println(steamFoldersJson.entrySet());
	}

	private static JsonObject convertVDFtoJson(String vdf) {
		BufferedReader reader;
		try{
			reader = Files.newBufferedReader(Paths.get(vdf));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				// line = line.replace("\"", "\":");
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		//JsonObject obj = JsonParser.parseString(vdf).getAsJsonObject();
		// return obj;
		
		return null;
	}
}

/*
how do I read this?


"libraryfolders"
{
	"0"
	{
		"path"		"C:\\Program Files (x86)\\Steam"
		"label"		""
		"contentid"		"6670095753565597716"
		"totalsize"		"0"
		"update_clean_bytes_tally"		"2704306368"
		"time_last_update_corruption"		"0"
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
		"contentid"		"7401735915361502012"
		"totalsize"		"1000148795392"
		"update_clean_bytes_tally"		"9825438356"
		"time_last_update_corruption"		"0"
		"apps"
		{
			"105600"		"687925078"
			"230270"		"1025481560"
			"371970"		"867275641"
			"438100"		"1205497531"
			"881100"		"1638783693"
			"1281930"		"136816097"
			"1349230"		"9957235"
			"1902710"		"3070136262"
			"1948280"		"587782222"
			"1972440"		"92631440"
			"2018130"		"464606973"
			"2074890"		"4358821981"
			"2420510"		"230619096"
			"2622000"		"2544435460"
			"2707980"		"351827998"
			"2719150"		"272353844"
			"2850470"		"292640128"
		}
	}
}

 */