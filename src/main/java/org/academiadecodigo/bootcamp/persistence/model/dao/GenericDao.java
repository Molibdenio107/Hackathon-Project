package org.academiadecodigo.bootcamp.persistence.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericDao{

    @PersistenceContext
    protected EntityManager em;
    /**
     *
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
