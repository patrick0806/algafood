package com.nicezi.patrick.algafood.infra.repository.spec;

import com.nicezi.patrick.algafood.domain.model.Restaurant;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class RestaurantWithSimilarNameSpec implements Specification {
    private String name;

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
}
