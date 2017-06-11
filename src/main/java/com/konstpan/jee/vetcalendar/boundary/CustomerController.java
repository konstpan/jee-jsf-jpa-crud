package com.konstpan.jee.vetcalendar.boundary;

import com.konstpan.jee.vetcalendar.entity.Customer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Panos
 */
@Named
@SessionScoped
public class CustomerController implements Serializable {

	private Customer customer;

	@Inject
	CustomerService customerService;

	public CustomerController() {
	}

	public String create() {
		customerService.create(customer);
		customer = null;
		return "index";
	}

	public Customer getCustomer() {
		if (customer == null) {
			customer = new Customer();
		}
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
