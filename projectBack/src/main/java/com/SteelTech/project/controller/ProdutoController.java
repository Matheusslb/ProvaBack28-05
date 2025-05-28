package com.SteelTech.project.controller;

import com.SteelTech.project.entity.Produto;
import com.SteelTech.project.repository.ProdutoRepository;
import com.SteelTech.project.services.ProdutoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoServices produtoServices;


    @PostMapping
    public ResponseEntity<Produto>CriarProduto(@RequestBody Produto produto){
        Produto novoProduto = produtoServices.salvarProduto(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Produto>> listarMedicos() {
        List<Produto> produtos = produtoServices.listarTodos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        produtoServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
