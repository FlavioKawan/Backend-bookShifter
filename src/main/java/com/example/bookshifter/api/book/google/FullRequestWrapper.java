package com.example.bookshifter.api.book.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullRequestWrapper {
    private int totalItems;
    @JsonProperty("items")
    private ItemsWrapper[] items;


    public  int getTotalItems(){
        return this.totalItems;
    }
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public ItemsWrapper[] getItems(){
        return this.items;
    }

    public void setItemsWrappers(ItemsWrapper[] items) {
        this.items = items;
    }
}
