package windowJframe;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.saveLoadDoc;

public class updateGameFromToFile {
	public static void updateOneGameFromToFile(){
		JOptionPane optionPane = new JOptionPane();
		JPanel panel = new JPanel();
		JTextField id = new JTextField();
		Object[] message = {
			"ID of the game to update:", id
		};
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Update game");
		dialog.setVisible(true);
		String idValue = id.getText();
		if (idValue.equals("")) { JOptionPane.showMessageDialog(null, "ID is required", "Error", JOptionPane.ERROR_MESSAGE); return; }
		if (isIDInDatabase.isInDatabase(idValue)) {
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
								String ids = e.getAttribute("id").trim();
								if (ids.equals(idValue)) {
									String oldname = e.getElementsByTagName("name").item(0).getTextContent().trim();
									String olddeveloper = e.getElementsByTagName("developer").item(0).getTextContent().trim();
									String oldplayed_version = e.getElementsByTagName("played_version").item(0).getTextContent().trim();
									String olddateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
									String oldhowFarUserPlayed = e.getElementsByTagName("howFarUserPlayed").item(0).getTextContent().trim();
									String oldstillOnPc = e.getElementsByTagName("stillOnPc").item(0).getTextContent().trim();
									String oldengine = e.getElementsByTagName("engine").item(0).getTextContent().trim();
									// String oldos = e.getElementsByTagName("os").item(0).getTextContent().trim();
									String oldselfNote = e.getElementsByTagName("selfNote").item(0).getTextContent().trim();
									JTextField newname = new JTextField();
									JTextField newdeveloper = new JTextField();
									JTextField newplayed_version = new JTextField();
									JTextField newdateof_lastupate = new JTextField();

									JRadioButton howFarUserPlayed_NotPlayed = new JRadioButton("Not played"), howFarUserPlayed_Playing = new JRadioButton("In progress"), howFarUserPlayed_Finished = new JRadioButton("Finish"), howFarUserPlayed_100Percent = new JRadioButton("100% Finished");
									howFarUserPlayed_NotPlayed.setActionCommand("Not played"); howFarUserPlayed_Playing.setActionCommand("In progress"); howFarUserPlayed_Finished.setActionCommand("Finish"); howFarUserPlayed_100Percent.setActionCommand("100% Finished");
									ButtonGroup howFarUserPlayed = new ButtonGroup(); howFarUserPlayed.add(howFarUserPlayed_NotPlayed); howFarUserPlayed.add(howFarUserPlayed_Playing); howFarUserPlayed.add(howFarUserPlayed_Finished); howFarUserPlayed.add(howFarUserPlayed_100Percent);
									JPanel howFarUserPlayedPanel = new JPanel(); howFarUserPlayedPanel.add(howFarUserPlayed_NotPlayed); howFarUserPlayedPanel.add(howFarUserPlayed_Playing); howFarUserPlayedPanel.add(howFarUserPlayed_Finished); howFarUserPlayedPanel.add(howFarUserPlayed_100Percent);
									for (int k = 0; k < howFarUserPlayedPanel.getComponentCount(); k++) {
										JRadioButton r = (JRadioButton) howFarUserPlayedPanel.getComponent(k);
										if (r.getActionCommand().equals(oldhowFarUserPlayed)) { r.setSelected(true); }
									}
									JRadioButton stillOnPc_yes = new JRadioButton("Yes"), stillOnPc_no = new JRadioButton("No"), stillOnPc_unknown = new JRadioButton("Unknown");
									stillOnPc_yes.setActionCommand("yes"); stillOnPc_no.setActionCommand("no"); stillOnPc_unknown.setActionCommand("unknown");
									ButtonGroup stillOnPc = new ButtonGroup(); stillOnPc.add(stillOnPc_yes); stillOnPc.add(stillOnPc_no); stillOnPc.add(stillOnPc_unknown);
									JPanel stillOnPcPanel = new JPanel(); stillOnPcPanel.add(stillOnPc_yes); stillOnPcPanel.add(stillOnPc_no); stillOnPcPanel.add(stillOnPc_unknown);						
									for (int k = 0; k < stillOnPcPanel.getComponentCount(); k++) {
										JRadioButton r = (JRadioButton) stillOnPcPanel.getComponent(k);
										if (r.getActionCommand().equals(oldstillOnPc)) { r.setSelected(true); }
									}

									JTextField newengine = new JTextField();
									// JTextField newos = new JTextField();
									JTextField newselfNote = new JTextField();
									JLabel Namelabel = new JLabel("Name: (required)");
									panel.add(Namelabel); panel.add(newname);
									JLabel developerlabel = new JLabel("Developer:");
									panel.add(developerlabel); panel.add(newdeveloper);
									JLabel played_versionlabel = new JLabel("Played version:");
									panel.add(played_versionlabel); panel.add(newplayed_version);
									JLabel dateOfLastUpdatelabel = new JLabel("Date of last update:");
									panel.add(dateOfLastUpdatelabel); panel.add(newdateof_lastupate);
									JLabel howFarUserPlayedlabel = new JLabel("Player prograssion:");
									panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
									JLabel stillOnPclabel = new JLabel("Deleted from pc:");
									panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
									JLabel enginelabel = new JLabel("Engine:");
									panel.add(enginelabel); panel.add(newengine);
									// JLabel oslabel = new JLabel("OS:");
									// panel.add(oslabel);
									// panel.add(os);
									JLabel selfNotelabel = new JLabel("Self note:");
									panel.add(selfNotelabel); panel.add(newselfNote);
									panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
									int option = JOptionPane.showConfirmDialog(null, panel, "Update game", JOptionPane.OK_CANCEL_OPTION);
									if (option == JOptionPane.OK_OPTION) {
										String newnameValue = newname.getText();
										String newdeveloperValue = newdeveloper.getText();
										String newplayed_versionValue = newplayed_version.getText();
										String newdateof_lastupateValue = newdateof_lastupate.getText();
										String newhowFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
										String newstillOnPcValue = stillOnPc.getSelection().getActionCommand();
										String newengineValue = newengine.getText();
										// String newosValue = newos.getText();
										String newselfNoteValue = newselfNote.getText();
										if (newnameValue.equals("")) { newnameValue = oldname; }
										if (newdeveloperValue.equals("")) { newdeveloperValue = olddeveloper; }
										if (newplayed_versionValue.equals("")) { newplayed_versionValue = oldplayed_version; }
										if (newdateof_lastupateValue.equals("")) { newdateof_lastupateValue = olddateof_lastupate; }
										if (newhowFarUserPlayedValue.equals("")) { newhowFarUserPlayedValue = oldhowFarUserPlayed; }
										if (newstillOnPcValue.equals("")) { newstillOnPcValue = oldstillOnPc; }
										if (newengineValue.equals("")) { newengineValue = oldengine; }
										// if (newosValue.equals("")) { newosValue = oldos; }
										if (newselfNoteValue.equals("")) { newselfNoteValue = oldselfNote; }
										e.getElementsByTagName("name").item(0).setTextContent(newnameValue);
										e.getElementsByTagName("developer").item(0).setTextContent(newdeveloperValue);
										e.getElementsByTagName("played_version").item(0).setTextContent(newplayed_versionValue);
										e.getElementsByTagName("dateof_lastupate").item(0).setTextContent(newdateof_lastupateValue);
										e.getElementsByTagName("howFarUserPlayed").item(0).setTextContent(newhowFarUserPlayedValue);
										e.getElementsByTagName("stillOnPc").item(0).setTextContent(newstillOnPcValue);
										e.getElementsByTagName("engine").item(0).setTextContent(newengineValue);
										// e.getElementsByTagName("os").item(0).setTextContent(newosValue);
										e.getElementsByTagName("selfNote").item(0).setTextContent(newselfNoteValue);
										saveLoadDoc.saveDocument(dom);
										_initFrame.refreshTable();
										JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" has been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
										break;
									} else {
										JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" has not been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
										break;
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Game with id: "+idValue+" doesn't exists", "Error", JOptionPane.ERROR_MESSAGE); return;
		}
	}
}
