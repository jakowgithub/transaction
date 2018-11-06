package com.infopulse.dao;

import com.infopulse.entity.Order;

public interface OrderDAO {



    void createOrder(Order o, Long clientId);

}
