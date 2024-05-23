package br.com.alura.literalura.dto;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosAutor;
import br.com.alura.literalura.model.Livro;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AutorDTO {

    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    private List<Livro> livros = new ArrayList<>();

    public AutorDTO(){}

    public AutorDTO(Autor autor){
        this.nome = autor.getNome();
        this.anoNascimento = autor.getAnoNascimento();
        this.anoFalecimento = autor.getAnoFalecimento();
    }
}
