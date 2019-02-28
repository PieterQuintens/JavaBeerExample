package be.pxl.student;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

	App app;

	@Before
	public void setUp() throws Exception {
		app = new App("192.168.33.22", "StudentDB", "admin", "admin");
	}

	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}

	@Test
	public void it_should_return_hello() {
		Assert.assertEquals("Hello", app.sayHello());
	}

	@Test
	public void it_should_return_hello_world() {
		Assert.assertEquals("Hello World", app.sayHelloWorld());
	}

	@Test
	public void it_should_create_jdbc_url() {
		String servername = "someservername";
		String databasename = "somedatabasename";
		Assert.assertEquals("jdbc:mysql://someservername/somedatabasename", app.createJdbcUrl(servername, databasename));
	}

	@Test
	public void it_should_have_a_valid_connection() throws SQLException {
		Connection connection = app.getConnection();
		Assert.assertTrue(connection.isValid(0));
	}

	@Test
	public void it_should_return_result_list() throws SQLException {
		List<String> beers = app.getBeerNames();
		Assert.assertFalse(beers.isEmpty());
	}
}