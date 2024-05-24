package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("id") String idLivroApi,
                         @JsonAlias("title") String titulo,
                         @JsonAlias("authors") List<DadosAutor> autores,
                         @JsonAlias("languages") List<String> idiomas,
                         @JsonAlias("download_count") String numeroDeDownloads) {

    @Override
    public String toString() {
        return "\nID do Livro na Api: " + idLivroApi +
               "\nTítulo: " + titulo +
               "\nAutores: " + autores +
               "\nAdiomas: " + idiomas +
               "\nNúmero De Downloads: " + numeroDeDownloads;
    }
}