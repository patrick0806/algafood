package com.nicezi.patrick.algafood.domain.model;

import com.nicezi.patrick.algafood.domain.model.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders")
public class Order {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subtotal;
    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Embedded
    private Address address;
    private OrderStatus status;

    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name="confirmation_date")
    private LocalDateTime confirmationDate;

    @ManyToOne
    @JoinColumn(nullable = false, name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false, name = "client_id")
    private User client;

    @OneToMany
    private List<OrderItem> items = new ArrayList<>();
}
