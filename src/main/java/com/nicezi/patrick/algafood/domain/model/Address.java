package com.nicezi.patrick.algafood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Address {
    @Column(name = "zip_code")
    private String zipCode;

    @Column
    private String street;

    @Column
    private String number;
    @Column
    private String complement;

    @Column
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
