package com.infopulse.connection;

import com.infopulse.exception.ConnectionException;
import com.infopulse.exception.DataBaseException;
import com.infopulse.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactory {
    public static final TransactionFactory factory = new TransactionFactory();
    private ThreadLocal<ConnectionWrap> threadLocal = new ThreadLocal<>();
    private ConnectionFactory connectionFactory = ConnectionFactory.instance();

    public static TransactionFactory transactionFactory(){
        return factory;
    }

    public void beginTransaction()  {
        try {
            if(threadLocal.get() == null){
                Connection con = connectionFactory.getConnection();
                con.setAutoCommit(false);
                ConnectionWrap connectionWrap = new ConnectionWrap(con, true);
                threadLocal.set(connectionWrap);
            }
        } catch (SQLException e) { throw new ConnectionException("can not receive connection"); }
    }

    public ConnectionWrap getConnection(){

        if(threadLocal.get()!=null) return threadLocal.get();

        Connection con = null;
        try {
            con = connectionFactory.getConnection();
            con.setAutoCommit(false);
            ConnectionWrap connectionWrap = new ConnectionWrap(con,false);
            return  connectionWrap;
        } catch (SQLException e) { throw new ConnectionException("can not receive connection"); }
    }

    public void endTransaction(){
        if(threadLocal.get() == null) throw new TransactionException("thransaction can not be finished");

        ConnectionWrap connectionWrap = threadLocal.get();
        threadLocal.set(null);
        Connection con = connectionWrap.getConnection();
        try {
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
                throw new TransactionException(e);
            } catch (SQLException sqlException) { throw new DataBaseException(sqlException); }

        }finally {
            try {
                con.close();
            } catch (SQLException e) { throw new DataBaseException(e); }
        }
    }

}
