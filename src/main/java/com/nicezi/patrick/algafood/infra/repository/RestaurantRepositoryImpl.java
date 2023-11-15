package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.Restaurant;
import com.nicezi.patrick.algafood.domain.repository.RestaurantRepository;
import com.nicezi.patrick.algafood.domain.repository.RestaurantRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQuery {
    @PersistenceContext
    private EntityManager manager;

    public List<Restaurant> find(String name, BigDecimal initialTax, BigDecimal finalTax){
        var jpql = "from Restaurant where name like :name " +
                "and deliveryTax between :initialTax and : finalTax";

        return manager.createQuery(jpql, Restaurant.class)
                .setParameter("name","%" + name + "%")
                .setParameter("initialTax",initialTax)
                .setParameter("finalTax",finalTax)
                .getResultList();
    }
}
