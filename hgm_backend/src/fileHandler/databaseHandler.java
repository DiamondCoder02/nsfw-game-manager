package hgm_backend.src.fileHandler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hgm_backend.startBackend;
import hgm_backend.src.log;

public class databaseHandler {
	private static File locationToSave = new File("./_saveMe");
	private static String name = "HGM_database_";

	public static boolean dbCheck() {
		if (!checkIfDBisPresent()) createDB();
		return false;
	}

	/**
	 * @see https://github.com/xerial/sqlite-jdbc library
	 * @see https://www.sqlitetutorial.net/sqlite-java/ tutorial
	 * @return
	 */
	private static boolean createDB() {
		// NOTE: Connection and Statement are AutoCloseable.
		// Don't forget to close them both in order to avoid leaks.
		try (
				Connection connection = DriverManager.getConnection(
						"jdbc:sqlite:" + locationToSave.toString() + "/" +
								name + loadConfig.configOpject.getJSONObject("appInfo").getInt("chosenDatabase")
								+ ".db");
				Statement statement = connection.createStatement();) {
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate("create table person (id integer, name string)");
			statement.executeUpdate("insert into person values(1, 'leo')");
			statement.executeUpdate("insert into person values(2, 'yui')");
			ResultSet rs = statement.executeQuery("select * from person");
			while (rs.next()) {
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
			return true;
		} catch (SQLException e) {
			// if the error message is "out of memory", it probably means no database file is found
			e.printStackTrace(System.err);
			log.errorPane("Error"+e.getMessage(), "database Error");
			return false;
		}
	}

	private static boolean checkIfDBisPresent() {
		log.print(locationToSave.toString() + "/" + name + loadConfig.configOpject.getJSONObject("appInfo").getInt("chosenDatabase")+ ".db");
		return false;
	}
}
