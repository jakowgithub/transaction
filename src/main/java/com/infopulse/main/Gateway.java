package com.infopulse.main;

import com.infopulse.dao.ClientDAO;
import com.infopulse.dao.DaoFactory;
import com.infopulse.dao.UtilDao;
import com.infopulse.entity.Client;
import com.infopulse.entity.Order;
import com.infopulse.exception.ConnectionException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Gateway {

    public static void main (String [] args){

        List<Client> clients = new CopyOnWriteArrayList<>();
        clients.clear();

        for (int i=1; i<6; i++){

            Client client = new Client((long) i, "Surname"+i);
            List<Order> orders = new CopyOnWriteArrayList<>();

            for (int j=1; j<6; j++){
                Order order = new Order((long) i+j, "custom"+i+j, client.getId());
                ((CopyOnWriteArrayList<Order>) orders).addIfAbsent(order);
            }
            client.setOrders(orders);

            clients.add(client);
        }
        clients.forEach(client ->  UtilDao.insertClientOrder(client));

        List<Client> clientsPull = UtilDao.pullAll();

        clientsPull.forEach(client -> System.out.println(client.getId()+" "+client.getName()));
            //client.getOrders().forEach(order -> System.out.println("                  " + order.getOrderName()));

    }
}
