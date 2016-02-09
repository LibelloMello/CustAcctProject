package org.ics.eao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ics.ejb.Customer;

/**
 * Session Bean implementation class CustomerEAOImpl
 */
@Stateless

public class CustomerEAOImpl implements CustomerEAOLocal {

	@PersistenceContext(unitName = "LabEJBSql")
	private EntityManager em;

	public CustomerEAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public Customer findByCustomerId(long id) {
		return em.find(Customer.class, id);
	}

	public Customer createCustomer(Customer customer) {
		em.persist(customer);
		return customer;
	}

	public Customer updateCustomer(Customer customer) {
		em.merge(customer);
		return customer;
	}

	public void deleteCustomer(long id) {
		Customer a = this.findByCustomerId(id);
		if (a != null) {
			em.remove(a);
		}

		/**
		 * Default constructor.
		 */

	}
}
