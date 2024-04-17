package folderHandling;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import integrationCheck.defaultValues;

public class checkDatabase {
	public static boolean isInDatabase(String givenID, String sourceFrom){
		try{
			Document dom = ADocHandle.load(defaultValues.mainDirectory + "/hentai.xml");
			Element e = ADocHandle.getElementsFromDB(dom);
			String id = e.getAttribute("id").trim();
			String from = e.getAttribute("from").trim();
			if (id.equals(givenID) && from.equals(sourceFrom)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
