# # homeproject-vendas

Repositório Projeto Teste - Vendas

API REST com finalidade de gerar **VENDAS**, baseada em dados de **CLIENTE**, **VENDEDOR** E **PRODUTUOS**, desenvolvida com Spring Boot, utilizando o banco de dados em memória H2 e com integração com o Spring Boot Data JPA.

Para rodar a aplicação, primeramente importe as dependência necessárias do Maven e execute com.vendas.sistema.teste.Application.java

---

Para testar o fluxo da venda, deverá utilizar as seguintes URL:

* Primeiro passo, criar o vendedor:
POST -> localhost:8080/api/vendedores 
com o seguinte body: 

{
"cpfCnpj" : "110.321.321-21",

"nomeCompleto" : "JOSE HENRIQUE VENDEDOR"
}

---

* Em sequência, criar o cliente:
POST -> localhost:8080/api/clientes 
com o seguinte body: 

{
"cpfCnpj" : "143.123.432-11",

"nomeCompleto" : "JOSE HENRIQUE CLIENTE"
}

----
* Por final, criar os produtos:
com o seguinte body:

{
"descricao" : "Pera",

"preco" : "7.50",

"desconto" : "0.1"
}
>> **Nota** Produto poderá ser cadastrado sem desconto. Seu valor deverá estar entre 0.0 e 1.0.

---

**Tendo todos os dados necessários, basta enviar uma última requisição:**
* POST -> localhost:8080/api/vendas
com o seguinte body: 

{

"cliente":{

"id": 1

},

"vendedor":{

"id": "1"

},

"produto":[

{"id": "1"}

]
}

>> **Nota** Caso deseje adicionar mais de um produto para, basta alterar o array de produto para:
>"produto": [
{"id": "1"},{"id": "2"}
]


#### Cobertura geral de testes
![enter image description here](%5BImgur%5D%28https://imgur.com/9juOzkg%29)

[enter link description here](https://imgur.com/9juOzkg)



# Projeto-Teste-Vendas
