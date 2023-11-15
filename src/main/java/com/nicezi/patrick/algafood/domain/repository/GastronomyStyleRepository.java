package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GastronomyStyleRepository extends JpaRepository<GastronomyStyle, Long> {
    List<GastronomyStyle> findAllByNameContaining(String name);

    Optional<GastronomyStyle> findByName(String name);
}
