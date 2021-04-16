package com.vendas.sistema.teste.dto.response;

import com.vendas.sistema.teste.model.entities.enums.ESituacaoVenda;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaResumoResponse {

    private Integer idVenda;
    private ClienteResumoResponse cliente;
    private VendedorResumoResponse vendedor;
    private List<ProdutoResumoResponse> produto;
    private ESituacaoVenda status;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFinalizacao;
}