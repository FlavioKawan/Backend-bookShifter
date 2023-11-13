package com.example.bookshifter.api.fatec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationInfo {

    String cep;
    @JsonProperty("logradouro")
    String rua;
    String bairro;

    @JsonProperty("localidade")
    String cidade;

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }
}
