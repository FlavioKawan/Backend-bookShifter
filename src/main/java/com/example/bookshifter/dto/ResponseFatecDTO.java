package com.example.bookshifter.dto;


import com.example.bookshifter.entities.Fatec;

import java.util.List;

public record ResponseFatecDTO(Fatec fatec, List<BookDTO> bookDTO) {
}
