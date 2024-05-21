package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    private static final String ENDERECO_API = "https://gutendex.com/books/";
    private ConverteDados converteDados = new ConverteDados();
    private List<DadosLivro> livros = new ArrayList<>();

    public void exibeMenu(){
        String responseBody = converteDados.obterPropriedadeJsonEspecifica(ConsumoApi.obterDados(ENDERECO_API), "results");

        System.out.println("Response Body " + responseBody);
        livros = converteDados.serializaLista(responseBody, DadosLivro.class);
        livros.stream().forEach(System.out::println);

        System.out.println("Opa");
    }

}
