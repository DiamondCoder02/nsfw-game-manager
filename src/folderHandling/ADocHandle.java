package folderHandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Map;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import folderHandling.initialFileLoading.loadSettings;

public class ADocHandle {
	// TODO note: Reloads a lot
	/**
	 * This function will load the correct database. <p>
	 * If the database does not exist then it will create a new one. <p>
	 * If the database is not the main database then it will load the correct one. <p>
	 * @param mainDirectory - The needed path to the XML file without the file name or extension
	 * @return Document - Returns the correct database
	 */
	public static Document load(String mainDirectory) {
		Document dom = null;
		loadSettings.load(mainDirectory);
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			String dbNum = loadSettings.databaseNumber;
			String[] databases = new String[loadSettings.databaseNames.split("//").length];
			boolean maindb = false;
			// TODO shorten this. Integer in databeses so this big for loop is not needed
			for (int i = 0; i < databases.length; i++) {
				// get all files that names start with "hentai" and end with ".xml"
				File[] files = new File(mainDirectory).listFiles((dir, name) -> name.startsWith("hentai") && name.endsWith(".xml"));
				for (int j = 0; j < files.length; j++) {
					if (files[j].getName().equals("hentai.xml") && !maindb) {
						databases[i] = files[j].getName();
						maindb = true;
					}
					if (files[j].getName().equals("hentai"+i+".xml")) {
						databases[i] = files[j].getName();
					}
				}
			}
			for (int i = 0; i < databases.length; i++) {
				if (databases[i] == null) {
					dom = docBuilder.parse(new File("Assets/default_hentai.xml"));
					dom.normalize();
					ADocHandle.saveAll(dom, mainDirectory, "/hentai"+i+".xml");
				}
			}
			for (int i = 0; i < databases.length; i++) {
				if (databases[i] == null || dbNum.equals("0")) {
					dom = docBuilder.parse(new File(mainDirectory + "/hentai.xml"));
					dom.normalize();
					return dom;
				}
				if (databases[i].equals("hentai"+dbNum+".xml")) {
					dom = docBuilder.parse(new File(mainDirectory + "/hentai"+dbNum+".xml"));
					dom.normalize();
					return dom;
				}
			}
			if (dom == null) { dom = docBuilder.parse(new File(mainDirectory + "/hentai.xml")); dom.normalize(); }
			return dom;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error loading document");
		}
	}

	/**
	 * Save a document to a directory
	 * @param doc - The document to save
	 * @param finalDirectory - The directory to save the document
	 * @param finalPath - The path to save the document
	 * @return boolean - returns true if the document was saved successfully
	 */
	private static boolean saveAll(Document doc, String finalDirectory, String finalPath) {
		try{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// is this needed? (So far, yes)
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			DOMSource domsource = new DOMSource(doc);
			StreamResult result = new StreamResult(finalDirectory + finalPath);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// System.out.println("File saved at: "+finalDirectory);
		return true;
	}

	/**
	 * Save a document to a directory
	 * @param doc - The document to save
	 * @param finalDirectory - The directory to save the document
	 * @return boolean - returns true if the document was saved successfully
	 */
	public static boolean save(Document doc, String finalDirectory) {
		String dbNum = loadSettings.databaseNumber;
		if (dbNum.equals("0")) {
			finalDirectory = finalDirectory + "/hentai.xml";
		} else {
			finalDirectory = finalDirectory + "/hentai"+dbNum+".xml";
		}
		try{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// is this needed? (So far, yes)
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
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

	/**
	 * Get an element from a document
	 * @param dom - The document to get the element from
	 * @param idValue - The ID of the element
	 * @return Element - returns the element
	 */
	public static Element getElementFromDB(Document dom, String idValue, String fromSite) {
		NodeList source = dom.getElementsByTagName("source");
		for (int i = 0; i < source.getLength(); i++) {
			Node sourceNode = source.item(i);
			if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList game = sourceNode.getChildNodes();
				for (int j = 0; j < game.getLength(); j++) {
					Node gameNode = game.item(j);
					if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) gameNode;
						if (idValue.equals(e.getAttribute("id").trim()) && fromSite.equals(e.getAttribute("from").trim())) {
							return e;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Get the source node from a document
	 * @param dom - The document to get the source node from
	 * @return Node - returns the source node
	 */
	public static Node getSourceNodeFromDB(Document dom){
		NodeList source = dom.getElementsByTagName("source");
		for (int i = 0; i < source.getLength(); i++) {
			Node sourceNode = source.item(i);
			if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
				return sourceNode;
			}
		}
		return null;
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

	/**
	 * Load a settings json file
	 * @param directory - The directory of the settings json file
	 * @return JsonObject - returns the loaded settings json file
	 */
	public static JsonObject loadSettingsJson(String directory) {
		try{
			BufferedReader reader = Files.newBufferedReader(Paths.get(directory));
			JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();	
			reader.close();
			return parser;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
}
