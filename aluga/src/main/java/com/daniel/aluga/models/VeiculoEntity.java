package com.daniel.aluga.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "veiculos")
public class VeiculoEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String cidade;
    private BigDecimal valor;
    
    @Column(name = "alugado_em", columnDefinition = "TIMESTAMP")
    private LocalDateTime alugadoEm;

    @PrePersist
    protected void onCreate() {
        alugadoEm = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}