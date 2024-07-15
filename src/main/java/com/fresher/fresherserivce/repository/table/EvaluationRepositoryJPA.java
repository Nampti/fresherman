package com.fresher.fresherserivce.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fresher.fresherserivce.model.Evaluation;
import com.fresher.fresherserivce.vo.until.ResultSelectEntity;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EvaluationRepositoryJPA extends JpaRepository<Evaluation, Long> {

    @Query(value = "SELECT e FROM Evaluation e WHERE e.idFresher = :idFresher ORDER BY e.createDate DESC LIMIT :limit", nativeQuery = true)
    List<Evaluation> findEvaluationByIdFresherAndIdCenter(@Param("idFresher") Long idFresher,
            @Param("limit") int limit);
}
