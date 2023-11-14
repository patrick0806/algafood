package com.nicezi.patrick.algafood.api.controller;

import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.Restaurant;
import com.nicezi.patrick.algafood.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    final private RestaurantService restaurantService;

    RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }


    @GetMapping
    public ResponseEntity<List<Restaurant>> list(){
        final var restaurants = this.restaurantService.listAll();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long restaurantId){
        try{    final var restaurant = this.restaurantService.findById(restaurantId);
        return ResponseEntity.ok(restaurant);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant){
        try{
            final var savedRestaurant = this.restaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId,@RequestBody Restaurant restaurant){
       try{
           var currentRestaurant = this.restaurantService.findById(restaurantId);

           BeanUtils.copyProperties( restaurant, currentRestaurant, "id");
           currentRestaurant = this.restaurantService.save(currentRestaurant);
           return ResponseEntity.ok(currentRestaurant);
       }catch (EntityNotFoundException ex){
           return ResponseEntity.notFound().build();
       }
    }
}
