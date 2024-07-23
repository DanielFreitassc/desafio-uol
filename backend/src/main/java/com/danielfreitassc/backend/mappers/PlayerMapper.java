package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.backend.dtos.PlayerDTO;
import com.danielfreitassc.backend.models.PlayerEntity;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDTO toDto(PlayerEntity playerEntity);
    PlayerEntity toEntity(PlayerDTO playerDTO);
}
 