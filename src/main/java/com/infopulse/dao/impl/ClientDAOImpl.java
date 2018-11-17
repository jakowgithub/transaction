package com.infopulse.dao.impl;

import com.infopulse.connection.ConnectionWrap;
import com.infopulse.connection.TransactionFactory;
import com.infopulse.dao.ClientDAO;
import com.infopulse.entity.Client;

import com.infopulse.entity.Order;
import com.infopulse.exception.DataBaseException;
import com.infopulse.main.Gateway;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientDAOImpl implements ClientDAO {

    private  static TransactionFactory factory = TransactionFactory.transactionFactory();
    private boolean flagDellClient =true;

    public ClientDAOImpl(){ }

    public Client insertClient(Client c) {

        ConnectionWrap connectionWrap = factory.getConnection();

        Client createdClient =null;

        try {
            if (flagDellClient) {

                flagDellClient = false;
                PreparedStatement preparedStatementDell = connectionWrap.preparedStatement("delete from clients; ");
                preparedStatementDell.execute();
            }
            PreparedStatement preparedStatement = connectionWrap.preparedStatement("insert into clients values(?,?)");

            preparedStatement.setLong(1, c.getId());
            preparedStatement.setString(2, c.getName());

            preparedStatement.execute();

            connectionWrap.commit();

            preparedStatement = connectionWrap.preparedStatement("select * from clients where name=?");

            preparedStatement.setString(1, c.getName());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                createdClient = new Client();
                createdClient.setId(rs.getLong(1));
                createdClient.setName(rs.getString(2));
            }
            return createdClient;

        } catch (SQLException e)
                { try {
                        connectionWrap.rollBack();
                } catch (SQLException sqlException) { throw new DataBaseException(sqlException); }
            throw new DataBaseException(e);

        }finally { try {
                   connectionWrap.close();
                  } catch (SQLException e) { throw new DataBaseException(e); }
        }
    }

    @Override
    public List<Client> findAll() {
        //todo:implementation

        ConnectionWrap connectionWrap = factory.getConnection();
        Client createdClient =null;
        List <Client> clients = new CopyOnWriteArrayList <> ();
        return clients;
    }

    public   static  List<Client> pullAllClient() {
        //todo:implementation
        ConnectionWrap connectionWrap = factory.getConnection();
        Client createdClient;
        List <Client> clients = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connectionWrap.preparedStatement("select * from clients");
            ResultSet rsClients = preparedStatement.executeQuery();

            while (rsClients.next()){
                createdClient = new Client();
                createdClient.setId(rsClients.getLong(1));
                createdClient.setName(rsClients.getString(2));

                List <Order> orders = new ArrayList<>();
                preparedStatement = connectionWrap.preparedStatement("select * from orders where clientid=?");
                preparedStatement.setLong(1, createdClient.getId());
                ResultSet rsOrders = preparedStatement.executeQuery();

                while (rsOrders.next()){
                  Order createdOrder = new Order();
                  createdOrder.setId(rsOrders.getLong(1));
                  createdOrder.setOrderName(rsOrders.getString(2));
                  createdOrder.setClientId(rsOrders.getLong(3));
                  orders.add(createdOrder);
                }
                createdClient.setOrders(orders);
                clients.add(createdClient);
            }
        } catch (SQLException e){throw new DataBaseException(e);}

        finally { try { connectionWrap.close();
                } catch (SQLException e) { throw new DataBaseException(e); }
        }
        return clients;
    }
}
