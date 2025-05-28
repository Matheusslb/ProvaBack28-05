package com.SteelTech.project.controller;

import com.SteelTech.project.entity.Estoque;
import com.SteelTech.project.entity.Pedido;
import com.SteelTech.project.repository.EstoqueRepository;
import com.SteelTech.project.repository.PedidoRepository;
import com.SteelTech.project.services.EstoqueServices;
import com.SteelTech.project.services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    private PedidoServices pedidoServices;


    @PostMapping
    public ResponseEntity<Pedido> criarEstoque(@RequestBody Pedido pedido){
        Pedido novoPedido = pedidoServices.salvarPedido(pedido);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedido() {
        List<Pedido> pedidos = pedidoServices.listarTodos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public Pedido editar(@PathVariable Long id, @RequestBody Pedido novoPedido) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setProduto(novoPedido.getProduto());
                    pedido.setClientes(novoPedido.getClientes());
                    pedido.setStatus(novoPedido.getStatus());
                    pedido.setData(novoPedido.getData());
                    return pedidoServices.salvarPedido(pedido);
                })
                .orElseThrow(() -> new RuntimeException("pedido não encontrado"));
    }
}
