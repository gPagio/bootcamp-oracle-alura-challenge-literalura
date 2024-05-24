package br.com.alura.literalura.dto;

public record AutorDTO (Long idAutor,
                        String nome,
                        Integer anoNascimento,
                        Integer anoFalecimento) {

    @Override
    public String toString() {
        return "AutorDTO{" +
                "id=" + idAutor +
                ", nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento  +
                '}';
    }
}
