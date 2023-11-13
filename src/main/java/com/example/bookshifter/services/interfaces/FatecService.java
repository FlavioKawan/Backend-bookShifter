package com.example.bookshifter.services.interfaces;


import com.example.bookshifter.dto.FatecDTO;
import com.example.bookshifter.dto.FatecInfoDTO;
import com.example.bookshifter.dto.ResponseFatecDTO;

import java.util.List;

public interface FatecService {
    List<FatecInfoDTO> getAll();

    FatecInfoDTO getFatecById(Long id);

    FatecDTO createFatec(FatecDTO dto);

    ResponseFatecDTO getAllFatecBooks(Long fatecId);

}
