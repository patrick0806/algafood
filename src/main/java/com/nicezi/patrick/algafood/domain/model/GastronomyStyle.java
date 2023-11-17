package com.nicezi.patrick.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicezi.patrick.algafood.config.validation.Groups;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "gastronomy_styles")
public class GastronomyStyle {
    @NotNull(groups = Groups.GastronomyStyleId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "gastronomyStyle")
    private List<Restaurant> restaurants = new ArrayList<>();
}
