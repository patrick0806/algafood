package com.nicezi.patrick.algafood.domain.exception;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
/*
* public class EntityNotFoundException extends ResponseStatusException {
    public EntityNotFoundException(String message){
        super(HttpStatus.NOT_FOUND,message);
    }
}
* */

