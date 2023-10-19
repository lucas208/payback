package com.payback.domain.transaction;

import com.payback.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private User remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private User destinatario;

    private LocalDateTime timestamp;

}
