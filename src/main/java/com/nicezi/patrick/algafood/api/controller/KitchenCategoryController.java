package com.nicezi.patrick.algafood.api.controller;


import com.nicezi.patrick.algafood.domain.model.KitchenCategory;
import com.nicezi.patrick.algafood.domain.repository.KitchenCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/kitchen-categories")
public class KitchenCategoryController {
    private KitchenCategoryRepository kitchenCategoryRepository;

    KitchenCategoryController(KitchenCategoryRepository kitchenCategoryRepository){
        this.kitchenCategoryRepository = kitchenCategoryRepository;
    }

    @GetMapping
    public List<KitchenCategory> list(){
        return this.kitchenCategoryRepository.list();
    }
}
