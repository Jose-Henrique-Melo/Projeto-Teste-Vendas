package com.vendas.sistema.teste.repositories;

import com.vendas.sistema.teste.model.entities.Venda;
import com.vendas.sistema.teste.model.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>, QuerydslPredicateExecutor<Venda>, VendaRepositoryCustom {

    Optional<Venda> findByVendedorId(Integer id);

    Optional<Venda> findByClienteId(Integer id);

    Optional<Venda> findByProdutoId(Integer id);
}