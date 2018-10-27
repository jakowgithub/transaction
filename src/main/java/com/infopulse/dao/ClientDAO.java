package com.infopulse.dao;

import com.infopulse.entity.Client;

import java.util.List;

public interface ClientDAO {

    Client insertClient(Client c);

    List<Client> findAll();
}
