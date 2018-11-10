package com.infopulse.dao.impl;

import com.infopulse.connection.ConnectionWrap;
import com.infopulse.connection.TransactionFactory;
import com.infopulse.dao.OrderDAO;
import com.infopulse.entity.Order;
import com.infopulse.exception.DataBaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {

    public  OrderDAOImpl(){}

    private  TransactionFactory factory = com.infopulse.connection.TransactionFactory.transactionFactory();


    @Override
    public void createOrder(Order order, Long clientId) {
        //todo: implementation
        ConnectionWrap connectionWrap = factory.getConnection();

        try {
            PreparedStatement preparedStatementDell = connectionWrap.preparedStatement("delete from orders * ; ");
            preparedStatementDell.executeUpdate();

            PreparedStatement preparedStatement = connectionWrap.preparedStatement("insert into orders values(?,?,?)");

            preparedStatement.setLong(1, order.getId());
            preparedStatement.setString(2, order.getOrderName());
            preparedStatement.setLong(3, clientId);

            preparedStatement.executeUpdate();
            //preparedStatement.execute();

            connectionWrap.commit();

        } catch (SQLException e) { try { connectionWrap.rollBack();
                                  } catch (SQLException sqlException) {throw new DataBaseException(sqlException);}
                                   throw new DataBaseException(e);

        }finally { try { connectionWrap.close();
                  } catch (SQLException e) { throw new DataBaseException(e); }
        }

    }
}
