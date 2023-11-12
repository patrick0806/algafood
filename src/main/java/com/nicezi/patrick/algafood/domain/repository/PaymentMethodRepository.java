package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {
    List<PaymentMethod> list();
    PaymentMethod findById(Long id);
    PaymentMethod save(PaymentMethod city);
    void remove(Long id);
}
