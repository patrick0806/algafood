package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.KitchenCategory;
import com.nicezi.patrick.algafood.domain.repository.KitchenCategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenCategoryService {
    final private KitchenCategoryRepository kitchenCategoryRepository;

    KitchenCategoryService(KitchenCategoryRepository kitchenCategoryRepository){
        this.kitchenCategoryRepository = kitchenCategoryRepository;
    }

    public List<KitchenCategory> listAll(){
        return this.kitchenCategoryRepository.list();
    }

    public KitchenCategory findById(Long kitchenCategoryId){
        final var kitchenCategory = this.kitchenCategoryRepository.findById(kitchenCategoryId);

        if(kitchenCategory == null){
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de categoria de cozinha com o código %d", kitchenCategoryId)
            );
        };
        return kitchenCategory;
    }

    public KitchenCategory save(KitchenCategory kitchenCategory){
        return this.kitchenCategoryRepository.save(kitchenCategory);
    }

    public void remove(Long id){
        try{
            this.kitchenCategoryRepository.remove(id);
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
