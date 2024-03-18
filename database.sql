
/*----------------------------------------------------------------
                    POSTGRESQL | ROTALOCAL 
----------------------------------------------------------------*/

------------------------- EXCLUIR TABELAS -------------------------

DROP TABLE IF EXISTS recomendacao_atracao CASCADE;
DROP TABLE IF EXISTS avaliacao_atracao CASCADE;
DROP TABLE IF EXISTS avaliacao_recomendacao CASCADE;
DROP TABLE IF EXISTS atracao CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS localizacao CASCADE;
DROP TABLE IF EXISTS imagem CASCADE;
DROP TABLE IF EXISTS recomendacao CASCADE;
DROP TYPE IF EXISTS perfil_usuario CASCADE;
DROP TYPE IF EXISTS categoria_atracao CASCADE;
DROP TYPE IF EXISTS status_atracao CASCADE;


------------------------- CRIAR TABELAS -------------------------
--CLASSES
CREATE TABLE usuario(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   senha VARCHAR(255) NOT NULL,
   foto VARCHAR(255) NOT NULL,
   ativo boolean NOT NULL,
   perfil VARCHAR(255) NOT NULL,
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
   descricao text NOT NULL,
   ativo boolean NOT NULL,
   categoria VARCHAR(255) NOT NULL, 
   status VARCHAR(255) NOT NULL, 
   data_registro timestamp not null,
   usuario_id BIGINT,
   localizacao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_localizacao FOREIGN KEY(localizacao_id) REFERENCES localizacao(id)

);

CREATE TABLE imagem(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(255) NOT NULL,
   url_caminho VARCHAR(255) NOT NULL,
   atracao_id BIGINT,
   usuario_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

CREATE TABLE avaliacao_atracao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   avaliacao_positiva BOOLEAN NOT NULL,
   usuario_id BIGINT,
   atracao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id)
);

CREATE TABLE recomendacao_atracao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   recomendacao text NOT NULL,
   data_registro timestamp not null,
   usuario_id BIGINT,
   atracao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id)
);

CREATE TABLE avaliacao_recomendacao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   avaliacao_positiva BOOLEAN NOT NULL,
   usuario_id BIGINT,
   recomendacao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_recomendacao FOREIGN KEY(recomendacao_id) REFERENCES recomendacao_atracao(id)
);


------------------------- POPULAR TABELAS -------------------------
	
INSERT INTO public.localizacao (pais,estado,cidade) VALUES
	('Brasil','Rio de Janeiro','Macae')
   ,('Brasil', 'Sao Paulo', 'Sao Paulo')
   ,('Brasil', 'Minas Gerais', 'Belo Horizonte')
   ,('Brasil', 'Rio de Janeiro', 'Rio de Janeiro')
   ,('Brasil', 'Minas Gerais', 'Belo Horizonte')
   ,('Brasil', 'Bahia', 'Salvador')
   ,('Brasil', 'Ceara', 'Fortaleza')
   ,('Brasil', 'Parana', 'Curitiba')
   ,('Brasil', 'Rio Grande do Sul', 'Porto Alegre')
   ,('Brasil', 'Pernambuco', 'Recife')
   ,('Brasil', 'Amazonas', 'Manaus')
   ,('Brasil', 'Distrito Federal', 'Brasilia');

INSERT INTO public.usuario (nome, email, senha, foto, ativo, perfil) VALUES 
('Monica',    'monica@mail.com'   , 'senha-123', 'http://localhost:8080/images/monica.jpg'   , true, 'ADMINISTRADOR'),
('Cebolinha', 'cebolinha@mail.com', 'senha-123', 'http://localhost:8080/images/cebolinha.png', true, 'ADMINISTRADOR'),
('Batman',    'batman@mail.com'   , 'senha-123', 'http://localhost:8080/images/batman.jpg'   , true, 'COLABORADOR'),
('Coringa',   'coringa@mail.com'  , 'senha-123', 'http://localhost:8080/images/coringa.png'  , true, 'COLABORADOR'), --4
('Wolverine', 'wolverine@mail.com', 'senha-123', 'http://localhost:8080/images/wolverine.jpg', true, 'COLABORADOR'),
('Jean grey', 'jeangrey@mail.com' , 'senha-123', 'http://localhost:8080/images/jean-grey.png', true, 'COLABORADOR'),
('Ciclope',   'ciclope@mail.com'  , 'senha-123', 'http://localhost:8080/images/ciclope.png'  , true, 'COLABORADOR'), --7
('Arlequina', 'arlequina@mail.com', 'senha-123', 'http://localhost:8080/images/arlequina.png', true, 'COLABORADOR'),
('Pinguim',   'pinguim@mail.com'  , 'senha-123', 'http://localhost:8080/images/pinguim.png'  , true, 'COLABORADOR');

INSERT INTO public.atracao (nome, descricao, ativo, categoria, status, data_registro, usuario_id, localizacao_id) VALUES
('Praia das pedrinhas','Situada ao longo da costa serena, Praia das pedrinhas é uma joia fascinante que captura a essência da tranquilidade e da beleza natural. Ao pisar nas areias macias e pulverulentas, senti uma onda instantânea de relaxamento tomar conta de mim, como se o estresse do mundo fosse deixado para trás a cada pegada. A primeira coisa que me impressionou em Sunset Bay Beach foi sua beleza imaculada. As águas azuis batiam suavemente na costa, criando uma sinfonia relaxante que ecoava pelo ar. A extensa extensão de areia dourada parecia estender-se infinitamente, convidando-me a passear e explorar os seus tesouros escondidos.', true,'PRAIAS' ,'PUBLICO', current_timestamp,1,1),
('Praia das ilha das couves','Além de suas belezas naturais, Praia das ilha das couves também oferece uma série de atividades para os visitantes desfrutarem. Desde passeios tranquilos ao longo da costa até esportes aquáticos de aventura, como mergulho com snorkel e paddleboard, há algo para todos experimentarem. Gostei particularmente de dar um mergulho nas águas cristalinas, onde fui recebido por um caleidoscópio de vida marinha colorida que prosperava abaixo da superfície. Para aqueles que procuram um retiro tranquilo longe da agitação da vida cotidiana, Praia das ilha das couves é o destino perfeito. Quer pretenda relaxar com um bom livro à sombra de uma palmeira ou simplesmente aproveitar o calor do sol, este oásis idílico oferece um santuário para a alma.', true,'PRAIAS' ,'PUBLICO', current_timestamp,1,2),
('Cristo rendentor','No topo da cidade está instalado Cristo Redentor, os lados turísticos mais procurados do Brasil e do Rio de Janeiro. Maior e mais famosa escritura Art Déco do mundo, a estátua de Cristo começou a ser planejada em 1921 e foi desenvolvida pelo engenheiro Heitor da Silva Costa ao longo de 6 anos de trabalho, de 1925 a 1931, o ano de abertura do monumento. Localizado no Parque Nacional da Tijuca, a 710 metros acima do nível do mar, onde qualquer um pode apreciar uma das mais belas vistas da cidade. Ao longo de todos os 220 degraus que levam aos famosos pés da estátua, foi eleita uma das Sete Maravilhas do Mundo, realizada por votação formal em 2007 pela New 7 Wonders Foundation da Instituição Suíça. O monumento é acessível de trem, van ou carro.', true,'MONUMENTOS' ,'PUBLICO', current_timestamp,2,4),
('Batcaverna','No coração de Gotham City encontra-se uma maravilha escondida que intriga moradores e visitantes há gerações: a lendária Caverna do Batman. Aninhado sob as ruas movimentadas da cidade, este santuário enigmático serve como o covil secreto do próprio Cavaleiro das Trevas, oferecendo um vislumbre do misterioso mundo do cruzado de capa de Gotham. Desde o momento em que desci às profundezas da Caverna do Batman, fui envolvido por uma atmosfera de intriga e expectativa. Os corredores mal iluminados e os túneis sinuosos aumentavam a sensação de mistério, como se eu estivesse embarcando em uma aventura emocionante rumo ao desconhecido.', true, 'MONUMENTOS' ,'PUBLICO', current_timestamp,3,5),
('Fogo de chao','Recomendo a todos os cariocas e turistas que estejam no Rio uma visita; a vista é uma delícia e pra quem é de Instagram dá pra fazer fotos lindas; o bufê de salada é o máximo da qualidade com sugestões de combinações surpreendentes: physalis com gorgonzola, brie com morango, esplendidos aspargos frescos, folhas hiper crocantes, alcachofras maravilhosas, presunto cru, legumes grelhados um nunca se acabar de delícias; e quarta e sábado ainda tem feijoada; as carnes e os acompanhamentos têm o mesmo patamar de excelência do bufê de salada; tudo excepcional; acho uma maravilha que a gente paga o que elege comer e as opções são múltiplas; o atendimento é de cair o queixo, se estiver lotado ou não é impecável e melhor ainda, gentil, simpático e bem humorado; o petit gateau é de querer que o dia de voltar chegue logo; se dê este presente sempre que puder.', true, 'RESTAURANTES' ,'PUBLICO', current_timestamp,5,7),
('Masp','O MASP, Museu de Arte de São Paulo, destaca-se por sua arquitetura única e coleção de renomados artistas internacionais como Van Gogh e Picasso, além de representações significativas da arte brasileira. O amplo espaço sob o museu é utilizado para eventos culturais, incluindo uma feira de antiguidades aos domingos. A integração da cultura e da comunidade faz do MASP uma atração dinâmica e enriquecedora, oferecendo uma experiência artística diversificada.', true, 'MUSEUS' ,'PUBLICO', current_timestamp,4,2),
('Cachoeira do buracao','Localizada Curitiba, a parte elevada da Cachoeira do buracao, é uma das mais belas do mundo. No inicio começa em um campo aberto com uma pequena cachoeira. Sem um bom poço para banho, só a vista da cachoeira em meio à mata faz valer o esforço. Quase 80 pés de queda após uma trilha que leva a uma piscina na base das cataratas.', true, 'CACHOEIRAS' ,'PUBLICO', current_timestamp,6,8),
('Pinguim Nightclub','Linda boate, com um show maravilhoso do alok, mas perde pelo mal atendimento aos clientes. Funcionários do bar desinformados, não sabiam o valor certo do produto que estava sendo vendido no caixa. O barmam, coitado, estava super desorientado pq estava ficando a maior parte do tempo sozinho no bar, tanto que eu pedi um tropical gin, ele colocou o energético e esqueceu do gin, tive que lembra-lo. Sem falar nos valores exorbitantes, 10 reais por uma garrafa de água, não fornecem copos, temos que comprar e custa 5 reais. E ainda pagamos também o cartão, que serve como uma comanda. Cheguei a brincar que se eu respirasse, teria que pagar mais 5 reais.', true, 'BOATES' ,'PUBLICO', current_timestamp,9,12),
('Pedra da Mariola', 'A Pedra da Mariola possui 844 metros, fica localizada dentro do Parque Nacional da Tijuca e é uma das formações rochosas mais admiradas e famosas de Manaus. É o desafio ideal para quem procura por muita aventura, adrenalina e pelo visual mais bonito do Amazonas. Iniciaremos a famosa TRILHA da PEDRA DA GÁVEA que fica localizado no Parque Nacional da Tijuca, a maior floresta urbana do mundo. O itinerário será pelos principais pontos da trilha, como Pedra do Navio, Mirante da pedra bonita, paredão da Gávea, Carrasqueira, Plãto da Gávea, Cabeça do Imperador e Pirâmide e Cachoeira Solimã. Além de toda a beleza da fauna e flora da floresta tropical. O tempo de trilha é de aproximadamente 6 horas e total de 3 km, no caminho iremos nos conectar com a natureza, os animais que lá habitam e a energia da floresta urbana. Iremos passar por uns trechos de Pedras para subir aonde iniciaremos nossa "escalaminhada", onde já teremos equipamentos necessários para realizar essa aventura, aonde fica melhor ainda nossa experiência na trekking.', true, 'TRILHAS' ,'PUBLICO', current_timestamp,7,11);


INSERT INTO public.imagem (nome,url_caminho,usuario_id,atracao_id) VALUES
('praia-pedrinhas.jpg','http://localhost:8080/images/praia-pedrinhas.jpg',1,1),
('praia-ilha-couves.jpg','http://localhost:8080/images/praia-ilha-couves.jpg',1,2),
('cristo-redentor.jpg','http://localhost:8080/images/cristo-redentor.jpg',2,3),
('batcaverna.png','http://localhost:8080/images/batcaverna.png',3,4),
('fogo-chao.jpg','http://localhost:8080/images/fogo-chao.jpg',5,5),
('masp.jpg','http://localhost:8080/images/masp.jpg',4,6),
('cachoeira-buracao.jpg','http://localhost:8080/images/cachoeira-buracao.jpg',6,7),
('boate.jpg','http://localhost:8080/images/boate.jpg',9,8),
('trilha-rio-boi.jpeg','http://localhost:8080/images/trilha-rio-boi.jpeg',7,9);


INSERT INTO public.avaliacao_atracao (avaliacao_positiva,usuario_id,atracao_id) VALUES
(true,1,1),
(true,2,1),
(true,3,1),
(true,4,1),
(true,5,1),
(false,1,2),
(false,2,2),
(false,3,2),
(false,4,2),
(false,5,2),
(false,6,2),
(true,4,3),
(true,5,3),
(false,1,3),
(true,1,4),
(true,1,4),
(false,2,4),
(true,4,4),
(true,5,4),
(true,6,4),
(true,1,5),
(true,6,5),
(true,3,6),
(true,8,6),
(true,9,6),
(false,1,6),
(true,6,7),
(true,8,7),
(true,3,7),
(true,5,8),
(false,1,8),
(false,2,8),
(false,3,8),
(false,4,8),
(false,7,8),
(false,5,9);
	
INSERT INTO public.recomendacao_atracao (recomendacao, data_registro, usuario_id, atracao_id) VALUES
('consegui pegar um bronze', current_timestamp, 2,1),
('nao é tranquila! roubaram minha bolsa', current_timestamp, 6,2),
('muito quente, mas sensacional', current_timestamp, 9,3),
('horrivel esse lugar, cheio de morcego', current_timestamp, 9,4),
('fiquei sabendo que o wolverine usa as garrinhas pra cortar as carnes', current_timestamp, 7,5),
('único lugar que nao brigo com o coringa!', current_timestamp, 3,6),
('vou direto com o pinguim nadar ai', current_timestamp, 4,7),
('lugar LIXO', current_timestamp, 3,8),
('romantico, paisagem linda :) ', current_timestamp, 6,9);

INSERT INTO public.avaliacao_recomendacao (avaliacao_positiva,usuario_id,recomendacao_id) VALUES
(true,3,1),
(true,2,1),
(true,4,1),
(true,5,1),
(true,1,2),
(true,3,2),
(true,1,3),
(true,2,3),
(true,4,3),
(true,5,3),
(true,6,3),
(true,7,3),
(false,8,3),
(true,8,4),
(true,4,4),
(true,2,5),
(true,3,5),
(true,7,5),
(false,1,5),
(true,8,6),
(true,6,7),
(true,8,7),
(true,3,7),
(true,1,8),
(true,2,8),
(true,5,8),
(false,9,8),
(false,4,8),
(false,8,8),
(false,8,8),
(false,5,9);
 