package be.pxl.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

	String servername;
	String databasename;
	String username;
	String password;

	public App(String servername, String databasename, String username, String password) {
		this.servername = servername;
		this.databasename = databasename;
		this.username = username;
		this.password = password;
	}

	public String sayHello() {
		return "Hello";
	}
	public String sayHelloWorld() {
		return "Hello World";
	}

	public String createJdbcUrl (String server, String databasename) {
		return "jdbc:mysql://"+server+"/"+databasename;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(createJdbcUrl(servername, databasename), username, password);
	}

	public List<String> getBeerNames() throws SQLException {
		List<String> beerNames = new ArrayList<>();

		String queryString = "select * from Beers;";
		try (ResultSet resultSet = getConnection().createStatement().executeQuery(queryString)) {
			while (resultSet.next()) {
				beerNames.add(resultSet.getString("Name"));
			}
		}

		return beerNames;
	}
}
