package com.fresher.fresherserivce.service;

import java.util.List;

import com.fresher.fresherserivce.model.Evaluation;

public interface EvaluationService {

    List<Evaluation> findEvaluationByIdFresherAndIdCenter(Long idFresher);

    public Evaluation save(Evaluation evaluation);

    public Object update(Long id, Evaluation evaluationDetails);

    public void deleteById(Long id);

}
