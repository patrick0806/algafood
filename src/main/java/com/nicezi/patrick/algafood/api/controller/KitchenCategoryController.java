package com.nicezi.patrick.algafood.api.controller;


import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.KitchenCategory;
import com.nicezi.patrick.algafood.domain.service.KitchenCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/kitchen-categories")
public class KitchenCategoryController {
    final private KitchenCategoryService kitchenCategoryService;

    KitchenCategoryController(KitchenCategoryService kitchenCategoryService){
        this.kitchenCategoryService = kitchenCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<KitchenCategory>> list(){
        final var kitchens = this.kitchenCategoryService.listAll();

        return ResponseEntity.ok(kitchens);
    }

    @GetMapping("/{kitchenCategoryId}")
    public ResponseEntity<KitchenCategory> findById(@PathVariable Long kitchenCategoryId){
       try{
           final var kitchenCategory = this.kitchenCategoryService.findById(kitchenCategoryId);
           return ResponseEntity.ok(kitchenCategory);
       }catch(EntityNotFoundException ex){
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<KitchenCategory> createKitchenCategory(@RequestBody  KitchenCategory kitchenCategory){
        final var savedKitchenCategory = this.kitchenCategoryService.save(kitchenCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedKitchenCategory);
    }

    @PutMapping("/{kitchenCategoryId}")
    public ResponseEntity<KitchenCategory> updateKitchenCategory(
            @PathVariable Long kitchenCategoryId,
            @RequestBody  KitchenCategory kitchenCategory){
        try{
            final var currentKitchenCategory = this.kitchenCategoryService.findById(kitchenCategoryId);

            BeanUtils.copyProperties(kitchenCategory, currentKitchenCategory, "id");

            final var savedKitchenCategory = this.kitchenCategoryService.save(currentKitchenCategory);

            return ResponseEntity.ok(savedKitchenCategory);
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{kitchenCategoryId}")
    public ResponseEntity<KitchenCategory> deleteKitchenCategory(@PathVariable Long kitchenCategoryId){
        try{
            this.kitchenCategoryService.remove(kitchenCategoryId);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        catch (EntityInUseException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
