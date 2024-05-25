package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<LivroDTO> listarTodosOsLivros(){
        return listLivroToListLivroDTO(repository.findAll());
    }

    public List<LivroDTO> listarLivrosPorTitulo(String tituloLivro){
        List<Livro> listaLivrosEncontrados = repository.findByTituloContainingIgnoreCase(tituloLivro);
        if (!listaLivrosEncontrados.isEmpty()){
            return listLivroToListLivroDTO(listaLivrosEncontrados);
        }
        return null;
    }

    public List<AutorDTO> listarTodosOsAutores(){
        return listAutorToListAutorDTO(repository.listarTodosOsAutores());
    }

    private LivroDTO livroToLivroDTO(Livro livro){
        return new LivroDTO(livro.getIdLivro(),
                livro.getIdLivroApi(),
                livro.getTitulo(),
                livro.getAutores()
                        .stream()
                        .map(autor -> new AutorDTO(autor.getIdAutor(),
                                autor.getNome(),
                                autor.getAnoNascimento(),
                                autor.getAnoFalecimento()))
                        .collect(Collectors.toList()),
                livro.getIdiomas(),
                livro.getNumeroDeDownloads());
    }

    public List<AutorDTO> listarAutoresVivosEmDeterminadoAno(int ano) {
        return listAutorToListAutorDTO(repository.listarAutoresVivosEmDeterminadoAno(ano));
    }

    public String listarIdiomasDosLivrosCadastrados(){
        return String.join(", ", repository.listarIdiomasDosLivrosCadastrados());
    }

    public List<LivroDTO> listarLivrosPorIdioma(String siglaIdioma){
        return listLivroToListLivroDTO(repository.listarLivrosPorIdioma(siglaIdioma));
    }

    public List<LivroDTO> listarLivrosPorAutor(String nomeAutorParaBusca) {
        return listLivroToListLivroDTO(repository.listarLivrosPorAutor(nomeAutorParaBusca));
    }

    private List<LivroDTO> listLivroToListLivroDTO(List<Livro> listLivro) {
        return listLivro.stream().map(livro -> new LivroDTO(livro.getIdLivro(), livro.getIdLivroApi(), livro.getTitulo(), livro.getAutores().stream().map(autor -> new AutorDTO(autor.getIdAutor(), autor.getNome(), autor.getAnoNascimento(), autor.getAnoFalecimento())).collect(Collectors.toList()), livro.getIdiomas(), livro.getNumeroDeDownloads())).collect(Collectors.toList());
    }

    private List<AutorDTO> listAutorToListAutorDTO(List<Autor> listAutor){
        return listAutor.stream().map(autor -> new AutorDTO(autor.getIdAutor(), autor.getNome(), autor.getAnoNascimento(), autor.getAnoFalecimento())).collect(Collectors.toList());
    }

    public void salvarLivroNoBanco(DadosLivro livroEncontrado) {
        repository.save(new Livro(livroEncontrado));
    }

}
