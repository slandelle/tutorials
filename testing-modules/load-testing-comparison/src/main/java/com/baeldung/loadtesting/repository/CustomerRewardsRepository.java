package com.baeldung.loadtesting.repository;

import com.baeldung.loadtesting.model.CustomerRewardsAccount;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CustomerRewardsRepository {

  private AtomicInteger accountIds = new AtomicInteger();
	private final Map<Integer, CustomerRewardsAccount> accounts = new ConcurrentHashMap<>();

    public Optional<CustomerRewardsAccount> findByCustomerId(Integer customerId) {
    	return accounts.values().stream().filter(account -> account.getCustomerId().equals(customerId)).findFirst();
    }

  public CustomerRewardsAccount save(CustomerRewardsAccount account) {
    if (account.getId() == null) {
      account.setId(accountIds.getAndIncrement());
    }
    accounts.put(account.getId(), account);
    return account;
  }

  public List<CustomerRewardsAccount> findAll() {
      return accounts.values().stream().collect(Collectors.toList());
  }
}
