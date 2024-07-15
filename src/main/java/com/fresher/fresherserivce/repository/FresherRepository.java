package com.fresher.fresherserivce.repository;

import com.fresher.fresherserivce.vo.dto.SearchFresherDTO;
import com.fresher.fresherserivce.vo.until.ResultSelectEntity;

public interface FresherRepository {

    ResultSelectEntity findFresherAndCount(SearchFresherDTO searchFresherDTO);
}
