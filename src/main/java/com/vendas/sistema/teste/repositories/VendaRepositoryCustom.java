package com.vendas.sistema.teste.repositories;

import com.querydsl.core.Tuple;

import java.util.List;

public interface VendaRepositoryCustom {
    List<Tuple> findAllByDateBetween(String dataInicio, String dataFim);
}