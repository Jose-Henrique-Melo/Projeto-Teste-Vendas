package com.vendas.sistema.teste.service;

import com.vendas.sistema.teste.model.entities.Vendedor;
import com.vendas.sistema.teste.model.exception.NegocioException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.vendas.sistema.teste.helper.Helper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/scripts/tests_vendedores.sql"})
@Transactional
public class VendedorServiceTest {

    @Autowired
    private VendedorService vendedorService;

    @Test
    public void listarVendedores() {
        var vendedor = vendedorService.listarTodosVendedores();

        assertThat(vendedor.size()).isEqualTo(2);
    }

    @Test
    public void cadastrarVendedorSucesso() {
        var vendedor = vendedorService.salvarVendedor(umVendedorRequest());

        assertThat(vendedor)
                .extracting("nomeCompleto", "cpfCnpj")
                .containsExactly("VENDEDOR REQUEST TESTE", "331.211.543-54");
    }

    @Test
    public void atualizarVendedorSucesso() {
        var vendedor = vendedorService.atualizarVendedor(2, umVendedorAtualizarRequest());

        assertThat(vendedor.getBody())
                .extracting(Vendedor::getId, Vendedor::getNomeCompleto, Vendedor::getCpfCnpj)
                .containsExactly(2, "VENDEDOR ATUALIZAR REQUEST TESTE", "234.645.865-88");
    }

    @Test
    public void atualizarVendedorFalhaVendedorComVinculoComVenda() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendedorService.atualizarVendedor(1, umVendedorAtualizarRequest2()))
                .withMessage("O vendedor já consta vinculado a uma venda! O mesmo não poderá ser alterado.");

    }

    @Test
    public void atualizarVendedorNaoEncontradoVendedorSemVinculoComVenda() {
        var vendedor = vendedorService.atualizarVendedor(999, umVendedorAtualizarRequest());

        assertThat(vendedor.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deletarVendedorSucessoVendedorSemVinculoComVenda() {
        var vendedor = vendedorService.removerVendedor(2);

        assertThat(vendedor.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deletarVendedorNaoEncontradoVendedorSemVinculoComVenda() {
        var vendedor = vendedorService.removerVendedor(999);

        assertThat(vendedor.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deletarVendedorFalhaVendedorComVinculoComVenda() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendedorService.removerVendedor(1))
                .withMessage("O vendedor já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
    }
}