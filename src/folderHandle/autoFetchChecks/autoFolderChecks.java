package folderHandle.autoFetchChecks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;
import main.mainInit;

public class autoFolderChecks {
	public static void fetchFoldersForTable() {
		String[] location = loadSettingsFromXml.loadStringSettings("folderLocation");
		Path folder = Paths.get(location[0]);
		Document gameDatabase = saveLoadDoc.loadDocument(mainInit.databasePath);
		LocalDate currentTime = LocalDate.now();
		try {
			Files.walk(folder, 1).forEach(file -> {
				if (Files.isDirectory(file)) {
					String fileName = file.getFileName().toString().trim();
					if (fileName.startsWith("f95") || fileName.startsWith("man") || fileName.startsWith("dls")) {
						String site="-", id="-", name="-", version="-";
						try{ site = fileName.split("-")[0];
							id = fileName.split("-")[1].split("_")[0];
							name = fileName.split("_")[1].split("_")[0];
							version = fileName.split("_")[2].split(" ")[0];
						} catch (Exception e) { JOptionPane.showMessageDialog(null, file.toString() + "\n file doesn't have the correct name!\n" + "man-000000_{gameName}_{gameVersion} {anythingElseYouWant}", "Error", JOptionPane.ERROR_MESSAGE); return; }
						try {
							BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
							String lastModified = attr.lastModifiedTime().toString().substring(0, 10);
							if (lastModified.equals(currentTime.toString())) { return; }
							checkAllGameAndUpdate(gameDatabase, site, id, name, version, lastModified);
						} catch (IOException e) { JOptionPane.showMessageDialog(null, "Error reading folder directory! (folderHandle.autoFetchChecks)", "Error", JOptionPane.ERROR_MESSAGE); return; }
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void checkAllGameAndUpdate(
		Document docToWorkWith, String siteToWorkWith, String idToWorkWith, 
		String nameToWorkWith, String versionToWorkWith, String lastModifiedToWorkWith
	) {
		//System.out.println(siteToWorkWith+" "+idToWorkWith+" "+nameToWorkWith+" "+versionToWorkWith+" "+lastModifiedToWorkWith); 
		NodeList source = docToWorkWith.getElementsByTagName("source");
		for (int i = 0; i < source.getLength(); i++) {
			Node sourceNode = source.item(i);
			if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList game = sourceNode.getChildNodes();
				for (int j = 0; j < game.getLength(); j++) {
					Node gameNode = game.item(j);
					if (gameNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) gameNode;
						String id = e.getAttribute("id").trim();
						String site = e.getAttribute("from").trim();
						if (id.equals(idToWorkWith) && site.equals(siteToWorkWith)) {
							if (site.equals("man")) {e.getElementsByTagName("name").item(0).setTextContent(nameToWorkWith);}
							e.getElementsByTagName("played_version").item(0).setTextContent(versionToWorkWith);
							e.getElementsByTagName("dateof_lastplay").item(0).setTextContent(lastModifiedToWorkWith);
							e.getElementsByTagName("stillOnPc").item(0).setTextContent("yes");
						}
					}
				}
			}
		}
		saveLoadDoc.saveDocument(docToWorkWith, mainInit.databasePath);
	}
}
