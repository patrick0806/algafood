package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import com.nicezi.patrick.algafood.domain.model.Restaurant;
import com.nicezi.patrick.algafood.domain.repository.GastronomyStyleRepository;
import com.nicezi.patrick.algafood.domain.repository.RestaurantRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    final private GastronomyStyleRepository gastronomyStyleRepository;
    final private RestaurantRepository restaurantRepository;

    RestaurantService(RestaurantRepository restaurantRepository, GastronomyStyleRepository gastronomyStyleRepository){
        this.restaurantRepository = restaurantRepository;
        this.gastronomyStyleRepository = gastronomyStyleRepository;
    }

    public List<Restaurant> listAll(){
        return this.restaurantRepository.list();
    }

    public Restaurant findById(Long id){
        final var restaurant = this.restaurantRepository.findById(id);

        if(restaurant == null){
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de restaurante com o código %d", id)
            );
        }

        return restaurant;
    }

    public Restaurant save(Restaurant restaurant){
        final var gastronomyStyleId = restaurant.getGastronomyStyle().getId();
        GastronomyStyle gastronomyStyle = this.gastronomyStyleRepository.findById(gastronomyStyleId);

        if(gastronomyStyle == null){
            throw  new EntityNotFoundException(
                    String.format("Não existe cadastro de cozinha com o código %d",gastronomyStyleId)
            );
        }

        return this.restaurantRepository.save(restaurant);
    }

    public void remove(Long id){
        try{
            this.restaurantRepository.remove(id);
        }
        catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de restaurante com o código %d", id)
            );
        }
        catch (DataIntegrityViolationException ex){
            throw new EntityInUseException(
                    String.format("Restaurante com código %d, não pode ser removido, pois está em uso", id)
            );
        }
    }
}
