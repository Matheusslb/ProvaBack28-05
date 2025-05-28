package com.SteelTech.project.services;

import com.SteelTech.project.entity.Pedido;
import com.SteelTech.project.entity.Produto;
import com.SteelTech.project.repository.PedidoRepository;
import com.SteelTech.project.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServices {
    @Autowired
    private PedidoRepository pedidoRepository;


    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pedido não encontrado"));
    }

    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = buscarPorId(id);
        pedido.setClientes(pedidoAtualizado.getClientes());
        pedido.setStatus(pedidoAtualizado.getStatus());
        pedido.setData(pedidoAtualizado.getData());
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }




}
