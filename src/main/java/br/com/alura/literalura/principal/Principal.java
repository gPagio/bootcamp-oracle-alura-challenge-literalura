package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.DadosAutor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
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

    private final String ENDERECO_API_GERAL = "https://gutendex.com/books";
    private final String ENDERECO_API_BUSCA = ENDERECO_API_GERAL + "/?search=";

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
                    7 - Listar Livros Por Autor
                    8 - Buscar os TOP 10 Livros Mais Baixados da API
                    9 - Buscar os TOP 10 Livros Menos Baixados da API
                    
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
                case 7:
                    listarLivrosPorAutor();
                    break;
                case 8:
                    buscarTop10LivrosMaisBaixadosDaApi();
                    break;
                case 9:
                    buscarTop10LivrosMenosBaixadosDaApi();
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
        var jsonBodyResponse = ConsumoApi.obterDados(ENDERECO_API_BUSCA + tituloBusca);
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
                System.out.println(livroEncontrado);
                livroService.salvarLivroNoBanco(livroEncontrado);
                System.out.println("\nLivro foi salvo com sucesso");
                break;
            default:
                salvarUmDosLivrosBuscados(listaLivrosBuscados);
                break;
        }
    }

    private void salvarUmDosLivrosBuscados(List<DadosLivro> listaLivrosBuscados) {
        System.out.println("\nEncontramos os livros abaixo...");
        listaLivrosBuscados.stream()
                .sorted(Comparator.comparing(DadosLivro::titulo))
                .forEach(System.out::println);

        System.out.print("\nDigite o ID da API para o livro que deseja salvar (Digite 'Menu' Para Voltar ao Menu Principal) >>> ");
        var idLivroEscolhidoParaSalvar = leitura.nextLine();

        List<DadosLivro> livroEscolhido = listaLivrosBuscados
                .stream()
                .filter(dadosLivro -> dadosLivro.idLivroApi().equals(idLivroEscolhidoParaSalvar))
                .collect(Collectors.toList());

        if (idLivroEscolhidoParaSalvar.isEmpty() || idLivroEscolhidoParaSalvar.equalsIgnoreCase("menu")){
            System.out.println("\nVoltando ao menu principal...");
        } else if (livroEscolhido.isEmpty()){
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
        List<LivroDTO> listaLivroDTOEncontrados = livroService.listarLivrosPorTitulo(titulo);

        if (!listaLivroDTOEncontrados.isEmpty()){
            listaLivroDTOEncontrados.stream()
                    .sorted(Comparator.comparing(LivroDTO::titulo))
                    .forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro cadastrado contendo " + titulo + " no título");
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void listarTodosOsLivros() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        List<LivroDTO> listaComTodosLivros = livroService.listarTodosOsLivros();

        if (!listaComTodosLivros.isEmpty()){
            listaComTodosLivros.stream()
                    .sorted(Comparator.comparing(LivroDTO::titulo))
                    .forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro cadastrado!");
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void listarTodosOsAutores() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        List<AutorDTO> listaComTodosAutores = livroService.listarTodosOsAutores();

        if (!listaComTodosAutores.isEmpty()){
            listaComTodosAutores.stream()
                    .sorted(Comparator.comparing(AutorDTO::nome))
                    .forEach(System.out::println);
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
            listaComAutoresVivosEmDeterminadoAno.stream()
                    .sorted(Comparator.comparing(AutorDTO::nome))
                    .forEach(System.out::println);
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
            livrosEncontrados.stream()
                    .sorted(Comparator.comparing(LivroDTO::titulo))
                    .forEach(System.out::println);
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void listarLivrosPorAutor() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("\nInsira o nome do autor para ser buscado >>> ");
        var nomeAutorParaBusca = leitura.nextLine().trim();

        List<LivroDTO> livrosEncontrados = livroService.listarLivrosPorAutor(nomeAutorParaBusca);
        if (livrosEncontrados.isEmpty()){
            System.out.println("Nenhum livro encontrado contendo " + nomeAutorParaBusca + " no nome do autor");
        } else {
            livrosEncontrados.stream()
                    .sorted(Comparator.comparing(LivroDTO::titulo))
                    .forEach(System.out::println);
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void buscarTop10LivrosMaisBaixadosDaApi() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("\nBuscando os 10 livros mais baixados da API Gutendex...");

        var jsonBodyResponse = ConsumoApi.obterDados(ENDERECO_API_GERAL+"/?sort=popular");
        var jsonResultsProperty = conversor.obterPropriedadeJsonEspecifica(jsonBodyResponse, "results");

        System.out.println("\n\nAbaixo estão listados os 10 livros mais baixados da API Gutendex!");
        conversor.serializaLista(jsonResultsProperty, DadosLivro.class)
                .stream()
                .map(dadosLivro -> new Livro(dadosLivro))
                .sorted(Comparator.comparing(Livro::getNumeroDeDownloads).reversed())
                .limit(10)
                .map(livro ->
                        new DadosLivro(livro.getIdLivroApi().toString(),
                                livro.getTitulo(),
                                livro.getAutores()
                                        .stream()
                                        .map(autor ->
                                                new DadosAutor(autor.getNome(),
                                                        autor.getAnoNascimento().toString(),
                                                        autor.getAnoFalecimento().toString()))
                                        .collect(Collectors.toList()),
                                livro.getIdiomas(),
                                livro.getNumeroDeDownloads().toString()))
                .forEach(System.out::println);

        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void buscarTop10LivrosMenosBaixadosDaApi() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("\nBuscando os 10 livros menos baixados da API Gutendex...");

        var jsonBodyResponse = ConsumoApi.obterDados(ENDERECO_API_GERAL+"/?sort=ascending");
        var jsonResultsProperty = conversor.obterPropriedadeJsonEspecifica(jsonBodyResponse, "results");

        System.out.println("\n\nAbaixo estão listados os 10 livros menos baixados da API Gutendex!");
        conversor.serializaLista(jsonResultsProperty, DadosLivro.class)
                .stream()
                .map(dadosLivro -> new Livro(dadosLivro))
                .sorted(Comparator.comparing(Livro::getNumeroDeDownloads))
                .limit(10)
                .map(livro ->
                        new DadosLivro(livro.getIdLivroApi().toString(),
                                livro.getTitulo(),
                                livro.getAutores()
                                        .stream()
                                        .map(autor ->
                                                new DadosAutor(autor.getNome(),
                                                        autor.getAnoNascimento().toString(),
                                                        autor.getAnoFalecimento().toString()))
                                        .collect(Collectors.toList()),
                                livro.getIdiomas(),
                                livro.getNumeroDeDownloads().toString()))
                .forEach(System.out::println);

        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
}


