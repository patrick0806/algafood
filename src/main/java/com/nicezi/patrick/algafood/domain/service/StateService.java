package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.State;
import com.nicezi.patrick.algafood.domain.repository.StateRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    final private StateRepository stateRepository;

    StateService(StateRepository stateRepository){
        this.stateRepository = stateRepository;
    }

    public List<State> listAll(){
        return this.stateRepository.findAll();
    }

    public State findById(Long stateId){
        final var state = this.stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de estado com o código %d", stateId)
                ));

        return state;
    }

    public State save(State state){
        return this.stateRepository.save(state);
    }

    public void remove(Long id){
        try{
            this.stateRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de estado com o código %d", id)
            );
        }
        catch (DataIntegrityViolationException ex){
            throw new EntityInUseException(
                    String.format("Estado com código %d, não pode ser removida, pois está em uso", id)
            );
        }
    }
}
