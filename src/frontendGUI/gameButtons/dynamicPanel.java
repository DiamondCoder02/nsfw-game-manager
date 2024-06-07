package frontendGUI.gameButtons;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class dynamicPanel {
	public static JPanel frameCreate(JLabel[] right, JLabel[] left, JPanel[] rightP, JTextField[] textField){
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(1, 2));
		panelLeft.setLayout(new GridLayout(left.length, 1));
		panelRight.setLayout(new GridLayout(right.length, 1));

		for (int i = 0; i < left.length; i++) {
			if (left[i] != null) {
				// left[i].setBackground( i%2==0 ? null : new java.awt.Color(0, 0, 0));
				// left[i].setBackground(new java.awt.Color(100, 100, 100));
				panelLeft.add(left[i]); // .setBackground( i%2==0 ? null : new java.awt.Color(255, 255, 255) )
			} else {
				JLabel label = new JLabel(" ");
				// label.setBackground( i%2==0 ? null : new java.awt.Color(0, 0, 0));
				panelLeft.add(label);
			}
		}

		for (int i = 0; i < right.length; i++) {
			if (rightP[i] != null) {
				// rightP[i].setBackground(new java.awt.Color(100, 100, 100));
				panelRight.add(rightP[i]);
			} else if (right[i] != null) {
				// right[i].setBackground(new java.awt.Color(100, 100, 100));
				panelRight.add(right[i]); // .setBackground( i%2==0 ? null : new java.awt.Color(255, 255, 255) )
			} else {
				panelRight.add(textField[i] != null ? textField[i] : new JTextField(30));
			}
		}

		// ever second row should be slightly darker background
		/*for (int i = 0; i < left.length; i++) {
			panelLeft.add(left[i]);
		}
		for (int i = 0; i < right.length; i++) {
			panelRight.add(right[i]);
		}*/

		panel.add(panelLeft);
		panel.add(panelRight);

		return panel;
	}
}
