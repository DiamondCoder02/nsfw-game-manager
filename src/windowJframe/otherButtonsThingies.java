package windowJframe;

import javax.swing.JOptionPane;

public class otherButtonsThingies {
	public static void FACKQU(){ // TODO faq
		JOptionPane.showMessageDialog(null, "FAQ\n\n"+
		"Q: What is this?\nA: A simple excel like hentai game manager.\n\n"+
		"Q: Why this exist?\nA: Because I had enough managing my games in an excel table and wanted something better.\n\n"+
		"Q: Features?\nA: -----*Currently:*-----\n"+
		"- Add, store, update, remove game infos manually\n"+
		"-----*Plans:*-----\n"+
		"- F95zone, so only ID needed to add game\n"+
		"- Dlsite so also only ID needed\n"+
		"- DarkMode\n"+
		"- Colorful table\n"+
		"- (Far future) If I can, I will also add to download/detect what games are downloaded\n"+
		"\n"+
		"Q: qu?\nA: an\n\n",
		"Frequently Asked Questions", 
		JOptionPane.INFORMATION_MESSAGE);
	}

	public static void money(){
		JOptionPane.showMessageDialog(null, 
		"Credits\n\n"+
		"Hi, I'm Diamond.\n"+
		"This is a small project after learning Java in university.\n"+
		"The main of this was to convert my old excel file into something more readeble.\n"+
		"Main focus is something dynamic and easy to use for hentai games.\n\n"+
		"Thank you for using this program.\n\n"+
		"Full open source: https://github.com/DiamondPRO02/nsfw-game-manager",
		"Credit", 
		JOptionPane.INFORMATION_MESSAGE);
	}
}
