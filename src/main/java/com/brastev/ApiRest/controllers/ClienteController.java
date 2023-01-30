package com.brastev.ApiRest.controllers;

import com.brastev.ApiRest.models.Cliente;
import com.brastev.ApiRest.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String,Object>> crear(@RequestBody Cliente cliente){
        Cliente c = null;
        Map<String,Object> response = new HashMap<>();
        try {
            c = service.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar la creacion en base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje: ", "El usuario se creo con exito");
        response.put("Cliente: ", cliente);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?>show(@PathVariable Long id){
        Cliente c = null;
        Map<String,Object> response = new HashMap<>();
        try{
            c = service.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje: ", "error al realizar la consulta en base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (c == null){
            response.put("Mensaje","el cliente con ID: ".concat(" ").concat("No existe"));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(c,HttpStatus.OK);
    }
    @PutMapping("usuario/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente c, @PathVariable Long id){
    Cliente clienteActual = service.findById(id);
    Cliente clienteUpdate = null;
    Map<String, Object> response = new HashMap<>();

    if (clienteActual==null){
        response.put("Mensaje","el cliente con ID: ".concat(" ").concat("No existe"));
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
    }

    try {
        clienteActual.setNombre(c.getNombre());
        clienteActual.setApellido(c.getApellido());
        clienteUpdate= service.save(clienteActual);

    }catch (DataAccessException e) {
        response.put("mensaje: ", "error al realizar actualizacion en la base de datos");
        response.put("error: ", e.getMessage());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    response.put("Mensaje","cliente editado con exito");
    response.put("Cliente",clienteUpdate);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            service.delete(id);
        }catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar eliminacion en la base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje","cliente eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
