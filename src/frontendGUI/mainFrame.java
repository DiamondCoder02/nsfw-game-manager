package frontendGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.colors.frameColor;
import frontendGUI.frames.frameCounter;
import frontendGUI.frames.frameMenu;
import frontendGUI.frames.frameTable;
import frontendGUI.frames.frameTableReload;

public class mainFrame {
	public static JFrame frame = new JFrame();
	public static JLabel label = new JLabel();
	private static JTable table = new JTable();
	private static JScrollPane pane;
	/**
	 * Creates the main frame
	 * @param mainDirectory - The main directory of the program
	 */
	public static void createFrame(String mainDirectory) {
		
		frame = frameMenu.WindowCreate(frame, mainDirectory);
		table = frameTable.createTable(mainDirectory, table);
		frameCounter.getNumberOfGames();

		frame.add(label, BorderLayout.PAGE_START);
		frame.add(table.getTableHeader(), BorderLayout.CENTER);

		pane = new JScrollPane(table);
		frame.add(pane, BorderLayout.CENTER);

		frameColor.WindowRefresh(pane, table);

		frame.setVisible(true);
	}

	/**
	 * Refreshes the table with the new data
	 */
	public static void refreshTable(String mainDir) {
		frameCounter.getNumberOfGames();
		loadSettings.load(mainDir);
		frameTableReload.reloadTable(table, mainDir);
		frameTable.setColumns(table);
		frameColor.WindowRefresh(pane, table);
	}
}
