package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import com.qa.domain.Account;

public class AccountService {

	private Map<Integer, Account> accountMap;

	private int count = 0;

	public AccountService() {
		accountMap = new HashMap<Integer, Account>();
	}

	public void addAccountFromMap(Account userAccount) {
		accountMap.put(count, userAccount);
		count++;
	}

	public void removeAccountFromMap(Integer accountToRemove) {
		boolean countExists = accountMap.containsKey(accountToRemove);
		if (countExists) {
			accountMap.remove(accountToRemove);
		}
	}

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
	}

}
