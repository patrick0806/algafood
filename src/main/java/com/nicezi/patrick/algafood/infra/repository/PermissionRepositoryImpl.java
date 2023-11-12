package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.Permission;
import com.nicezi.patrick.algafood.domain.repository.PermissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PermissionRepositoryImpl implements PermissionRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Permission> list() {
        TypedQuery<Permission> query = manager.createQuery("from Permission", Permission.class);

        return query.getResultList();
    }

    @Override
    public Permission findById(Long id) {
        return manager.find(Permission.class, id);
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        return manager.merge(permission);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        final var manegedEntity = manager.find(Permission.class,id);
        manager.remove(manegedEntity);
    }
}
