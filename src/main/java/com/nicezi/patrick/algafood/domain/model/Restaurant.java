package com.nicezi.patrick.algafood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

    public Long getId() {
        return id;
    }

    public Restaurant setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getDeliveryTax() {
        return deliveryTax;
    }

    public Restaurant setDeliveryTax(BigDecimal deliveryTax) {
        this.deliveryTax = deliveryTax;
        return this;
    }
}
