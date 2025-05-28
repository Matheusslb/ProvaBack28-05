package com.SteelTech.project.services;

import com.SteelTech.project.entity.Produto;
import com.SteelTech.project.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServices {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("produto não encontrado"));
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPorId(id);
        produto.setNome(produtoAtualizado.getNome());

        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }




}