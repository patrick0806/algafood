package com.nicezi.patrick.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicezi.patrick.algafood.Groups;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank//(message = "Nome é obrigatório")
    private String name;

    //@DecimalMin("0")
    @NotNull
    @PositiveOrZero
    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

    @Valid// to enable deep validation(validate inner objects)
    @ConvertGroup(from = Default.class, to = Groups.GastronomyStyleId.class)
    @NotNull
    @ManyToOne
    private GastronomyStyle gastronomyStyle;

    @ManyToMany
    @JoinTable(
            name="restaurant_payment_methods",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name="payment_method_id")
    )
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    //@JsonIgnore
    @Embedded
    private Address address;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime creationDate;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
