package com.vendas.sistema.teste.service;

import com.vendas.sistema.teste.model.entities.Produto;
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
@Sql(scripts = {"classpath:/scripts/tests_produtos.sql"})
@Transactional
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void listarProdutos() {
        var produtos = produtoService.listarTodosProdutos();

        assertThat(produtos.size()).isEqualTo(2);
    }

    @Test
    public void cadastrarProdutoComDesconto() {
        var produto = produtoService.salvarProduto(umProdutoRequestComDesconto());

        assertThat(produto)
                .extracting("descricao", "preco", "desconto")
                .containsExactly("PRODUTO REQUEST COM DESCONTO", 33.22, 0.1);
    }

    @Test
    public void cadastrarProdutoSemDesconto() {
        var produto = produtoService.salvarProduto(umProdutoRequestSemDesconto());

        assertThat(produto)
                .extracting("descricao", "preco")
                .containsExactly("PRODUTO REQUEST SEM DESCONTO", 33.22);
    }

    @Test
    public void atualizarProdutoComDescontoSucesso() {
        var produto = produtoService.atualizarProduto(2, umProdutoRequestComDesconto());

        assertThat(produto.getBody())
                .extracting(Produto::getId, Produto::getDescricao, Produto::getPreco, Produto::getDesconto)
                .containsExactly(2, "PRODUTO REQUEST COM DESCONTO", 33.22, 0.1);
    }

    @Test
    public void atualizarProdutoSemDescontoSucesso() {
        var produto = produtoService.atualizarProduto(2, umProdutoRequestSemDesconto());

        assertThat(produto.getBody())
                .extracting(Produto::getId, Produto::getDescricao, Produto::getPreco, Produto::getDesconto)
                .containsExactly(2, "PRODUTO REQUEST SEM DESCONTO", 33.22, 0.0);
    }

    @Test
    public void atualizarProdutoComDescontoFalhaProdutoComVinculoComVenda() {

        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> produtoService.atualizarProduto(1, umProdutoRequestComDesconto2()))
                .withMessage("O produto já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
    }

    @Test
    public void atualizarProdutoNaoEncontradoProdutoSemVinculoComVenda() {
        var produto = produtoService.atualizarProduto(999, umProdutoRequestComDesconto());

        assertThat(produto.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deletarProdutoSucessoProdutoSemVinculoComVenda() {
        var produto = produtoService.removerProduto(2);

        assertThat(produto.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deletarProdutoNaoEncontradoProdutoSemVinculoComVenda() {
        var produto = produtoService.removerProduto(999);

        assertThat(produto.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deletarProdutoFalhaProdutoComVinculoComVenda() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> produtoService.removerProduto(1))
                .withMessage("O produto já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
    }
}