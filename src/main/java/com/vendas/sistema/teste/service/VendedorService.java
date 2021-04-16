package com.vendas.sistema.teste.service;

import com.vendas.sistema.teste.dto.request.VendedorRequest;
import com.vendas.sistema.teste.dto.response.VendedorResumoResponse;
import com.vendas.sistema.teste.model.entities.Vendedor;
import com.vendas.sistema.teste.model.exception.NegocioException;
import com.vendas.sistema.teste.repositories.VendaRepository;
import com.vendas.sistema.teste.repositories.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VendaRepository vendaRepository;

    public VendedorResumoResponse toModel(Vendedor vendedor) {
        return modelMapper.map(vendedor, VendedorResumoResponse.class);
    }

    public List<VendedorResumoResponse> toColletionModel(List<Vendedor> vendedores) {
        return vendedores.stream().map(vendedor -> toModel(vendedor)).collect(Collectors.toList());
    }

    public Vendedor toEntity(VendedorRequest vendedorRequest) {
        return modelMapper.map(vendedorRequest, Vendedor.class);
    }

    private void verificaVinculoVendedorComVenda(Integer vendedorId) {
        vendaRepository.findByVendedorId(vendedorId).ifPresent(venda -> {
            throw new NegocioException("O vendedor já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
        });
    }

    public List<VendedorResumoResponse> listarTodosVendedores() {
        return toColletionModel(vendedorRepository.findAll());
    }

    public VendedorResumoResponse salvarVendedor(VendedorRequest vendedorRequest) {
        Vendedor vendedor = toEntity(vendedorRequest);
        return toModel(vendedorRepository.save(vendedor));
    }

    public ResponseEntity<Vendedor> atualizarVendedor(Integer vendedorId, VendedorRequest vendedorRequest) {
        verificaVinculoVendedorComVenda(vendedorId);

        return vendedorRepository.findById(vendedorId).isPresent()
                ? ResponseEntity.ok(vendedorRepository.save(VendedorResumoResponse.of(vendedorRequest, vendedorId)))
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> removerVendedor(Integer vendedorId) {
        verificaVinculoVendedorComVenda(vendedorId);

        if (!vendedorRepository.existsById(vendedorId)) {
            return ResponseEntity.notFound().build();
        }
        vendedorRepository.deleteById(vendedorId);
        return ResponseEntity.noContent().build();
    }
}