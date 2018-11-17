package com.infopulse.connection;

//import com.infopulse.exception.ConnectionException;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final ConnectionFactory factory = new ConnectionFactory();
    private HikariDataSource ds = new HikariDataSource();
    //private String jdbcDB,localhost, databaseName,useSSL, user, password ;


    private ConnectionFactory() {

        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/transaction");
        ds.setUsername("jakow");
        ds.setPassword("pass1");

//        jdbcDB="jdbc:postgresql://";
//        localhost = "localhost/";
//        databaseName="transaction?";
//        useSSL = "useSSL=false&";
//        user="user=jakow&";
//        password = "password=pass1";

//       try { Class.forName("org.postgresql.Driver");
//           } catch ( ClassNotFoundException e) { throw new ConnectionException(e); }
   }

    public static ConnectionFactory instance(){ return factory ; }

    public synchronized Connection getConnection() throws SQLException {
        return ds.getConnection();
             //DriverManager.getConnection(jdbcDB+ localhost+databaseName+useSSL+user+password);
    }
}
