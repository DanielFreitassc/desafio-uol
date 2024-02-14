package com.daniel.aluga.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.daniel.aluga.dtos.VeiculoRecordDTO;
import com.daniel.aluga.models.VeiculoEntity;
import com.daniel.aluga.repositories.VeiculoRepository;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    public ResponseEntity<VeiculoEntity> saveVeiculo(VeiculoRecordDTO veiculoRecordDTO) {
        VeiculoEntity veiculoEntity = new VeiculoEntity();
        BeanUtils.copyProperties(veiculoRecordDTO, veiculoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoRepository.save(veiculoEntity));
    }

    public ResponseEntity<List<VeiculoEntity>> getTipoVeiculo(String tipo) {
        List<VeiculoEntity> veiculoTipo = veiculoRepository.findByTipo(tipo);
        return ResponseEntity.status(HttpStatus.OK).body(veiculoTipo);
    }
}


