package com.infopulse.main;

import com.infopulse.dao.UtilDao;
import com.infopulse.entity.Client;
import com.infopulse.entity.Order;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Gateway {

    public static void main (String [] args){

        List<Client> clients = new CopyOnWriteArrayList<>();

        for (int i=1; i<6; i++){

            Client client = new Client((long) i, "SurnameName"+i);

            List<Order> orders = new CopyOnWriteArrayList<>();

            for (int j=1; j<6; j++){
                Order order = new Order((long) j, "custom"+j, (long) i);
                ((CopyOnWriteArrayList<Order>) orders).addIfAbsent(order);
            }
            client.setOrders(orders);

            ((CopyOnWriteArrayList<Client>) clients).addIfAbsent(client);
        }
        clients.forEach(client -> {

            UtilDao.insertClientOrder(client);

            client.getOrders().forEach(order->
                    System.out.println(client.getId()+" "+client.getName()+" " + order.getOrderName()));
                });


    }
}
