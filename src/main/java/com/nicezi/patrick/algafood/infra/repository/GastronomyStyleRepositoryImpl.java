package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import com.nicezi.patrick.algafood.domain.repository.GastronomyStyleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GastronomyStyleRepositoryImpl implements GastronomyStyleRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<GastronomyStyle> list() {
        TypedQuery<GastronomyStyle> query = manager.createQuery("from GastronomyStyle", GastronomyStyle.class);

        return query.getResultList();
    }

    @Override
    public GastronomyStyle findById(Long id) {
        return manager.find(GastronomyStyle.class, id);
    }

    @Override
    @Transactional
    public GastronomyStyle save(GastronomyStyle gastronomyStyle) {
        return manager.merge(gastronomyStyle);
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
