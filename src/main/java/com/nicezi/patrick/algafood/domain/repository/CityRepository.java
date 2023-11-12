package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.City;

import java.util.List;

public interface CityRepository {
    List<City> list();
    City findById(Long id);
    City save(City city);
    void remove(Long id);
}
