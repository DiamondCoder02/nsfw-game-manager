package folderHandling;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class creatingDefaultDoc {
	/*
	 * Special char:
	 * & &amp;
	 * < &lt;
	 * > &gt;
	 * "" &quot;
	 * '' &apos;
	 * <name>John &amp; Doe</name>
	 */
	public static boolean creatingDocHandler(File mainDirectory, String endPath, String rootCreateElement, String[][] everythingNeeded) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement(rootCreateElement);

			switch (rootCreateElement) {
				case "settings": rootElement = createSettings(doc, rootElement, everythingNeeded); break;
				case "source": rootElement = createDatabase(doc, rootElement, everythingNeeded); break;
				default: break;
			}

			doc.appendChild(rootElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// TODO - is this needed?  transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			DOMSource domsource = new DOMSource(doc);
			StreamResult result = new StreamResult(mainDirectory + "/" + endPath);
			transformer.transform(domsource, result);
			System.out.println("File created: "+endPath);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error creating "+endPath+" file", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	// Create settings.xml
	private static Element createSettings(Document doc, Element rootElement, String[][] everythingNeeded) {
		for (String[] element : everythingNeeded) {
			Element newElement = doc.createElement(element[0]);
			if (element.length == 3) {
				newElement.setAttribute(element[1].split("-")[0], element[1].split("-")[1]);
				newElement.appendChild(doc.createTextNode(element[2]));
			} else {
				newElement.appendChild(doc.createTextNode(element[1]));
			}
			rootElement.appendChild(newElement);
		}
		return rootElement;
	}

	// Create database.xml
	private static Element createDatabase(Document doc, Element rootElement, String[][] everythingNeeded) {
		Element newGame = doc.createElement(everythingNeeded[0][0]);
		newGame.setAttribute(everythingNeeded[0][1].split("/")[0].split("-")[0], everythingNeeded[0][1].split("/")[0].split("-")[1]);
		newGame.setAttribute(everythingNeeded[0][1].split("/")[1].split("-")[0], everythingNeeded[0][1].split("/")[1].split("-")[1]);

		for (int i = 1; i < everythingNeeded.length; i++) {
			Element newElement = doc.createElement(everythingNeeded[i][0]);
			newElement.appendChild(doc.createTextNode(everythingNeeded[i][1]));
			newGame.appendChild(newElement);
		}
		rootElement.appendChild(newGame);
		return rootElement;
	}
}
