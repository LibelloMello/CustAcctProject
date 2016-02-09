package org.ics.facade;

import javax.ejb.Remote;

import org.ics.ejb.Account;
import org.ics.ejb.Customer;
import org.ics.exceptions.MyAccountException;

@Remote
public interface FacadeRemote {
	public Account findByAccountId(String id);

	public Account openAccount(String accountNbr, String accType, double amount, long customerId);

	public Account updateAccount(Account account);

	public void deleteAccount(String nbr);

	public void deposit(String accountNumber, double amount) throws MyAccountException;

	public void withdraw(String accountNumber, double amount) throws MyAccountException;

	public Customer findByCustomerId(long id);

	public Customer createCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public void deleteCustomer(long id);

}
