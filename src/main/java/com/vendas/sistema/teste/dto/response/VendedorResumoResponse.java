package com.vendas.sistema.teste.dto.response;

import com.vendas.sistema.teste.dto.request.VendedorRequest;
import com.vendas.sistema.teste.model.entities.Vendedor;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendedorResumoResponse {

    private Integer id;
    private String nomeCompleto;
    private String cpfCnpj;

    public static Vendedor of(VendedorRequest vendedorRequest, Integer id) {
        return Vendedor.builder()
                .id(id)
                .nomeCompleto(vendedorRequest.getNomeCompleto())
                .cpfCnpj(vendedorRequest.getCpfCnpj()).build();
    }

    public static VendedorResumoResponse of(Vendedor vendedor) {
        return VendedorResumoResponse.builder()
                .id(vendedor.getId())
                .nomeCompleto(vendedor.getNomeCompleto())
                .cpfCnpj(vendedor.getCpfCnpj()).build();
    }
}