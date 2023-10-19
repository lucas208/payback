package com.payback.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal valor, Long remetenteId, Long destinatarioId) {
}
