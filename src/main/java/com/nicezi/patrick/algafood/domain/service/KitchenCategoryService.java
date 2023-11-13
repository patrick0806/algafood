package com.nicezi.patrick.algafood.domain.service;

import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import com.nicezi.patrick.algafood.domain.model.KitchenCategory;
import com.nicezi.patrick.algafood.domain.repository.KitchenCategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenCategoryService {
    final private KitchenCategoryRepository kitchenCategoryRepository;

    KitchenCategoryService(KitchenCategoryRepository kitchenCategoryRepository){
        this.kitchenCategoryRepository = kitchenCategoryRepository;
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
