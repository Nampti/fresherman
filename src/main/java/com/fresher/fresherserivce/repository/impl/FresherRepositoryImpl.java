package com.fresher.fresherserivce.repository.impl;

import org.springframework.stereotype.Repository;

import com.fresher.fresherserivce.model.Fresher;
import com.fresher.fresherserivce.repository.CommonDataBaseRepository;
import com.fresher.fresherserivce.repository.FresherRepository;
import com.fresher.fresherserivce.vo.dto.SearchFresherDTO;
import com.fresher.fresherserivce.vo.until.ResultSelectEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FresherRepositoryImpl extends CommonDataBaseRepository implements FresherRepository {

    @Override
    public ResultSelectEntity findFresherAndCount(SearchFresherDTO searchFresherDTO) {
        StringBuilder sql = new StringBuilder();
        List<Object> parrParams = new ArrayList<>();
        sql.append("SELECT ");
        sql.append(" F.ID AS id,                            ");
        sql.append(" F.ID_CENTER AS idCenter,               ");
        sql.append(" F.NAME AS name,                        ");
        sql.append(" F.EMAIL AS email,                      ");
        sql.append(" F.PHONE_NUMBER AS phoneNumber,         ");
        sql.append(" F.DOB AS dob,                          ");
        sql.append(" F.ADDRESS AS address,                  ");
        sql.append(" F.JOINING_DATE AS joiningDate,         ");
        sql.append(" F.GRADUATION_DATE AS graduationDate,   ");
        sql.append(" F.UNIVERSITY AS university,            ");
        sql.append(" F.GENDER AS gender,                    ");
        sql.append(" F.DEPARTMENT AS department,            ");
        sql.append(" F.LANGUAGE_CODE AS languageCode,       ");
        sql.append(" F.NOTE AS note,                        ");
        sql.append(" F.STATUS AS status,                    ");
        sql.append(" F.CREATE_DATE AS createDate            ");
        sql.append(" FROM FRESHER F                         ");
        sql.append(" WHERE 1 = 1                            ");
        if (searchFresherDTO.getName() != null) {
            sql.append(" AND UPPER(F.NAME) like ? ");
            parrParams.add("%" + searchFresherDTO.getName().toUpperCase() + "%");
        }
        if (searchFresherDTO.getEmail() != null) {
            sql.append(" AND F.EMAIL = ? ");
            parrParams.add(searchFresherDTO.getEmail());
        }
        if (searchFresherDTO.getPhoneNumber() != null) {
            sql.append(" AND F.PHONE_NUMBER = ? ");
            parrParams.add(searchFresherDTO.getPhoneNumber());
        }
        if (searchFresherDTO.getDob() != null) {
            sql.append(" AND F.DOB = ? ");
            parrParams.add(searchFresherDTO.getDob());
        }
        if (searchFresherDTO.getStatus() != null) {
            sql.append(" AND F.STATUS = ? ");
            parrParams.add(searchFresherDTO.getStatus());
        }
        if (searchFresherDTO.getUniversity() != null) {
            sql.append(" AND F.UNIVERSITY = ? ");
            parrParams.add(searchFresherDTO.getUniversity());
        }
        if (searchFresherDTO.getDepartment() != null) {
            sql.append(" AND F.DEPARTMENT = ? ");
            parrParams.add(searchFresherDTO.getDepartment());
        }
        if (searchFresherDTO.getLanguageCode() != null) {
            sql.append(" AND F.LANGUAGE_CODE = ? ");
            parrParams.add(searchFresherDTO.getLanguageCode());
        }

        Integer start = 0;
        if (searchFresherDTO.getStartRecord() != null) {
            start = searchFresherDTO.getStartRecord();
        }
        Integer pageSize = null;
        if (searchFresherDTO.getPageSize() != null) {
            pageSize = searchFresherDTO.getPageSize();
        }
        System.out.println(sql);

        return getListDataAndCount(sql, parrParams, start, pageSize, Fresher.class);
    }
}
