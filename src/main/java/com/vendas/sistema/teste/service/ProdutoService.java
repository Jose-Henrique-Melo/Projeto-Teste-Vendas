package com.vendas.sistema.teste.service;

import com.vendas.sistema.teste.dto.request.ProdutoRequest;
import com.vendas.sistema.teste.dto.response.ProdutoResumoResponse;
import com.vendas.sistema.teste.model.entities.Produto;
import com.vendas.sistema.teste.model.exception.NegocioException;
import com.vendas.sistema.teste.repositories.ProdutoRepository;
import com.vendas.sistema.teste.repositories.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VendaRepository vendaRepository;

    public ProdutoResumoResponse toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoResumoResponse.class);
    }

    public List<ProdutoResumoResponse> toColletionModel(List<Produto> produtos) {
        return produtos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Produto toEntity(ProdutoRequest produtoRequest) {
        return modelMapper.map(produtoRequest, Produto.class);
    }

    private void verificaVinculoProdutoComVenda(Integer produtoId) {
        vendaRepository.findByProdutoId(produtoId).ifPresent(venda -> {
            throw new NegocioException("O produto já consta vinculado a uma venda! O mesmo não poderá ser alterado.");
        });
    }

    public List<ProdutoResumoResponse> listarTodosProdutos() {
        return toColletionModel(produtoRepository.findAll());
    }

    public ProdutoResumoResponse salvarProduto(ProdutoRequest produtoRequest) {
        Produto produto = toEntity(produtoRequest);
        return toModel(produtoRepository.save(produto));
    }

    public ResponseEntity<Produto> atualizarProduto(Integer produtoId, ProdutoRequest produtoRequest) {
        verificaVinculoProdutoComVenda(produtoId);
        return produtoRepository.findById(produtoId).isPresent()
                ? ResponseEntity.ok(produtoRepository.save(ProdutoResumoResponse.of(produtoRequest, produtoId)))
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> removerProduto(Integer produtoId) {
        verificaVinculoProdutoComVenda(produtoId);
        if (!produtoRepository.existsById(produtoId)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(produtoId);
        return ResponseEntity.noContent().build();
    }
}