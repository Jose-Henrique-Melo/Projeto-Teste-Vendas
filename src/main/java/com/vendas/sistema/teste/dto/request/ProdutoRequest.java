package com.vendas.sistema.teste.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
public class ProdutoRequest {

    @Size(min = 2, max = 60)
    @NotNull
    private String descricao;

    @Min(1)
    @Max(999999999)
    @NotNull
    private double preco;

    @Min(0)
    @Max(1)
    private double desconto;
}