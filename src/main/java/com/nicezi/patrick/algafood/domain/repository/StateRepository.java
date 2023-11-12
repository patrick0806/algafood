package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.State;

import java.util.List;

public interface StateRepository {
    List<State> list();
    State findById(Long id);
    State save(State city);
    void remove(Long id);
}
