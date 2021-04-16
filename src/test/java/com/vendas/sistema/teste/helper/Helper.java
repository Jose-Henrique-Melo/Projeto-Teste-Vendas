package com.vendas.sistema.teste.helper;

import com.vendas.sistema.teste.dto.request.ClienteRequest;
import com.vendas.sistema.teste.dto.request.ProdutoRequest;
import com.vendas.sistema.teste.dto.request.VendaRequest;
import com.vendas.sistema.teste.dto.request.VendedorRequest;
import com.vendas.sistema.teste.dto.response.ClienteResumoResponse;
import com.vendas.sistema.teste.dto.response.ProdutoResumoResponse;
import com.vendas.sistema.teste.dto.response.VendaResumoResponse;
import com.vendas.sistema.teste.dto.response.VendedorResumoResponse;
import com.vendas.sistema.teste.model.entities.Cliente;
import com.vendas.sistema.teste.model.entities.Produto;
import com.vendas.sistema.teste.model.entities.Vendedor;
import com.vendas.sistema.teste.model.entities.enums.ESituacaoVenda;

import java.time.LocalDateTime;
import java.util.List;

public class Helper {

    public static Cliente umCliente() {
        return Cliente.builder()
                .id(1)
                .nomeCompleto("JOSE CLIENTE PARA TESTE")
                .cpfCnpj("222.333.444-44").build();
    }

    public static Cliente umClienteNaoEncontrado() {
        return Cliente.builder()
                .id(10000)
                .nomeCompleto("JOSE CLIENTE PARA TESTE NAO ENCONTRADO")
                .cpfCnpj("111.333.444-44").build();
    }

    public static Vendedor umVendedor() {
        return Vendedor.builder()
                .id(1)
                .nomeCompleto("JOSE VENDEDOR PARA TESTE")
                .cpfCnpj("111.111.222-22").build();
    }

    public static Vendedor umVendedorNaoEncontrado() {
        return Vendedor.builder()
                .id(1000)
                .nomeCompleto("JOSE VENDEDOR PARA TESTE NAO ENCONTRADO")
                .cpfCnpj("777.111.222-22").build();
    }

    public static Produto umProdutoTesteComDesconto() {
        return Produto.builder()
                .id(1)
                .descricao("PRODUTO TESTE COM DESCONTO")
                .preco(32.00)
                .desconto(0.1).build();
    }

    public static Produto umProdutoTesteSemDesconto() {
        return Produto.builder()
                .id(4)
                .descricao("PRODUTO TESTE SEM DESCONTO")
                .preco(55.00)
                .build();
    }

    public static Produto umProdutoTesteComDescontoNaoEncontrado() {
        return Produto.builder()
                .id(10000)
                .descricao("PRODUTO TESTE COM DESCONTO NAO ENCONTRADO")
                .preco(32.00)
                .desconto(0.1).build();
    }

    public static Produto umProdutoTesteSemDescontoNaoEncontrado() {
        return Produto.builder()
                .id(40000)
                .descricao("PRODUTO TESTE SEM DESCONTO")
                .preco(55.00)
                .build();
    }

    public static ClienteRequest umClienteRequest() {
        return ClienteRequest.builder()
                .nomeCompleto("CLIENTE REQUEST TESTE")
                .cpfCnpj("121.431.123-32").build();
    }

    public static ClienteRequest umClienteFalhaRequest() {
        return ClienteRequest.builder()
                .nomeCompleto("CLIENTE REQUEST TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
                .cpfCnpj("112.221.563-113333333").build();
    }

    public static ClienteRequest umClienteAtualizarRequest() {
        return ClienteRequest.builder()
                .nomeCompleto("CLIENTE ATUALIZAR REQUEST TESTE")
                .cpfCnpj("362.423.153-61").build();
    }

    public static ClienteRequest umClienteAtualizarRequest2() {
        return ClienteRequest.builder()
                .nomeCompleto("CLIENTE ATUALIZAR REQUEST TESTE 2")
                .cpfCnpj("311.423.153-61").build();
    }

    public static ClienteRequest umClienteAtualizarFalhaRequest() {
        return ClienteRequest.builder()
                .nomeCompleto("VENDEDOR REQUEST TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
                .cpfCnpj("331.211.543-543333333").build();
    }

    public static VendedorRequest umVendedorRequest() {
        return VendedorRequest.builder()
                .nomeCompleto("VENDEDOR REQUEST TESTE")
                .cpfCnpj("331.211.543-54").build();
    }

    public static VendedorRequest umVendedorFalhaRequest() {
        return VendedorRequest.builder()
                .nomeCompleto("VENDEDOR REQUEST TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
                .cpfCnpj("331.211.543-543333333").build();
    }

    public static VendedorRequest umVendedorAtualizarRequest() {
        return VendedorRequest.builder()
                .nomeCompleto("VENDEDOR ATUALIZAR REQUEST TESTE")
                .cpfCnpj("234.645.865-88").build();
    }

    public static VendedorRequest umVendedorAtualizarRequest2() {
        return VendedorRequest.builder()
                .nomeCompleto("VENDEDOR ATUALIZAR REQUEST TESTE 2")
                .cpfCnpj("211.645.865-88").build();
    }

    public static VendedorRequest umVendedorAtualizarFalhaRequest() {
        return VendedorRequest.builder()
                .nomeCompleto("VENDEDOR ATUALIZAR REQUEST TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
                .cpfCnpj("234.645.865-883333333").build();
    }

    public static ProdutoRequest umProdutoRequestComDesconto() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO REQUEST COM DESCONTO")
                .preco(33.22)
                .desconto(0.1).build();
    }

    public static ProdutoRequest umProdutoRequestComDesconto2() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO REQUEST COM DESCONTO 2")
                .preco(22.22)
                .desconto(0.2).build();
    }

    public static ProdutoRequest umProdutoRequestSemDesconto() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO REQUEST SEM DESCONTO")
                .preco(33.22).build();
    }

    public static ProdutoRequest umProdutoRequestComDescontoFalha() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO REQUEST COM DESCONTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
                .preco(33.2222222222)
                .desconto(1.1).build();
    }

    public static ProdutoRequest umProdutoRequestSemDescontoFalha() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO REQUEST COM DESCONTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
                .preco(33.2222222222).build();
    }

    public static ProdutoRequest umProdutoRequestAtualizarComDesconto() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO ATUALIZAR COM DESCONTO")
                .preco(55.22)
                .desconto(0.1).build();
    }

    public static ProdutoRequest umProdutoRequestAtualizarSemDesconto() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO ATUALIZAR SEM DESCONTO")
                .preco(33.22).build();
    }

    public static ProdutoRequest umProdutoRequestAtualizarComDescontoFalha() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO REQUEST ATUALIZAR COM DESCONTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
                .preco(33.22222222)
                .desconto(1.1).build();
    }

    public static ProdutoRequest umProdutoRequestAtualizarSemDescontoFalha() {
        return ProdutoRequest.builder()
                .descricao("PRODUTO REQUEST ATUALIZAR COM DESCONTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
                .preco(33.22222222).build();
    }

    public static ClienteResumoResponse umClienteResumoResponse() {
        return ClienteResumoResponse.of(umCliente());
    }

    public static ClienteResumoResponse umClienteResumoResponseNaoEncontrado() {
        return ClienteResumoResponse.of(umClienteNaoEncontrado());
    }

    public static VendedorResumoResponse umVendedorResumoResponse() {
        return VendedorResumoResponse.of(umVendedor());
    }

    public static VendedorResumoResponse umVendedorResumoResponseNaoEncontrado() {
        return VendedorResumoResponse.of(umVendedorNaoEncontrado());
    }

    public static ProdutoResumoResponse umProdutoResumoResponseComDesconto() {
        return ProdutoResumoResponse.of(umProdutoTesteComDesconto());
    }

    public static ProdutoResumoResponse umProdutoResumoResponseSemDesconto() {
        return ProdutoResumoResponse.of(umProdutoTesteSemDesconto());
    }

    public static ProdutoResumoResponse umProdutoResumoResponseComDescontoNaoEncontrado() {
        return ProdutoResumoResponse.of(umProdutoTesteComDescontoNaoEncontrado());
    }

    public static ProdutoResumoResponse umProdutoResumoResponseSemDescontoNaoEncontrado() {
        return ProdutoResumoResponse.of(umProdutoTesteSemDescontoNaoEncontrado());
    }

    public static List<ProdutoResumoResponse> umaListaProdutoResumoResponse() {
        return List.of(umProdutoResumoResponseComDesconto(), umProdutoResumoResponseSemDesconto());
    }

    public static List<ProdutoResumoResponse> umaListaProdutoResumoResponseNaoEncontrado() {
        return List.of(umProdutoResumoResponseComDescontoNaoEncontrado(), umProdutoResumoResponseSemDescontoNaoEncontrado());
    }

    public static VendaRequest umaVendaRequest() {
        return VendaRequest.builder()
                .cliente(umClienteResumoResponse())
                .vendedor(umVendedorResumoResponse())
                .produto(umaListaProdutoResumoResponse()).build();
    }

    public static VendaResumoResponse umaVendaResumoResponseAberta() {
        return VendaResumoResponse.builder()
                .idVenda(1)
                .cliente(umClienteResumoResponse())
                .vendedor(umVendedorResumoResponse())
                .produto(umaListaProdutoResumoResponse())
                .status(ESituacaoVenda.ABERTA)
                .dataAbertura(LocalDateTime.now()).build();
    }

    public static VendaRequest umaVendaRequestSemCliente() {
        return VendaRequest.builder()
                .cliente(umClienteResumoResponseNaoEncontrado())
                .vendedor(umVendedorResumoResponse())
                .produto(umaListaProdutoResumoResponse()).build();
    }

    public static VendaRequest umaVendaRequestSemVendedor() {
        return VendaRequest.builder()
                .cliente(umClienteResumoResponse())
                .vendedor(umVendedorResumoResponseNaoEncontrado())
                .produto(umaListaProdutoResumoResponse()).build();
    }

    public static VendaRequest umaVendaRequestSemProdutos() {
        return VendaRequest.builder()
                .cliente(umClienteResumoResponse())
                .vendedor(umVendedorResumoResponse())
                .produto(umaListaProdutoResumoResponseNaoEncontrado()).build();
    }
}