package com.konstpan.jee.vetcalendar.boundary;

import com.konstpan.jee.vetcalendar.entity.Customer;
import com.konstpan.jee.vetcalendar.entity.Pet;

import java.util.List;
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
    
    public List<Customer> findAll() {
        return em.createQuery("SELECT o FROM Customer o", Customer.class).getResultList();
    }

    public void update(Customer customer) {
        em.merge(customer);
    }

    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }
}
