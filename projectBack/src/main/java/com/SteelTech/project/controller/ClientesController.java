package com.SteelTech.project.controller;

import com.SteelTech.project.entity.Clientes;
import com.SteelTech.project.repository.ClienteRepository;
import com.SteelTech.project.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Clientes")
public class ClientesController {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private ClientesService clientesService;


    @PostMapping
    public ResponseEntity<Clientes> CriarProduto(@RequestBody Clientes clientes){
        Clientes novoCliente = clientesService.salvarCliente(clientes);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Clientes>> listarClientes() {
        List<Clientes> clientes = clientesService.listarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clientesService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public Clientes editar(@PathVariable Long id, @RequestBody Clientes novoCliente) {
        return clienteRepository.findById(id)
                .map(clientes -> {
                    clientes.setNome(novoCliente.getNome());
                    clientes.setEndereco(novoCliente.getEndereco());
                    clientes.setContatos(novoCliente.getContatos());
                    clientes.setPedidos(novoCliente.getPedidos());
                    return clienteRepository.save(clientes);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}
