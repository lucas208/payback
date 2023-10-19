package com.payback.repositories;

import com.payback.domain.transaction.Transaction;
import com.payback.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
