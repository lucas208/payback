package com.payback.domain.user;

import com.payback.dtos.UserDTO;
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

    public User(UserDTO userDTO){
        this.nome = userDTO.nome();
        this.sobrenome = userDTO.sobrenome();
        this.cpf = userDTO.cpf();
        this.email = userDTO.email();
        this.senha = userDTO.senha();
        this.saldo = userDTO.saldo();
        this.userType = userDTO.userType();
    }
}
