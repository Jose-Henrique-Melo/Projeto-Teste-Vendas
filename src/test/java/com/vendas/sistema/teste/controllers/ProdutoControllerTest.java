package com.vendas.sistema.teste.controllers;

import com.vendas.sistema.teste.service.ProdutoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    private static final String ENDPOINT = "/api/produtos";

    @Test
    public void listarProdutosSucesso() throws Exception {
        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void cadastrarProdutoComDescontoSucesso201() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestComDesconto())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void cadastrarProdutoSemDescontoSucesso201() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestSemDesconto())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void cadastrarProdutoComDescontoFalha400() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestComDescontoFalha())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void cadastrarProdutoSemDescontoFalha400() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestSemDescontoFalha())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void atualizarProdutoComDescontoSucesso200() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestAtualizarComDesconto())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void atualizarProdutoSemDescontoSucesso200() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestAtualizarSemDesconto())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void atualizarProdutoComDescontoFalha400() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestAtualizarComDescontoFalha())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void atualizarProdutoSemDescontoFalha400() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umProdutoRequestAtualizarSemDescontoFalha())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void deletarVendedorSucesso204() throws Exception {
        when(produtoService.removerProduto(1))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));

        mockMvc.perform(delete(ENDPOINT + "/{id}", 1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void deletarProdutoNaoEncontrado404() throws Exception {
        when(produtoService.removerProduto(1))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

        mockMvc.perform(delete(ENDPOINT + "/{id}", 1))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}