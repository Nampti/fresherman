package com.fresher.fresherserivce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fresher.fresherserivce.model.Evaluation;
import com.fresher.fresherserivce.repository.table.EvaluationRepositoryJPA;
import com.fresher.fresherserivce.service.EvaluationService;
import com.fresher.fresherserivce.vo.enums.CoreErrorApp;

import static com.fresher.fresherserivce.vo.enums.CoreErrorApp.ERR_UPDATE_EVALUATE;
import static com.fresher.fresherserivce.vo.response.ResponseEntity.responseToClient;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepositoryJPA evaluationRepositoryJPA;

    @Override
    public List<Evaluation> findEvaluationByIdFresherAndIdCenter(Long idFresher) {
        return evaluationRepositoryJPA.findEvaluationByIdFresherAndIdCenter(idFresher, 3);
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        return evaluationRepositoryJPA.save(evaluation);
    }

    @Override
    public Object update(Long id, Evaluation evaluationDetails) {
        Optional<Evaluation> evaluationOptional = evaluationRepositoryJPA.findById(id);
        if (evaluationOptional.isPresent()) {
            Evaluation evaluation = evaluationOptional.get();
            evaluation.setEvaluationDate(evaluationDetails.getEvaluationDate());
            evaluation.setScore(evaluationDetails.getScore());
            evaluation.setComments(evaluationDetails.getComments());
            return evaluationRepositoryJPA.save(evaluation);
        } else {
            return responseToClient(ERR_UPDATE_EVALUATE);
        }
    }

    @Override
    public void deleteById(Long id) {
        evaluationRepositoryJPA.deleteById(id);
    }

}
