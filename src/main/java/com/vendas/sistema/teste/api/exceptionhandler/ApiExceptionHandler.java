package com.vendas.sistema.teste.api.exceptionhandler;

import com.vendas.sistema.teste.model.exception.EntidadeNaoEncontradaExpection;
import com.vendas.sistema.teste.model.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private ErrorResponse setErrorResponse(HttpStatus status) {
        var problema = new ErrorResponse();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        return problema;
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        ErrorResponse problema = setErrorResponse(status);
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaExpection.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        ErrorResponse problema = setErrorResponse(status);
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    String nome = ((FieldError) objectError).getField();
                    String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    return new ErrorResponse.Campo(nome, mensagem);
                }).collect(Collectors.toList());

        ErrorResponse problema = setErrorResponse(status);
        problema.setTitulo("Alguns campos estão inválidos!");
        problema.setCampos(campos);

        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
}