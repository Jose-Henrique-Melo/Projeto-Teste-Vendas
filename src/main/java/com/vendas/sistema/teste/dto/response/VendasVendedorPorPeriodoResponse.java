package com.vendas.sistema.teste.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendasVendedorPorPeriodoResponse {

    private String nomeVendedor;
    private long quantidadeDeVendas;
    private float mediaDeVendasPorPeriodo;

    public static VendasVendedorPorPeriodoResponse of(
            String nomeVendedor,
            long quantidadeDeVendas,
            float mediaDeVendasPorPeriodo) {
        return VendasVendedorPorPeriodoResponse.builder()
                .nomeVendedor(nomeVendedor)
                .quantidadeDeVendas(quantidadeDeVendas)
                .mediaDeVendasPorPeriodo(mediaDeVendasPorPeriodo)
                .build();
    }
}