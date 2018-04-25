package com.qa.service;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.domain.RepositoryLayer;
import com.qa.util.JSONUtil;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {
	
	private Account a;
	
	@InjectMocks
	private RepositoryLayer repo;
	
	@Mock
	private EntityManager manager;
	
	@Mock
	private Query query;

	private JSONUtil util;
	
	private static final String MOCK_DATA_ARRAY = "[{\"id\":\"0\",\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";
	
	

	private static final String MOCK_OBJECT = "[{\"id\":\"0\",\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";
	
	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	@Test
	public void testAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		ArrayList<Account> acc = new ArrayList<Account>();
		acc.add(new Account("0","John","Doe","1234"));
		Mockito.when(query.getResultList()).thenReturn(acc);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllAccount());
	}
	
	@Test
	public void testCreateAccount() {
		
		
		String reply = repo.createAccount(MOCK_DATA_ARRAY);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully added\"}");
	}
	

	@Test
	public void testUpdateAccount() {
		String reply = repo.updateAccount(1L, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully updated\"}");
	}
	
	@Test
	public void testDeleteanAccount() {
		String reply = repo.deleteanAccount(1L);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully deleted\"}");
	}
	
	
	

}
