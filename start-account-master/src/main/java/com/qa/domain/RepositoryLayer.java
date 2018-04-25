package com.qa.domain;

import java.util.Collection;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.util.JSONUtil;


@Transactional(SUPPORTS)
@Default
public class RepositoryLayer {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager; 
	
	@Inject
	private JSONUtil util;
	
	
	public String getAllAccount() {
		Query query = manager.createQuery("Select a FROM ACCOUNT a");
		Collection <Account> ac = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(ac);
		
		
	}
	
	
	
	@Transactional(REQUIRED)
	public String createAccount(String ac) {
		Collection <Account> ac1 = (Collection<Account>) util.getObjectForJSON(ac, Account.class);
		//Account createAccount1 = util.getObjectForJSON(ac, Account.class);
		manager.persist(ac1);
		return "{\"message\": \"account sucessfully added\"}";
		
	}
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		if (accountToUpdate != null) {
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}
	
	/*
	@Transactional(REQUIRED)
	public String deleteAccount() {
		TypedQuery<Account> query = manager.createQuery("DELETE FROM ACCOUNT WHERE ID = 1", Account.class);
		//query.executeUpdate();
		Collection <Account> ac = (Collection<Account>) query.getResultList();
	
		return "{\"message\": \"account sucessfully deleted\"}";
		
	}
	*/
	
	@Transactional(REQUIRED)
	public String deleteanAccount(Long id) {
		Account accountInDB = findAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}
	
	private Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}
}
