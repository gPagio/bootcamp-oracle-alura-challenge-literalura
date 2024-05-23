package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.LivroService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();
    private LivroService livroService;

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public Principal(LivroService livroService){
        this.livroService = livroService;
    }

    public void exibeMenu(){
        var opcao = -1;

        while (opcao != 0){
            System.out.print("""
                    1 - Buscar Livro por Titulo
                    2 - Listar Livro por Titulo
                    3 - Listar Todos os Livros
                    4 - Listar Todos os Autores
                    5 - Listar Autores Vivos em Determinado Ano
                    
                    0 - Sair
                    """);

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivroPorTitulo();
                    break;
                case 3:
                    listarTodosOsLivros();
                    break;
                case 4:
                    listarTodosOsAutores();
                    break;
                case 5:
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 0:
                    System.out.println("Obrigado por usar o LiterAlura!");
                    break;
                default:
                    break;
            }
        }
    }

    private void buscarLivroPorTitulo() {
        System.out.print("\nDigite o titulo para busca >>> ");
        var titulo = leitura.nextLine().toLowerCase().trim();

        var jsonBodyResponse = ConsumoApi.obterDados(ENDERECO+titulo);
        var jsonResultsProperty = conversor.obterPropriedadeJsonEspecifica(jsonBodyResponse, "results");

        List<DadosLivro> listaLivrosBuscados = conversor.serializaLista(jsonResultsProperty, DadosLivro.class)
                .stream()
                .filter(livro -> livro.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());

        if (listaLivrosBuscados.size() == 1){
            DadosLivro livroEncontrado = listaLivrosBuscados.get(0);
            livroService.salvarLivroNoBanco(livroEncontrado);
            System.out.println("Livro foi salvo com sucesso");
        } else {
            System.out.println("Encontramos os livros abaixo...\n");
            listaLivrosBuscados.forEach(System.out::println);

            System.out.println("\nDigite o ID do livro que deseja salvar >>> ");
            var idLivroEscolhidoParaSalvar = leitura.nextInt();
            leitura.nextLine();

            DadosLivro livroEscolhido = listaLivrosBuscados
                    .stream()
                    .filter(dadosLivro -> dadosLivro.idLivroApi().equals(idLivroEscolhidoParaSalvar))
                    .collect(Collectors.toList()).get(0);

            livroService.salvarLivroNoBanco(livroEscolhido);
            System.out.println("Livro foi salvo com sucesso");
        }

    }

    private void listarLivroPorTitulo() {
        System.out.print("Digite o titulo para listagem >>> ");
        var titulo = leitura.nextLine().trim();
        LivroDTO livroDTO = livroService.listarLivroPorTitulo(titulo);

        if (livroDTO != null){
            System.out.println(livroDTO);
        } else {
            System.out.println("Nenhum livro cadastrado com o titulo " + titulo);
        }
    }

    private void listarTodosOsLivros() {
        List<LivroDTO> listaComTodosLivros = livroService.listarTodosOsLivros();

        if (!listaComTodosLivros.isEmpty()){
            listaComTodosLivros.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro cadastrado!");
        }
    }

    private void listarTodosOsAutores() {
        List<AutorDTO> listaComTodosAutores = livroService.listarTodosOsAutores();

        if (!listaComTodosAutores.isEmpty()){
            listaComTodosAutores.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor cadastrado!");
        }
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.print("Digite o ano para listagem >>> ");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<AutorDTO> listaComAutoresVivosEmDeterminadoAno = livroService.listarAutoresVivosEmDeterminadoAno(ano);
        if (!listaComAutoresVivosEmDeterminadoAno.isEmpty()){
            listaComAutoresVivosEmDeterminadoAno.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor vivo no ano "+ ano +" está cadastrado!");
        }
    }
}


