package com.nicezi.patrick.algafood.api.controller;

import com.nicezi.patrick.algafood.domain.model.State;
import com.nicezi.patrick.algafood.domain.repository.StateRepository;
import org.springframework.http.MediaType;
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

    // the procues is using when you can return more than one type of resposne
    // like json and xml but with procudes you force return only json
    // this property can stay in top of class
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<State> list(){
        return this.stateRepository.list();
    }
}
