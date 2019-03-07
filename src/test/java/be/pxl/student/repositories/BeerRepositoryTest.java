package be.pxl.student.repositories;

import be.pxl.student.ConnectionFactory;
import be.pxl.student.exceptions.EntityNotFoundException;
import be.pxl.student.model.Beer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BeerRepositoryTest {

    BeerRepository repository;
    Connection connection;

    @Before
    public void setUp() throws Exception {
//		app = new App("192.168.33.22", "StudentDB", "admin", "admin");
//		app = new App("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:StudentDB.sql';MODE=MySQL");
        connection = ConnectionFactory.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:StudentDB.sql';MODE=MySQL", null, null);
        repository = new BeerRepository(connection);
//		connection = ConnectionFactory.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void it_should_have_a_valid_connection() throws SQLException {
//		Connection connection = app.getConnection();
        Assert.assertTrue(connection.isValid(0));
    }

    @Test
    public void it_should_return_result_list() throws SQLException {
        List<String> beers = repository.getBeerNames();
        Assert.assertFalse(beers.isEmpty());
    }

    @Test
    public void should_get_price_jupiler_2_55() throws SQLException {
        Assert.assertEquals(2.55f, repository.getBeerPrice("Jupiler"), 0.01f);
    }

    @Test
    public void should_update_price_jupiler() throws SQLException {
        String beerName = "Jupiler";
        repository.updateBeerPrice(beerName, 3.0f);
        Assert.assertEquals(3.0f, repository.getBeerPrice(beerName), 0.01f);
    }

    @Test
    public void should_return_beer_object_with_id() throws EntityNotFoundException, SQLException {
        Beer expected = new Beer(4, "A.C.O.", 7, 2.75f);
        Beer actualBeer = repository.getBeerById(4);
        Assert.assertEquals(expected, actualBeer);
    }

    @Test
    public void update_beer_should_update_database() throws SQLException, EntityNotFoundException {
        Beer newBeer = new Beer(4, "Cristal", 5, 1.8f);

        int affectedRows = repository.updateBeer(newBeer);
        Beer actualBeer = repository.getBeerById(4);

        Assert.assertEquals(newBeer, actualBeer);
        Assert.assertEquals(actualBeer, 1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete_beer_should_delete_the_beer() throws SQLException, EntityNotFoundException{
        Beer getBeer = repository.getBeerById(4);

        System.out.println(repository.deleteBeer(4));
        repository.getBeerById(4);
    }
}
