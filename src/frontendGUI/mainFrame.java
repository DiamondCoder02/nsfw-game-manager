package frontendGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import frontendGUI.frames.frameColor;
import frontendGUI.frames.frameCreate;
import frontendGUI.frames.frameTable;
import frontendGUI.frames.frameTableReload;

public class mainFrame {
	private static JFrame frame = new JFrame();
	private static JTable table = new JTable();
	private static JScrollPane pane;
	public static void createFrame(String mainDirectory) {
		// TODO - This needs rewrite
		frame = frameCreate.WindowCreate(frame, mainDirectory);
		
		table = frameTable.createTable(mainDirectory, table);
		frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.add(table, BorderLayout.CENTER);

		pane = new JScrollPane(table);
		frame.add(pane, BorderLayout.CENTER);
		
		frameColor.WindowRefresh(frame, pane, table);

		frame.setVisible(true);
	}

	public static void refreshTable(String mainDirectory) {
		frameTableReload.reloadTable(table, mainDirectory);
		frameTable.setColumns(table);
	}
}
