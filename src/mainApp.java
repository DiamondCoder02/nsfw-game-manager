import main.mainInit;
import integrationCheck.systemCheck;

public class mainApp {
	public static void main(String[] args) {
		if (!systemCheck.programSystemCheck()) { return; }
		mainInit.mainStart();
	}
}

/* order of storage:
0 - Site		1 - ID		2 - Name	3 - Developer
4 - Played version			5 - Last time play
6 - Rated		7 - Newest version		8 - Last update
9 - People rating			10 - Player progress
11 - Still on pc?			12 - Engine	
13 - OS			14 - Language			15 - Personal notes
 */