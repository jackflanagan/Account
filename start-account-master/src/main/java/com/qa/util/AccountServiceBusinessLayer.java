package com.qa.util;



import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.domain.RepositoryLayer;

public class AccountServiceBusinessLayer implements DBImplementation {
	
	

		private static final Logger LOGGER = Logger.getLogger(DBImplementation.class);

		@Inject
		private RepositoryLayer rp;

	

	public String getAllAccounts() {
		LOGGER.info("wtf");
		return rp.getAllAccount();
	}

	@Override
	public String addAccount(String ac) {
		
		return rp.createAccount(ac);
	}
	@Override
	public String updateAccount() {
		return rp.updateAccount();
	}
	@Override
	public String deleteAccout() {
		return rp.deleteAccount();
	}
	
	public void setRepoLayer(RepositoryLayer rp) {
		this.rp = rp;
	}}
