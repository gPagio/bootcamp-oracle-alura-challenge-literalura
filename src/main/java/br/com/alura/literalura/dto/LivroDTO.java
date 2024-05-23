package br.com.alura.literalura.dto;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;

import java.util.List;

public class LivroDTO {

    private Long idLivro;
    private Integer idLivroApi;
    private String titulo;
    private List<Autor> autores;
    private List<String> idiomas;
    private Integer numeroDeDownloads;

    public LivroDTO (Livro livro){
        this.idLivroApi = livro.getIdLivroApi();
        this.titulo = livro.getTitulo();
        this.autores = livro.getAutores();
        this.idiomas = livro.getIdiomas();
        this.numeroDeDownloads = livro.getNumeroDeDownloads();
    }

    @Override
    public String toString() {
        return "LivroDTO{" +
                "idLivro=" + idLivro +
                ", idLivroApi=" + idLivroApi +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numeroDeDownloads=" + numeroDeDownloads +
                '}';
    }
}
