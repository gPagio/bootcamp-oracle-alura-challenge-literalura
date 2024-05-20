package br.com.alura.literalura.service;

import java.util.List;

public interface IConverteDados {
    <T> T serializaDados(String json, Class<T> classe);

    <T> List<T> serializaLista(String json, Class<T> classe);
}