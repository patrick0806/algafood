package com.nicezi.patrick.algafood.api.controller;


import com.nicezi.patrick.algafood.domain.model.KitchenCategory;
import com.nicezi.patrick.algafood.domain.repository.KitchenCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<KitchenCategory>> list(){
        final var kitchens = this.kitchenCategoryRepository.list();

        return ResponseEntity.ok(kitchens);
    }

    @GetMapping("/{kitchenCategoryId}")
    public ResponseEntity<KitchenCategory> findById(@PathVariable Long kitchenCategoryId){
        final var kitchenCategory = this.kitchenCategoryRepository.findById(kitchenCategoryId);

        if(kitchenCategory == null){
            return ResponseEntity.notFound().build();
        };

        return ResponseEntity.ok(kitchenCategory);
    }

    @PostMapping
    public ResponseEntity<KitchenCategory> createKitchenCategory(@RequestBody  KitchenCategory kitchenCategory){
        final var savedKitchenCategory = this.kitchenCategoryRepository.save(kitchenCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedKitchenCategory);
    }
}
