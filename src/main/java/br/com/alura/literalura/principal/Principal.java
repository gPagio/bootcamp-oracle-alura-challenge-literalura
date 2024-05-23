package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private LivroService livroService;

    public Principal(LivroService livroService){
        this.livroService = livroService;
    }

    public void exibeMenu(){
        var opcao = -1;

        while (opcao != 0){
            System.out.print("""
                    1 - Buscar Livro por Titulo
                    2 - Listar Todos os Livros
                    3 - Listar Todos os Autores
                    4 - Buscar Autores Vivos em Determinado Ano
                    
                    0 - Sair
                    """);

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarTodosOsLivros();
                    break;
                case 3:
                    listarTodosOsAutores();
                    break;
                case 4:
                    buscarAutoresVivosEmDeterminadoAno();
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
        System.out.print("Digite o titulo para busca >>> ");
        var titulo = leitura.nextLine().trim();
        LivroDTO livroDTO = livroService.buscarLivroPorTitulo(titulo);

        if (livroDTO != null){
            System.out.println(livroDTO);
        } else {
            System.out.println("Nenhum livro encontrado com o titulo " + titulo);
        }
    }

    private void listarTodosOsLivros() {
        List<LivroDTO> listaComTodosLivros = livroService.obterTodosOsLivros();

        if (!listaComTodosLivros.isEmpty()){
            listaComTodosLivros.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro cadastrado!");
        }
    }

    private void listarTodosOsAutores() {
        List<AutorDTO> listaComTodosAutores = livroService.buscarTodosOsAutores();

        if (!listaComTodosAutores.isEmpty()){
            listaComTodosAutores.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor cadastrado!");
        }
    }

    private void buscarAutoresVivosEmDeterminadoAno() {
        System.out.print("Digite o ano para busca >>> ");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<AutorDTO> listaComAutoresVivosEmDeterminadoAno = livroService.buscarAutoresVivosEmDeterminadoAno(ano);
        if (!listaComAutoresVivosEmDeterminadoAno.isEmpty()){
            listaComAutoresVivosEmDeterminadoAno.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor vivo no ano "+ ano +"!");
        }
    }
}


