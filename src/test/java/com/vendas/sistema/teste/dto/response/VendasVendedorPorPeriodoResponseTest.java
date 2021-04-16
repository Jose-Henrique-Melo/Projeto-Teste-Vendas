package com.vendas.sistema.teste.dto.response;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VendasVendedorPorPeriodoResponseTest {

    @Test
    public void of_DeveRetornarVendasVendedorPorPeriodo_RecebeDataQuantidadeVendasEMedia() {
        var vendasVendedor = VendasVendedorPorPeriodoResponse.of("JOSE TESTE #TEST", 10, 2);

        assertThat(vendasVendedor)
                .extracting("nomeVendedor", "quantidadeDeVendas", "mediaDeVendasPorPeriodo")
                .containsExactly("JOSE TESTE #TEST", 10L, 2.0f);
    }
}