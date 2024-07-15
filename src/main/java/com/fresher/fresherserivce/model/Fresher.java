package com.fresher.fresherserivce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fresher.fresherserivce.vo.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fresher")
public class Fresher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ID_CENTER")
    private Long idCenter;

    private String name;

    private String email;

    private String phoneNumber;

    private Date dob;

    private String address;

    @CreatedDate
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date joiningDate;

    @CreatedDate
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String graduationDate;

    private String university;

    private String gender;

    private String department;

    private String languageCode;

    private String note;

    private Long status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
}
