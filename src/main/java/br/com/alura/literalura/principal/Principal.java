package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

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
            System.out.println("""
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
    }

    private void listarTodosOsAutores() {
    }

    private void buscarAutoresVivosEmDeterminadoAno() {
    }

}
