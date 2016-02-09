package org.ics.facade;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.ics.eao.AccountEAOLocal;
import org.ics.eao.CustomerEAOLocal;
import org.ics.ejb.Account;
import org.ics.ejb.Customer;
import org.ics.exceptions.MyAccountException;

/**
 * Session Bean implementation class Facade
 */
@Stateless
@LocalBean
public class Facade implements FacadeRemote, FacadeLocal {

	@EJB
	CustomerEAOLocal customerEAO;
	@EJB
	AccountEAOLocal accountEAO;

	/**
	 * Default constructor.
	 */
	public Facade() {
		// TODO Auto-generated constructor stub
	}

	public Account findByAccountId(String id) {
		return accountEAO.findByAccountNbr(id);
	}

	public Account openAccount(String accountNbr, String accType, double amount, long customerId) {
		Account newAccount = null;

		Customer c = this.findByCustomerId(customerId);

		if (c != null && !accountNbr.equals("")) {
			newAccount = new Account();
			newAccount.setAccountNbr(accountNbr);
			newAccount.setAccountType(accType);
			newAccount.setBalance(amount);
			newAccount = accountEAO.createAccount(newAccount);

			newAccount.setCustomer(c);
			customerEAO.updateCustomer(c);
		}
		return newAccount;
	}

	public Account updateAccount(Account account) {
		return accountEAO.updateAccount(account);
	}

	public void deleteAccount(String nbr) {
		accountEAO.deleteAccount(nbr);
	}

	public void deposit(String accountNumber, double amount) throws MyAccountException {
		try {
			accountEAO.deposit(accountNumber, amount);
		} catch (MyAccountException e) {
			throw e;
		}
	}

	public void withdraw(String accountNumber, double amount) throws MyAccountException {
		try {
			accountEAO.withdraw(accountNumber, amount);
		} catch (MyAccountException e) {
			throw e;
		}
	}

	public Customer findByCustomerId(long id) {
		return customerEAO.findByCustomerId(id);
	}

	public Customer createCustomer(Customer customer) {
		return customerEAO.createCustomer(customer);
	}

	public Customer updateCustomer(Customer customer) {
		return customerEAO.updateCustomer(customer);
	}

	public void deleteCustomer(long id) {
		customerEAO.deleteCustomer(id);
	}

}
