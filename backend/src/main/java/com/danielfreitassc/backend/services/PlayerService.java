package com.danielfreitassc.backend.services;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.PlayerDTO;
import com.danielfreitassc.backend.mappers.PlayerMapper;
import com.danielfreitassc.backend.models.PlayerEntity;
import com.danielfreitassc.backend.repositories.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final CodenameService codenameService;
    
    public PlayerDTO create(PlayerDTO playerDTO) {
        PlayerEntity playerEntity = playerMapper.toEntity(playerDTO);
        System.out.println("Player group: " + playerDTO.group());
        String codename = codenameService.getUniqueCodename(playerDTO.group());

        if(codename != null) {
            playerEntity.setCodename(codename);
        } else {
            return null;
        }
        
        return playerMapper.toDto(playerRepository.save(playerEntity));
    }

    public List<PlayerDTO> getAll() {
        return playerRepository.findAll().stream().map(playerMapper::toDto).toList();
    }
}
