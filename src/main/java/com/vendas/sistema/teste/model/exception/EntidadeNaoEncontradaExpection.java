package com.vendas.sistema.teste.model.exception;

public class EntidadeNaoEncontradaExpection extends NegocioException {

    private static final long serialVerisionUID = 1L;

    public EntidadeNaoEncontradaExpection(String message) {
        super(message);
    }
}