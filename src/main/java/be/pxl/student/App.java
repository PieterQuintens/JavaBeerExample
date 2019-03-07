package be.pxl.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

//	String servername;
//	String databasename;
//	String username;
//	String password;
//
//	String jdbcUrl;
//
//	public App(String servername, String databasename, String username, String password) {
//		this.servername = servername;
//		this.databasename = databasename;
//		this.username = username;
//		this.password = password;
//	}

//	public App(String jdbcUrl) {
//		this.jdbcUrl = jdbcUrl;
//	}


	public String sayHello() {
		return "Hello";
	}
	public String sayHelloWorld() {
		return "Hello World";
	}


//	public Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(jdbcUrl, username, password);
//	}

	public List<String> getBeerNames(Connection connection) throws SQLException {
		List<String> beerNames = new ArrayList<>();
		String queryString = "select * from Beers;";
		try (ResultSet resultSet = connection.createStatement().executeQuery(queryString)) {
			while (resultSet.next()) {
				beerNames.add(resultSet.getString("Name"));
			}
		}
		return beerNames;
	}

	public float getBeerPrice(Connection connection, String beerName) throws SQLException {
		try(ResultSet resultSet = connection.createStatement().executeQuery("Select Price from Beers where name='"+beerName+"'")) {
			resultSet.first();
			return resultSet.getFloat(1);
		}
	}

	public int updateBeerPrice(Connection connection, String beerName, float price) throws SQLException {
		try(Statement stmt = connection.createStatement()){
			return stmt.executeUpdate("update Beers set price="+price+ " where Name='"+beerName+"'");
		}
	}

	public int updateTwoBeerPrices(Connection connection, String beerOne, String beerTwo, float priceOne, float priceTwo) throws SQLException{
//		Statement stmt1 = connection.createStatement("update Beers set price=" + priceOne + " where Name=" + beerOne);
		return 0;
	}
}
