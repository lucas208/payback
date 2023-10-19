package com.payback.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String senha;

    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
