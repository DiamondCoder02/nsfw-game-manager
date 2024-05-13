package folderHandling;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class checkDatabase {
	/**
	 * This function will check if a game is in the database.
	 * @param givenID - The ID of the game to check.
	 * @param sourceFrom - The source of the game to check.
	 * @return boolean - returns true if the game is in the database.
	 */
	public static boolean isInDatabase(String mainDri, String givenID, String sourceFrom){
		try{
			Document dom = ADocHandle.load(mainDri + "/hentai.xml");
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
