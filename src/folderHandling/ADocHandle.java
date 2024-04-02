package folderHandling;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
}
