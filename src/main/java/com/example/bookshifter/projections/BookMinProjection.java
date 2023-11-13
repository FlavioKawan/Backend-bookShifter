package com.example.bookshifter.projections;

public interface BookMinProjection {
    Long getId();
    String getTitle();
    String getPublisher();
    String getMediumCoverUrl();
}
