package com.infopulse.main;

import com.infopulse.dao.UtilDao;
import com.infopulse.dao.impl.ClientDAOImpl;
import com.infopulse.entity.Client;
import com.infopulse.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Gateway {

    public static void main (String [] args){

        List<Client> clients = new ArrayList<>();
        clients.clear();

        for (int i=1; i<4; i++){

            Client client = new Client((long) i, "Surname"+i);
            List<Order> orders = new CopyOnWriteArrayList<>();

            for (int j=1; j<4; j++){
                Order order = new Order((long)((10* i)+j), "custom"+i+j, client.getId());
                 orders.add(order);
            }
            client.setOrders(orders);

            clients.add(client);
        }

        clients.forEach(client -> {
            System.out.println(client.getId()+" "+client.getName());
            client.getOrders().forEach(order -> System.out.println("    create " + order.getOrderName()));
        });

        clients.forEach(UtilDao::insertClientOrder);

        List<Client> clientsPull = ClientDAOImpl.pullAllClient();

       clientsPull.forEach(client -> {
           System.out.println(client.getId()+" "+client.getName());
           client.getOrders().forEach(Order::printlnOrderName);
        });
    }
}
