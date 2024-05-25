package br.com.alura.literalura.dto;

public record AutorDTO (Long idAutor,
                        String nome,
                        Integer anoNascimento,
                        Integer anoFalecimento) {

    @Override
    public String toString() {
        return "\nID Autor: " + idAutor +
               "\nNome: " + nome +
               "\nAno Nascimento: " + anoNascimento +
               "\nAno Falecimento: " + anoFalecimento;
    }
}
