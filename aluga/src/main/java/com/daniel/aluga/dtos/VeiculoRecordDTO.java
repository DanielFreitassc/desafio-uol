package com.daniel.aluga.dtos;

import java.math.BigDecimal;
import java.util.Date;

public record VeiculoRecordDTO(String tipo , String cidade, BigDecimal valor, Date alugadoEm, Date devolvidoEm) {
    
}
