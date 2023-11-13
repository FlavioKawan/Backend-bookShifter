package com.example.bookshifter.dto;

import java.util.List;

public record UserAndBookDTO(String firstName, String lastName, String email, List<BookDTO> books) {

}
