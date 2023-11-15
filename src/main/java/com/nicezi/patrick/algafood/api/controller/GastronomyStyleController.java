package com.nicezi.patrick.algafood.api.controller;


import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import com.nicezi.patrick.algafood.domain.service.GastronomyStyleService;
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
public class GastronomyStyleController {
    final private GastronomyStyleService gastronomyStyleService;

    GastronomyStyleController(GastronomyStyleService gastronomyStyleService){
        this.gastronomyStyleService = gastronomyStyleService;
    }

    @GetMapping
    public ResponseEntity<List<GastronomyStyle>> list(){
        final var kitchens = this.gastronomyStyleService.listAll();

        return ResponseEntity.ok(kitchens);
    }

    @GetMapping("/{gastronomyStyleId}")
    public ResponseEntity<GastronomyStyle> findById(@PathVariable Long gastronomyStyleId){
       try{
           final var gastronomyStyle = this.gastronomyStyleService.findById(gastronomyStyleId);
           return ResponseEntity.ok(gastronomyStyle);
       }catch(EntityNotFoundException ex){
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<GastronomyStyle> createGastronomyStyle(@RequestBody  GastronomyStyle gastronomyStyle){
        final var savedGastronomyStyle = this.gastronomyStyleService.save(gastronomyStyle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGastronomyStyle);
    }

    @PutMapping("/{gastronomyStyleId}")
    public ResponseEntity<GastronomyStyle> updateGastronomyStyle(
            @PathVariable Long gastronomyStyleId,
            @RequestBody  GastronomyStyle gastronomyStyle){
        try{
            final var currentGastronomyStyle = this.gastronomyStyleService.findById(gastronomyStyleId);

            BeanUtils.copyProperties(gastronomyStyle, currentGastronomyStyle, "id");

            final var savedGastronomyStyle = this.gastronomyStyleService.save(currentGastronomyStyle);

            return ResponseEntity.ok(savedGastronomyStyle);
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{gastronomyStyleId}")
    public ResponseEntity<GastronomyStyle> deleteGastronomyStyle(@PathVariable Long gastronomyStyleId){
        try{
            this.gastronomyStyleService.remove(gastronomyStyleId);
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
