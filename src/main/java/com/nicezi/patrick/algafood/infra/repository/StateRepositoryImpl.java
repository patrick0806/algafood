package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.State;
import com.nicezi.patrick.algafood.domain.repository.StateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StateRepositoryImpl implements StateRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<State> list() {
        TypedQuery<State> query = manager.createQuery("from State", State.class);

        return query.getResultList();
    }

    @Override
    public State findById(Long id) {
        return manager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return manager.merge(state);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        final var manegedEntity = manager.find(State.class,id);
        manager.remove(manegedEntity);
    }
}
