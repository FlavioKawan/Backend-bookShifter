package com.example.bookshifter.dto;

import com.example.bookshifter.projections.BookMinProjection;

public class MinimalBookDTO {
    private String title;
    private String publisher;
    String mediumCoverUrl;

    public MinimalBookDTO(BookMinProjection minimalInfo) {
        this.title = minimalInfo.getTitle();
        this.publisher = minimalInfo.getPublisher();
        this.mediumCoverUrl = minimalInfo.getMediumCoverUrl();

    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getMediumCoverUrl() {
        return mediumCoverUrl;
    }
}
