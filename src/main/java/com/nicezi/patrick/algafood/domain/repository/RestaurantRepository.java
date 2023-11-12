package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> list();
    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    void remove(Long id);
}
