package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;
    private Integer idLivroApi;
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;
    private List<String> idiomas;
    private Integer numeroDeDownloads;

    public Livro(){}

    public Livro (DadosLivro dadosLivro){
        try {
            this.idLivroApi = Integer.valueOf(dadosLivro.idLivroApi());
        } catch (NumberFormatException e){
            this.idLivroApi = 0;
        }

        this.titulo = dadosLivro.titulo().trim();

        this.autores = dadosLivro.autores().stream().map(dadosAutor -> new Autor(dadosAutor)).collect(Collectors.toList());
        this.idiomas = dadosLivro.idiomas().stream().map(idiomas -> idiomas.trim()).collect(Collectors.toList());

        try {
            this.numeroDeDownloads = Integer.valueOf(dadosLivro.numeroDeDownloads());
        } catch (NumberFormatException e){
            this.numeroDeDownloads = 0;
        }
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public Integer getIdLivroApi() {
        return idLivroApi;
    }

    public void setIdLivroApi(Integer idLivroApi) {
        this.idLivroApi = idLivroApi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }
}