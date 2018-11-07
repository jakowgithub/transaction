package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@Setter
//@Getter
//@NoArgsConstructor
public class Client {
   private Long id;
   private String name;
   private List<Order> orders;

   public Client(Long id, String name) {
      this.id = id;
      this.name = name;
   }

   public Client(){}

   public Long getId() { return id; }

   public void setId(Long id) { this.id = id; }

   public String getName() { return name; }

   public void setName(String name) { this.name = name; }

   public List<Order> getOrders() { return orders; }

   public void setOrders(List<Order> orders) { this.orders = orders; }

}
