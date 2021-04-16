package com.vendas.sistema.teste.dto.response;

import org.junit.Test;

import static com.vendas.sistema.teste.helper.Helper.umVendedor;
import static com.vendas.sistema.teste.helper.Helper.umVendedorRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class VendedorResumoResponseTest {

    @Test
    public void of_DeveRetornarVendedor_RecebeVendedorRequestAndId() {
        var vendedor = VendedorResumoResponse.of(umVendedorRequest(), 1);

        assertThat(vendedor)
                .extracting("id", "nomeCompleto", "cpfCnpj")
                .containsExactly(1, "VENDEDOR REQUEST TESTE", "331.211.543-54");
    }

    @Test
    public void of_DeveRetornarClienteResumoResponse_RecebeCliente() {
        var vendedor = VendedorResumoResponse.of(umVendedor());

        assertThat(vendedor)
                .extracting("id", "nomeCompleto", "cpfCnpj")
                .containsExactly(1, "JOSE VENDEDOR PARA TESTE", "111.111.222-22");
    }
}