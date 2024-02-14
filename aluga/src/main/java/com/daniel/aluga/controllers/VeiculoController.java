package com.daniel.aluga.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.aluga.dtos.VeiculoRecordDTO;
import com.daniel.aluga.models.VeiculoEntity;
import com.daniel.aluga.services.VeiculoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoEntity> saveVeiculo(@RequestBody @Valid VeiculoRecordDTO veiculoRecordDTO) {
        return veiculoService.saveVeiculo(veiculoRecordDTO);
    }
    
    @GetMapping("{tipo}")
    public ResponseEntity<List<VeiculoEntity>> getTipoVeiculo(@PathVariable(value = "tipo") String tipo) {
        return veiculoService.getTipoVeiculo(tipo);
    }
}
