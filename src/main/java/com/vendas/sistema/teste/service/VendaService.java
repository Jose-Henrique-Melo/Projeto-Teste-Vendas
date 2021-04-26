package com.vendas.sistema.teste.service;

import com.vendas.sistema.teste.dto.request.VendaRequest;
import com.vendas.sistema.teste.dto.response.VendaResumoResponse;
import com.vendas.sistema.teste.dto.response.VendasVendedorPorPeriodoResponse;
import com.vendas.sistema.teste.model.entities.Produto;
import com.vendas.sistema.teste.model.entities.Venda;
import com.vendas.sistema.teste.model.entities.enums.ESituacaoVenda;
import com.vendas.sistema.teste.model.exception.EntidadeNaoEncontradaExpection;
import com.vendas.sistema.teste.model.exception.NegocioException;
import com.vendas.sistema.teste.repositories.ClienteRepository;
import com.vendas.sistema.teste.repositories.ProdutoRepository;
import com.vendas.sistema.teste.repositories.VendaRepository;
import com.vendas.sistema.teste.repositories.VendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VendaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private VendaResumoResponse toModel(Venda venda) {
        return modelMapper.map(venda, VendaResumoResponse.class);
    }

    private List<VendaResumoResponse> toColletionModel(List<Venda> vendas) {
        return vendas.stream().map(venda -> toModel(venda)).collect(Collectors.toList());
    }

    private Venda toEntity(VendaRequest vendaRequest) {
        return modelMapper.map(vendaRequest, Venda.class);
    }

    public List<VendaResumoResponse> listarVendas() {
        return toColletionModel(vendaRepository.findAll());
    }

    public ResponseEntity<VendaResumoResponse> listarVendaPorId(Integer vendaId) {
        Optional<Venda> venda = vendaRepository.findById(vendaId);

        if (venda.isPresent()) {
            VendaResumoResponse vendaResumoResponse = toModel(venda.get());
            return ResponseEntity.ok(vendaResumoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    private void defineCliente(Venda venda) {
        var cliente = clienteRepository.findById(venda.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
        venda.setCliente(cliente);
    }

    private void defineVendedor(Venda venda) {
        var vendedor = vendedorRepository.findById(venda.getVendedor().getId()).orElseThrow(() -> new NegocioException("Vendedor não encontrado!"));
        venda.setVendedor(vendedor);
    }

    private void defineProdutos(Venda venda) {
        var produtos = produtoRepository.findAllById(venda.getProduto()
                .stream()
                .map(Produto::getId)
                .collect(Collectors.toList()));
        if (produtos.isEmpty()) {
            throw new NegocioException("Produtos não encontrados!");
        }
        venda.setProduto(produtos);
    }

    private void setInformacoesVendaAbertura(Venda venda) {
        venda.setStatus(ESituacaoVenda.ABERTA);
        venda.setDataAbertura(LocalDateTime.now());
    }

    public Venda salvarVenda(Venda venda) {
        defineCliente(venda);
        defineVendedor(venda);
        defineProdutos(venda);
        return vendaRepository.save(venda);
    }

    public VendaResumoResponse cadastrarVendaAbertura(VendaRequest vendaRequest) {
        Venda venda = toEntity(vendaRequest);
        setInformacoesVendaAbertura(venda);
        return toModel(salvarVenda(venda));
    }

    private Venda localizarVendaPorId(Integer vendaId) {
        return vendaRepository.findById(vendaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaExpection("Venda não encontrada!"));
    }

    public void finalizarVenda(Integer vendaId) {
        Venda venda = localizarVendaPorId(vendaId);
        venda.finalizar();
        vendaRepository.save(venda);
    }

    public void cancelarVenda(Integer vendaId) {
        Venda venda = localizarVendaPorId(vendaId);
        venda.cancelar();
        vendaRepository.save(venda);
    }

    public List<VendasVendedorPorPeriodoResponse> listarVendasPorPeriodo(String dataInicio, String dataFim) {
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");

        var quantidadeDias = (Float.valueOf(LocalDateTime.parse(dataInicio, formatterData)
                .until(LocalDateTime.parse(dataFim, formatterData), ChronoUnit.DAYS)));

        var dadosVendedor = vendaRepository.findAllByDateBetween(dataInicio, dataFim);

        return dadosVendedor.stream()
                .map(i -> {
                    var nomeVendedor = i.get(0, String.class);
                    var quantidadeVendas = i.get(1, Long.class);
                    var calcMedia = quantidadeVendas / (quantidadeDias == 0 ? 1 : quantidadeDias);
                    return VendasVendedorPorPeriodoResponse.of(nomeVendedor, quantidadeVendas, calcMedia);
                }).collect(Collectors.toList());
    }
}