package br.com.alura.literalura.dto;

import java.util.List;
import java.util.stream.Collectors;

public record LivroDTO (Long idLivro,
                        Integer idLivroApi,
                        String titulo,
                        List<AutorDTO> autores,
                        List<String> idiomas,
                        Integer numeroDeDownloads) {

    @Override
    public String toString() {
        return "\nID Livro: " + idLivro +
               "\nId Livro Api: " + idLivroApi +
               "\nTítulo: " + titulo +
               "\nAutores: " + autores.stream().map(autorDTO -> "ID Autor: " + autorDTO.idAutor() + ", Nome: " + autorDTO.nome() + ", Ano Nascimento: " + autorDTO.anoNascimento() + " Ano Falecimento: " + autorDTO.anoFalecimento()).collect(Collectors.joining(", ")) +
               "\nIdiomas: " + String.join(", ", idiomas) +
               "\nNúmero de Downloads: " + numeroDeDownloads;
    }
}
