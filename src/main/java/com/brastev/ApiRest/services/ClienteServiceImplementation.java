package com.brastev.ApiRest.services;

import com.brastev.ApiRest.models.Cliente;
import com.brastev.ApiRest.repositories.ClienteRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImplementation implements ClienteServices{
    @Autowired
    private ClienteRep repository;
    @Override
    public List<Cliente> findAll() {

        return (List<Cliente>) repository.findAll();
    }

    @Override
    public Cliente findById(Long Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void delete(Long Id) {
        repository.deleteById(Id);
    }
}
