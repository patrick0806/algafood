package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.Restaurant;
import com.nicezi.patrick.algafood.domain.repository.RestaurantRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQuery {
    @PersistenceContext
    private EntityManager manager;

    public List<Restaurant> find(String name, BigDecimal initialTax, BigDecimal finalTax){
        CriteriaBuilder queryBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> query = queryBuilder.createQuery(Restaurant.class);
        Root<Restaurant> root = query.from(Restaurant.class);

        final var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasLength(name)){
            predicates.add(queryBuilder.like(root.get("name"), "%"+name+"%"));
        }

        if(initialTax != null){
            predicates.add(queryBuilder.greaterThanOrEqualTo(root.get("deliveryTax"), initialTax));
        }

        if(finalTax !=null){
            predicates.add(queryBuilder.lessThanOrEqualTo(root.get("deliveryTax"), finalTax));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return manager.createQuery(query).getResultList();
        /*How make a dynamic query with jpql
        var jpql = new StringBuilder();
        var parameters = new HashMap<String, Object>();
        jpql.append("from Restaurant where 0 = 0 ");

        if(StringUtils.hasLength(name)){
            jpql.append("and name like :name ");
            parameters.put("name","%" + name + "%");
        }

        if(initialTax != null){
            jpql.append("and deliveryTax >= :initialTax ");
            parameters.put("initialTax", initialTax);
        }

        if(finalTax != null) {
            jpql.append("and deliveryTax <= :finalTax ");
            parameters.put("finalTax", finalTax);
        }

        TypedQuery<Restaurant> query =  manager.createQuery(jpql.toString(), Restaurant.class);

        parameters.forEach(query::setParameter);

        return  query.getResultList();*/
    }
}
