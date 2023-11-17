package com.nicezi.patrick.algafood.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicezi.patrick.algafood.domain.exception.BusinessException;
import com.nicezi.patrick.algafood.domain.exception.ValidationException;
import com.nicezi.patrick.algafood.domain.model.Restaurant;
import com.nicezi.patrick.algafood.domain.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
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
    final private SmartValidator validator;

    RestaurantController(RestaurantService restaurantService, SmartValidator validator) {
        this.restaurantService = restaurantService;
        this.validator = validator;
    }


    @GetMapping
    public ResponseEntity<List<Restaurant>> list() {
        final var restaurants = this.restaurantService.listAll();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long restaurantId) {
        final var restaurant = this.restaurantService.findById(restaurantId);
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Restaurant restaurant) {

        final var savedRestaurant = this.restaurantService.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);

    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody @Valid Restaurant restaurant) {
        var currentRestaurant = this.restaurantService.findById(restaurantId);

        BeanUtils.copyProperties(restaurant, currentRestaurant,
                "id", "paymentMethods", "address", "creation_date", "products");
        currentRestaurant = this.restaurantService.save(currentRestaurant);
        return ResponseEntity.ok(currentRestaurant);

    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long restaurantId) {
        this.restaurantService.remove(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> filedsToUpdate, HttpServletRequest request) {
        var currentRestaurant = this.restaurantService.findById(restaurantId);

        if (currentRestaurant == null) {
            return ResponseEntity.notFound().build();
        }

        this.mergeFieldsInObject(filedsToUpdate, currentRestaurant, request);
        validate(currentRestaurant, "restaurant");
        currentRestaurant = this.restaurantService.save(currentRestaurant);
        return ResponseEntity.ok(currentRestaurant);

    }

    private void mergeFieldsInObject(Map<String, Object> fields, Restaurant restaurant, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,true);

            Restaurant restaruantUpdateData = objectMapper.convertValue(fields, Restaurant.class);
            fields.forEach((fieldName, fieldValue) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, fieldName);
                field.setAccessible(true); // turn private variable accessible

                Object newValue = ReflectionUtils.getField(field, restaruantUpdateData);
                ReflectionUtils.setField(field, restaurant, newValue);
            });
        }catch (IllegalArgumentException ex){
            Throwable rootCause = ExceptionUtils.getRootCause(ex);
            throw new HttpMessageNotReadableException(ex.getMessage(),rootCause, serverHttpRequest);
        }
    }

    private void validate(Restaurant restaurant,String objectName){
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant,objectName);
        validator.validate(restaurant, bindingResult);

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
    }
}
