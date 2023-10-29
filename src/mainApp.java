import WebsiteHandle.autoUpdateCheck;
import folderHandle.checkAndBackup.checksFiles;
import main.downloadNewVersion;
import main.mainInit;

public class mainApp {
	public static void main(String[] args) {
		Boolean updateNeeded = false;
		// TODO Update autoCheck:
		updateNeeded = autoUpdateCheck.checkUpdate();
		checksFiles.checkSettingsFolder();
		if (updateNeeded) { 
			downloadNewVersion.getNewestGithubVersion();
		} else { 
			checksFiles.checks();
			mainInit.mainStart();
		}
	}
}

/*
Problem: Might have to rewrite how folders and files are checked...

Idea: Check if there is setting file, if not use written in version. Stupid as I might forget to update it, but it solves the problem.

Logic: 
If setting file
	no: Check inside the class what version the program is and check online. If different:
	Different: update
	Not: Continue
yes: check version
	old config, no version: Fuck it and copy what's inside and continue.
	newest version: Good, continue.
	old version: update.
*/