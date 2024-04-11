// import _main.mainInit;
import folderHandling.changeSettings;
import folderHandling.initialFileLoading.loadSettings;
import integrationCheck.newVersion;
import integrationCheck.systemCheck;

public class mainApp {
	private static String mainDirectory = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager";
	public static void main(String[] args) {
		// TODO - This is stupid
		changeSettings.retard(mainDirectory);

		if (!systemCheck.programSystemCheck(mainDirectory)) { return; }
		System.out.println("--- System check passed! ---");

		if (!loadSettings.load(mainDirectory)) { return; }
		System.out.println("--- Settings loaded ---");

		Boolean updateNeed = false;
		System.out.println("--- Checking for new version --- Enabled:" + loadSettings.othersettings[0]);
		if (loadSettings.othersettings[0]) { updateNeed = newVersion.checkNewVersion();	}
		if (updateNeed) { System.out.println("--- New Version Available ---"); return; }
		System.out.println("--- No New Version ---");

		changeSettings.changeSetting("appVersion", "0.1.1.69");

		// mainInit.mainStart();
	}

	// https://code-disaster.github.io/steamworks4j/getting-started.html
}

/* order of storage:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes
 */