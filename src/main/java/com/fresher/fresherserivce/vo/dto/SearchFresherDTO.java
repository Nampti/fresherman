package com.fresher.fresherserivce.vo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchFresherDTO {

    private String name;

    private String email;

    private String phoneNumber;

    private Date dob;

    private String address;

    private Date joiningDate;

    private String graduationDate;

    private String university;

    private String gender;

    private String department;

    private String note;

    private String languageCode;

    private Long status;

    @JsonIgnore
    Integer startRecord;

    @JsonIgnore
    Integer pageSize;
}
