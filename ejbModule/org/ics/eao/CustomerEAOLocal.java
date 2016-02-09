package org.ics.eao;

import javax.ejb.Local;

import org.ics.ejb.Customer;

@Local
public interface CustomerEAOLocal {
	public Customer findByCustomerId(long id);

	public Customer createCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public void deleteCustomer(long id);
}
