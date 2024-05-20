package br.com.alura.literalura.principal;

import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

public class Principal {

    private static final String ENDERECO_API = "https://gutendex.com/books/";
    private ConverteDados converteDados = new ConverteDados();

    public void exibeMenu(){
        String responseBody = ConsumoApi.obterDados(ENDERECO_API);

        System.out.println("Response Body " + converteDados.obterPropriedadeJsonEspecifica(responseBody, "results"));
        System.out.println("Opa");
    }

}
