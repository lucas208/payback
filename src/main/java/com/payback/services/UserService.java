package com.payback.services;

import com.payback.domain.user.User;
import com.payback.domain.user.UserType;
import com.payback.dtos.UserDTO;
import com.payback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User remetente, BigDecimal valor) throws Exception {
        if(remetente.getUserType() == UserType.LOJISTA){
            throw new Exception("Lojista não pode realizar transação!");
        }

        if(remetente.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente para realizar transação!");
        }
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }

    public User createUser(UserDTO userDTO){
        User user = new User(userDTO);
        saveUser(user);
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
