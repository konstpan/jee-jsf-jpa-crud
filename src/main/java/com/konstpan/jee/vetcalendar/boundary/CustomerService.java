package com.konstpan.jee.vetcalendar.boundary;

import com.konstpan.jee.vetcalendar.entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Panos
 */
@Stateless
public class CustomerService {
	
	@PersistenceContext(unitName = "myPU")
	EntityManager em;
	
	public void create(Customer customer) {
		em.persist(customer);
	}
	
}
