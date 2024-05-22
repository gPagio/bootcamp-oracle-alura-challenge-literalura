package br.com.alura.literalura.dto;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;

import java.util.List;
import java.util.stream.Collectors;

public class LivroDTO {

    private Long id;
    private Integer idApi;
    private String titulo;
    private List<Autor> autores;
    private List<String> idiomas;
    private Integer numeroDeDownloads;

    public LivroDTO (Livro livro){
        this.idApi = livro.getIdApi();
        this.titulo = livro.getTitulo().trim();
        this.idiomas = livro.getIdiomas().stream().map(idiomas -> idiomas.trim()).collect(Collectors.toList());
        this.numeroDeDownloads = livro.getNumeroDeDownloads();
    }
}