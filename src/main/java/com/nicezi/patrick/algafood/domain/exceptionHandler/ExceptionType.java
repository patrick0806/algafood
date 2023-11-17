package com.nicezi.patrick.algafood.domain.exceptionHandler;

import lombok.Getter;

@Getter
public enum ExceptionType {
    ENTITY_NOT_FOUND("Entidade não encontrada","/entity-not-found"),
    ENTITY_IN_USE("Entidade em uso","/entity-in-use"),
    BUSINESS_EXCEPTION("Violação de regra de negocio","/business-exception"),
    INVALID_BODY("Mensagem incompreensivel","/invlida-body"),
    INVALID_PARAM("Parâmetro inválido", "/invalid-params"),
    ROUTE_NOT_FOUND("A rota não existe", "/route-not-found");

    private String title;
    private String uri;

    ExceptionType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }
}
