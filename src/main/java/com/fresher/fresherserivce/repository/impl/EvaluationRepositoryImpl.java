package com.fresher.fresherserivce.repository.impl;

import org.springframework.stereotype.Repository;

import com.fresher.fresherserivce.model.Fresher;
import com.fresher.fresherserivce.repository.CommonDataBaseRepository;
import com.fresher.fresherserivce.repository.EvaluationRepository;
import com.fresher.fresherserivce.vo.until.ResultSelectEntity;

@Repository
public class EvaluationRepositoryImpl extends CommonDataBaseRepository implements EvaluationRepository {

    @Override
    public ResultSelectEntity findEvaluationByIdFresherAndIdCenter(Long idFresher, Long idCenter) {
        return null;
    }
}
