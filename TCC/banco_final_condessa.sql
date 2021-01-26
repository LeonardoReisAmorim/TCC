CREATE DATABASE condessa;
DROP DATABASE condessa;
CREATE TABLE fornecedor(
   id INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(100) NOT NULL,
   cnpj VARCHAR(50),
   cidade VARCHAR(70) NOT NULL,
   contato VARCHAR(100),
   PRIMARY KEY (id)
);

CREATE TABLE materiaprima(
   lote INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   quantidade DOUBLE NOT NULL,
   preco NUMERIC(6,2) NOT NULL, 
   id_fornecedor INT NOT NULL,
   PRIMARY KEY (lote),
   CONSTRAINT FK_MATERIA_FORNECEDOR FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id)
);

CREATE TABLE produto (
   id INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL UNIQUE,
   unidade VARCHAR(10) NOT NULL,
   preco NUMERIC(5,2) NOT NULL,
   sta_prod BOOLEAN NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE cliente(
   id INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   telefone VARCHAR(50),
   bairro VARCHAR(50) NOT NULL,
   cidade VARCHAR(50) NOT NULL,
   rua VARCHAR(70) NOT NULL,
   numero INT NOT NULL,
   cep INT NOT NULL,
   cpf VARCHAR(20),
   DataDeNascimento VARCHAR(25),
   PRIMARY KEY (id)
);

CREATE TABLE estoque(
   id INT NOT NULL AUTO_INCREMENT,
   quantidade DOUBLE NOT NULL,
   LOTE_materia INT NOT NULL,
   ID_produto INT NOT NULL UNIQUE,
   quantidade_materiaprima DOUBLE NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT FK_ESTOQUE_LOTE FOREIGN KEY (LOTE_materia) REFERENCES materiaprima(lote),
   CONSTRAINT FK_ESTOQUE_PRODUTO FOREIGN KEY (ID_PRODUTO) REFERENCES produto(id)
);

CREATE TABLE vendas(
   id INT NOT NULL AUTO_INCREMENT,
   statusvenda VARCHAR(20) DEFAULT "A confirmar",
   preco NUMERIC(6,2),
   PRIMARY KEY (id)
);

CREATE TABLE item_venda(
   id INT NOT NULL AUTO_INCREMENT,
   id_cliente INT NOT NULL,
   id_vendas INT NOT NULL,
   id_estoque INT NOT NULL,
   quantidade_comprada DOUBLE NOT NULL,
   formadepagamento VARCHAR(50) NOT NULL,
   datavenda VARCHAR(15) NOT NULL,
   valor_compra NUMERIC(10,2),
   PRIMARY KEY(id),
   CONSTRAINT FK_ITEM_VENDA_ESTOQUE FOREIGN KEY (id_estoque) REFERENCES estoque(id),
   CONSTRAINT FK_ITEM_VENDA_VENDA FOREIGN KEY (id_vendas) REFERENCES vendas(id),
   CONSTRAINT FK_ITEM_VENDA_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE precomateria(
	click INT NOT NULL AUTO_INCREMENT,
	preco NUMERIC(8,2),
	lote_materia INT NULL,
	PRIMARY KEY (click),
	CONSTRAINT FK_PRECO_MATERIA FOREIGN KEY (lote_materia) REFERENCES materiaprima(lote)
);

DELIMITER $$
CREATE PROCEDURE materia()
BEGIN
     INSERT INTO precomateria(click, preco) VALUES(0, 0);
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER after_insert_materia
	AFTER INSERT ON materiaprima
        FOR EACH ROW
BEGIN		
     UPDATE precomateria SET preco = preco + (new.quantidade*new.preco), lote_materia = 1  WHERE click=1;
END $$
DELIMITER ;


# Relatorio de vendas
SELECT iv.id_vendas, c.nome, iv.formadepagamento, iv.datavenda, GROUP_CONCAT(p.nome SEPARATOR ', ') AS "Nome dos produtos", 
GROUP_CONCAT(iv.quantidade_comprada SEPARATOR ' | ') AS "Qtd_compra", iv.valor_compra
FROM item_venda iv
INNER JOIN vendas v ON v.id = iv.id_vendas
INNER JOIN cliente c ON c.id = iv.id_cliente
INNER JOIN estoque e ON e.id = iv.id_estoque
INNER JOIN produto p ON p.id = e.ID_produto
WHERE v.statusvenda = "Venda concluída"
GROUP BY iv.id_vendas;


# relatorio de estoque
SELECT p.nome, p.preco, es.quantidade, p.unidade, es.quantidade_materiaprima, SUM(IFNULL(iv.quantidade_comprada, 0))
FROM produto p
INNER JOIN estoque es ON es.ID_produto = p.id
LEFT JOIN item_venda iv ON iv.id_estoque = es.id
GROUP BY p.nome
ORDER BY SUM(iv.quantidade_comprada) DESC;


# relatorio de cliente
SELECT  c.nome, c.cep, c.telefone, p.nome, SUM(iv.quantidade_comprada)
FROM cliente c
LEFT JOIN item_venda iv ON iv.id_cliente = c.id
INNER JOIN estoque e ON e.id = iv.id_estoque
INNER JOIN produto p ON p.id = e.ID_produto 
GROUP BY iv.id_cliente, iv.id_estoque
ORDER BY SUM(iv.quantidade_comprada) DESC;

#lucro
SELECT p.preco AS 'Custo total', SUM(v.preco) AS 'Lucro bruto', SUM(v.preco)-p.preco AS 'Lucro líquido'
FROM vendas v
INNER JOIN precomateria p
WHERE p.click=1 AND v.statusvenda = 'Venda concluída'


SELECT * FROM item_venda







