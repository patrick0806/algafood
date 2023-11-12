package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.KitchenCategory;

import java.util.List;

public interface KitchenCategoryRepository {
    List<KitchenCategory> list();
    KitchenCategory findById(Long id);
    KitchenCategory save(KitchenCategory kitchenCategory);
    void remove(Long id);
}
