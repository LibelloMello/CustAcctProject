package org.ics.eao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ics.ejb.Account;
import org.ics.exceptions.MyAccountException;

/**
 * Session Bean implementation class AccountEAOImpl
 */
@Stateless
@LocalBean
public class AccountEAOImpl implements AccountEAOLocal {

	@PersistenceContext(unitName = "LabEJBSql")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public AccountEAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public Account findByAccountNbr(String id) {
		return em.find(Account.class, id);
	}

	public Account createAccount(Account account) {
		em.persist(account);
		return account;
	}

	public Account updateAccount(Account account) {
		em.merge(account);
		return account;
	}

	public void deleteAccount(String id) {
		Account a = this.findByAccountNbr(id);
		if (a != null) {
			em.remove(a);
		}
	}

	public void deposit(String accountNumber, double amount) throws MyAccountException {
		Account account = this.findByAccountNbr(accountNumber);
		if (account != null) {
			if (amount > 0) {
				double new_balance = account.getBalance() + amount;
				account.setBalance(new_balance);
				this.updateAccount(account);
			} else {
				throw new MyAccountException("Deposit of negative amount is not allowed");
			}
		}

	}

	public void withdraw(String accountNumber, double amount) throws MyAccountException {
		Account account = this.findByAccountNbr(accountNumber);
		if (account != null) {
			if (amount > account.getBalance()) {
				throw new MyAccountException("Overdrawing the account. Withdraw was rejected.");
			} else if (amount < 0) {
				throw new MyAccountException("Withdraw of negative amount is not allowed");
			} else {
				double new_balance = account.getBalance() - amount;
				account.setBalance(new_balance);
				this.updateAccount(account);
			}
		}
	}
}
