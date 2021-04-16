package com.vendas.sistema.teste.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VENDEDOR")
public class Vendedor extends Pessoa {

    @Id
    @SequenceGenerator(name = "SEQ_VENDEDOR", sequenceName = "SEQ_VENDEDOR", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_VENDEDOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;
}