package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.GastronomyStyle;
import com.nicezi.patrick.algafood.domain.repository.GastronomyStyleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastronomyStyleService {
    final private GastronomyStyleRepository gastronomyStyleRepository;

    GastronomyStyleService(GastronomyStyleRepository gastronomyStyleRepository){
        this.gastronomyStyleRepository = gastronomyStyleRepository;
    }

    public List<GastronomyStyle> listAll(){
        return this.gastronomyStyleRepository.findAll();
    }

    public GastronomyStyle findById(Long gastronomyStyleId){
        return this.gastronomyStyleRepository.findById(gastronomyStyleId)
                .orElseThrow(() ->new EntityNotFoundException(
                        String.format("Não existe um cadastro de categoria de cozinha com o código %d", gastronomyStyleId)
                ) );
    }

    public GastronomyStyle save(GastronomyStyle gastronomyStyle){
        return this.gastronomyStyleRepository.save(gastronomyStyle);
    }

    public void remove(Long id){
        try{
            this.gastronomyStyleRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de categoria de cozinha com o código %d", id)
            );
        }
        catch (DataIntegrityViolationException ex){
            throw new EntityInUseException(
                    String.format("Categoria de cozinha com código %d, não pode ser removida, pois está em uso", id)
            );
        }
    }
}
