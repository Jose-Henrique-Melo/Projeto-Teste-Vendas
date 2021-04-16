package com.vendas.sistema.teste.dto.request;

import com.vendas.sistema.teste.dto.response.ClienteResumoResponse;
import com.vendas.sistema.teste.dto.response.ProdutoResumoResponse;
import com.vendas.sistema.teste.dto.response.VendedorResumoResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Setter
@Getter
@Builder
public class VendaRequest {

    @Valid
    private ClienteResumoResponse cliente;

    @Valid
    private VendedorResumoResponse vendedor;

    @Valid
    private List<ProdutoResumoResponse> produto;
}