package com.pentspace.transactionservice.service.impl;

import com.pentspace.transactionservice.clients.EmailServiceClient;
import com.pentspace.transactionservice.entities.Transaction;
import com.pentspace.transactionservice.entities.enums.Status;
import com.pentspace.transactionservice.entities.repositories.TransactionRepository;
import com.pentspace.transactionservice.handler.impl.BaseHandler;
import com.pentspace.transactionservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class TransactionServiceImpl extends BaseHandler implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private EmailServiceClient emailServiceClient;

    @Override
    public Transaction create(Transaction transaction) {
        String otp = generateOTP();
        transaction.setOtp(otp);
        return transactionRepository.save(transaction);

    }

    @Override
    public Transaction getById(String transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(()->new NoSuchElementException("Transaction not found"));
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction updateTransactionStatus(String transactionId, String status) {
        Transaction transaction = getById(transactionId);
        transaction.setStatus(Status.valueOf(status));
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public String validateDeposit(String id, String otp) {
        Transaction transaction = getById(id);
        if(!transaction.getOtp().equalsIgnoreCase(otp)){
            log.info(" Input OTP [{}]", otp);
            return "Invalid OTP";
        }
        if(Objects.nonNull(transaction.getOtpStatus()) && transaction.getOtpStatus().equalsIgnoreCase(Status.APPROVED.toString())){
            return "OTP already used";
        }
        transaction.setStatus(Status.APPROVED);
        transaction.setOtpStatus(Status.APPROVED.toString());
        // Todo lets add expiry time to OTP
        transactionRepository.save(transaction);
        return "Successful";
    }

    @Override
    public Transaction getBySourceAccount(String sourceAccount) {
        return transactionRepository.findBySourceAccount(sourceAccount).orElse(new Transaction());
    }
}
