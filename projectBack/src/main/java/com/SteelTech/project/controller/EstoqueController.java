package com.SteelTech.project.controller;

import com.SteelTech.project.entity.Clientes;
import com.SteelTech.project.entity.Estoque;
import com.SteelTech.project.repository.ClienteRepository;
import com.SteelTech.project.repository.EstoqueRepository;
import com.SteelTech.project.services.ClientesService;
import com.SteelTech.project.services.EstoqueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    private EstoqueServices estoqueServices;


    @PostMapping
    public ResponseEntity<Estoque> criarEstoque(@RequestBody Estoque estoque){
        Estoque novoEstoque = estoqueServices.salvarEstoque(estoque);
        return new ResponseEntity<>(estoque, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoque() {
        List<Estoque> estoques = estoqueServices.listarTodos();
        return new ResponseEntity<>(estoques, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estoqueServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public Estoque editar(@PathVariable Long id, @RequestBody Estoque novoEstoque) {
        return estoqueRepository.findById(id)
                .map(estoque -> {
                    estoque.setProduto(novoEstoque.getProduto());
                    estoque.setSaldo(novoEstoque.getSaldo());
                    return estoqueServices.salvarEstoque(estoque);
                })
                .orElseThrow(() -> new RuntimeException("estoque não encontrado"));
    }
}
