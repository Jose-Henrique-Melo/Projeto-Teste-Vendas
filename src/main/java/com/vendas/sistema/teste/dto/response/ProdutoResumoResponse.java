package com.vendas.sistema.teste.dto.response;

import com.vendas.sistema.teste.dto.request.ProdutoRequest;
import com.vendas.sistema.teste.model.entities.Produto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResumoResponse {

    private Integer id;
    private String descricao;
    private double preco;
    private Double desconto;

    public static Produto of(ProdutoRequest produtoRequest, Integer id) {
        return Produto.builder()
                .id(id)
                .descricao(produtoRequest.getDescricao())
                .preco(produtoRequest.getPreco())
                .desconto(produtoRequest.getDesconto()).build();
    }

    public static ProdutoResumoResponse of(Produto produto) {
        return ProdutoResumoResponse.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .desconto(produto.getDesconto()).build();
    }
}