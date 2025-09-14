package hgm_backend;

import hgm_backend.src.log;
import hgm_backend.src.fileHandler.checkSteamOS;
import hgm_backend.src.fileHandler.loadConfig;

import java.io.IOException;

public class startBackend {
	public static void main(String[] args) throws IOException {
		if (!loadConfig.configFile()) {
			log.print("Something went terribly wrong with config", log.ERROR);
			System.exit(0);
		}

		if (loadConfig.configOpject.getJSONObject("othersettings").getBoolean("showConsole")) log.frameLog();

		if (!checkSteamOS.checkForOS()) {
			if (!checkSteamOS.askForPath(true)) {
				log.print("Can't locate Steam path", log.WARNING);
				System.exit(0);
			}
		}

		/*
		saveConfig.setConfig("version", "0.2.1", saveConfig.str);
		saveConfig.setConfig("frontendType", "3", saveConfig.num);
		saveConfig.setConfig("discordRPC", "true", saveConfig.boo);
		*/

		log.print("started");
	}
}
