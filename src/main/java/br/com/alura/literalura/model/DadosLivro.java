package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

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
               "\nAutores - " + autores.stream().map(dadosAutor -> "Nome: " + dadosAutor.nome() + ", Ano Nascimento: " + dadosAutor.anoNascimento() + " Ano Falecimento: " + dadosAutor.anoFalecimento()).collect(Collectors.joining(" - ")) +
               "\nAdiomas: " + String.join(", ", idiomas) +
               "\nNúmero De Downloads: " + numeroDeDownloads;
    }
}