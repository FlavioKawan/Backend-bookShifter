package com.example.bookshifter.api.book.openlibrary;

public class FullRequestOpenLibrary {

    private Docs[] docs;

    public Docs[] getDocs(){
        return this.docs;
    }

    public void setDocs(Docs[] docs) {
        this.docs = docs;
    }
}
