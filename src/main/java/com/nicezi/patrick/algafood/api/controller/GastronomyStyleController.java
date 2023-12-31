package com.nicezi.patrick.algafood.api.controller;


import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import com.nicezi.patrick.algafood.domain.service.GastronomyStyleService;
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
@RequestMapping(path = "/gastronomy-styles")
public class GastronomyStyleController {
    final private GastronomyStyleService gastronomyStyleService;

    GastronomyStyleController(GastronomyStyleService gastronomyStyleService) {
        this.gastronomyStyleService = gastronomyStyleService;
    }

    @GetMapping
    public ResponseEntity<List<GastronomyStyle>> list() {
        final var gastronomyStyles = this.gastronomyStyleService.listAll();

        return ResponseEntity.ok(gastronomyStyles);
    }

    @GetMapping("/{gastronomyStyleId}")
    public ResponseEntity<GastronomyStyle> findById(@PathVariable Long gastronomyStyleId) {
        final var gastronomyStyle = this.gastronomyStyleService.findById(gastronomyStyleId);
        return ResponseEntity.ok(gastronomyStyle);

    }

    @PostMapping
    public ResponseEntity<GastronomyStyle> createGastronomyStyle(@RequestBody @Valid GastronomyStyle gastronomyStyle) {
        final var savedGastronomyStyle = this.gastronomyStyleService.save(gastronomyStyle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGastronomyStyle);
    }

    @PutMapping("/{gastronomyStyleId}")
    public ResponseEntity<GastronomyStyle> updateGastronomyStyle(
            @PathVariable Long gastronomyStyleId,
            @RequestBody @Valid GastronomyStyle gastronomyStyle) {

        final var currentGastronomyStyle = this.gastronomyStyleService.findById(gastronomyStyleId);

        BeanUtils.copyProperties(gastronomyStyle, currentGastronomyStyle, "id");

        final var savedGastronomyStyle = this.gastronomyStyleService.save(currentGastronomyStyle);

        return ResponseEntity.ok(savedGastronomyStyle);

    }

    @DeleteMapping("/{gastronomyStyleId}")
    public ResponseEntity<GastronomyStyle> deleteGastronomyStyle(@PathVariable Long gastronomyStyleId) {
        this.gastronomyStyleService.remove(gastronomyStyleId);
        return ResponseEntity.noContent().build();
        //for exceptions we can use the ResponseStatusException from spring
    }
}
