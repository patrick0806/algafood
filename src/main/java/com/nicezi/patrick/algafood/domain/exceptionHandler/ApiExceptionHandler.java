package com.nicezi.patrick.algafood.domain.exceptionHandler;

import com.nicezi.patrick.algafood.domain.exception.BusinessException;
import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice//handle all controller exceptions
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ExceptionData exceptionBodyData = createExceptionDataResponseBuilder(
                (HttpStatus) status,
                ExceptionType.INVALID_BODY,
                "O corpo da requisição está inválido. Verifique erro de sintaxe").build();

        return super.handleExceptionInternal(ex, exceptionBodyData,headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(BusinessException ex,WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionData exceptionBodyData = createExceptionDataResponseBuilder(
                status,
                ExceptionType.ENTITY_NOT_FOUND,
                ex.getMessage()).build();
        return  handleExceptionInternal(ex, exceptionBodyData, new HttpHeaders(), status, request);
        /*final var exceptionResponse = ExceptionData.builder()
                .time(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);*/
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionData exceptionBodyData = createExceptionDataResponseBuilder(
                status,
                ExceptionType.ENTITY_IN_USE,
                ex.getMessage()).build();

        return  handleExceptionInternal(ex, exceptionBodyData, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionData exceptionBodyData = createExceptionDataResponseBuilder(
                status,
                ExceptionType.BUSINESS_EXCEPTION,
                ex.getMessage()).build();
        return  handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
       if(body ==null){
           body = ExceptionData.builder()
                   .title("Houve um erro inesperado")
                   .status(statusCode.value())
                   .detail(ex.getMessage())
                   .build();
       }else if(body instanceof String){
           body = ExceptionData.builder()
                   .title("Houve um erro inesperado")
                   .status(statusCode.value())
                   .detail(ex.getMessage())
                   .build();
       }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ExceptionData.ExceptionDataBuilder createExceptionDataResponseBuilder(HttpStatus status,ExceptionType type, String detail){
        return ExceptionData.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }
}
