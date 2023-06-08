package xmlFolderHandle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class isIDInDatabase {
	static String found;
	public static boolean isInDatabase(String givenID){
		try{
			Document dom = saveLoadDoc.loadDocument();
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
							if (id.equals(givenID)) {
								found += id + " found with " + e.getElementsByTagName("name").item(0).getTextContent().trim() + "\n";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (found != null) {
			// JOptionPane.showMessageDialog(null, found, "ID found", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		return false;
	}
}
