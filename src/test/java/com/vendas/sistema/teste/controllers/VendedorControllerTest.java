package com.vendas.sistema.teste.controllers;

import com.vendas.sistema.teste.service.VendedorService;
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
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    private static final String ENDPOINT = "/api/vendedores";

    @Test
    public void listarVendedoresSucesso() throws Exception {
        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void cadastrarVendedorSucesso201() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umVendedorRequest())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void cadastrarVendedorFalha400() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umVendedorFalhaRequest())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void atualizarVendedorSucesso200() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umVendedorAtualizarRequest())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void atualizarVendedorFalha400() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umVendedorAtualizarFalhaRequest())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void deletarVendedorSucesso204() throws Exception {
        when(vendedorService.removerVendedor(1))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));

        mockMvc.perform(delete(ENDPOINT + "/{id}", 1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void deletarVendedorNaoEncontrado404() throws Exception {
        when(vendedorService.removerVendedor(1))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

        mockMvc.perform(delete(ENDPOINT + "/{id}", 1))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}