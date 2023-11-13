package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.KitchenCategory;
import com.nicezi.patrick.algafood.domain.repository.KitchenCategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class KitchenCategoryRepositoryImpl implements KitchenCategoryRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<KitchenCategory> list() {
        TypedQuery<KitchenCategory> query = manager.createQuery("from KitchenCategory", KitchenCategory.class);

        return query.getResultList();
    }

    @Override
    public KitchenCategory findById(Long id) {
        return manager.find(KitchenCategory.class, id);
    }

    @Override
    @Transactional
    public KitchenCategory save(KitchenCategory kitchenCategory) {
        return manager.merge(kitchenCategory);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        final var manegedEntity = manager.find(KitchenCategory.class,id);

        if(manegedEntity == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(manegedEntity);
    }
}
