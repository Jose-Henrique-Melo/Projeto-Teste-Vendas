INSERT INTO VENDEDOR (ID ,CPF_CNPJ ,NOME_COMPLETO)
VALUES (1,'111.111.222-22', 'JOSE VENDEDOR PARA TESTE 1');

INSERT INTO VENDEDOR (ID, CPF_CNPJ ,NOME_COMPLETO)
VALUES (2, '111.111.222-22', 'JOSE VENDEDOR PARA TESTE 2');

INSERT INTO CLIENTE (ID, CPF_CNPJ, NOME_COMPLETO)
VALUES (1, '222.333.444-44', 'JOSE CLIENTE PARA TESTE 1');

INSERT INTO CLIENTE (ID, CPF_CNPJ, NOME_COMPLETO)
VALUES (2,'555.553.444-44', 'JOSE CLIENTE PARA TESTE 2');

INSERT INTO VENDA (ID, DATA_ABERTURA, STATUS, VENDEDOR_ID, CLIENTE_ID)
VALUES (1,'2021-04-10T20:39:51.627736','ABERTA', 2 , 1);