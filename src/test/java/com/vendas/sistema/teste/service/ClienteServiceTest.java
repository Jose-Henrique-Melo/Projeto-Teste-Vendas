package com.vendas.sistema.teste.service;

import com.vendas.sistema.teste.model.entities.Cliente;
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
@Sql(scripts = {"classpath:/scripts/tests_clientes.sql"})
@Transactional
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void listarClientes() {
        var clientes = clienteService.listarTodosClientes();

        assertThat(clientes.size()).isEqualTo(2);
    }

    @Test
    public void cadastrarClienteSucesso() {
        var cliente = clienteService.salvarCliente(umClienteRequest());

        assertThat(cliente)
                .extracting("nomeCompleto", "cpfCnpj")
                .containsExactly("CLIENTE REQUEST TESTE", "121.431.123-32");
    }

    @Test
    public void atualizarClienteSucesso() {
        var cliente = clienteService.atualizarCliente(2, umClienteAtualizarRequest());

        assertThat(cliente.getBody())
                .extracting(Cliente::getId, Cliente::getNomeCompleto, Cliente::getCpfCnpj)
                .containsExactly(2, "CLIENTE ATUALIZAR REQUEST TESTE", "362.423.153-61");
    }

    @Test
    public void atualizarClienteFalhaClienteComVinculoComVenda() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> clienteService.atualizarCliente(1, umClienteAtualizarRequest2()))
                .withMessage("O cliente já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
    }

    @Test
    public void atualizarClienteNaoEncontradoClienteSemVinculoComVenda() {
        var cliente = clienteService.atualizarCliente(999, umClienteAtualizarRequest());

        assertThat(cliente.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deletarClienteSucessoClienteSemVinculoComVenda() {
        var cliente = clienteService.removerCliente(2);

        assertThat(cliente.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deletarClienteNaoEncontradoClienteSemVinculoComVenda() {
        var cliente = clienteService.removerCliente(999);

        assertThat(cliente.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deletarClienteFalhaClienteComVinculoComVenda() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> clienteService.removerCliente(1))
                .withMessage("O cliente já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
    }
}