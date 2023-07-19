package folderHandle.autoFetchChecks;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import folderHandle.loadSaveGamesSettings.loadSettingsFromXml;
import folderHandle.loadSaveGamesSettings.saveLoadDoc;
import main.mainInit;
import main.langLoad;
import main.application.frameCreate;

public class autoFolderChecks {
	static String[] lf = langLoad.folder, bs = langLoad.base;
	public static void fetchFoldersForTable() {
		String[] location = loadSettingsFromXml.loadStringSettings("folderLocation");
		if (location[0].equals("null")) { JOptionPane.showMessageDialog(null, "No hentai folder selected. Please select, then try again!", "Error", JOptionPane.ERROR_MESSAGE); return; }
		Path folder = Paths.get(location[0]);
		
		LocalDate currentTime = LocalDate.now();
		// get the length of the folder
		int folderLength = 0;
		try { folderLength = (int) Files.walk(folder, 1).count(); } catch (IOException e1) { e1.printStackTrace(); }
		JProgressBar pbar = new JProgressBar(0, folderLength);
		pbar.setStringPainted(true);
		pbar.setPreferredSize( new Dimension ( 300, 20));
		JFrame frame = new JFrame(lf[1]!=null?lf[1]:"Checking games...");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(pbar);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		frame.setLocation(550, 300);
		frame.pack();
		frame.setVisible(true);
		CompletableFuture.runAsync(() -> {
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
								try {
									BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
									String lastModified = attr.lastModifiedTime().toString().substring(0, 10);
									if (lastModified.equals(currentTime.toString())) { return; }
									frame.setTitle((lf[1]!=null?lf[1]:"Checking games...") +" "+ site + ":" + id);
									pbar.setValue(pbar.getValue()+1);
									Document gameDatabase = saveLoadDoc.loadDocument(mainInit.databasePath);
									checkAllGameAndUpdate(gameDatabase, site, id, name, version, lastModified);
								} catch (IOException e) { JOptionPane.showMessageDialog(null, "Error reading folder directory! (folderHandle.autoFetchChecks)", "Error", JOptionPane.ERROR_MESSAGE); return; }
							} catch (Exception e) { JOptionPane.showMessageDialog(null, file.toString() + "\n file doesn't have the correct name!\n" + "man-000000_{gameName}_{gameVersion} {anythingElseYouWant}", "Error", JOptionPane.ERROR_MESSAGE); return; }
						}
					}
				});
				pbar.setValue(pbar.getMaximum());
				frame.dispose();
				JOptionPane.showMessageDialog(null, lf[2]==null?"All game infos got updated":lf[2], bs[3]==null?"Update":bs[3], JOptionPane.INFORMATION_MESSAGE);
				frameCreate.refreshTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
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
