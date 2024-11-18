## Sistema de Biblioteca

Com o objetivo de atender a entrega final do módulo de Arquitetura Java, nos foi proposto
a crição de um sistema atendesse a determinados requisitos, dessa forma, foi criado um sistema
de biblioteca.

## Introdução

O sistema de biblioteca será utilizado para gerenciar as operações triviais de uma biblioteca, como por exemplo, 
cadastro de livros, cadastro de clientes, empréstimos de livros, devolução de livros, etc.

Todo o projeto será desenvolvido utilizando a linguagem de programação Java, com o framework Spring Boot, como proposto
em relação as interfaces gráficas estarei utilizando o Angular + PrimeNG

## Roadmap

- [x] Criar e modelar entidades de domínio.
- [x] Criar e modelar as camadas de serviço e persistência (Services, Repositories).
- [x] Criar e modelar as camadas de controle/apresentação (Controllers).
- [x] Criar FrontEnd da aplicação e integrar endpoints.
- [x] Criar documentação com OpenAPI e Swagger.
- [x] Criar Docker-compose para subir FrontEnd + BackEnd

## Requisitos

- NodeJS > 20 (Caso queira subir o FrontEnd)
- JDK 17 (BackEnd)

## Como utilizar

- Docker (Recomendado)
Caso você tenha o Docker instalado basta clonar o projeto, ir até a raiz e utilizar os comandos abaixo em sequência.
```
./gradlew build
# Caso esteja no windows
./gradlew.bat build

docker compose up
```
Esses comandos serão suficiente para subir o BackEnd na porta 9000 e o FrontEnd na porta 4201 e utilizar a aplicação.

- CLI
Caso você não tenha o Docker, basta ir clonar o projeto, ir até a raiz e utilizar os seguintes comandos em sequência

  - Backend:
```
./gradlew bootRun
```
 - FrontEnd
Com o NodeJS instalado entre na pasta `frontend` na raiz e utilize a seguinte sequência de comandos:
```
corepack enable
pnpm install
pnpm run start
```

Após os passos acima a aplicação deverá estar disponível para ser acessada visualmente na porta 4201 ou via CURL na 8080.
