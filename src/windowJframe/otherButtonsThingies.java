package windowJframe;

import javax.swing.JOptionPane;

public class otherButtonsThingies {
	public static void FACKQU(){ // TODO faq
		JOptionPane.showMessageDialog(null, "FAQ\n\n"+
		"Q: questions1?\nA: answer1\n\n"+
		"Q: questions2?\nA: answer2 -> answer1\n\n"+
		"Q: questionsLong?\nA: answerLONGaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n"+
		"Q: questions3?\nA: answer3\n\n",
		"Frequently Asked Questions", 
		JOptionPane.INFORMATION_MESSAGE);
	}

	public static void money(){
		JOptionPane.showMessageDialog(null, 
		"Credits\n\n"+
		"Hi, I'm Diamond.\n"+
		"This is a small project after learning Java in university.\n"+
		"The main of this was to convert my old excel file into something more readeble.\n"+
		"Also thankfully this helps me easilly to update game infos.\n\n"+
		"Thank you.",
		"Frequently Asked Questions", 
		JOptionPane.INFORMATION_MESSAGE);
	}
}
