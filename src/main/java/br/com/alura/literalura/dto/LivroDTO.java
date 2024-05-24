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
        return "LivroDTO{" +
                "idLivro=" + idLivro +
                ", idLivroApi=" + idLivroApi +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores.stream().map(AutorDTO::toString).collect(Collectors.joining(", ")) +
                ", idiomas=" + idiomas +
                ", numeroDeDownloads=" + numeroDeDownloads +
                '}';
    }
}
