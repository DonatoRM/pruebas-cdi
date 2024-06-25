package es.donatodev.cdi.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.*;

import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/pruebasDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @DBConnection
    private Connection beanConnection() throws SQLException {
        return ds.getConnection();
    }

    private void destroyConnection(@Disposes @DBConnection Connection conn) throws SQLException {
        conn.close();
    }
}
