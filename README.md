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

0 - Sair
```

## 🚀 Uso
Para executar o projeto temos duas opções:

1ª Opção
Abra o mesmo com a `IDE IntelliJ IDEA` e execute o método abaixo na classe `LiteraluraApplication`:

``` Java
public static void main(String[] args)
```


2ª Opção
Entre na pasta do projeto pelo terminal e execute o comando abaixo:

```
mvn spring-boot:run
```
> [!IMPORTANT]
> 1. Para uso da `2ª opção`, é necessário instalar e configurar o [`Maven`](https://maven.apache.org/install.html) em sua máquina.
> 1. Ao instalar a última versão do Maven em sua máquina, caso encontre problemas na execução do LiterAlura, instale a versão `3.9.5`, que foi usada na construção do projeto.

Após a execução do programa, basta escolher dentre as opções do menu para usar o software.

Para escolher dentre as opções, basta digitar a opção desejada no local indicado no terminal. Além disso, não há muito o que dizer por aqui, pois o software é extremamente simples e intuitivo.

## ⚠️ Avisos
1. Este projeto foi construído e testado sobre o JDK 21, dessa forma recomendamos o uso do mesmo durante a execução do mesmo.
