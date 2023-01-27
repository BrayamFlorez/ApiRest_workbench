package com.brastev.ApiRest.controllers;

import com.brastev.ApiRest.models.Cliente;
import com.brastev.ApiRest.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteServices service;
    @GetMapping("/usuario")
    public List<Cliente>listar(){
        return service.findAll();
    }
    @PostMapping("/usuario")
    public void crear(@RequestBody Cliente cliente){
        service.save(cliente);
    }
}
