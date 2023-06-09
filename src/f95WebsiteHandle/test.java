package f95WebsiteHandle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class test {
	static String[] allTheInfo = new String[7];
	public static void main(String[] args) {
		// 162918 162859 4153 106296
		
		String content = getUrlContents("https://f95zone.to/threads/4153/");
		// System.out.println(output);
		File file = new File("C:\\Users\\Diamond\\Desktop\\F95zone\\test.html");
		// write the output in a file using filewriter
		try (FileWriter fw = new FileWriter(file)) {
			fw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String longTitle, asd, engi[] = new String[9];
		longTitle = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>"));
		// allTheInfo[] = Name, Developer, Newest version, Date of last update, People rating, Engine, OS

		for (int i = 1; i < longTitle.split(" - ").length; i++) {
			asd = longTitle.split(" - ")[i];
			if (i == longTitle.split(" - ").length - 1) { allTheInfo[0] = asd.split(" \\[")[0]; }
		}
		allTheInfo[1] = longTitle.split(" \\[")[2].split("]")[0];
		allTheInfo[2] = longTitle.split(" \\[")[1].split("]")[0];
		for (int i = 0; i < longTitle.split(" - ").length; i++) { engi[i] = longTitle.split(" - ")[i]; }
		if (engi[0] == "Collection") {allTheInfo[5] = engi[0];
		} else { for (int i = 0; i < engi.length; i++) { if (engi[i] != "Collection" || engi[i] != "VN") { allTheInfo[5] = engi[i]; break; } } }



		for (int i = 0; i < allTheInfo.length; i++) {
			System.out.println(allTheInfo[i]);
		}
	}

	private static String getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();
		// Use try and catch to avoid the exceptions
		try {
			URL url = new URL(theUrl); // creating a url object
			URLConnection urlConnection = url.openConnection(); // creating a urlconnection object
			// wrapping the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// reading from the urlconnection using the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(content);
		return content.toString();
	}
}