package hgm_backend.src.fileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONObject;

import hgm_backend.src.log;

/**
 * Only this class can access the config for shit reason
 */
public class loadConfig {
	/**
	 * Just get the config... If empty, do configFile();
	 * @example obj.getString("language").toString(); JSONObject res = obj.getJSONObject("appInfo"); 
	 * @return JSONObject 
	 */
	public static JSONObject configOpject;

	private static File configFileLocation = new File("./_saveMe/config.json");
	private static File originalconfigLocation = new File("hgm_backend/src/fileHandler/_defaultConfig.json");

	/**
	 * If there is any doubt that the config is readeable or correct
	 * @return boolean - true, if config is intact and/or repaired. False if fatal error happens
	 */
	public static boolean configFile() {
		try {
			if (!configFileLocation.isFile()) {
				new File("./_saveMe").mkdirs();
				Files.copy(
						originalconfigLocation.toPath(), // move from
						configFileLocation.toPath(),	// move to
						StandardCopyOption.REPLACE_EXISTING);
				loadIntoJsonObject();
			} else {
				if (configFixer()) loadIntoJsonObject();
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			log.errorPane("Unable to read config file.\n"+e, "Error");
			// if (configFileLocation.delete()) {configFile();}
			return false;
		}
	}

	/**
	 * Due to update or anything a single config is missing, this will go and check
	 * if everything is present.
	 * DO NOT OVERWRITE, only check and create if missing.
	 */
	private static boolean configFixer() {
		try {
			Scanner originalConfigScanner = new Scanner(originalconfigLocation.toURI().toURL().openStream());
			String originalStringConfig = new String();

			loadIntoJsonObject();
			JSONObject userConfigObject = configOpject;

			while (originalConfigScanner.hasNext())
				originalStringConfig += originalConfigScanner.nextLine();
			originalConfigScanner.close();
			JSONObject originalConfigObject = new JSONObject(originalStringConfig);

			// if config is the same, skip other checks
			// log.print("Test:" + originalConfigObject.similar(userConfigObject));
			if (originalConfigObject.similar(userConfigObject)) return true; 

			Boolean missingAnything = false;

			Iterator<String> originalKeys = originalConfigObject.keys();
            while(originalKeys.hasNext()) {
                String OKey = originalKeys.next();
				if (!userConfigObject.has(OKey)) {
					userConfigObject.put(OKey, originalConfigObject.get(OKey));
					missingAnything = true;
				}
				if(originalConfigObject.get(OKey) instanceof JSONObject) {
                    JSONObject originalObject = (JSONObject) originalConfigObject.get(OKey);
                    JSONObject userObject = (JSONObject) userConfigObject.get(OKey);
                    Iterator<String> innerOriginalKeys = originalObject.keys();
                    while(innerOriginalKeys.hasNext()) {
                        String innerKey = innerOriginalKeys.next();
						if (!userObject.has(innerKey)) {
							userObject.put(innerKey, originalObject.get(innerKey));
							missingAnything = true;
						}
                    }
                }
            }
			if (!missingAnything) return true;
			return saveConfig.save();
		} catch (IOException e) {
			e.printStackTrace();
			log.errorPane("Unable to repair config.\nPlease delete your config or manually edit it.\n"+e, "Error");
			return false;
		}
	}

	private static void loadIntoJsonObject(){
		try{
			Scanner userConfigScanner = new Scanner(configFileLocation.toURI().toURL().openStream());
			String userStringConfig = new String();

			while (userConfigScanner.hasNext())
				userStringConfig += userConfigScanner.nextLine();
			userConfigScanner.close();

			configOpject = new JSONObject(userStringConfig);
			/*
				JSONObject res = configOpject.getJSONObject("appInfo");
				log.print(configOpject.getString("language").toString());
				log.print(res.getInt("ChosenDatabase"));
				log.print(res.getJSONArray("DatabaseNames").toString());
			*/
		} catch (IOException e) {
			e.printStackTrace();
			log.errorPane("Unable to set variables for config \n"+e, "Error");
		}
	}

}
