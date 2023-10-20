package com.payback.services;

import com.payback.domain.transaction.Transaction;
import com.payback.domain.user.User;
import com.payback.dtos.TransactionDTO;
import com.payback.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User remetente = userService.findUserById(transactionDTO.remetenteId());
        User destinatario = userService.findUserById(transactionDTO.destinatarioId());

        userService.validateTransaction(remetente, transactionDTO.valor());

//        boolean autorizado = authorizeTransaction(remetente, transactionDTO.valor());
//
//        if(!autorizado) {
//            throw new Exception("Transação não autorizada");
//        }

        Transaction transaction = new Transaction();
        transaction.setValor(transactionDTO.valor());
        transaction.setRemetente(remetente);
        transaction.setDestinatario(destinatario);
        transaction.setTimestamp(LocalDateTime.now());

        remetente.setSaldo(remetente.getSaldo().subtract(transactionDTO.valor()));
        destinatario.setSaldo(destinatario.getSaldo().add(transactionDTO.valor()));

        transactionRepository.save(transaction);
        userService.saveUser(remetente);
        userService.saveUser(destinatario);

//        notificationService.enviaNotificacao(remetente, "Transação realizada com sucesso");
//        notificationService.enviaNotificacao(destinatario, "Transação recebida com sucesso");

        return transaction;
    }

    public  boolean authorizeTransaction(User remetente, BigDecimal value) {
       ResponseEntity<Map> authResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

       if(authResponse.getStatusCode() == HttpStatus.OK) {
           String message = (String) authResponse.getBody().get("message");
           return "Autorizado".equalsIgnoreCase(message);
       } else return false;
    }
}
