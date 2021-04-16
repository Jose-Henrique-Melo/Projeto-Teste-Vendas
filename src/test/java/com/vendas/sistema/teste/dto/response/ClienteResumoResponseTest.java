package com.vendas.sistema.teste.dto.response;

import org.junit.Test;

import static com.vendas.sistema.teste.helper.Helper.umCliente;
import static com.vendas.sistema.teste.helper.Helper.umClienteRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class ClienteResumoResponseTest {

    @Test
    public void of_DeveRetornarCliente_RecebeClienteRequestAndId() {
        var cliente = ClienteResumoResponse.of(umClienteRequest(), 1);

        assertThat(cliente)
                .extracting("id", "nomeCompleto", "cpfCnpj")
                .containsExactly(1, "CLIENTE REQUEST TESTE", "121.431.123-32");
    }

    @Test
    public void of_DeveRetornarClienteResumoResponse_RecebeCliente() {
        var cliente = ClienteResumoResponse.of(umCliente());

        assertThat(cliente)
                .extracting("id", "nomeCompleto", "cpfCnpj")
                .containsExactly(1, "JOSE CLIENTE PARA TESTE", "222.333.444-44");
    }
}