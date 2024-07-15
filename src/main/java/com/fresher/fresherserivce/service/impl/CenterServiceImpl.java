package com.fresher.fresherserivce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fresher.fresherserivce.model.Center;
import com.fresher.fresherserivce.repository.table.CenterRepositoryJPA;
import com.fresher.fresherserivce.service.CenterService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepositoryJPA centerRepositoryJPA;

    public List<Center> findAll() {
        return centerRepositoryJPA.findAll();
    }

    public Optional<Center> findById(Long id) {
        return centerRepositoryJPA.findById(id);
    }

    public Center save(Center center) {
        return centerRepositoryJPA.save(center);
    }

    public void deleteById(Long id) {
        centerRepositoryJPA.deleteById(id);
    }
}
