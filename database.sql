DROP TABLE IF EXISTS avaliacao_recomendacao;
DROP TABLE IF EXISTS recomendacao;
DROP TABLE IF EXISTS avaliacao_atracao;
DROP TABLE IF EXISTS atracao;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS localizacao;
drop type if exists usuario_type;
drop type if exists categoria_type;

CREATE TABLE usuario(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   senha VARCHAR(255) NOT NULL,
   perfil VARCHAR NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE localizacao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   pais VARCHAR(255) NOT NULL,
   estado VARCHAR(255) NOT NULL,
   cidade VARCHAR(255) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE atracao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(255) NOT NULL,
   descricao VARCHAR NOT NULL,
   foto VARCHAR NOT NULL,
   categoria VARCHAR NOT NULL,
   usuario_id INT,
   localizacao_id INT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_localizacao FOREIGN KEY(localizacao_id) REFERENCES localizacao(id)
);

CREATE TABLE avaliacao_atracao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   avaliacao_positiva BOOLEAN NOT NULL,
   usuario_id INT,
   atracao_id INT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id)
);

CREATE TABLE recomendacao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   conteudo VARCHAR NOT NULL,
   usuario_id INT,
   atracao_id INT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id)
);

CREATE TABLE avaliacao_recomendacao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   avaliacao_positiva BOOLEAN NOT NULL,
   usuario_id INT,
   recomendacao_id INT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_recomendacao FOREIGN KEY(recomendacao_id) REFERENCES recomendacao(id)
);

INSERT INTO public.usuario (nome,email,senha,perfil) VALUES
	 ('John','mail@mail.com','123','ADMINISTRADOR');
	
INSERT INTO public.localizacao (pais,estado,cidade) VALUES
	 ('Brasil','Rio de Janeiro','Macae');

INSERT INTO public.atracao (nome,descricao,foto,categoria,usuario_id,localizacao_id) VALUES
	 ('Praia das pedrinhas','Uma linda praia no litoral de tão tão distante','http://localhost:8080/images/banner.png','PRAIAS',1,1);
INSERT INTO public.atracao (nome,descricao,foto,categoria,usuario_id,localizacao_id) VALUES
	 ('Praia das conchas','Uma linda praia no litoral de tão tão distante','http://localhost:8080/images/banner.png','PRAIAS',1,1);
INSERT INTO public.atracao (nome,descricao,foto,categoria,usuario_id,localizacao_id) VALUES
	 ('Praia das palmeiras','Uma linda praia no litoral de tão tão distante','http://localhost:8080/images/banner.png','PRAIAS',1,1);

INSERT INTO public.avaliacao_atracao (avaliacao_positiva,usuario_id,atracao_id) VALUES
	 (true,1,1);
	
INSERT INTO public.recomendacao (conteudo,usuario_id,atracao_id) VALUES
	 ('Aqui vai uma recomendação bacana sobre uma atração legal qualquer ',1,1);

INSERT INTO public.avaliacao_recomendacao (avaliacao_positiva,usuario_id,recomendacao_id) VALUES
	 (true,1,1);

