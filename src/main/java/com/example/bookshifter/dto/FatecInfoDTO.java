package com.example.bookshifter.dto;

import com.example.bookshifter.entities.Fatec;

public class FatecInfoDTO{
    private Long id;
    private String name;
    private String street;
    private String neighborhood;
    private String city;

    public FatecInfoDTO(){

    }

    public FatecInfoDTO(Long id, String name, String street, String neighborhood, String city){
        this.id = id;
        this.name = name;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
    }

    public FatecInfoDTO(Fatec fatec){
        this.id = fatec.getId();
        this.name = fatec.getName();
        this.street = fatec.getStreet();
        this.neighborhood = fatec.getNeighborhood();
        this.city = fatec.getCity();
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getStreet(){
        return this.street;
    }

    public String getNeighborhood(){
        return  this.neighborhood;
    }

    public String getCity(){
        return this.city;
    }
}
