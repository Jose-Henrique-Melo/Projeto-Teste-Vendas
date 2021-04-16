package com.vendas.sistema.teste.controllers;

import com.vendas.sistema.teste.dto.request.ClienteRequest;
import com.vendas.sistema.teste.dto.response.ClienteResumoResponse;
import com.vendas.sistema.teste.model.entities.Cliente;
import com.vendas.sistema.teste.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteResumoResponse> getClienteAll() {
        return clienteService.listarTodosClientes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResumoResponse setCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        return clienteService.salvarCliente(clienteRequest);
    }

    @PutMapping("{clienteId}")
    public ResponseEntity<Cliente> updateVendedor(@Valid @PathVariable Integer clienteId,
                                                  @Valid @RequestBody ClienteRequest clienteRequest) {
        return clienteService.atualizarCliente(clienteId, clienteRequest);
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer clienteId) {
        return clienteService.removerCliente(clienteId);
    }
}