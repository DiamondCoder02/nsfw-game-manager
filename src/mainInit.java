import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.lang.System.out;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class mainInit {
	public static void main(String[] args) {
		try {
			File file = new File("hentai.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			dom.normalize();
			Window window = new Window();
			Object[][] data = loadXML(dom);
			window.WindowCreate(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object[][] loadXML(Document dom) {
		Object[][] allDataFromFile = new Object[100][5];
		Integer counter = 0;
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
						String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
						String developer = e.getElementsByTagName("developer").item(0).getTextContent().trim();
						String played_version = e.getElementsByTagName("played_version").item(0).getTextContent().trim();
						String dateof_lastupate = e.getElementsByTagName("dateof_lastupate").item(0).getTextContent().trim();
						allDataFromFile[counter][0] = id;
						allDataFromFile[counter][1] = name;
						allDataFromFile[counter][2] = developer;
						allDataFromFile[counter][3] = played_version;
						allDataFromFile[counter][4] = dateof_lastupate;
						counter++;
					}
				}
			}
		}
		return allDataFromFile;
	}
}

class Window extends JFrame implements ActionListener {
	private final JFileChooser fc = new JFileChooser();
	JMenuBar mb;

	JMenuItem refreshFromAPI, refreshFile, saveFileToDifferent;

	JCheckBoxMenuItem darkMode;
	JCheckBoxMenuItem showID, showName, showDeveloper, showPlayedVersion, showDateOfLastUpdate;

	JMenuItem dataToShow, faq, credits;

	JTable table;
	JButton addGame, removeGame, updateList;

	String[] columnNames = { "Game ID", "Name", "Developer", "Last played version", "Date of last update" };

	public void WindowCreate(Object[][] dataFromXMLFile) {
		setTitle("Hentai Game Database");
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mb = new JMenuBar());
		setVisible(true);

		JMenu games;
		mb.add(games = new JMenu("Games"));
		games.add(saveFileToDifferent = new JMenuItem("Save file to different"));
		games.addSeparator();
		games.add(refreshFile = new JMenuItem("File refresh"));
		games.add(refreshFromAPI = new JMenuItem("API refresh"));

		JMenu settings;
		mb.add(settings = new JMenu("Settings"));
		JMenu show;
		settings.add(show = new JMenu("Show"));
		show.add(showID = new JCheckBoxMenuItem("ID", true));
		show.add(showName = new JCheckBoxMenuItem("Name", true));
		show.add(showDeveloper = new JCheckBoxMenuItem("Developer", true));
		show.add(showPlayedVersion = new JCheckBoxMenuItem("Played version", true));
		show.add(showDateOfLastUpdate = new JCheckBoxMenuItem("Date of last update", true));
		
		settings.add(darkMode = new JCheckBoxMenuItem("Dark mode", true));

		JMenu help;
		mb.add(help = new JMenu("Other"));
		help.add(faq = new JMenuItem("FAQ"));
		help.add(credits = new JMenuItem("Credits"));

		refreshFromAPI.addActionListener(this);
		refreshFile.addActionListener(this);
		saveFileToDifferent.addActionListener(this);
		darkMode.addActionListener(this);
		faq.addActionListener(this);
		credits.addActionListener(this);

		table = new JTable(dataFromXMLFile, columnNames);
		table.setBounds(30, 40, 200, 300);
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
		// small buttons at the bottom right 
		add(addGame = new JButton("Add game"), BorderLayout.AFTER_LAST_LINE);
		addGame.addActionListener(this);
		add(removeGame = new JButton("Remove game"), BorderLayout.PAGE_END);
		removeGame.addActionListener(this);
		add(updateList = new JButton("Update list"), BorderLayout.PAGE_END);
		updateList.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addGame) {
			out.println("addGame");
		} else if (e.getSource() == removeGame) {
			out.println("removeGame");
		} else if (e.getSource() == updateList) {
			out.println("updateList");
		} else if (e.getSource() == refreshFromAPI) {
			out.println("refreshFromAPI");
		} else if (e.getSource() == refreshFile) {
			out.println("refreshFile");
		} else if (e.getSource() == saveFileToDifferent) {
			out.println("saveFileToDifferent");
		} else if (e.getSource() == darkMode) {
			out.println("darkMode");
		} else if (e.getSource() == faq) {
			out.println("faq");
		} else if (e.getSource() == credits) {
			out.println("credits");
		}
	}
}