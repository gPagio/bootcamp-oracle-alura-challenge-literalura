package br.com.alura.literalura.model;

import java.util.List;

public record Livro(String titulo,
                    List<Autor> autores,
                    String idiomas,
                    Integer numeroDeDownloads) {
}