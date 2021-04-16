package com.vendas.sistema.teste.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
public class ClienteRequest {

    @NotNull
    @Size(min = 2, max = 60)
    private String nomeCompleto;

    @Size(min = 11, max = 20)
    @NotNull
    private String cpfCnpj;
}