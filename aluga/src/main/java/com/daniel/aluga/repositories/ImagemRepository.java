package com.daniel.aluga.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.aluga.models.ImagemEntity;

@Repository
public interface ImagemRepository extends JpaRepository<ImagemEntity, Long> {
    Optional<ImagemEntity> findByTipo(String tipo);
}
