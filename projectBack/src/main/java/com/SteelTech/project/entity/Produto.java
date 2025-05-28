package com.SteelTech.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipoAco;
    private String especificacao;
    private int quantidade;
    private double preco;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;



}
