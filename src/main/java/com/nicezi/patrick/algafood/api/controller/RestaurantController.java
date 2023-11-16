package com.nicezi.patrick.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.Restaurant;
import com.nicezi.patrick.algafood.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

           BeanUtils.copyProperties( restaurant, currentRestaurant,
                   "id","paymentMethods","address","creation_date","products");
           currentRestaurant = this.restaurantService.save(currentRestaurant);
           return ResponseEntity.ok(currentRestaurant);
       }catch (EntityNotFoundException ex){
           return ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long restaurantId){
        try{
            this.restaurantService.remove(restaurantId);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        catch (EntityInUseException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> filedsToUpdate){
        var currentRestaurant = this.restaurantService.findById(restaurantId);

        if(currentRestaurant == null){
            return ResponseEntity.notFound().build();
        }

        this.mergeFieldsInObject(filedsToUpdate, currentRestaurant);

        currentRestaurant = this.restaurantService.save(currentRestaurant);
        return ResponseEntity.ok(currentRestaurant);

    }

    private void mergeFieldsInObject(Map<String,Object> fields, Restaurant restaurant){
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaruantUpdateData = objectMapper.convertValue(fields, Restaurant.class);
        fields.forEach((fieldName, fieldValue)->{
            Field field = ReflectionUtils.findField(Restaurant.class, fieldName);
            field.setAccessible(true); // turn private variable accessible

            Object newValue = ReflectionUtils.getField(field,restaruantUpdateData);
            ReflectionUtils.setField(field, restaurant, newValue);
        });
    }
}
