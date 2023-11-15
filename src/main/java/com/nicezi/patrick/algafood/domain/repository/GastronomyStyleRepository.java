package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;

import java.util.List;

public interface GastronomyStyleRepository {
    List<GastronomyStyle> list();
    GastronomyStyle findById(Long id);
    GastronomyStyle save(GastronomyStyle gastronomyStyle);
    void remove(Long id);
}
