package frontendGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import folderHandling.initialFileLoading.loadSettings;
import frontendGUI.frames.frameColor;
import frontendGUI.frames.frameCreate;
import frontendGUI.frames.frameTable;
import frontendGUI.frames.frameTableReload;
import integrationCheck.defaultValues;

public class mainFrame {
	public static JFrame frame = new JFrame();
	private static JTable table = new JTable();
	private static JScrollPane pane;
	public static void createFrame(String mainDirectory) {
		frame = frameCreate.WindowCreate(frame, mainDirectory);

		table = frameTable.createTable(mainDirectory, table);
		frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.add(table, BorderLayout.CENTER);

		pane = new JScrollPane(table);
		frame.add(pane, BorderLayout.CENTER);

		frameColor.WindowRefresh(frame, pane, table);

		frame.setVisible(true);
	}

	public static void refreshTable() {
		loadSettings.load(defaultValues.mainDirectory);
		frameTableReload.reloadTable(table, defaultValues.mainDirectory);
		frameTable.setColumns(table);
		frameColor.WindowRefresh(frame, pane, table);
	}
}
