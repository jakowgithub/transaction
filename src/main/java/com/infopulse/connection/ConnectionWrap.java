package com.infopulse.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrap {
    private Connection con;
    private boolean transactionFlag=false;

    public ConnectionWrap(Connection con, boolean flag){

        this.con = con;
        this.transactionFlag = flag;
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException { return con.prepareStatement(sql); }

    Connection getConnection() {
        return con;
    }

    public void commit() throws SQLException {
        if(!transactionFlag)  con.commit();
    }

    public void rollBack() throws SQLException {
        if(!transactionFlag)  con.rollback();
    }

    public void close() throws SQLException {
        if(!transactionFlag)    con.close();
    }

}
