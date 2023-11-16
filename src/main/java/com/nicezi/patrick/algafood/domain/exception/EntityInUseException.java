package com.nicezi.patrick.algafood.domain.exception;

public class EntityInUseException extends BusinessException{
    public EntityInUseException(String message){
        super(message);
    }
}
