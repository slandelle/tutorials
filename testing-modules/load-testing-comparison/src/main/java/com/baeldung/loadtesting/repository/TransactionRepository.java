package com.baeldung.loadtesting.repository;

import com.baeldung.loadtesting.model.Transaction;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TransactionRepository {

    private AtomicInteger transactionIds = new AtomicInteger();
    private final Map<Integer, Transaction> transactions = new ConcurrentHashMap<>();

    public List<Transaction> findByCustomerRewardsId(Integer rewardsId) {
        return transactions.values().stream().filter(transaction -> rewardsId.equals(transaction.getCustomerRewardsId())).collect(Collectors.toList());
    }

    public Transaction save(Transaction trnsctn) {
        if (trnsctn.getId() == null) {
            trnsctn.setId(transactionIds.getAndIncrement());
        }
        transactions.put(trnsctn.getId(), trnsctn);
        return trnsctn;
    }
}
