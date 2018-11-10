package com.infopulse.dao;

import com.infopulse.connection.ConnectionWrap;
import com.infopulse.connection.TransactionFactory;
import com.infopulse.entity.Client;
import com.infopulse.entity.Order;
import com.infopulse.exception.DataBaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UtilDao {

    public static void insertClientOrder(Client c){

        TransactionFactory factory =  TransactionFactory.transactionFactory();
        DaoFactory daoFactory = DaoFactory.getInstance();
        ClientDAO clientDAO = daoFactory.getClientDao();
        OrderDAO orderDAO = daoFactory.getOrderDao();

        factory.beginTransaction();

        List<Order> orders = c.getOrders();

        Client createdClient = clientDAO.insertClient(c);

        orders.forEach(order -> orderDAO.createOrder(order, createdClient.getId()));

        factory.endTransaction();
    }

    public static List<Client> pullAll() {
        //todo:implementation
        TransactionFactory factory =  TransactionFactory.transactionFactory();
        factory.beginTransaction();
        ConnectionWrap connectionWrap = factory.getConnection();
        Client createdClient;
        List <Client> clients = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connectionWrap.preparedStatement("select * from clients");
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                createdClient = new Client();
                createdClient.setId(rs.getLong(1));
                createdClient.setName(rs.getString(2));
                clients.add(createdClient);
            }
        } catch (SQLException e){throw new DataBaseException(e);}

        finally { try { connectionWrap.close();
        } catch (SQLException e) { throw new DataBaseException(e); }
        }
        return clients;

    }
}
