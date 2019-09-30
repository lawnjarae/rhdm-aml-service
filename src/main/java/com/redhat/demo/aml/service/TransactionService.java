package com.redhat.demo.aml.service;

import com.redhat.demo.aml.components.DatabasePopulator;
import com.redhat.demo.aml.model.TransactionEntity;
import com.redhat.demo.aml.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository repository;

    @Autowired
    DatabasePopulator dbPopulator;

    public List<TransactionEntity> getAllTransactions() {
        List<TransactionEntity> transactionList = repository.findAll();

        if(transactionList.size() > 0) {
            return transactionList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<TransactionEntity> getTransactionsById(Long id) {
        List<TransactionEntity> transactions = repository.findByCustId(id);

        return transactions;
    }

    public List<Long> getDistinctCustIds() {
        List<Long> custIds = repository.getDistinctCustId();

        return custIds;
    }

    public TransactionEntity createTransaction(TransactionEntity entity)
    {
        entity = repository.save(entity);
        return entity;
    }

    public List<TransactionEntity> createBulkTransactions(int numTransactions) {
        ArrayList<TransactionEntity> list = new ArrayList<>();
        for (int i = 0; i < numTransactions; i++) {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setAmount(ThreadLocalRandom.current().nextDouble(500, 20000));
            transaction.setCustId(ThreadLocalRandom.current().nextLong(1, 99 + 1));
            transaction.setCustomerCountry(dbPopulator.countries[ThreadLocalRandom.current().nextInt(dbPopulator.countries.length)]);
            transaction.setCustomerIndustry(dbPopulator.industries[ThreadLocalRandom.current().nextInt(dbPopulator.industries.length)]);
            list.add(createTransaction(transaction));
        }

        return list;
    }
}
