package com.fresher.fresherserivce.repository;

import com.fresher.fresherserivce.vo.dto.SearchFresherDTO;
import com.fresher.fresherserivce.vo.until.ResultSelectEntity;

public interface EvaluationRepository {

    ResultSelectEntity findEvaluationByIdFresherAndIdCenter(Long idFresher, Long idCenter);
}
