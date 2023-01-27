package com.brastev.ApiRest.services;

import com.brastev.ApiRest.models.Cliente;

import java.util.List;

public interface ClienteServices{
    public List<Cliente> findAll();
    public Cliente findById(Long Id);
    public Cliente save(Cliente cliente);
    public void delete(Long Id);

}
