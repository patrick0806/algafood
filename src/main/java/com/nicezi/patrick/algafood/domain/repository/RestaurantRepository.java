package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository
        extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQuery, JpaSpecificationExecutor<Restaurant> {
    @Query("from Restaurant r join r.gastronomyStyle left join fetch r.paymentMethods")
    List<Restaurant> findAll();
    List<Restaurant> findByDeliveryTaxBetween(BigDecimal initialTax, BigDecimal finalTax);

    @Query("from Restaurant where name like %:name% and gastronomyStyle.id = :gastronomyStyleId")
    List<Restaurant> findByName(String name, @Param("gastronomyStyleId") Long gastronomyStyleId);
    List<Restaurant> findByNameContainingAndGastronomyStyleId(String name, Long gastronomyStyleId);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    boolean existsByName(String name);

    int countByGastronomyStyleId(Long gastronomyStyleId);
}
