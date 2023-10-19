package com.payback.dtos;

import com.payback.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String nome, String sobrenome, String cpf, BigDecimal saldo, String email, String senha, UserType userType) {
}
