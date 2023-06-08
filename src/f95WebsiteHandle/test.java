package f95WebsiteHandle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class test {
	public static void main(String[] args) {
		String output = getUrlContents("https://f95zone.to/threads/4153/");
		// System.out.println(output);
		File file = new File("C:\\Users\\Diamond\\Desktop\\F95zone\\test.html");
		// write the output in a file using filewriter
		try (FileWriter fw = new FileWriter(file)) {
			fw.write(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// get Title
		String title = output.substring(output.indexOf("<title>") + 7, output.indexOf("</title>"));
		System.out.println(title);
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
		System.out.println(content);
		return content.toString();
	}
}