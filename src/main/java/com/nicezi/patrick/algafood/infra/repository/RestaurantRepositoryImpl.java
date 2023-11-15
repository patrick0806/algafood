package com.nicezi.patrick.algafood.infra.repository;


import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import com.nicezi.patrick.algafood.domain.model.Restaurant;
import com.nicezi.patrick.algafood.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> list() {
        TypedQuery<Restaurant> query = manager.createQuery("from Restaurant", Restaurant.class);

        return query.getResultList();
    }

    @Override
    public Restaurant findById(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        final var manegedEntity = manager.find(GastronomyStyle.class,id);

        if(manegedEntity == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(manegedEntity);
    }
}
