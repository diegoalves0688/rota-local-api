# rota-local-api

## Visão Geral
Projeto backend do sistema Rota Local, responsável pela implementação das API's de recursos relacionados à gestão das regras de negócio e persistência das entidades de domínio. Esta aplicação funciona em conjunto com [frontend rota-local](https://github.com/diegoalves0688/rota-local).

### Exemplo de interação entre as aplicações frontend e backend

![Diagrama projeto](/diagrama-projeto.png)

## Tecnologias utilizadas
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [ Spring Framework](https://spring.io/projects/spring-framework)

## Topologia

Abaixo temos uma imagem que descreve com mais detalhes a arquitetura e a interação no sistema Rota Local.

![API](/api.png)

1 - O ator acessa o [endereço do frontend](https://github.com/diegoalves0688/rota-local) disponibilizado em http://localhost:3000.

2 - O frontend realiza requisições na API REST do backend

3 - O backend utiliza como dependência o banco de dados Postgres, realizando consultas, inserções e atualizações de registros.

## Como executar localmente

### Pre requisitos
- **[Java 21:](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)** É necessário instalar essa versão do Java em seu sistema operacional para o correto funcionamento da aplicação backend.
- **[Docker compose](https://docs.docker.com/compose/install/) :**  Para provisionar o banco de dados, basta instalar o docker-compose e executar o seguinte comando na raiz do projeto: 
```
docker-compose up
```

Utilizando sua IDE preferida, execute o projeto.

### Executando com Visual Studio Code

Com as extensões para Java instaladas, é possível executar a aplicação diretamente no método principal(``main``), ou no ícone ``Run Java`` na barra superior à direita.

![Visual Studio Code](/ide.png)

## Recursos de API

Com a aplicação em execução já é possível enviar requisições para a API REST. Os recursos disponíveis estão relacionados às entidades de domínio abaixo:

 - Atrações
 - Usuários
 - Recomendações
 - Avaliações

 Toda a especificação da API pode ser encontrada nessa [Collection do Postman](https://github.com/diegoalves0688/rota-local-api/blob/main/rota_local_api.postman_collection.json). Ou pelo [link público direto.](https://documenter.getpostman.com/view/27022140/2sA2xmUVms)


## Como autenticar na api

Toda requisição que não seja do método [GET] precisa estar autenticada com um par de chaves de API, cada par representa um usuário no sistema. As chaves devem ser passadas como Headers nas requisições.

- ``X-API-KEY:`` Identifica o usuário que tem cadastro no sistema rotalocal.
- ``X-API-TOKEN:`` Chave de acesso gerada para o usuário para autenticar as requisições.

Para gerar uma par de chaves de acesso é necessário seguir os passos abaixo([Link com a especificação da API](https://documenter.getpostman.com/view/27022140/2sA2xmUVms#810594a4-6e4a-46dd-bc34-45c9d4438bd6)).

1° Enviar uma requisição com o usuário e senha para a api:

```
curl --location 'http://localhost:8080/api/usuario/autenticar' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "mail@mail.com",
    "senha": "123"
}'
```

2° Utilizar o `userId` e o `token` retornados na resposta para autenticar as requisições, passando os valores como headers `X-API-KEY` e `X-API-TOKEN`, como por exemplo:
 
```
curl --location 'localhost:8080/api/atracao' \
--header 'X-API-KEY: aqui-vai-o-userId' \
--header 'X-API-TOKEN: aqui-vai-o-token' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "batcaverna-testehhhNZZZ",
    "descricao": "cafofo do batmanhhhhN",
    "categoria": "TRILHAS",
    "usuario": {
        "id": 2
    },
    "localizacao": {
        "id": 4
    }
}'
```


## Como contribuir

1º Clone o projeto selecionando o endereço no repositório

```git clone git@github.com:diegoalves0688/rota-local-api.git```

2º Crie uma branch para sua nova feature usando o codigo abaixo

```git checkout -b nome-da-minha-branch```

3º Altere o código como desejar

4ª Adicionar as alterações para o staging. Na raiz do repositório ou na IDE:

```git add .```

5º Criar o commit do que foi adicionado ao staging

```git commit -m "exemplo de comentario"```

6º Faça o Push do seu commit para o Github

```git push```

ou 

```git push --set-upstream origin nome-da-minha-branch```

## Collection do Postman

[Arquivo json](https://github.com/diegoalves0688/rota-local-api/blob/main/rota_local_api.postman_collection.json)

## SQL com o banco de dados

[Arquivo SQL](https://github.com/diegoalves0688/rota-local-api/blob/main/database.sql)

## Especificação da API

[Link com a especificação da API](https://documenter.getpostman.com/view/27022140/2sA2xmUVms)