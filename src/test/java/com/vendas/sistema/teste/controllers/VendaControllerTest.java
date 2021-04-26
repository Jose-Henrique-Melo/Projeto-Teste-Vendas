package com.vendas.sistema.teste.controllers;


import com.vendas.sistema.teste.model.exception.NegocioException;
import com.vendas.sistema.teste.service.VendaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.vendas.sistema.teste.helper.Helper.*;
import static com.vendas.sistema.teste.helper.TestsHelper.convertObjectToJsonBytes;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VendaControllerTest {

    private static final String ENDPOINT = "/api/vendas";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendaService vendaService;

    @Test
    public void retornarCadastroVendaResponse() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umaVendaRequest())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void retornarVendaResponseFinalizada() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/finalizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umaVendaResumoResponseAberta())))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void retornarVendaResponseCancelada() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/cancelar/{id}", 100)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umaVendaResumoResponseAberta())))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void retornarListaVendasVendedorPorPeriodoResponse() throws Exception {
        mockMvc.perform(get(ENDPOINT + "/filtros?dataInicio=2021-03-1101:11&dataFim=2021-03-1123:59"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void retornarErroSemClienteVendaRequest() throws Exception {
        when(vendaService.cadastrarVendaAbertura(umaVendaRequestSemCliente()))
                .thenThrow(new NegocioException("Cliente não encontrado!"));

        mockMvc.perform(post(ENDPOINT))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void of_deveRetornarErroSemVendedorVendaRequest() throws Exception {
        when(vendaService.cadastrarVendaAbertura(umaVendaRequestSemVendedor()))
                .thenThrow(new NegocioException("Vendedor não encontrado!"));

        mockMvc.perform(post(ENDPOINT))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void retornarErroSemProdutoVendaRequest() throws Exception {
        when(vendaService.cadastrarVendaAbertura(umaVendaRequestSemProdutos()))
                .thenThrow(new NegocioException("Produto não encontrado!"));

        mockMvc.perform(post(ENDPOINT))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}