package com.vendas.sistema.teste.service1;

import com.vendas.sistema.teste.dto.response.ProdutoResumoResponse;
import com.vendas.sistema.teste.dto.response.VendaResumoResponse;
import com.vendas.sistema.teste.dto.response.VendasVendedorPorPeriodoResponse;
import com.vendas.sistema.teste.model.entities.enums.ESituacaoVenda;
import com.vendas.sistema.teste.model.exception.NegocioException;
import com.vendas.sistema.teste.service.VendaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.vendas.sistema.teste.helper.Helper.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/scripts/tests_vendas.sql"})
@Transactional
public class VendaServiceTest {

    @Autowired
    private VendaService vendaService;

    @Test
    public void listarVendas() {
        var vendas = vendaService.listarVendas();

        assertThat(vendas.size()).isEqualTo(4);
    }

    @Test
    public void cadastrarVendaSucesso() {
        var venda = vendaService.cadastrarVendaAbertura(umaVendaRequest());

        assertThat(venda)
                .extracting("cliente.id", "vendedor.id", "dataFinalizacao", "status")
                .contains(1, 1, null, ESituacaoVenda.ABERTA);

        assertThat(venda.getProduto())
                .extracting(ProdutoResumoResponse::getId)
                .containsAnyElementsOf(List.of(1, 4));

        assertThat(venda)
                .extracting("dataAbertura").isNotNull();
    }

    @Test
    public void cadastrarVendaSemVendedor() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendaService.cadastrarVendaAbertura(umaVendaRequestSemVendedor()))
                .withMessage("Vendedor não encontrado!");
    }

    @Test
    public void cadastrarVendaSemCliente() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendaService.cadastrarVendaAbertura(umaVendaRequestSemCliente()))
                .withMessage("Cliente não encontrado!");
    }

    @Test
    public void cadastrarVendaSemProdutos() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendaService.cadastrarVendaAbertura(umaVendaRequestSemProdutos()))
                .withMessage("Produtos não encontrados!");
    }

    @Test
    public void cancelarVendaSucesso() {
        vendaService.cancelarVenda(1);

        var venda = vendaService.listarVendaPorId(1);

        assertThat(venda.getBody()).extracting(VendaResumoResponse::getStatus).isEqualTo(ESituacaoVenda.CANCELADA);
    }


    @Test
    public void cancelarVendaNaoEncontrada() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendaService.cancelarVenda(5))
                .withMessage("Venda não encontrada!");
    }

    @Test
    public void cancelarVendaFalhaValidacaoVendaCanceladaOuFinalizada() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendaService.cancelarVenda(2))
                .withMessage("Venda já foi Finalizada ou Cancelada. Sua situação não poderá ser alterada!");
    }

    @Test
    public void finalizarVendaSucesso() {
        vendaService.finalizarVenda(4);

        var venda = vendaService.listarVendaPorId(4);

        assertThat(venda.getBody()).extracting(VendaResumoResponse::getStatus)
                .isEqualTo(ESituacaoVenda.FINALIZADA);
    }

    @Test
    public void finalizarVendaNaoEncontrada() {
        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendaService.finalizarVenda(6))
                .withMessage("Venda não encontrada!");
    }

    @Test
    public void finalizarVendaFalhaValidacaoVendaFinalizadaOuFinalizada() {

        assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> vendaService.finalizarVenda(2))
                .withMessage("Venda já foi Finalizada ou Cancelada. Sua situação não poderá ser alterada!");
    }

    @Test
    public void listarVendasPorPeriodoSucesso() {
        var vendas = vendaService.listarVendasPorPeriodo("2021-04-0923:50", "2021-04-1423:50");

        assertThat(vendas).extracting(VendasVendedorPorPeriodoResponse::getNomeVendedor,
                VendasVendedorPorPeriodoResponse::getQuantidadeDeVendas,
                VendasVendedorPorPeriodoResponse::getMediaDeVendasPorPeriodo)
                .containsExactlyInAnyOrder(tuple("JOSE VENDEDOR PARA TESTE", 4L, 0.8f));
    }
}