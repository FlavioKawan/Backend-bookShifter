package com.example.bookshifter.api.book.openlibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Docs {
    @JsonProperty("publish_year")
    private List<Integer> publishYear = new ArrayList<>();

    private List<String> publisher;

    public List<Integer> getPublishYear(){
        return  this.publishYear;
    }

    public void setPublishYear(List<Integer> publishYear) {
        this.publishYear = publishYear;
    }

    public List<String> getPublisher(){
        return this.publisher;
    }
    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }
}
