package com.fresher.fresherserivce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fresher.fresherserivce.model.Evaluation;
import com.fresher.fresherserivce.service.EvaluationService;

import static com.fresher.fresherserivce.vo.response.ResponseEntity.responseToClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    /**
     *
     * @param idFresher
     * @return
     */
    @GetMapping("/{idFresher}")
    public ResponseEntity<List<Evaluation>> getTop3Evaluations(
            @PathVariable Long idFresher) {
        List<Evaluation> evaluations = evaluationService.findEvaluationByIdFresherAndIdCenter(idFresher);
        return ResponseEntity.ok(evaluations);
    }

    /**
     *
     * @param evaluation
     * @return
     */
    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        Evaluation savedEvaluation = evaluationService.save(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvaluation);
    }

    /**
     *
     * @param id
     * @param evaluationDetails
     * @return
     */
    @PutMapping("/{id}")
    public Object updateEvaluation(@PathVariable Long id, @RequestBody Evaluation evaluationDetails) {
        Object resultObj = evaluationService.update(id, evaluationDetails);
        return new ResponseEntity<>(responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
