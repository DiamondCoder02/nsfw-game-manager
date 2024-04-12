package frontEndGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class mainFrame {
	public static void createFrame(String mainDirectory) {
		// TODO - This needs rewrite
		JFrame frame = new JFrame();

		frame = frameCreate.WindowCreate(frame, mainDirectory);
		

		JTable table = frameTable.createTable();
		JScrollPane pane = new JScrollPane(table);

		frameColor.WindowRefresh(frame, pane, table);

		frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.add(table, BorderLayout.CENTER);
		frame.add(pane, BorderLayout.CENTER);

		frame.setVisible(true);
	}
}
