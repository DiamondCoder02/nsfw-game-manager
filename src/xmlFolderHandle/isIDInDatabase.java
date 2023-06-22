package xmlFolderHandle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.mainInit;

public class isIDInDatabase {
	public static boolean isInDatabase(String givenID, String sourceFrom){
		try{
			Document dom = saveLoadDoc.loadDocument(mainInit.databasePath);
			NodeList source = dom.getElementsByTagName("source");
			for (int i = 0; i < source.getLength(); i++) {
				Node sourceNode = source.item(i);
				if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
					NodeList game = sourceNode.getChildNodes();
					for (int j = 0; j < game.getLength(); j++) {
						Node gameNode = game.item(j);
						if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
							Element e = (Element) gameNode;
							String id = e.getAttribute("id").trim();
							String from = e.getAttribute("from").trim();
							if (id.equals(givenID) && from.equals(sourceFrom)) {
								return true;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
