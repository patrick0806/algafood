package com.nicezi.patrick.algafood.api.controller;

import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.City;
import com.nicezi.patrick.algafood.domain.service.CityService;
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
@RequestMapping("/cities")
public class CityController {
    final private CityService cityService;

    CityController(CityService cityService){
        this.cityService = cityService;
    }


    @GetMapping
    public ResponseEntity<List<City>> list(){
        final var cities = this.cityService.listAll();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> findById(@PathVariable Long cityId){
        try{    final var city = this.cityService.findById(cityId);
            return ResponseEntity.ok(city);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody City state){
        try{
            final var savedCity = this.cityService.save(state);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId,@RequestBody City state){
        try{
            var currentCity = this.cityService.findById(cityId);

            BeanUtils.copyProperties( state, currentCity, "id");
            currentCity = this.cityService.save(currentCity);
            return ResponseEntity.ok(currentCity);
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> deleteCity(@PathVariable Long cityId){
        try{
            this.cityService.remove(cityId);
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
