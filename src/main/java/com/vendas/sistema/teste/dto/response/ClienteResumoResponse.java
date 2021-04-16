package com.vendas.sistema.teste.dto.response;

import com.vendas.sistema.teste.dto.request.ClienteRequest;
import com.vendas.sistema.teste.model.entities.Cliente;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResumoResponse {

    private Integer id;
    private String nomeCompleto;
    private String cpfCnpj;

    public static Cliente of(ClienteRequest clienteRequest, Integer id) {
        return Cliente.builder()
                .id(id)
                .nomeCompleto(clienteRequest.getNomeCompleto())
                .cpfCnpj(clienteRequest.getCpfCnpj()).build();
    }

    public static ClienteResumoResponse of(Cliente cliente) {
        return ClienteResumoResponse.builder()
                .id(cliente.getId())
                .nomeCompleto(cliente.getNomeCompleto())
                .cpfCnpj(cliente.getCpfCnpj()).build();
    }
}