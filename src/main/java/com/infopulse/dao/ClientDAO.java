package com.infopulse.dao;

import com.infopulse.entity.Client;

import java.util.List;

public interface ClientDAO {

    void insertClient(Client c);

    List<Client> findAll();
}
