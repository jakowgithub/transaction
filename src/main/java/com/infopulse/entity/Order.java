package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
public class Order {
    Long id;
    String orderName;
    Long clientId;

    public Order(Long id, String orderName, Long clientId) {
        this.id = id;
        this.orderName = orderName;
        this.clientId = clientId;
    }

    public Order(){}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getOrderName() { return orderName; }

    public void setOrderName(String orderName) { this.orderName = orderName; }

    public Long getClientId() { return clientId; }

    public void setClientId(Long clientId) { this.clientId = clientId; }
}
