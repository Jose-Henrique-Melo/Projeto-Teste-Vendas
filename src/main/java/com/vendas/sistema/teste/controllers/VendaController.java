package com.vendas.sistema.teste.controllers;

import com.vendas.sistema.teste.dto.request.VendaRequest;
import com.vendas.sistema.teste.dto.response.VendaResumoResponse;
import com.vendas.sistema.teste.dto.response.VendasVendedorPorPeriodoResponse;
import com.vendas.sistema.teste.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<VendaResumoResponse> getVendaAll() {
        return vendaService.listarVendas();
    }

    @GetMapping("filtros")
    public List<VendasVendedorPorPeriodoResponse> listarVendasPorPeriodo(@RequestParam String dataInicio,
                                                                         @RequestParam String dataFim) {
        return vendaService.listarVendasPorPeriodo(dataInicio, dataFim);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResumoResponse cadastrar(@Valid @RequestBody VendaRequest vendaRequest) {
        return vendaService.cadastrarVendaAbertura(vendaRequest);
    }

    @PutMapping("finalizar/{vendaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@Valid @PathVariable Integer vendaId) {
        vendaService.finalizarVenda(vendaId);
    }

    @PutMapping("cancelar/{vendaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@Valid @PathVariable Integer vendaId) {
        vendaService.cancelarVenda(vendaId);
    }
}