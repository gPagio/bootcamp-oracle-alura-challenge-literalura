package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<LivroDTO> obterTodosOsLivros(){
        return listLivroToListLivroDTO(repository.findAll());
    }

    public LivroDTO buscarLivroPorTitulo(String tituloLivro){
        Optional<Livro> containerLivro = repository.findByTituloContainingIgnoreCase(tituloLivro);
        if (containerLivro.isPresent()){
            return livroToLivroDTO(containerLivro.get());
        }
        return null;
    }

    public List<AutorDTO> buscarTodosOsAutores(){
        return listAutorToListAutorDTO(repository.buscarTodosOsAutores());
    }

    private LivroDTO livroToLivroDTO(Livro l){
        return new LivroDTO(l);
    }

    public List<AutorDTO> buscarAutoresVivosEmDeterminadoAno(int ano) {
        return listAutorToListAutorDTO(repository.buscarAutoresVivosEmDeterminadoAno(ano));
    }

    private List<LivroDTO> listLivroToListLivroDTO(List<Livro> listLivro) {
        return listLivro.stream().map(livro -> new LivroDTO(livro)).collect(Collectors.toList());
    }

    private List<AutorDTO> listAutorToListAutorDTO(List<Autor> listAutor){
        return listAutor.stream().map(autor -> new AutorDTO(autor)).collect(Collectors.toList());
    }
}
