package folderHandling.initialFileLoading;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

import integrationCheck.defaultValues;

// The way this was done is fucking retarded
public class loadLanguage {
	public static String[] base = new String[30], basic = new String[30], tabl = new String[30], 
		jlapa = new String[30], jrabu = new String[30], buton = new String[30], 
		folder = new String[30], serc = new String[30], rand = new String[30]; 
	private static String split = ";";
	private static String[] languages;
	private static String[] lanMeans;

	public static String[][] loadLangChoices(){
		try{
			String[][] ret = new String[2][languages.length-1];
			for (int i = 0; i < (languages.length-1); i++) { ret[0][i] = languages[i+1]; }
			for (int i = 0; i < (languages.length-1); i++) { 
				if (lanMeans[i+1].equals("")) { ret[1][i] = languages[i+1]; }
					else { ret[1][i] = lanMeans[i+1]; }
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLangChoices)", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	public static boolean loadLangFile() {
		try{
			BufferedReader br = new BufferedReader( new FileReader(
				defaultValues.mainDirectory+"/language.csv", 
				java.nio.charset.Charset.forName("UTF-8")
			));
			languages = br.readLine().split(split);
			lanMeans = br.readLine().split(split);

			Integer langCol = 1;
			for (int i = 1; i < languages.length; i++) { if (languages[i].equals(loadSettings.language)) { langCol = i; break; } }
			if (langCol == 0) { langCol = 1; } // For safety

			String line, lastLang="";
			Integer tempInt = 0;
			while ((line = br.readLine()) != null) {
				String[] nextLine = line.split(split);

				String langString = nextLine[langCol];
				if (langString.contains("\\n")) {
					langString = langString.replace("\\n", "\n");
				}
				if (!lastLang.equals(nextLine[0])) { lastLang = nextLine[0]; tempInt = 0; }
				// System.out.println(nextLine[0] + " - " + langString +" + "+ nextLine[langCol]);

				switch (nextLine[0]) {
					case "base": base[tempInt] = langString; break;
					case "basic": basic[tempInt] = langString; break;
					case "tablec": tabl[tempInt] = langString; break;
					case "JLabPan": jlapa[tempInt] = langString; break;
					case "JRadBut": jrabu[tempInt] = langString; break;
					case "buttons": buton[tempInt] = langString; break;
					case "folders": folder[tempInt] = langString; break;
					case "search": serc[tempInt] = langString; break;
					case "random": rand[tempInt] = langString; break;
				}
				tempInt++;
			}
			br.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error language file (loadLangFile)", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
