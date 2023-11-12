package com.nicezi.patrick.algafood.api.controller;

import com.nicezi.patrick.algafood.domain.model.State;
import com.nicezi.patrick.algafood.domain.repository.StateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/states")
public class StateCotroller {
    private StateRepository stateRepository;
    StateCotroller(StateRepository stateRepository){
        this.stateRepository = stateRepository;
    }

    @GetMapping
    public List<State> list(){
        return this.stateRepository.list();
    }
}
