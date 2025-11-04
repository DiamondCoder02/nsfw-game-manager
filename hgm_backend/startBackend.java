package hgm_backend;

import hgm_backend.src.log;
import hgm_backend.src.fileHandler.checkSteamOS;
import hgm_backend.src.fileHandler.databaseHandler;
import hgm_backend.src.fileHandler.loadConfig;

import java.io.IOException;

public class startBackend {
	public static void main(String[] args) throws IOException {
		if (!loadConfig.configFile()) {
			log.errorPane("Something went terribly wrong with config", "Error");
			System.exit(0);
		}

		if (loadConfig.configOpject.getJSONObject("othersettings").getBoolean("showConsole")) log.frameLog();

		if (!checkSteamOS.checkForOS()) {
			if (!checkSteamOS.askForPath(true)) {
				log.print("Can't locate Steam path", log.WARNING);
			}
		}

		/*
		saveConfig.setConfig("version", "0.2.1", saveConfig.str);
		saveConfig.setConfig("frontendType", "3", saveConfig.num);
		saveConfig.setConfig("discordRPC", "true", saveConfig.boo);
		*/

		log.print("DB? -> "+databaseHandler.dbCheck());
		

		log.print("started");
	}
}
