package com.fresher.fresherserivce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fresher.fresherserivce.model.Fresher;
import com.fresher.fresherserivce.repository.FresherRepository;
import com.fresher.fresherserivce.repository.table.FresherRepositoryJPA;
import com.fresher.fresherserivce.service.FresherService;
import com.fresher.fresherserivce.vo.dto.SearchFresherDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {

    private final FresherRepository fresherRepository;

    private final FresherRepositoryJPA fresherRepositoryJPA;

    public Object getAllFreshers(SearchFresherDTO searchFresherDTO) {

        return fresherRepository.findFresherAndCount(searchFresherDTO);
    }

    public Fresher getFresherById(Long id) {
        return fresherRepositoryJPA.findById(id).orElse(null);
    }

    public Fresher saveFresher(Fresher fresher) {
        return fresherRepositoryJPA.save(fresher);
    }

    public void deleteFresher(Long id) {
        fresherRepositoryJPA.deleteById(id);
    }
}
