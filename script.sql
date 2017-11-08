CREATE TABLE clientes
(
    id INTEGER DEFAULT nextval('clientes_id_seq'::regclass) PRIMARY KEY NOT NULL,
    nome VARCHAR(50),
    rg VARCHAR(10),
    telefone VARCHAR(11)
);
CREATE TABLE pets
(
    id INTEGER DEFAULT nextval('pets_id_seq'::regclass) PRIMARY KEY NOT NULL,
    id_cliente INTEGER,
    nome VARCHAR(50),
    tipo VARCHAR(15),
    CONSTRAINT pets_clientes_id_fk FOREIGN KEY (id_cliente) REFERENCES clientes (id)
);
CREATE TABLE tipos_servicos
(
    id INTEGER DEFAULT nextval('tipos_servicos_id_seq'::regclass) PRIMARY KEY NOT NULL,
    nome VARCHAR(50),
    tipo_atendimento VARCHAR(25),
    preco DOUBLE PRECISION
);
CREATE TABLE vendas
(
    id INTEGER DEFAULT nextval('venda_id_seq'::regclass) PRIMARY KEY NOT NULL,
    id_cliente INTEGER,
    data_venda TIMESTAMP,
    CONSTRAINT venda_clientes_id_fk FOREIGN KEY (id_cliente) REFERENCES clientes (id)
);
CREATE TABLE vendas_items
(
    id_venda INTEGER,
    id_tipo_servico INTEGER,
    CONSTRAINT vendas_items_vendas_id_fk FOREIGN KEY (id_venda) REFERENCES vendas (id),
    CONSTRAINT vendas_items_tipos_servicos_id_fk FOREIGN KEY (id_tipo_servico) REFERENCES tipos_servicos (id)
);