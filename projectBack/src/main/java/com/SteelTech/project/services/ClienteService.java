package com.SteelTech.project.services;

import com.SteelTech.project.entity.Clientes;
import com.SteelTech.project.entity.Produto;
import com.SteelTech.project.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Clientes salvarProduto(Clientes clientes) {
        return clienteRepository.save(clientes);
    }
    public List<Clientes> listarTodos() {
        return clienteRepository.findAll();
    }
    public Clientes buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("clientes não encontrado"));
    }

    public Clientes atualizar(Long id, Clientes clientesAtualizado) {
        Clientes clientes = buscarPorId(id);
        clientes.setNome(clientesAtualizado.getNome());

        return clienteRepository.save(clientes);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }


}
