package com.vendas.sistema.teste.service;

import com.vendas.sistema.teste.dto.request.ClienteRequest;
import com.vendas.sistema.teste.dto.response.ClienteResumoResponse;
import com.vendas.sistema.teste.model.entities.Cliente;
import com.vendas.sistema.teste.model.exception.NegocioException;
import com.vendas.sistema.teste.repositories.ClienteRepository;
import com.vendas.sistema.teste.repositories.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VendaRepository vendaRepository;

    public ClienteResumoResponse toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResumoResponse.class);
    }

    public List<ClienteResumoResponse> toColletionModel(List<Cliente> clientes) {
        return clientes.stream().map(cliente -> toModel(cliente)).collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    private void verificaVinculoClienteComVenda(Integer clienteId) {
        vendaRepository.findByClienteId(clienteId).ifPresent(venda -> {
            throw new NegocioException("O cliente já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
        });
    }

    public List<ClienteResumoResponse> listarTodosClientes() {
        return toColletionModel(clienteRepository.findAll());
    }

    public ClienteResumoResponse salvarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = toEntity(clienteRequest);
        return toModel(clienteRepository.save(cliente));
    }

    public ResponseEntity<Cliente> atualizarCliente(Integer clienteId, ClienteRequest clienteRequest) {
        verificaVinculoClienteComVenda(clienteId);
        return clienteRepository.findById(clienteId).isPresent()
                ? ResponseEntity.ok(clienteRepository.save(ClienteResumoResponse.of(clienteRequest, clienteId)))
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> removerCliente(Integer clienteId) {
        verificaVinculoClienteComVenda(clienteId);
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(clienteId);
        return ResponseEntity.noContent().build();
    }
}