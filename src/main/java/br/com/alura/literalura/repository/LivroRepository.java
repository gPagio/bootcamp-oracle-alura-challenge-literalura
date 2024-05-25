package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloContainingIgnoreCase(String tituloLivro);

    @Query("SELECT a FROM Livro l JOIN l.autores a")
    List<Autor> listarTodosOsAutores();

    @Query("SELECT a FROM Livro l JOIN l.autores a WHERE :ano <= a.anoFalecimento AND :ano >=a.anoNascimento")
    List<Autor> listarAutoresVivosEmDeterminadoAno(int ano);

    @Query("SELECT DISTINCT idiomas FROM Livro")
    List<String> listarIdiomasDosLivrosCadastrados();

    @Query("SELECT l FROM Livro l WHERE :siglaIdioma MEMBER OF l.idiomas")
    List<Livro> listarLivrosPorIdioma(String siglaIdioma);
}
