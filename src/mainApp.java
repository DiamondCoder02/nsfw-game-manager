import java.awt.BorderLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.mainProgramStart;
public class mainApp {
	private static String tempDir;
	/**
	 * This function will start the program.
	 * @param args -
	 * @throws IOException -
	 * @throws InterruptedException -
	 * @throws URISyntaxException -
	 */
	public static void main (String [] args){
		checkOS();

		Boolean consoleNeeded = false;
		if (loadSettings.load(tempDir)) { consoleNeeded = loadSettings.othersettings[5]; }
		if (consoleNeeded) { 
			// https://www.codeease.net/programming/java/how-to-create-a-console-in-java-gui
			JFrame frame = new JFrame();
			frame.setTitle("HGM - Console");
			frame.add( new JLabel(" Outout" ), BorderLayout.NORTH );
			JTextArea ta = new JTextArea();
			ta.setEditable( false );
			ta.setLineWrap( false );
			
			PrintStream ps = new PrintStream( new OutputStream() {
				@Override
				public void write(int b) throws IOException {
					ta.append( String.valueOf( (char) b ) );
				}
			} );
			System.setOut(ps);
			System.setErr(ps);

			frame.add(new JScrollPane( ta ));
			frame.pack();
			frame.setVisible(true);
			frame.setSize(800,300);

			System.out.println("- Console enabled! -");
		} else { 
			System.out.println("- No console needed! -"); 
		}
		System.out.println("--- Starting program ---");
		System.out.println("Main: "+mainProgramStart.mainProgDir);
		System.out.println("Steam: "+mainProgramStart.steamDir);
		System.out.println("--- OS checked ---");
		mainProgramStart.mainMain();
    }

	// TODO - Linux NEED TEST
	/**
	 * This function will check the OS and set the directories.
	 * @return true if the OS is known, false if unknown
	 */
	private static Boolean checkOS(){
		String sysOS = System.getProperty("os.name").toLowerCase();
		System.out.println("OS: "+sysOS);
		if (sysOS.contains("win")) {
			mainProgramStart.mainProgDir = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager";
			tempDir = System.getenv("APPDATA") + "/DiamondCoder/nsfwGameManager";
			mainProgramStart.steamDir = System.getenv("ProgramFiles(x86)") + "/Steam/steamapps";
			return true;
		/*
		} else if (sysOS.contains("nix") || sysOS.contains("nux") || sysOS.contains("aix")) {
			mainProgramStart.mainProgDir = System.getenv("???") + "/DiamondCoder/nsfwGameManager";
			mainProgramStart.steamDir = System.getenv("???") + "/Steam/steamapps";
			return true;
		/*
		} else if (sysOS.contains("mac")) {
			mainProgramStart.mainProgDir = System.getenv("???") + "/DiamondCoder/nsfwGameManager";
			mainProgramStart.steamDir = System.getenv("???") + "/Steam/steamapps";
			return true;
		*/
		} else { return false; }
	}
}

/*
 *  IMPORTANT:
 *  This is just a rough draft on future plans.
 *  These are not final and can be changed at any time.
 */
/*	[version number] ( to do / done / all ) 
TODO list: [0.1.2.0] (?/?/?) 

- Fixed exe saying "This application requires a Java Runtime Environment."
  - https://www.quora.com/Can-the-JVM-be-bundled-with-a-Java-program-so-it-runs-on-a-computer-without-a-JVM-installed
- Patchnotes and Info when starting
- Save slots
  -https://github.com/DiamondCoder02/nsfw-game-manager/issues/7
- Rewrite Wiki again




- Added a counter for the sites of game and all games counter
- Change pictures in Credit and FAQ
- Able to click and copy values from table, but no longer edit them as originally planned.
- Experimenting with colors, will probably change next update.
- Updated images and huge thanks to @NyanekoNNK for the new logo!
  - Yes, the image is a pun
- Written comments in the program so future development will be easier hopefully.
- Fix Discord custom button
- optimize search ( searchByName: dungeon ) 
  - Now it won't overflow out of your screen
- Written wiki on Github
- Update FAQ in program
- settings moved to json
  - You will have to redo it, sorry for that >.<
- added steam
- Load steam when start loop thing api 
- remake files
  - Asset folder LOL
- App unable to start if there is space anywhere in the path of the program
  - Only if developer console is enabled. Somehow the console commits unalive

TODO list: [0.1.3.0] (4/0/4)

- move database from xml ( MUST NOT BREAK - 0.1.2.1 ? )
- Rewrite language.csv
- Cum counter ( ͡° ͜ʖ ͡°)
- improve dark mode?

TODO list: [0.1.4.0] (2/0/2)

- dlsite.com support
- Make it LINUX compatible

TODO random: [???] ( All other todos in this app: 3 )

- Memory & CPU usage higher? ( Need more test )
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