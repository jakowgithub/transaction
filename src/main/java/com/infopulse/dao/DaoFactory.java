package com.infopulse.dao;

import com.infopulse.dao.impl.ClientDAOImpl;

public class DaoFactory {
    public static final DaoFactory factory = new DaoFactory();

    private DaoFactory(){}

    private ClientDAO clientDao =new ClientDAOImpl();
    private OrderDAO orderDao =new OrderDAOImpl();

    public static DaoFactory getInstance(){
        return factory;
    }

    public ClientDAO getClientDao(){
        return clientDao;
    }

    public OrderDAO getOrderDao(){
        return orderDao;
    }

}
