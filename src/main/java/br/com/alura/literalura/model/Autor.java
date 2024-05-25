package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor (DadosAutor dadosAutor){
        this.nome = Optional.of(dadosAutor.nome().trim()).orElse("Nenhum Nome Informado");

        try {
            this.anoNascimento = Integer.valueOf(dadosAutor.anoNascimento());
        } catch (NumberFormatException e){
            this.anoNascimento = 0;
        }

        try {
            this.anoFalecimento = Integer.valueOf(dadosAutor.anoFalecimento());
        } catch (NumberFormatException e){
            this.anoFalecimento = 0;
        }
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
