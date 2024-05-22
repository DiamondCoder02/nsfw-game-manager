package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import backendThings.integrationCheck.defaultValues;
import folderHandling.initialFileLoading.loadLanguage;

public class sites {
	static String[] base = loadLanguage.base, basic = loadLanguage.basic, jla = loadLanguage.jlapa;
	public static String[] requestSiteAndId(String optDialog){
		JPanel panel = new JPanel(new GridLayout(2, 2));

		ButtonGroup webButtons = new ButtonGroup();
		JPanel webPanel = new JPanel();

		for (int i = 0; i < defaultValues.infoSite.length; i++) {
			JRadioButton button = new JRadioButton(defaultValues.infoSites2LOL[i], i==0? true : false); 
			button.setActionCommand(defaultValues.infoSite[i]);
			webButtons.add(button); webPanel.add(button);
		}
		webPanel.setLayout(new BoxLayout(webPanel, BoxLayout.X_AXIS));

		JLabel IDlabel = new JLabel(jla[0]!=null?jla[0]:"ID: (required)");
		JTextField id = new JTextField(8);

		panel.add(IDlabel); panel.add(id);
		panel.add(new JLabel("Website:"));	panel.add(webPanel);

		//  CLOSED - -1  //  Yes-OK - 0  //  No - 1  //  Cancel - 2  //
		Integer option = JOptionPane.showOptionDialog(null, 
			panel, optDialog, 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.PLAIN_MESSAGE, 
			null, null, null);
		if (option != 0) { return null; }

		if (id.getText().equals("")) { 
			JOptionPane.showMessageDialog(null, 
				basic[0]!=null?basic[0]:"ID is required", base[1]!=null?base[1]:"Error", 
				JOptionPane.ERROR_MESSAGE); 
			return null; 
		}

		return new String[] {webButtons.getSelection().getActionCommand(), id.getText()};
	}
}
