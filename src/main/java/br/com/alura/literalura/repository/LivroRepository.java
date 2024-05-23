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
    List<Autor> buscarTodosOsAutores();

    @Query("SELECT a FROM Livro l JOIN l.autores a WHERE :ano < a.anoFalecimento")
    List<Autor> buscarAutoresVivosEmDeterminadoAno(int ano);
}
