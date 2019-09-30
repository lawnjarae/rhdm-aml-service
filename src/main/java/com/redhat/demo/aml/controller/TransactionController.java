package com.redhat.demo.aml.controller;

import com.redhat.demo.aml.model.TransactionEntity;
import com.redhat.demo.aml.service.RulesService;
import com.redhat.demo.aml.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    RulesService rulesService;

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getAllEmployees() {
        List<TransactionEntity> list = transactionService.getAllTransactions();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionEntity>> getEmployeeById(@PathVariable("id") Long id) {
        List<TransactionEntity> list = transactionService.getTransactionsById(id);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Long>> getCustomerIds() {
        List<Long> list = transactionService.getDistinctCustIds();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionEntity transaction) {
        TransactionEntity updated = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/createTenTransactions")
    public ResponseEntity<List<TransactionEntity>> createTenTransactions() {
        List<TransactionEntity> list = transactionService.createBulkTransactions(10);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/runrules")
    public ResponseEntity<List<String>> runRules() {
        List<String> retval = new ArrayList<>();
        List<Long> custIds = transactionService.getDistinctCustIds();

        for (Long id : custIds) {
            retval.addAll(rulesService.runRulesOnCustomer(id));
        }

        return new ResponseEntity<>(retval, new HttpHeaders(), HttpStatus.OK);
    }
}
