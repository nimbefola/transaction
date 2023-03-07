package com.pentspace.transactionservice.service;

import com.pentspace.transactionservice.entities.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction create(Transaction transaction);
    Transaction getById(String transactionId);
    List<Transaction> getAll();
    Transaction updateTransactionStatus(String transactionId, String status);
    String validate(String id, String otp);

}
