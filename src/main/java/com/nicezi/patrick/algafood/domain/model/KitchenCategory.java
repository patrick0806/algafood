package com.nicezi.patrick.algafood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitchen_categories")
public class KitchenCategory {
    @Id
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public KitchenCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public KitchenCategory setName(String name) {
        this.name = name;
        return this;
    }
}
