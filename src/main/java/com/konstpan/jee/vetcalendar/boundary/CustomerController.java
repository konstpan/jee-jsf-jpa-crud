package com.konstpan.jee.vetcalendar.boundary;

import com.konstpan.jee.vetcalendar.entity.Customer;
import com.konstpan.jee.vetcalendar.entity.Pet;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
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

    private Pet pet;

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

    public String addPet() {
        Customer cust = customerService.findCustomerById(customer.getId());
        cust.getPets().add(pet);
        customerService.update(cust);
        pet = null;

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("createdPetMessage"),
                null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);

        return "index";
    }

    public String edit(Long id) {
        customer = customerService.findCustomerById(id);

        return "editCustomer";
    }

    public String update() {
        LOGGER.info("Updating customer id " + customer.getId());

        customerService.update(customer);
        customer = null;

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("updatedCustomerMessage"),
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

    public Pet getPet() {
        if (pet == null) {
            pet = new Pet();
        }
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<Customer> getCustomerList() {
        return customerService.findAll();
    }

}
