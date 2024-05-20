package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
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
