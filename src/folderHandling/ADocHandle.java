package folderHandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.w3c.dom.Document;

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
			// TODO - is this needed? (So far, yes)
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			DOMSource domsource = new DOMSource(doc);
			StreamResult result = new StreamResult(finalDirectory);
			transformer.transform(domsource, result);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("File saved at: "+finalDirectory);
		return true;
	}

	public static boolean saveJson(JSONObject json, String finalDirectory) {
		File file = new File(finalDirectory);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
			try{
				out.append(json.toString());
				out.newLine();
			} finally {
				out.close();
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static JSONObject loadJson(String directory) {
		// TODO - This works?
		File file = new File(directory);
		JSONObject obj = new JSONObject();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			
			
			/*
			for (int i = 0; i < allSettings.length; i++) {
				if (allSettings[i][0] == "othersettings") {
					JSONObject subObj = new JSONObject();
					for (int j = 1; j < allSettings[i].length; j++) {
						subObj.put(allSettings[i][j], false);
					}
					obj.put(allSettings[i][0], subObj);
				} else if (allSettings[i][0] == "shownColumns") {
					JSONObject subObj = new JSONObject();
					for (int j = 1; j < allSettings[i].length; j++) {
						subObj.put(allSettings[i][j], true);
					}
					obj.put(allSettings[i][0], subObj);
				} else {
					obj.put(allSettings[i][0], allSettings[i][1]);
				}
			}*/
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
