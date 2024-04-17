package folderHandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Map;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ADocHandle {
	public static Document create() {
		Document doc;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error creating document");
		}
		return doc;
	}

	public static Document load(String directory) {
		Document doc;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File(directory));
			doc.normalize();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error loading document");
		}
		return doc;
	}

	public static boolean save(Document doc, String finalDirectory) {
		try{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// is this needed? (So far, yes)
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			DOMSource domsource = new DOMSource(doc);
			StreamResult result = new StreamResult(finalDirectory);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// System.out.println("File saved at: "+finalDirectory);
		return true;
	}

	private static JsonObject jsonSettings;
	private static Map<String, Object> mapSettings; // Yeah, I don't know how to merge the two. I'm sorry.
	public static boolean saveSettingsJson(String finalDirectory, Map<String, Object> settingsSave) { 	mapSettings = settingsSave; return saveSettings(finalDirectory, false); }
	public static boolean saveSettingsJson(String finalDirectory, JsonObject settingsSave) { 			jsonSettings = settingsSave; return saveSettings(finalDirectory, true); }
	private static boolean saveSettings(String finalDirectory, Boolean a) {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(finalDirectory));
			// https://howtodoinjava.com/gson/pretty-print-json-output/
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			writer.write(gson.toJson(a? jsonSettings : mapSettings));
			writer.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static JsonObject loadSettingsJson(String directory) {
		try{
			BufferedReader reader = Files.newBufferedReader(Paths.get(directory));
			JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();	
			reader.close();
			return parser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
