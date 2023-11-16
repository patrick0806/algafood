package com.nicezi.patrick.algafood.domain.exceptionHandler;

import com.nicezi.patrick.algafood.domain.exception.BusinessException;
import com.nicezi.patrick.algafood.domain.exception.EntityInUseException;
import com.nicezi.patrick.algafood.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice//handle all controller exceptions
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(BusinessException ex,WebRequest request) {
        return  handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        /*final var exceptionResponse = ExceptionData.builder()
                .time(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);*/
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request) {
        return  handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        return  handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
       if(body ==null){
           body = ExceptionData.builder()
                   .time(LocalDateTime.now())
                   .message(ex.getMessage())
                   .build();
       }else if(body instanceof String){
           body = ExceptionData.builder()
                   .time(LocalDateTime.now())
                   .message((String) body)
                   .build();
       }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
