package be.pxl.student;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {

    @Test
    public void should_return_a_valid_in_memory_connection() throws SQLException {
        Connection connection = ConnectionFactory.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:StudentDB.sql';MODE=MySQL",
                null,
                null);
        Assert.assertTrue(connection.isValid(0));
    }

    // This is not a unit test but an integration test. Other people may have a different database server.
    @Ignore
    @Test
    public void should_have_valid_connection() throws Exception {
        Connection connection = ConnectionFactory.getConnection(
                Utils.createJdbcUrl("localhost:3306", "studentdb"),
                "root",
                "root");
        Assert.assertTrue(connection.isValid(0));

    }
}
