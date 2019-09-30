package com.redhat.demo.aml.components;

import com.redhat.demo.aml.model.TransactionEntity;
import com.redhat.demo.aml.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DatabasePopulator {

    @Autowired
    TransactionService service;

    public static final String countries[] = {"USA", "USA", "USA", "USA", "USA", "USA", "USA", "USA",
            "USA", "USA", "USA", "USA", "USA", "USA", "USA", "USA",
            "Mexico", "Canada", "Belize", "Bahamas", "France", "England"};

    //"Saudi Arabia", "Iran", "Pakistan", "Russia", "North Korea", "Indonesia", "Germany"};

    public static final String industries[] = {"Software", "Utilities", "Telco", "Healthcare"};

    //"Manufacturing", "Financial",

    private final int minValue = 500;
    private final int maxValue = 5000;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 99; i++) {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setAmount(ThreadLocalRandom.current().nextDouble(minValue, maxValue));
            transaction.setCustId(new Long(i + 1));
            transaction.setCustomerCountry(countries[ThreadLocalRandom.current().nextInt(countries.length)]);
            transaction.setCustomerIndustry(industries[ThreadLocalRandom.current().nextInt(industries.length)]);
            service.createTransaction(transaction);
        }

        for (int i = 0; i <= 900; i++) {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setAmount(ThreadLocalRandom.current().nextDouble(minValue, maxValue));
            transaction.setCustId(ThreadLocalRandom.current().nextLong(1, 99 + 1));
            if (i % 100 == 0) {
                transaction.setCustomerCountry("Iran");
            }
            else {
                transaction.setCustomerCountry(countries[ThreadLocalRandom.current().nextInt(countries.length)]);
            }
            transaction.setCustomerIndustry(industries[ThreadLocalRandom.current().nextInt(industries.length)]);
            service.createTransaction(transaction);
        }
    }
}
