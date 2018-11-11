package com.infopulse.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static final ConnectionFactory factory = new ConnectionFactory();
   // private Jdbc3PoolingDataSource source = new Jdbc3PoolingDataSource();
    private String jdbcDB,localhost, databaseName,useSSL, user, password ;


    private ConnectionFactory() {

//        source.setDataSourceName("A Data Source");
//        source.setServerName("localhost");
//        source.setDatabaseName("transaction");
//        source.setUser("jakow");
//        source.setPassword("pass1");
//        source.setMaxConnections(10);

        jdbcDB="jdbc:postgresql://";
        localhost = "localhost/";
        databaseName="transaction?";
        useSSL = "useSSL=false&";
        user="user=jakow&";
        password = "password=pass1";

        try {
            Class.forName("org.postgresql.Driver");
           // new InitialContext().rebind("DataSource", source);
        } catch ( ClassNotFoundException e) { throw new RuntimeException(e); }
    }

    public static ConnectionFactory instance(){
        return factory ;
    }

    public synchronized Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcDB+ localhost+databaseName+useSSL+user+password);
                //source.getConnection();
    }

}
