package com.vendas.sistema.teste.controllers;

import com.vendas.sistema.teste.service.ClienteService;
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
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private static final String ENDPOINT = "/api/clientes";

    @Test
    public void of_ListarClientesSucesso() throws Exception {
        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void of_CadastrarClientesSucesso201() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umClienteRequest())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void of_CadastrarClientesFalha400() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umClienteFalhaRequest())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void of_AtualizarClienteSucesso200() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umClienteAtualizarRequest())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void of_AtualizarClienteFalha400() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umClienteAtualizarFalhaRequest())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void of_DeletarClienteSucesso204() throws Exception {
        when(clienteService.removerCliente(1))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));

        mockMvc.perform(delete(ENDPOINT + "/{id}", 1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void of_DeletarClienteNaoEncontrado404() throws Exception {
        when(clienteService.removerCliente(1))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

        mockMvc.perform(delete(ENDPOINT + "/{id}", 1))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}