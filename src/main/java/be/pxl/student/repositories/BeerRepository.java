package be.pxl.student.repositories;

import be.pxl.student.exceptions.EntityNotFoundException;
import be.pxl.student.model.Beer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerRepository {

    private Connection connection;

    public BeerRepository(Connection connection) {
        this.connection = connection;
    }

    public List<String> getBeerNames() throws SQLException {
        List<String> beerNames = new ArrayList<>();
        String queryString = "select * from Beers;";
        try (ResultSet resultSet = connection.createStatement().executeQuery(queryString)) {
            while (resultSet.next()) {
                beerNames.add(resultSet.getString("Name"));
            }
        }
        return beerNames;
    }

    public float getBeerPrice(String beerName) throws SQLException {
        try (ResultSet resultSet = connection.createStatement().executeQuery("Select Price from Beers where name='" + beerName + "'")) {
            resultSet.first();
            return resultSet.getFloat(1);
        }
    }

    public int updateBeerPrice(String beerName, float price) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            return stmt.executeUpdate("update Beers set price=" + price + " where Name='" + beerName + "'");
        }
    }

    public int updateTwoBeerPrices(Connection connection, String beerOne, String beerTwo, float priceOne, float priceTwo) throws SQLException {
//		Statement stmt1 = connection.createStatement("update Beers set price=" + priceOne + " where Name=" + beerOne);
        return 0;
    }

    public Beer getBeerById(int id) throws EntityNotFoundException, SQLException {
        String query = "select * from Beers where id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (!result.first()) {
                throw new EntityNotFoundException();
            }

            return new Beer(
                    result.getInt("Id"),
                    result.getString("Name"),
                    result.getInt("BrewerId"),
                    result.getInt("CategoryId"),
                    result.getInt("Stock"),
                    result.getFloat("Alcohol"),
                    result.getFloat("Price")
            );
        }
    }

    public int updateBeer(Beer beer) throws  SQLException{
        String query = "update beers set Name=?, Price=?, Alcohol=?, Stock=? where id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, beer.getName());
        stmt.setFloat(2, beer.getPrice());
        stmt.setFloat(3, beer.getAlcohol());
        stmt.setInt(4, beer.getStock());
        stmt.setInt(5, beer.getId());
        return stmt.executeUpdate();
    }

    public int deleteBeer(int id) throws SQLException{
        String delete = "delete from beers where Id=?";
        PreparedStatement stmt = connection.prepareStatement(delete);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }
}

