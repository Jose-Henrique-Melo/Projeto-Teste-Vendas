package com.vendas.sistema.teste.dto.response;

import org.junit.Test;

import static com.vendas.sistema.teste.helper.Helper.umProdutoRequestComDesconto;
import static com.vendas.sistema.teste.helper.Helper.umProdutoRequestSemDesconto;
import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoResumoResponseTest {

    @Test
    public void of_DeveRetornarProdutoComDesconto_RecebeProdutoRequestAndId() {
        var produto = ProdutoResumoResponse.of(umProdutoRequestComDesconto(), 1);

        assertThat(produto)
                .extracting("id", "descricao", "preco", "desconto")
                .containsExactly(1, "PRODUTO REQUEST COM DESCONTO", 33.22, 0.1);
    }

    @Test
    public void of_DeveRetornarProdutoSemDesconto_RecebeProdutoRequestAndId() {
        var produto = ProdutoResumoResponse.of(umProdutoRequestSemDesconto(), 1);

        assertThat(produto)
                .extracting("id", "descricao", "preco")
                .containsExactly(1, "PRODUTO REQUEST SEM DESCONTO", 33.22);
    }
}