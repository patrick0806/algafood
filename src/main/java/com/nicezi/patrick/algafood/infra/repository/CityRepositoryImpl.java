package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.City;
import com.nicezi.patrick.algafood.domain.repository.CityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<City> list() {
        TypedQuery<City> query = manager.createQuery("from City", City.class);

        return query.getResultList();
    }

    @Override
    public City findById(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        final var manegedEntity = manager.find(City.class,id);
        manager.remove(manegedEntity);
    }
}
