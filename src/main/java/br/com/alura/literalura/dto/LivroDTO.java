package br.com.alura.literalura.dto;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;

import java.util.List;

public class LivroDTO {

    private Long id;
    private Integer idApi;
    private String titulo;
    private List<Autor> autores;
    private List<String> idiomas;
    private Integer numeroDeDownloads;

    public LivroDTO (Livro livro){
        this.idApi = livro.getIdApi();
        this.titulo = livro.getTitulo();
        this.idiomas = livro.getIdiomas();
        this.numeroDeDownloads = livro.getNumeroDeDownloads();
    }
}
