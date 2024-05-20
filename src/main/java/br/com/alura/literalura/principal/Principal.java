package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    private static final String ENDERECO_API = "https://gutendex.com/books/";
    private ConverteDados converteDados = new ConverteDados();
    private List<Livro> livros = new ArrayList<>();

    public void exibeMenu(){
        String responseBody = converteDados.obterPropriedadeJsonEspecifica(ConsumoApi.obterDados(ENDERECO_API), "results");

        System.out.println("Response Body " + responseBody);
        livros = converteDados.serializaLista(responseBody, Livro.class);
        livros.stream().forEach(System.out::println);

        System.out.println("Opa");
    }

}
