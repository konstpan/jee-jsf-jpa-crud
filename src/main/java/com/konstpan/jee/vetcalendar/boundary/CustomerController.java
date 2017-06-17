package com.konstpan.jee.vetcalendar.boundary;

import com.konstpan.jee.vetcalendar.entity.Customer;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Panos
 */
@Named
@RequestScoped
public class CustomerController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

    private Customer customer;

    private ResourceBundle bundle;

    @Inject
    CustomerService customerService;

    @PostConstruct
    public void init() {
        bundle = ResourceBundle.getBundle("messages");
    }

    public String create() {
        customerService.create(customer);
        customer = null;

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("createdCustomerMessage"),
                null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);

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

    public List<Customer> getCustomerList() {
        return customerService.findAll();
    }

}
