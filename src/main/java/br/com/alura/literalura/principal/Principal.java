package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.LivroService;

import java.util.Comparator;
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
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("""
                 ##        ####    ######   ######   #####      ##     ##       ##  ##   #####      ##
                 ##         ##       ##     ##       ##  ##    ####    ##       ##  ##   ##  ##    ####
                 ##         ##       ##     ##       ##  ##   ##  ##   ##       ##  ##   ##  ##   ##  ##
                 ##         ##       ##     ####     #####    ######   ##       ##  ##   #####    ######
                 ##         ##       ##     ##       ####     ##  ##   ##       ##  ##   ####     ##  ##
                 ##         ##       ##     ##       ## ##    ##  ##   ##       ##  ##   ## ##    ##  ##
                 ######    ####      ##     ######   ##  ##   ##  ##   ######    ####    ##  ##   ##  ##
                """);

        var opcao = -1;
        while (opcao != 0){
            System.out.println(" ");
            System.out.print("""
                    1 - Buscar Livro por Titulo
                    2 - Listar Livro por Titulo
                    3 - Listar Todos os Livros
                    4 - Listar Todos os Autores
                    5 - Listar Autores Vivos em Determinado Ano
                    6 - Listar Livros Por Idioma
                    
                    0 - Sair
                    """);

            System.out.print("Digite a opção >>> ");
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
                case 6:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Obrigado por usar o LiterAlura!");
                    break;
                default:
                    System.out.println("Digite apenas opções presentes no menu!");
                    break;
            }
        }
    }

    private void buscarLivroPorTitulo() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("\nDigite o titulo para busca >>> ");
        var titulo = leitura.nextLine().toLowerCase().trim();
        var tituloBusca = titulo.replace(" ", "%20");

        System.out.print("Buscando livro contendo " + titulo + " no título...");
        var jsonBodyResponse = ConsumoApi.obterDados(ENDERECO + tituloBusca);
        var jsonResultsProperty = conversor.obterPropriedadeJsonEspecifica(jsonBodyResponse, "results");

        processarLivrosBuscados(titulo, jsonResultsProperty);
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void processarLivrosBuscados(String tituloInformadoParaBusca, String jsonLivrosEncontrados) {

        List<DadosLivro> listaLivrosBuscados = conversor.serializaLista(jsonLivrosEncontrados, DadosLivro.class)
                .stream()
                .filter(livro -> livro.titulo().toLowerCase().contains(tituloInformadoParaBusca.toLowerCase()))
                .collect(Collectors.toList());

        int quantidadeLivrosBuscados = listaLivrosBuscados.size();

        switch (quantidadeLivrosBuscados){
            case 0:
                System.out.println("\nNenhum livro encontrado contendo " + tituloInformadoParaBusca  + " no título ");
                break;
            case 1:
                DadosLivro livroEncontrado = listaLivrosBuscados.get(0);
                livroService.salvarLivroNoBanco(livroEncontrado);
                System.out.println("Livro foi salvo com sucesso");
                break;
            default:
                salvarUmDosLivrosBuscados(listaLivrosBuscados);
                break;
        }
    }

    private void salvarUmDosLivrosBuscados(List<DadosLivro> listaLivrosBuscados) {
        System.out.println("\nEncontramos os livros abaixo...");
        listaLivrosBuscados.stream()
                .sorted(Comparator.comparing(dadosLivro -> Integer.valueOf(dadosLivro.idLivroApi())))
                .forEach(System.out::println);

        System.out.print("Digite o ID da API para o livro que deseja salvar >>> ");
        var idLivroEscolhidoParaSalvar = leitura.nextLine();

        List<DadosLivro> livroEscolhido = listaLivrosBuscados
                .stream()
                .filter(dadosLivro -> dadosLivro.idLivroApi().equals(idLivroEscolhidoParaSalvar))
                .collect(Collectors.toList());

        if (livroEscolhido.isEmpty()){
            System.out.println("Nenhum livro encontrado com o ID " + idLivroEscolhidoParaSalvar);
        } else {
            livroService.salvarLivroNoBanco(livroEscolhido.get(0));
            System.out.println("\nLivro foi salvo com sucesso");
        }
    }

    private void listarLivroPorTitulo() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("\nDigite o titulo para listagem >>> ");
        var titulo = leitura.nextLine().trim();
        LivroDTO livroDTO = livroService.listarLivroPorTitulo(titulo);

        if (livroDTO != null){
            System.out.println(livroDTO);
        } else {
            System.out.println("Nenhum livro cadastrado contendo " + titulo + " no título");
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void listarTodosOsLivros() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        List<LivroDTO> listaComTodosLivros = livroService.listarTodosOsLivros();

        if (!listaComTodosLivros.isEmpty()){
            listaComTodosLivros.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro cadastrado!");
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void listarTodosOsAutores() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        List<AutorDTO> listaComTodosAutores = livroService.listarTodosOsAutores();

        if (!listaComTodosAutores.isEmpty()){
            listaComTodosAutores.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor cadastrado!");
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("\nDigite o ano para listagem >>> ");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<AutorDTO> listaComAutoresVivosEmDeterminadoAno = livroService.listarAutoresVivosEmDeterminadoAno(ano);
        if (!listaComAutoresVivosEmDeterminadoAno.isEmpty()){
            listaComAutoresVivosEmDeterminadoAno.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor vivo no ano "+ ano +" está cadastrado!");
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void listarLivrosPorIdioma() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("\nSiglas dos idiomas disponíveis >>> ");
        System.out.println(livroService.listarIdiomasDosLivrosCadastrados());

        System.out.print("Insira a sigla do idioma para ser buscado >>> ");
        var siglaIdiomaParaBusca = leitura.nextLine().trim();

        List<LivroDTO> livrosEncontrados = livroService.listarLivrosPorIdioma(siglaIdiomaParaBusca);
        if (livrosEncontrados.isEmpty()){
            System.out.println("Nenhum livro encontrado com o idioma " + siglaIdiomaParaBusca);
        } else {
            livrosEncontrados.forEach(System.out::println);
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
}


