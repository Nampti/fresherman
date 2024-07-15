package com.fresher.fresherserivce.service;

import java.util.List;
import java.util.Optional;

import com.fresher.fresherserivce.model.Center;

public interface CenterService {

    public List<Center> findAll();

    public Optional<Center> findById(Long id);

    public Center save(Center center);

    public void deleteById(Long id);
}
