# 📚 Buscador de Livros LiterAlura - Oracle ONE
Este é o projeto do primeiro challenge proposto na formação `Java e Spring Framework T6 - ONE`, a qual formação pertence à grade do programa Oracle ONE.

## 💡Objetivo
O objetivo deste desafio foi proporcionar uma experiência real no mundo do desenvolvimento. Foi proposto para os alunos a construção de um software nomeado `LiteraLura`, que pudesse realizar consultas em uma API para persistir os livros no banco de dados e realizar algumas buscas personalizadas nos livros já salvos no banco. 

A API [`Gutendex`](https://gutendex.com/), a qual pertence ao [`Projeto Gutemberg`](https://www.gutenberg.org/), que por sua vez é uma biblioteca online com milhares de e-books, foi escolhida para alimentar o LiterAlura com seus dados.

No [`último projeto`](https://github.com/gPagio/bootcamp-oracle-alura-challenge-conversor-de-moedas/tree/main) construído proposto pelo Oracle One, os dados apenas ficavam salvos na memória durante a vida do programa. No projeto atual, os dados são persistidos em um banco de dados `PostgreSQL` por meio da API do `Spring Boot` que se chama `Spring Data JPA`.

## 📝 Funcionalidades
Logo abaixo estão listadas todas as funções ofertadas pelo LiterAlura:

```
1 - Buscar Livro por Titulo
2 - Listar Livro por Titulo
3 - Listar Todos os Livros
4 - Listar Todos os Autores
5 - Listar Autores Vivos em Determinado Ano
6 - Listar Livros Por Idioma
7 - Listar Livros Por Autor
8 - Buscar os TOP 10 Livros Mais Baixados da API
9 - Buscar os TOP 10 Livros Menos Baixados da API

0 - Sair
```
> [!NOTE]
> 1. Salvar Livros no Banco:
>    1. Para salvar um livro no banco use a primeira opção do Menu, `1 - Buscar Livro por Titulo`;
>    1. Após realizar a busca do livro pelo título, a função perguntará o ID do livro que deseja salvar;
> 1. Nomenclatura de Funções:
>    1. As opções `Buscar` farão buscas diretamente na API Gutendex;
>    1. As opções `Listar` farão buscas diretamente no banco de dados do projeto, ou seja, apenas dentre os dados que já foram salvos no banco.

## 📌 Dependências
Para o correto funcionamento do LiterAlura, é necessário realizar a instalação das dependências abaixo. Clique no hyperlink em cada uma delas para ir a respectiva página de downloads.
 - [`PostgreSQL`](https://www.postgresql.org/download/): Banco de dados usado pelo LiterAlura
 - [`Maven`](https://maven.apache.org/install.html): Gerenciador de dependências usado pelo LiterAlura

## ⚙️ Configurações
Antes de executar o projeto devemos configurar algumas variáveis de ambiente em nossa máquina.

Abaixo estão listadas as variáveis de deverão ser criadas e o conteúdo que deve conter em cada uma delas:
|Variável|Conteúdo|Exemplo|
|---|---|---|
|`DB_LITERALURA_HOST`|Host do banco de dados. O endereço para acessar o mesmo juntamente com a porta|127.0.0.1:5432|
|`DB_LITERALURA_DATABASE`|Informa o nome do banco de dados que o LiterAlura irá persistir|postgres|
|`DB_LITERALURA_SCHEMA`|Informa o nome esquema do banco escolhido anteriormente|public|
|`DB_LITERALURA_USER`|Informa o nome do usuário para se conectar ao banco de dados|postgres|
|`DB_LITERALURA_PASSWORD`|Informa a senha do usuário definido na variável anterior|postgres|

## 🚀 Uso
Para executar o projeto temos duas opções:

### 1ª Opção
Abra o mesmo com a `IDE IntelliJ IDEA` e execute o método abaixo na classe `LiteraluraApplication`:

``` Java
public static void main(String[] args)
```


### 2ª Opção
Entre na pasta do projeto pelo terminal e execute o comando abaixo:

```
mvn spring-boot:run
```
> [!NOTE]
> Caso encontre problemas com o Maven na execução do LiterAlura, instale a versão `3.9.5`, que foi usada na construção do projeto.

Após a execução do programa, basta escolher dentre as opções do menu para usar o software.

Para escolher dentre as opções, basta digitar a opção desejada no local indicado no terminal. Além disso, não há muito o que dizer por aqui, pois o software é extremamente simples e intuitivo.

## ⚠️ Avisos
1. Este projeto foi construído e testado sobre o JDK 21, dessa forma recomendamos o uso do mesmo durante a execução do mesmo.
