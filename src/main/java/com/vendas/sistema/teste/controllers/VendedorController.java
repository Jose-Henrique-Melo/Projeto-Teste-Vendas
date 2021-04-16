package com.vendas.sistema.teste.controllers;

import com.vendas.sistema.teste.dto.request.VendedorRequest;
import com.vendas.sistema.teste.dto.response.VendedorResumoResponse;
import com.vendas.sistema.teste.model.entities.Vendedor;
import com.vendas.sistema.teste.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public List<VendedorResumoResponse> getVendedorAll() {
        return vendedorService.listarTodosVendedores();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendedorResumoResponse setVendedor(@Valid @RequestBody VendedorRequest vendedorRequest) {
        return vendedorService.salvarVendedor(vendedorRequest);
    }

    @PutMapping("{vendedorId}")
    public ResponseEntity<Vendedor> updateVendedor(@Valid @PathVariable Integer vendedorId,
                                                   @Valid @RequestBody VendedorRequest vendedorRequest) {
        return vendedorService.atualizarVendedor(vendedorId, vendedorRequest);
    }

    @DeleteMapping("{vendedorId}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Integer vendedorId) {
        return vendedorService.removerVendedor(vendedorId);
    }
}