package com.SteelTech.project.services;

import com.SteelTech.project.entity.Clientes;
import com.SteelTech.project.entity.Estoque;
import com.SteelTech.project.repository.ClienteRepository;
import com.SteelTech.project.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueServices {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Estoque salvarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }
    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }
    public Estoque buscarPorId(Long id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("clientes não encontrado"));
    }

    public Estoque atualizar(Long id, Estoque estoqueatualizado) {
        Estoque estoque = buscarPorId(id);
        estoque.setSaldo(estoqueatualizado.getSaldo());
        estoque.setProduto(estoqueatualizado.getProduto());


        return estoqueRepository.save(estoque);
    }

    public void deletar(Long id) {
        estoqueRepository.deleteById(id);
    }

}
