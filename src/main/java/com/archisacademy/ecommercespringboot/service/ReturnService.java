package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ReturnDto;

import java.util.List;

public interface ReturnService {
    String createReturn(ReturnDto returnDto);

    String updateReturn(ReturnDto returnDto);

    List<ReturnDto> getAllReturns();

    ReturnDto getReturnById(Long returnId);

    void deleteReturn(Long returnId);
}
