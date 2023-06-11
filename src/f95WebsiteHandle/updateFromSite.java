package f95WebsiteHandle;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import windowJframe._initFrame;
import xmlFolderHandle.isIDInDatabase;
import xmlFolderHandle.saveLoadDoc;

public class updateFromSite {
	public static void updatef95game(){
		JPanel panel = new JPanel(new GridLayout(10*2, 0));
		String idValue = JOptionPane.showInputDialog(null, "Enter the id of the game you want to edit", "Edit game", JOptionPane.PLAIN_MESSAGE);
		if (idValue == null) { JOptionPane.showMessageDialog(null, "You must enter an id", "Error", JOptionPane.ERROR_MESSAGE); return; }

		if (isIDInDatabase.isInDatabase(idValue, "f95")) {
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
									String olddateof_lastplay = e.getElementsByTagName("dateof_lastplay").item(0).getTextContent().trim();
									String olduser_rated = e.getElementsByTagName("user_rating").item(0).getTextContent().trim();
									String oldnewest_version = e.getElementsByTagName("newest_version").item(0).getTextContent().trim();
									String olddateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
									String oldpeople_rated = e.getElementsByTagName("people_rating").item(0).getTextContent().trim();
									String oldhowFarUserPlayed = e.getElementsByTagName("howFarUserPlayed").item(0).getTextContent().trim();
									String oldstillOnPc = e.getElementsByTagName("stillOnPc").item(0).getTextContent().trim();
									String oldengine = e.getElementsByTagName("engine").item(0).getTextContent().trim();
									String oldos = e.getElementsByTagName("OS").item(0).getTextContent().trim();
									String oldselfNote = e.getElementsByTagName("selfNote").item(0).getTextContent().trim();

									String[] output = loadSite.getUrlContents(idValue);
									String newnameValue = output[0].toString();
									String newdeveloperValue = output[1].toString();
									String newnewest_versionValue = output[2].toString();
									String newdateof_lastupateValue = output[3].toString();
									String newpeople_ratedValue = output[4].toString();
									String newengineValue = output[5].toString();
									String newosValue = output[6].toString();

									JTextField newplayed_version = new JTextField();
									JTextField newdateof_lastplay = new JTextField();
									JTextField newuser_rated = new JTextField();
									// Not played, In progress, Finish, 100% Finished
									JRadioButton howFarUserPlayed_NotPlayed = new JRadioButton("Not played"), howFarUserPlayed_Playing = new JRadioButton("In progress"), howFarUserPlayed_Finished = new JRadioButton("Finish"), howFarUserPlayed_100Percent = new JRadioButton("100% Finished");
									howFarUserPlayed_NotPlayed.setActionCommand("Not played"); howFarUserPlayed_Playing.setActionCommand("In progress"); howFarUserPlayed_Finished.setActionCommand("Finish"); howFarUserPlayed_100Percent.setActionCommand("100% Finished");
									ButtonGroup howFarUserPlayed = new ButtonGroup(); howFarUserPlayed.add(howFarUserPlayed_NotPlayed); howFarUserPlayed.add(howFarUserPlayed_Playing); howFarUserPlayed.add(howFarUserPlayed_Finished); howFarUserPlayed.add(howFarUserPlayed_100Percent);
									JPanel howFarUserPlayedPanel = new JPanel(); howFarUserPlayedPanel.add(howFarUserPlayed_NotPlayed); howFarUserPlayedPanel.add(howFarUserPlayed_Playing); howFarUserPlayedPanel.add(howFarUserPlayed_Finished); howFarUserPlayedPanel.add(howFarUserPlayed_100Percent);
									for (int k = 0; k < howFarUserPlayedPanel.getComponentCount(); k++) {
										JRadioButton r = (JRadioButton) howFarUserPlayedPanel.getComponent(k);
										if (r.getActionCommand().equals(oldhowFarUserPlayed)) { r.setSelected(true); }
									}
									// Yes, No, Unknown
									JRadioButton stillOnPc_yes = new JRadioButton("Yes"), stillOnPc_no = new JRadioButton("No"), stillOnPc_unknown = new JRadioButton("Unknown");
									stillOnPc_yes.setActionCommand("yes"); stillOnPc_no.setActionCommand("no"); stillOnPc_unknown.setActionCommand("unknown");
									ButtonGroup stillOnPc = new ButtonGroup(); stillOnPc.add(stillOnPc_yes); stillOnPc.add(stillOnPc_no); stillOnPc.add(stillOnPc_unknown);
									JPanel stillOnPcPanel = new JPanel(); stillOnPcPanel.add(stillOnPc_yes); stillOnPcPanel.add(stillOnPc_no); stillOnPcPanel.add(stillOnPc_unknown);						
									for (int k = 0; k < stillOnPcPanel.getComponentCount(); k++) {
										JRadioButton r = (JRadioButton) stillOnPcPanel.getComponent(k);
										if (r.getActionCommand().equals(oldstillOnPc)) { r.setSelected(true); }
									}
									JTextField newselfNote = new JTextField();

									howFarUserPlayedPanel.setLayout(new BoxLayout(howFarUserPlayedPanel, BoxLayout.X_AXIS));
									stillOnPcPanel.setLayout(new BoxLayout(stillOnPcPanel, BoxLayout.X_AXIS));

									JLabel Namelabel, developerlabel, newest_versionlabel, dateOfLastUpdatelabel, people_ratedlabel, enginelabel, oslabel;
									if (oldname.equals(newnameValue)) { Namelabel = new JLabel("Name: "+ newnameValue); } 
									else { Namelabel = new JLabel("Name: " + oldname + " -> " + newnameValue); }
									if (olddeveloper.equals(newdeveloperValue)) { developerlabel = new JLabel("Developer: " + newdeveloperValue); } 
									else { developerlabel = new JLabel("Developer: " + olddeveloper + " -> " + newdeveloperValue); }
									if (oldnewest_version.equals(newnewest_versionValue)) { newest_versionlabel = new JLabel("Newest version: " + newnewest_versionValue); } 
									else { newest_versionlabel = new JLabel("Newest version: " + oldnewest_version + " -> " + newnewest_versionValue); }
									if (olddateof_lastupate.equals(newdateof_lastupateValue)) { dateOfLastUpdatelabel = new JLabel("Date of last update: " + newdateof_lastupateValue); } 
									else { dateOfLastUpdatelabel = new JLabel("Date of last update: " + olddateof_lastupate + " -> " + newdateof_lastupateValue); }
									if (oldpeople_rated.equals(newpeople_ratedValue)) { people_ratedlabel = new JLabel("People rated: " + newpeople_ratedValue); } 
									else { people_ratedlabel = new JLabel("People rated: " + oldpeople_rated + " -> " + newpeople_ratedValue); }
									if (oldengine.equals(newengineValue)) { enginelabel = new JLabel("Engine: " + newengineValue); } 
									else { enginelabel = new JLabel("Engine: " + oldengine + " -> " + newengineValue); }
									if (oldos.equals(newosValue)) { oslabel = new JLabel("OS: " + newosValue); } 
									else { oslabel = new JLabel("OS: " + oldos + " -> " + newosValue); }

									panel.add(Namelabel);
									panel.add(developerlabel);
									JLabel played_versionlabel = new JLabel("Played version:" + " (old: "+oldplayed_version+")");
									panel.add(played_versionlabel); panel.add(newplayed_version);
									JLabel dateof_lastplaylabel = new JLabel("Date of last time play:" + " (old: "+olddateof_lastplay+")");
									panel.add(dateof_lastplaylabel); panel.add(newdateof_lastplay);
									JLabel user_ratedlabel = new JLabel("Rated:" + " (old: "+olduser_rated+")");
									panel.add(user_ratedlabel); panel.add(newuser_rated);
									panel.add(newest_versionlabel);
									panel.add(dateOfLastUpdatelabel);
									panel.add(people_ratedlabel);
									JLabel howFarUserPlayedlabel = new JLabel("Player progress:");
									panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
									JLabel stillOnPclabel = new JLabel("Deleted from pc:");
									panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
									panel.add(enginelabel);
									panel.add(oslabel);
									JLabel selfNotelabel = new JLabel("Self note:" + " (old: "+oldselfNote+")");
									panel.add(selfNotelabel); panel.add(newselfNote);
									int option = JOptionPane.showConfirmDialog(null, panel, "Update game", JOptionPane.OK_CANCEL_OPTION);
									if (option == JOptionPane.OK_OPTION) {
										String newplayed_versionValue = newplayed_version.getText();
										String newdateof_lastplayValue = newdateof_lastplay.getText();
										String newuser_ratedValue = newuser_rated.getText();
										String newhowFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
										String newstillOnPcValue = stillOnPc.getSelection().getActionCommand();
										String newselfNoteValue = newselfNote.getText();
										if (newnameValue.equals("")) { newnameValue = oldname; }
										if (newdeveloperValue.equals("")) { newdeveloperValue = olddeveloper; }
										if (newplayed_versionValue.equals("")) { newplayed_versionValue = oldplayed_version; }
										if (newdateof_lastplayValue.equals("")) { newdateof_lastplayValue = olddateof_lastplay; }
										if (newuser_ratedValue.equals("")) { newuser_ratedValue = olduser_rated; }
										if (newnewest_versionValue.equals("")) { newnewest_versionValue = oldnewest_version; }
										if (newdateof_lastupateValue.equals("")) { newdateof_lastupateValue = olddateof_lastupate; }
										if (newpeople_ratedValue.equals("")) { newpeople_ratedValue = oldpeople_rated; }
										// if (newhowFarUserPlayedValue.equals("")) { newhowFarUserPlayedValue = oldhowFarUserPlayed; }
										// if (newstillOnPcValue.equals("")) { newstillOnPcValue = oldstillOnPc; }
										// if (newengineValue.equals("")) { newengineValue = oldengine; }
										// if (newosValue.equals("")) { newosValue = oldos; }
										if (newselfNoteValue.equals("")) { newselfNoteValue = oldselfNote; }
										e.getElementsByTagName("name").item(0).setTextContent(newnameValue);
										e.getElementsByTagName("developer").item(0).setTextContent(newdeveloperValue);
										e.getElementsByTagName("played_version").item(0).setTextContent(newplayed_versionValue);
										e.getElementsByTagName("dateof_lastplay").item(0).setTextContent(newdateof_lastplayValue);
										e.getElementsByTagName("user_rating").item(0).setTextContent(newuser_ratedValue);
										e.getElementsByTagName("newest_version").item(0).setTextContent(newnewest_versionValue);
										e.getElementsByTagName("dateof_lastupate").item(0).setTextContent(newdateof_lastupateValue);
										e.getElementsByTagName("people_rating").item(0).setTextContent(newpeople_ratedValue);
										e.getElementsByTagName("howFarUserPlayed").item(0).setTextContent(newhowFarUserPlayedValue);
										e.getElementsByTagName("stillOnPc").item(0).setTextContent(newstillOnPcValue);
										e.getElementsByTagName("engine").item(0).setTextContent(newengineValue);
										e.getElementsByTagName("OS").item(0).setTextContent(newosValue);
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
