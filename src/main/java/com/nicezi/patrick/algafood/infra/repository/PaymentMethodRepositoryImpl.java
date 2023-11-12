package com.nicezi.patrick.algafood.infra.repository;

import com.nicezi.patrick.algafood.domain.model.PaymentMethod;
import com.nicezi.patrick.algafood.domain.repository.PaymentMethodRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<PaymentMethod> list() {
        TypedQuery<PaymentMethod> query = manager.createQuery("from PaymentMethod", PaymentMethod.class);

        return query.getResultList();
    }

    @Override
    public PaymentMethod findById(Long id) {
        return manager.find(PaymentMethod.class, id);
    }

    @Override
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return manager.merge(paymentMethod);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        final var manegedEntity = manager.find(PaymentMethod.class,id);
        manager.remove(manegedEntity);
    }
}
