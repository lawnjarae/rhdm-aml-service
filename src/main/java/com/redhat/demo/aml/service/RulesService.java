package com.redhat.demo.aml.service;

import com.redhat.demo.aml.components.KjarDeployer;
import com.redhat.demo.aml.model.TransactionEntity;
import com.redhat.demo.aml_rules.RuleResult;
import com.redhat.demo.aml_rules.Transaction;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Service
public class RulesService {
    @Autowired
    TransactionService transactionService;

    @Autowired
    KjarDeployer kjarDeployer;

    public List<String> runRulesOnCustomer(Long custId) {
        ArrayList<String> retval = new ArrayList<>();
        List<TransactionEntity> transactions = transactionService.getTransactionsById(custId);

        if (!transactions.isEmpty()) {
            KieSession kieSession = kjarDeployer.kieContainer.newKieSession();

            for (TransactionEntity transaction : transactions) {
                Transaction t = new Transaction();
                t.setCustId(transaction.getCustId());
                t.setAmount(transaction.getAmount());
                t.setCustomerCountry(transaction.getCustomerCountry());
                t.setCustomerIndustry(transaction.getCustomerIndustry());
                kieSession.insert(t);
            }

            kieSession.fireAllRules();
            Collection<?> objects = kieSession.getObjects(new ClassObjectFilter(RuleResult.class));
//            ArrayList<RuleResult> results = new ArrayList<RuleResult>(objects);
//            ArrayList<RuleResult> results2 = objects.stream().collect(toCollection(ArrayList::new));

            RuleResult rr;
            Iterator itr = objects.iterator();
            while (itr.hasNext()) {
                rr = (RuleResult) itr.next();
                System.out.println(rr);
                retval.add("custId: " + transactions.get(0).getCustId() + " - " + rr.getMessage());
            }
//            for (int i = 0; i < objects.size(); i++) {
//                rr = (RuleResult) objects.iterator().next();
//                System.out.println(rr);
//                retval.add("custId: " + transactions.get(0).getCustId() + " - " + rr.getMessage());
//            }

            kieSession.dispose();
        }

        return retval;
    }

}
