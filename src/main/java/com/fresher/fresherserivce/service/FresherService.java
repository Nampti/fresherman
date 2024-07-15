package com.fresher.fresherserivce.service;

import java.util.List;

import com.fresher.fresherserivce.model.Fresher;
import com.fresher.fresherserivce.vo.dto.SearchFresherDTO;

public interface FresherService {

    public Object getAllFreshers(SearchFresherDTO searchFresherDTO);

    public Fresher getFresherById(Long id);

    public Fresher saveFresher(Fresher fresher);

    public void deleteFresher(Long id);
}
