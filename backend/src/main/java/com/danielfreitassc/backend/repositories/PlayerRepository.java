package com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>{
    
}
