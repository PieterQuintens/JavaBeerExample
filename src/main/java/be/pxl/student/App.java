package be.pxl.student;

import java.sql.*;
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

	public float getBeerPrice(String beerName) throws SQLException {
		try(ResultSet resultSet = getConnection().createStatement().executeQuery("Select Price from Beers where name='"+beerName+"'")) {
			resultSet.first();
			return resultSet.getFloat(1);
		}
	}

	public int updateBeerPrice(String beerName, float price) throws SQLException {
		try(Statement stmt = getConnection().createStatement()){
			return stmt.executeUpdate("update Beers set price="+price+ " where Name='"+beerName+"'");
		}
	}
}
