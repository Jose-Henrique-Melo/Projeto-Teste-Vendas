package com.vendas.sistema.teste.controllers;

import com.vendas.sistema.teste.dto.request.ProdutoRequest;
import com.vendas.sistema.teste.dto.response.ProdutoResumoResponse;
import com.vendas.sistema.teste.model.entities.Produto;
import com.vendas.sistema.teste.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoResumoResponse> getProdutoAll() {
        return produtoService.listarTodosProdutos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResumoResponse setProduto(@Valid @RequestBody ProdutoRequest produtoRequest) {
        return produtoService.salvarProduto(produtoRequest);
    }

    @PutMapping("{produtoId}")
    public ResponseEntity<Produto> updateProduto(@Valid @PathVariable Integer produtoId,
                                                 @Valid @RequestBody ProdutoRequest produtoRequest) {
        return produtoService.atualizarProduto(produtoId, produtoRequest);
    }

    @DeleteMapping("{produtoId}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer produtoId) {
        return produtoService.removerProduto(produtoId);
    }
}