package com.vendas.sistema.teste.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Data
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PESSOA")
public abstract class Pessoa {

    @Column(name = "CPF_CNPJ")
    protected String cpfCnpj;

    @Column(name = "NOME_COMPLETO")
    protected String nomeCompleto;
}