package com.infopulse.connection;

import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final ConnectionFactory factory = new ConnectionFactory();
    private Jdbc3PoolingDataSource source = new Jdbc3PoolingDataSource();

    private ConnectionFactory() {

        source.setDataSourceName("A Data Source");
        source.setServerName("localhost");
        source.setDatabaseName("transaction");
        source.setUser("postgres");
        source.setPassword("qwertyui");
        source.setMaxConnections(10);
        try {
            new InitialContext().rebind("DataSource", source);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionFactory instance(){
        return factory;
    }

    public synchronized Connection getConnection() throws SQLException {
        return source.getConnection();
    }


}
