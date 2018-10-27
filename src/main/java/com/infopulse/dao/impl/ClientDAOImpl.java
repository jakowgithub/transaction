package com.infopulse.dao.impl;

import com.infopulse.connection.ConnectionWrap;
import com.infopulse.connection.TransactionFactory;
import com.infopulse.dao.ClientDAO;
import com.infopulse.entity.Client;
import com.infopulse.entity.Order;
import com.infopulse.exception.DataBaseException;
import com.infopulse.exception.TransactionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private TransactionFactory factory = TransactionFactory.transactionFactory();
    public ClientDAOImpl(){

    }
    public Client insertClient(Client c) {
        ConnectionWrap connectionWrap = factory.getConnection();
        String name = c.getName();
        Client createdClient =null;
        try {
            PreparedStatement preparedStatement = connectionWrap
                    .preparedStatement("insert into clients(name)values(?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connectionWrap.commit();
            preparedStatement = connectionWrap
                    .preparedStatement("select * from clients where name=?");
            preparedStatement.setString(1,c.getName());
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                createdClient = new Client();
                createdClient.setId(rs.getLong(1));
                createdClient.setName(rs.getString(2));
            }
            return createdClient;

        } catch (SQLException e) {
            try {
                connectionWrap.rollBack();
            } catch (SQLException sqlException) {
                throw new DataBaseException(sqlException);
            }
            throw new DataBaseException(e);
        }finally {
            try {
                connectionWrap.close();
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }


    }

    @Override
    public List<Client> findAll() {
        //todo:implementation
        return null;

    }
}
