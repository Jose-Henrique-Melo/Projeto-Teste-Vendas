INSERT INTO PRODUTO (ID, DESCRICAO, PRECO, DESCONTO)
VALUES (1, 'PRODUTO TESTE COM DESCONTO', 32.00, 0.1);

INSERT INTO PRODUTO (ID, DESCRICAO, PRECO)
VALUES (2, 'PRODUTO TESTE SEM DESCONTO', 55.00);

INSERT INTO CLIENTE (ID, CPF_CNPJ, NOME_COMPLETO)
VALUES (1, '555.553.444-44', 'JOSE CLIENTE PARA TESTE 2');

INSERT INTO VENDEDOR (ID, CPF_CNPJ ,NOME_COMPLETO)
VALUES (1, '111.111.222-22', 'JOSE VENDEDOR PARA TESTE');

INSERT INTO VENDA (ID, DATA_ABERTURA, STATUS, VENDEDOR_ID, CLIENTE_ID)
VALUES (1, '2021-04-10T20:39:51.627736','ABERTA', 1 , 1);

INSERT INTO VENDA_PRODUTO (VENDA_ID, PRODUTO_ID) VALUES (1, 1);

INSERT INTO VENDA_PRODUTO (VENDA_ID, PRODUTO_ID) VALUES (1, 1);

INSERT INTO VENDA (ID, DATA_ABERTURA, DATA_FINALIZACAO , STATUS, CLIENTE_ID, VENDEDOR_ID)
VALUES (2, '2021-04-10T20:39:51.627736', '2021-04-10T20:39:51.627736', 'FINALIZADA', 1, 1 );

INSERT INTO VENDA_PRODUTO (VENDA_ID, PRODUTO_ID) VALUES (2, 1);
INSERT INTO VENDA_PRODUTO (VENDA_ID, PRODUTO_ID) VALUES (2, 2);

INSERT INTO VENDA (ID, DATA_ABERTURA, DATA_FINALIZACAO , STATUS, CLIENTE_ID, VENDEDOR_ID)
VALUES (3, '2021-04-10T20:39:51.627736', '2021-04-10T20:39:51.627736', 'CANCELADA', 1, 1 );

INSERT INTO VENDA_PRODUTO (VENDA_ID, PRODUTO_ID) VALUES (3, 2);

INSERT INTO VENDA (ID, DATA_ABERTURA, STATUS, VENDEDOR_ID, CLIENTE_ID)
VALUES (4, '2021-04-10T20:39:51.627736','ABERTA', 1 , 1);