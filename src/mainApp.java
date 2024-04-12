// import _main.mainInit;
import folderHandling.changeSettings;
import folderHandling.initialFileLoading.loadLanguage;
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

		if (!loadLanguage.load(mainDirectory)) { return; }
		System.out.println("--- Languages loaded ---");
		// public static String[] base, basic, tabl, jlapa, jrabu, buton, folder, serc, rand;
		// public static String[] langChoices, lanMeans;
		System.out.println(loadLanguage.basic[0]);

		System.out.println("--- Checking for new version --- Enabled:" + loadSettings.othersettings[0]);
		if (loadSettings.othersettings[0]) { 
			if (newVersion.checkNewVersion()) {
				System.out.println("--- New Version Available ---"); return;
			}
		}
		System.out.println("--- No New Version ---");

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