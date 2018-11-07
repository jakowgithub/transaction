package com.infopulse.dao;

import com.infopulse.connection.TransactionFactory;
import com.infopulse.entity.Client;
import com.infopulse.entity.Order;

import java.util.List;

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

//        for(Order order:orders){
//            orderDAO.createOrder(order, createdClient.getId());
//        }
        factory.endTransaction();

    }
}
