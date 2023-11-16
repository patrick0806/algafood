package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.exception.BusinessException;
import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
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
        return this.restaurantRepository.findAll();
    }

    public Restaurant findById(Long id){
        final var restaurant = this.restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de restaurante com o código %d", id)
                ));

        return restaurant;
    }

    public Restaurant save(Restaurant restaurant){
        final var gastronomyStyleId = restaurant.getGastronomyStyle().getId();
        this.gastronomyStyleRepository.findById(gastronomyStyleId)
                .orElseThrow(() -> new BusinessException(String
                                .format("Não existe cadastro de cozinha com o código %d",gastronomyStyleId)));

        return this.restaurantRepository.save(restaurant);
    }

    public void remove(Long id){
        try{
            this.restaurantRepository.deleteById(id);
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
