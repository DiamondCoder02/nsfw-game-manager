package windowJframe;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
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

public class updateGameManually {
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
		if (isIDInDatabase.isInDatabase(idValue, "man")) {
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

									JTextField newname = new JTextField();
									JTextField newdeveloper = new JTextField();
									JTextField newplayed_version = new JTextField();
									JTextField newdateof_lastplay = new JTextField();
									JTextField newuser_rated = new JTextField();
									JTextField newnewest_version = new JTextField();
									JTextField newdateof_lastupate = new JTextField();
									JTextField newpeople_rated = new JTextField();

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
									// Flash, HTML, Java, QSP, RenPy, RPGmaker, Unity, Unreal, WinGit, WolfRPG, other/unknown
									JRadioButton engine_Flash = new JRadioButton("Flash"), engine_HTML = new JRadioButton("HTML"), engine_Java = new JRadioButton("Java"), engine_QSP = new JRadioButton("QSP"), engine_RenPy = new JRadioButton("RenPy"), engine_RPGmaker = new JRadioButton("RPGmaker"), engine_Unity = new JRadioButton("Unity"), engine_Unreal = new JRadioButton("Unreal"), engine_WinGit = new JRadioButton("WinGit"), engine_WolfRPG = new JRadioButton("WolfRPG"), engine_other = new JRadioButton("other/unknown", true);
									engine_Flash.setActionCommand("Flash"); engine_HTML.setActionCommand("HTML"); engine_Java.setActionCommand("Java"); engine_QSP.setActionCommand("QSP"); engine_RenPy.setActionCommand("RenPy"); engine_RPGmaker.setActionCommand("RPGmaker"); engine_Unity.setActionCommand("Unity"); engine_Unreal.setActionCommand("Unreal"); engine_WinGit.setActionCommand("WinGit"); engine_WolfRPG.setActionCommand("WolfRPG"); engine_other.setActionCommand("other/unknown");
									ButtonGroup engineGroup = new ButtonGroup(); engineGroup.add(engine_Flash); engineGroup.add(engine_HTML); engineGroup.add(engine_Java); engineGroup.add(engine_QSP); engineGroup.add(engine_RenPy); engineGroup.add(engine_RPGmaker); engineGroup.add(engine_Unity); engineGroup.add(engine_Unreal); engineGroup.add(engine_WinGit); engineGroup.add(engine_WolfRPG); engineGroup.add(engine_other);
									JPanel enginePanel = new JPanel(); enginePanel.add(engine_Flash); enginePanel.add(engine_HTML); enginePanel.add(engine_Java); enginePanel.add(engine_QSP); enginePanel.add(engine_RenPy); enginePanel.add(engine_RPGmaker); enginePanel.add(engine_Unity); enginePanel.add(engine_Unreal); enginePanel.add(engine_WinGit); enginePanel.add(engine_WolfRPG); enginePanel.add(engine_other);
									for (int k = 0; k < enginePanel.getComponentCount(); k++) {
										JRadioButton r = (JRadioButton) enginePanel.getComponent(k);
										if (r.getActionCommand().equals(oldengine)) { r.setSelected(true); }
									}
									// Windows, Linux, Mac, Android, other
									JCheckBox os_win = new JCheckBox("Windows"), os_lin = new JCheckBox("Linux"), os_mac = new JCheckBox("Mac"), os_and = new JCheckBox("Android"), os_other = new JCheckBox("other");
									os_win.setActionCommand("windows"); os_lin.setActionCommand("linux"); os_mac.setActionCommand("mac"); os_and.setActionCommand("android"); os_other.setActionCommand("other");
									JPanel osPanel = new JPanel(); osPanel.add(os_win); osPanel.add(os_lin); osPanel.add(os_mac); osPanel.add(os_and); osPanel.add(os_other);
									if (oldos.contains("Windows")) { os_win.setSelected(true); } if (oldos.contains("Linux")) { os_lin.setSelected(true); } if (oldos.contains("Mac")) { os_mac.setSelected(true); } if (oldos.contains("Android")) { os_and.setSelected(true); } if (oldos.contains("other")) { os_other.setSelected(true); }

									JTextField newselfNote = new JTextField();

									JLabel Namelabel = new JLabel("Name: (required)" + " (old: "+oldname+")");
									panel.add(Namelabel); panel.add(newname);
									JLabel developerlabel = new JLabel("Developer:" + " (old: "+olddeveloper+")");
									panel.add(developerlabel); panel.add(newdeveloper);
									JLabel played_versionlabel = new JLabel("Played version:" + " (old: "+oldplayed_version+")");
									panel.add(played_versionlabel); panel.add(newplayed_version);
									JLabel dateof_lastplaylabel = new JLabel("Date of last time play:" + " (old: "+olddateof_lastplay+")");
									panel.add(dateof_lastplaylabel); panel.add(newdateof_lastplay);
									JLabel user_ratedlabel = new JLabel("Rated:" + " (old: "+olduser_rated+")");
									panel.add(user_ratedlabel); panel.add(newuser_rated);
									JLabel newest_versionlabel = new JLabel("Newest version:" + " (old: "+oldnewest_version+")");
									panel.add(newest_versionlabel); panel.add(newnewest_version);
									JLabel dateOfLastUpdatelabel = new JLabel("Date of last update:" + " (old: "+olddateof_lastupate+")");
									panel.add(dateOfLastUpdatelabel); panel.add(newdateof_lastupate);
									JLabel people_ratedlabel = new JLabel("People rating:" + " (old: "+oldpeople_rated+")");
									panel.add(people_ratedlabel); panel.add(newpeople_rated);
									JLabel howFarUserPlayedlabel = new JLabel("Player progress:" + " (old: "+oldhowFarUserPlayed+")");
									panel.add(howFarUserPlayedlabel); panel.add(howFarUserPlayedPanel);
									JLabel stillOnPclabel = new JLabel("Deleted from pc:" + " (old: "+oldstillOnPc+")");
									panel.add(stillOnPclabel); panel.add(stillOnPcPanel);
									JLabel enginelabel = new JLabel("Engine:" + " (old: "+oldengine+")");
									panel.add(enginelabel); panel.add(enginePanel);
									JLabel oslabel = new JLabel("OS:" + " (old: "+oldos+")");
									panel.add(oslabel); panel.add(osPanel);
									JLabel selfNotelabel = new JLabel("Self note:" + " (old: "+oldselfNote+")");
									panel.add(selfNotelabel); panel.add(newselfNote);
									panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
									int option = JOptionPane.showConfirmDialog(null, panel, "Update game", JOptionPane.OK_CANCEL_OPTION);
									if (option == JOptionPane.OK_OPTION) {
										String newnameValue = newname.getText();
										String newdeveloperValue = newdeveloper.getText();
										String newplayed_versionValue = newplayed_version.getText();
										String newdateof_lastplayValue = newdateof_lastplay.getText();
										String newuser_ratedValue = newuser_rated.getText();
										String newnewest_versionValue = newnewest_version.getText();
										String newdateof_lastupateValue = newdateof_lastupate.getText();
										String newpeople_ratedValue = newpeople_rated.getText();
										String newhowFarUserPlayedValue = howFarUserPlayed.getSelection().getActionCommand();
										String newstillOnPcValue = stillOnPc.getSelection().getActionCommand();
										String newengineValue = engineGroup.getSelection().getActionCommand();
										String newosValue = "";
										if (os_win.isSelected()) { newosValue += "Windows / "; }
										if (os_lin.isSelected()) { newosValue += "Linux / "; }
										if (os_mac.isSelected()) { newosValue += "Mac / "; }
										if (os_and.isSelected()) { newosValue += "Android / "; }
										if (os_other.isSelected()) { newosValue += "other"; }
										if (newosValue.endsWith(" / ")) { newosValue = newosValue.substring(0, newosValue.length() - 3); }
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
			JOptionPane.showMessageDialog(null, "Game with *MANUAL* id: "+idValue+" doesn't exists", "Error", JOptionPane.ERROR_MESSAGE); return;
		}
	}
}
