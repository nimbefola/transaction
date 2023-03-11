package com.pentspace.transactionservice.endpoints;

import com.pentspace.transactionservice.entities.Transaction;
import com.pentspace.transactionservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "transaction")
@CrossOrigin(origins = "*")
public class TransactionEndpoint {
    @Autowired
    private TransactionService transactionService;

    @PostMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.create(transaction));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Transaction> getById(@PathVariable("id") String id){
        return new ResponseEntity<>(transactionService.getById(id), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Transaction>> getAll(){
        return new ResponseEntity<>(transactionService.getAll(), HttpStatus.OK);
    }

    @PutMapping(path = "/status/update", produces = "application/json")
    public ResponseEntity<Transaction> updateTransactionStatus(@RequestParam("id") String id, @RequestParam("status") String status){
        return new ResponseEntity<>(transactionService.updateTransactionStatus(id, status), HttpStatus.OK);
    }

//    @PutMapping(path = "/deposit/status", produces = "application/json")
//    public ResponseEntity<String> getDepositStatus(@RequestParam("id") String id, @RequestParam("otp") String otp){
//        return new ResponseEntity<>(transactionService.validateDeposit(id, otp), HttpStatus.OK);
//    }

    @GetMapping(path = "/externalTransaction/{externalTransactionId}", produces = "application/json")
    public ResponseEntity<Transaction> getBySourceId(@PathVariable("externalTransactionId") String externalTransactionId){
        return new ResponseEntity<>(transactionService.getBySourceAccount(externalTransactionId), HttpStatus.OK);
    }

}
