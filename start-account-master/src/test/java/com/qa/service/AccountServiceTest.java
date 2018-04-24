package com.qa.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	private AccountService service;
	private Account joeBloggs;
	private Account janeBloggs;
	private JSONUtil util;

	@Before
	public void init() {
		service = new AccountService();
		joeBloggs = new Account("0","Joe", "Bloggs", "1234");
		janeBloggs = new Account("1","Jane", "Bloggs", "1234");
		util = new JSONUtil();
	}

	@Test
	public void addAndRemoveAccountTest() {
		service.addAccountFromMap(joeBloggs);
		Assert.assertEquals(service.getAccountMap().size(), 1);
		service.addAccountFromMap(janeBloggs);
		Assert.assertEquals(service.getAccountMap().size(), 2);
		service.removeAccountFromMap(0);
		Assert.assertEquals(service.getAccountMap().size(), 1);
		service.removeAccountFromMap(1);
		Assert.assertEquals(service.getAccountMap().size(), 0);
		service.removeAccountFromMap(5);
		Assert.assertEquals(service.getAccountMap().size(), 0);
	}
/*
	@Test
	public void accountConversionToJSONTest() {
		String emptyMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals("{}", emptyMap);
		String accountAsJSON = "{\"0\":{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"},\"1\":{\"firstName\":\"Jane\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"}}";
		Assert.assertEquals("{}", emptyMap);
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		String populatedAccountMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals(accountAsJSON, populatedAccountMap);
	}
*/
	@Test
	public void getCountForFirstNamesInAccount() {
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 0);
		service.addAccountFromMap(joeBloggs);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 1);
		Account joeGordon = new Account("4","Joe", "Jimmy", "1234");
		service.addAccountFromMap(joeGordon);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 2);
	}

}
