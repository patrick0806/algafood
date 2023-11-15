package com.nicezi.patrick.algafood.infra.repository.spec;

import com.nicezi.patrick.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> withFreeDeliveryTax(){
        return (root,query,builder) ->
            builder.equal(root.get("deliveryTax"), BigDecimal.ZERO);
    };

    public static Specification<Restaurant> withSimilarName(String name){
        return (root, query, builder)->
                builder.like(root.get("name"), "%"+name+"%") ;
    };
}
