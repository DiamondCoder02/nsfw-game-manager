import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.mainProgramStart;
import integrationCheck.defaultValues;

// https://stackoverflow.com/questions/7704405/how-do-i-make-my-java-application-open-a-console-terminal-window
import java.io.Console;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;
public class mainApp {
	public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException{
		Boolean consoleNeeded = false;
		if (loadSettings.load(defaultValues.mainDirectory)) { consoleNeeded = loadSettings.othersettings[5]; }
		if (consoleNeeded) { 
			System.out.println("+ Console needed! +");
			Console console = System.console();
			if (console == null && !GraphicsEnvironment.isHeadless()) {
				String filename = mainApp.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
				Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
			} else{
				System.out.println("+ Console needed! +");
				mainProgramStart.mainMain();
			}
		} else { System.out.println("- No console needed! -"); mainProgramStart.mainMain(); }
    }
}

/* [version number] ( to do / done / all ) 
TODO list: [0.1.2.0] (4/4/8) 

- Update FAQ in program
- fix bugs / do something with all the ~10 TODOs that is in the source code
- optimize search ( searchByName: dungeon )
- Write comments in the program
- Fix Discord custom button


- Written wiki on Github
- settings moved to json
- added steam
- Fixed exe saying "This application requires a Java Runtime Environment."


TODO list: [0.1.3.0] (4/0/4)

- move database from xml ( MUST NOT BREAK - 0.1.2.1 ? )
- Rewrite language.csv
- improve dark mode
- Cum counter ( ͡° ͜ʖ ͡°)

TODO list: [0.1.4.0] (2/0/2)

- [ ] dlsite.com support
- [ ] Make it LINUX compatible


TODO random: [???]

- [ ] Memory & CPU usage higher? ( Need more test )
  - This happened when I run a full database update with API refreshes?
 */

/* order of table:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes
*/