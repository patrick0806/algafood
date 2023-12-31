package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.exception.BusinessException;
import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.City;
import com.nicezi.patrick.algafood.domain.repository.CityRepository;
import com.nicezi.patrick.algafood.domain.repository.StateRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    final private CityRepository cityRepository;
    final private StateRepository stateRepository;

    CityService(CityRepository cityRepository, StateRepository stateRepository){
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public List<City> listAll(){
        return this.cityRepository.findAll();
    }

    public City findById(Long id){
        final var city = this.cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de cidade com o código %d", id)
                ));

        return city;
    }

    public City save(City city){
        final var stateId = city.getState().getId();
        this.stateRepository.findById(stateId)
                .orElseThrow(() -> new BusinessException(
                String.format("Não existe um cadastro de estado com o código %d", stateId)));

        return this.cityRepository.save(city);
    }

    public void remove(Long id){
        try{
            this.cityRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de citye com o código %d", id)
            );
        }
        catch (DataIntegrityViolationException ex){
            throw new EntityInUseException(
                    String.format("Citye com código %d, não pode ser removido, pois está em uso", id)
            );
        }
    }
}
