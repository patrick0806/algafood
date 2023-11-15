package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQuery {
    List<Restaurant> find(String name, BigDecimal initialTax, BigDecimal finalTax);

    List<Restaurant> findWithFreeDeliveryTax(String name);
}
