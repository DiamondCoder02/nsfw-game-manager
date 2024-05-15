package frontendGUI.buttons;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import folderHandling.initialFileLoading.loadSettings;
import folderHandling.localFoldersChange.updateSettings;

public class databaseChange {
	/**
	 * This function will change the database.<p>
	 * In settings databaseNumber: 0 <p>
	 * In settings databaseNames: hentai//?//?//?//? <p>
	 * @param mainDir - The main directory of the program
	 */
	public static void changeDatabase(String mainDir) {
		String currentDBnum = loadSettings.databaseNumber;
		String currentDBname = loadSettings.databaseNames;
		String[] currentDBnames = currentDBname.split("//");

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));

		// Add text to the panel so name of the database can be changed
		JTextField name = new JTextField();
		panel.add(new JLabel("Database name: "));
		name.setText(currentDBnames[Integer.parseInt(currentDBnum)]);
		panel.add(name);

		JRadioButton[] buttons = new JRadioButton[5];
		ButtonGroup allButtons = new ButtonGroup();
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JRadioButton("Db "+(i+1) + ": - " + currentDBnames[i]);
			buttons[i].setActionCommand(i+"");
			allButtons.add(buttons[i]);
			panel.add(buttons[i]);
			if (currentDBnum.equals(i+"")) { buttons[i].setSelected(true); }
		}
		int result = JOptionPane.showConfirmDialog(null, panel, "📁 Database", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].isSelected()) {
					updateSettings.changeSetting(mainDir, "databaseNumber", i+"");
					// if old name is not the same as new name then change the name
					if (!name.getText().equals(currentDBnames[Integer.parseInt(currentDBnum)])) {
						currentDBnames[i] = name.getText();
						String newName = "";
						for (int j = 0; j < currentDBnames.length; j++) { newName += currentDBnames[j] + "//"; }
						updateSettings.changeSetting(mainDir, "databaseNames", newName.substring(0, newName.length()-2));
					}
				}
			}
		}
	}
}
