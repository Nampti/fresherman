package com.fresher.fresherserivce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long idFresher;

    private Long idCenter;

    private Date evaluationDate;

    private Double score;

    private String comments;

    private Date createDate;

    private Long status;

    private String note;

}
