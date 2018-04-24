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
	
	public String findAccount() {
		TypedQuery<Account> query = manager.createQuery("Select a FROM ACCOUNT WHERE FIRSTNAME = 'John'", Account.class);
		Collection <Account> ac = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(ac);
	}
	
	@Transactional(REQUIRED)
	public String createAccount(String ac) {
		
		Account createAccount1 = util.getObjectForJSON(ac, Account.class);
		manager.persist(createAccount1);
		return "{\"message\": \"account sucessfully added\"}";
		
	}
	@Transactional(REQUIRED)
	public String updateAccount() {
		
		TypedQuery<Account> query = manager.createQuery("update ACCOUNT SET FIRSTNAME = 'JANE'  WHERE ID = 2", Account.class);
		query.executeUpdate();
		return "{\"message\": \"account sucessfully updated\"}";
		
	}
	@Transactional(REQUIRED)
	public String deleteAccount() {
		TypedQuery<Account> query = manager.createQuery("DELETE FROM ACCOUNT WHERE ID = 1", Account.class);
		query.executeUpdate();
		return "{\"message\": \"account sucessfully deleted\"}";
		
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}
	
	public static void hi() {
		
	}

}
