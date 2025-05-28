package com.SteelTech.project.services;

import com.SteelTech.project.entity.Clientes;
import com.SteelTech.project.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Clientes salvarCliente(Clientes clientes) {
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
        clientes.setEndereco(clientesAtualizado.getEndereco());
        clientes.setContatos(clientesAtualizado.getContatos());
        clientes.setPedidos(clientesAtualizado.getPedidos());

        return clienteRepository.save(clientes);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }


}
