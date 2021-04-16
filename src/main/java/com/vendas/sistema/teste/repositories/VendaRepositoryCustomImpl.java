package com.vendas.sistema.teste.repositories;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.vendas.sistema.teste.model.entities.QVenda.venda;

public class VendaRepositoryCustomImpl implements VendaRepositoryCustom {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<Tuple> findAllByDateBetween(String dataInicio, String dataFim) {

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
        return new JPAQueryFactory(entityManager)
                .select(venda.vendedor.nomeCompleto.as("nomeVendedor"), venda.count().as("quantidadeVendas"))
                .from(venda)
                .where(venda.dataAbertura.between(LocalDateTime.parse(dataInicio, formatterData),
                        LocalDateTime.parse(dataFim, formatterData)))
                .groupBy(venda.vendedor.id)
                .fetch();
    }
}