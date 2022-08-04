package org.academiadecodigo.bootcamp.persistence.model.dao;

import org.academiadecodigo.bootcamp.persistence.model.Customer;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomerDao extends GenericDao {
    private Class<Customer> customer;

    public Customer findById(Integer id) {
        return em.find(customer, id);
    }

    public List<Customer> findAll() {

        CriteriaQuery<Customer> criteriaQuery = em.getCriteriaBuilder().createQuery(customer);
        Root<Customer> root = criteriaQuery.from(customer);
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     */
    public Customer saveOrUpdate(Customer modelObject) {
        return em.merge(modelObject);
    }

    /**
     *
     */
    public void delete(Integer id) {
        em.remove(em.find(customer, id));
    }
}
