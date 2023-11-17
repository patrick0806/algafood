package com.nicezi.patrick.algafood.api.controller;

import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.State;
import com.nicezi.patrick.algafood.domain.service.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    final private StateService stateService;

    StateController(StateService stateService) {
        this.stateService = stateService;
    }


    @GetMapping
    public ResponseEntity<List<State>> list() {
        final var states = this.stateService.listAll();
        return ResponseEntity.ok(states);
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> findById(@PathVariable Long stateId) {
        final var state = this.stateService.findById(stateId);
        return ResponseEntity.ok(state);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid State state) {

        final var savedState = this.stateService.save(state);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedState);

    }

    @PutMapping("/{stateId}")
    public ResponseEntity<?> update(@PathVariable Long stateId, @RequestBody @Valid State state) {

        var currentState = this.stateService.findById(stateId);

        BeanUtils.copyProperties(state, currentState, "id");
        currentState = this.stateService.save(currentState);
        return ResponseEntity.ok(currentState);

    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<State> deleteState(@PathVariable Long stateId) {
        this.stateService.remove(stateId);
        return ResponseEntity.noContent().build();
    }
}
