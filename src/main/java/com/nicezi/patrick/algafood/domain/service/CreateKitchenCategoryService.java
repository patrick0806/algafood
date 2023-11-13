package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.model.KitchenCategory;
import com.nicezi.patrick.algafood.domain.repository.KitchenCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateKitchenCategoryService {
    final private KitchenCategoryRepository kitchenCategoryRepository;

    CreateKitchenCategoryService(KitchenCategoryRepository kitchenCategoryRepository){
        this.kitchenCategoryRepository = kitchenCategoryRepository;
    }

    public KitchenCategory execute(KitchenCategory kitchenCategory){
        return this.kitchenCategoryRepository.save(kitchenCategory);
    }
}
