package com.danielfreitassc.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.PlayerDTO;
import com.danielfreitassc.backend.services.PlayerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
    
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    
    @PostMapping
    public PlayerDTO create(@RequestBody @Valid PlayerDTO playerDTO) {
        return playerService.create(playerDTO);
    }
    
    @GetMapping
    public List<PlayerDTO> getAll() {
        return playerService.getAll();
    }
}
