package com.vendas.sistema.teste.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @SequenceGenerator(name = "SEQ_PRODUTO", sequenceName = "SEQ_PRODUTO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_PRODUTO", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @Size(min = 2, max = 60)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Min(1)
    @Max(999999999)
    @Column(name = "PRECO")
    private double preco;

    @Min(0)
    @Max(1)
    @Column(name = "DESCONTO")
    private Double desconto;
}