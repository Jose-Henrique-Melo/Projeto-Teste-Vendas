package com.vendas.sistema.teste.model.entities;

import com.vendas.sistema.teste.model.entities.enums.ESituacaoVenda;
import com.vendas.sistema.teste.model.exception.NegocioException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VENDA")
public class Venda {

    @Id
    @SequenceGenerator(name = "SEQ_VENDA", sequenceName = "SEQ_VENDA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_VENDA", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Vendedor vendedor;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Produto> produto = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ESituacaoVenda status;

    @Column(name = "DATA_ABERTURA")
    private LocalDateTime dataAbertura;

    @Column(name = "DATA_FINALIZACAO")
    private LocalDateTime dataFinalizacao;

    private boolean podeAlterarSituacaoVenda() {
        return ESituacaoVenda.ABERTA.equals(getStatus());
    }

    private boolean naoPodeAlterarSituacaoVenda() {
        return !podeAlterarSituacaoVenda();
    }

    public void finalizar() {
        if (naoPodeAlterarSituacaoVenda()) {
            throw new NegocioException("Venda já foi Finalizada ou Cancelada. Sua situação não poderá ser alterada!");
        }
        setStatus(ESituacaoVenda.FINALIZADA);
        setDataFinalizacao(LocalDateTime.now());
    }

    public void cancelar() {
        if (naoPodeAlterarSituacaoVenda()) {
            throw new NegocioException("Venda já foi Finalizada ou Cancelada. Sua situação não poderá ser alterada!");
        }
        setStatus(ESituacaoVenda.CANCELADA);
        setDataFinalizacao(LocalDateTime.now());
    }
}