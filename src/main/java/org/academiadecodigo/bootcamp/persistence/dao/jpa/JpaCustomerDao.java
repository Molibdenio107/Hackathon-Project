package org.academiadecodigo.bootcamp.persistence.dao.jpa;

import org.academiadecodigo.bootcamp.persistence.dao.CustomerDao;
import org.academiadecodigo.bootcamp.persistence.model.Customer;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link CustomerDao} implementation
 */
@Repository
public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaCustomerDao() {
        super(Customer.class);
    }
}
