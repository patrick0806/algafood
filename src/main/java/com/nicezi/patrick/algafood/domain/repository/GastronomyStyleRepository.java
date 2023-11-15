package com.nicezi.patrick.algafood.domain.repository;

import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastronomyStyleRepository extends JpaRepository<GastronomyStyle, Long> {

}
