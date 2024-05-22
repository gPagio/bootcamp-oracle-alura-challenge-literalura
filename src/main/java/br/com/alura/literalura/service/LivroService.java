package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.LivroDTO;
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

    private LivroDTO livroToLivroDTO(Livro l){
        return new LivroDTO(l);
    }

    private List<LivroDTO> listLivroToListLivroDTO(List<Livro> listLivro) {
        return listLivro.stream().map(livro -> new LivroDTO(livro)).collect(Collectors.toList());
    }
}
