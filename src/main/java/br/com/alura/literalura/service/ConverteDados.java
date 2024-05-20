package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T serializaDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> serializaLista(String json, Class<T> classe) {
        JavaType listaDados = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json, listaDados);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String obterPropriedadeJsonEspecifica(String json, String propertyName) {
        try{
            JsonNode jsonNode = mapper.readTree(json);
            JsonNode propertyJson = jsonNode.path(propertyName);

            return propertyJson.toString();
        } catch (JsonProcessingException e){
            return null;
        }
    }
}
