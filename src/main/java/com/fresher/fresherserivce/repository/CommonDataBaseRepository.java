package com.fresher.fresherserivce.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresher.fresherserivce.vo.until.ResultSelectEntity;
import com.zaxxer.hikari.HikariDataSource;

import java.util.*;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommonDataBaseRepository {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOGGER = Logger.getLogger(CommonDataBaseRepository.class);

    public List<? extends Object> getListData_Core(StringBuilder queryString, List<Object> arrParams, Integer startPage,
            Integer pageLoad) {
        Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
        int countParams = 1;
        if (arrParams != null) {
            for (Iterator var7 = arrParams.iterator(); var7.hasNext(); ++countParams) {
                Object arrParam = var7.next();
                query.setParameter(countParams, arrParam);
            }
        }

        if (startPage != null && pageLoad != null) {
            query.setFirstResult(startPage).setMaxResults(startPage + pageLoad);
        }

        List objectList = query.getResultList();
        this.entityManager.close();
        return objectList;
    }

    public List<? extends Object> getListData(StringBuilder queryString, List<Object> arrParams, Integer startPage,
            Integer pageLoad, Class<?> classOfT) {
        try {
            boolean databaseIsOracle = false;

            String strVersionOracle;
            try {
                HikariDataSource hikariDataS = (HikariDataSource) this.entityManager.getEntityManagerFactory()
                        .getProperties().get("hibernate.connection.datasource");
                strVersionOracle = hikariDataS.getDriverClassName().toLowerCase();
                if (strVersionOracle.contains("oracle")) {
                    databaseIsOracle = true;
                }
            } catch (Exception var12) {
                LOGGER.error(var12);
            }

            StringBuilder sqlPage = new StringBuilder();
            if (databaseIsOracle && startPage != null && pageLoad != null) {
                sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
                sqlPage.append(queryString.toString());
                sqlPage.append(" ) a ) ");
                sqlPage.append(String.format(" WHERE r__ > %d", startPage));
                sqlPage.append(String.format(" and r__ <= %d", startPage + pageLoad));
            } else {
                sqlPage = queryString;
            }

            Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
            int countParams = 1;
            if (arrParams != null) {
                for (Iterator var10 = arrParams.iterator(); var10.hasNext(); ++countParams) {
                    Object arrParam = var10.next();
                    query.setParameter(countParams, arrParam);
                }
            }

            if (startPage != null && pageLoad != null && !databaseIsOracle) {
                query.setFirstResult(startPage).setMaxResults(pageLoad);
            }

            List objectList = query.getResultList();
            List<Object> listResult = convertToEntity(objectList, classOfT);
            this.entityManager.close();
            return listResult;
        } catch (Exception var13) {
            LOGGER.error(var13);
            return null;
        }
    }

    public List getListDataOriginal(StringBuilder queryString, List<Object> arrParams, Integer startPage,
            Integer pageLoad) {
        try {
            Boolean databaseIsOracle = false;

            String strVersionOracle;
            try {
                HikariDataSource hikariDataS = (HikariDataSource) this.entityManager.getEntityManagerFactory()
                        .getProperties().get("hibernate.connection.datasource");
                strVersionOracle = hikariDataS.getDriverClassName().toLowerCase();
                if (strVersionOracle.contains("oracle")) {
                    databaseIsOracle = true;
                }
            } catch (Exception var11) {
                LOGGER.error(var11);
            }

            StringBuilder sqlPage = new StringBuilder();
            if (databaseIsOracle && startPage != null && pageLoad != null) {
                sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
                sqlPage.append(queryString.toString());
                sqlPage.append(" ) a ) ");
                sqlPage.append(String.format(" WHERE r__ > %d", startPage));
                sqlPage.append(String.format(" and r__ <= %d", startPage + pageLoad));
            } else {
                sqlPage = queryString;
            }

            Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
            int countParams = 1;
            if (arrParams != null) {
                for (Iterator var9 = arrParams.iterator(); var9.hasNext(); ++countParams) {
                    Object arrParam = var9.next();
                    query.setParameter(countParams, arrParam);
                }
            }

            if (startPage != null && pageLoad != null && !databaseIsOracle) {
                query.setFirstResult(startPage).setMaxResults(pageLoad);
            }

            List objectList = query.getResultList();
            this.entityManager.close();
            return objectList;
        } catch (Exception var12) {
            LOGGER.error(var12);
            return null;
        }
    }

    public Object getFirstData(StringBuilder queryString, List<Object> arrParams, Class<?> classOfT) {
        try {
            Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
            int countParams = 1;
            if (arrParams != null) {
                for (Iterator var6 = arrParams.iterator(); var6.hasNext(); ++countParams) {
                    Object arrParam = var6.next();
                    query.setParameter(countParams, arrParam);
                }
            }

            query.setFirstResult(0).setMaxResults(1);
            List objectList = query.getResultList();
            List<Object> listResult = convertToEntity(objectList, classOfT);
            this.entityManager.close();
            return listResult;
        } catch (Exception var8) {
            LOGGER.error(var8);
            return null;
        }
    }

    public List<? extends Object> getListData(StringBuilder queryString, HashMap<String, Object> hmapParams,
            Integer startPage, Integer pageLoad, Class<?> classOfT) {
        try {
            boolean databaseIsOracle = false;

            String strVersionOracle;
            try {
                HikariDataSource hikariDataS = (HikariDataSource) this.entityManager.getEntityManagerFactory()
                        .getProperties().get("hibernate.connection.datasource");
                strVersionOracle = hikariDataS.getDriverClassName().toLowerCase();
                if (strVersionOracle.contains("oracle")) {
                    databaseIsOracle = true;
                }
            } catch (Exception var13) {
                LOGGER.error(var13);
            }

            StringBuilder sqlPage = new StringBuilder();
            if (databaseIsOracle && startPage != null && pageLoad != null) {
                sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
                sqlPage.append(queryString.toString());
                sqlPage.append(" ) a ) ");
                sqlPage.append(String.format(" WHERE r__ > %d", startPage));
                sqlPage.append(String.format(" and r__ <= %d", startPage + pageLoad));
            } else {
                sqlPage = queryString;
            }

            Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
            if (hmapParams != null) {
                Set set = hmapParams.entrySet();
                Iterator var10 = set.iterator();

                while (var10.hasNext()) {
                    Object o = var10.next();
                    Map.Entry mentry = (Map.Entry) o;
                    query.setParameter(mentry.getKey().toString(), mentry.getValue());
                }
            }

            if (startPage != null && pageLoad != null && !databaseIsOracle) {
                query.setFirstResult(startPage).setMaxResults(pageLoad);
            }

            List objectList = query.getResultList();
            List<Object> listResult = convertToEntity(objectList, classOfT);
            this.entityManager.close();
            return listResult;
        } catch (Exception var14) {
            LOGGER.error(var14);
            return null;
        }
    }

    public List<?> getListDataOriginal(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage,
            Integer pageLoad) {
        try {
            Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);

            if (hmapParams != null) {
                Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
                for (Map.Entry<String, Object> mentry : set) {
                    query.setParameter(mentry.getKey(), mentry.getValue());
                }
            }

            if (startPage != null && pageLoad != null) {
                query.setFirstResult(startPage).setMaxResults(pageLoad);
            }

            return query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Error executing query", e);
            return null;
        }
    }

    public Object getFirstData(StringBuilder queryString, HashMap<String, Object> hmapParams, Class<?> classOfT) {
        try {
            Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
            if (hmapParams != null) {
                Set set = hmapParams.entrySet();
                Iterator var6 = set.iterator();

                while (var6.hasNext()) {
                    Object o = var6.next();
                    Map.Entry mentry = (Map.Entry) o;
                    query.setParameter(mentry.getKey().toString(), mentry.getValue());
                }
            }

            query.setFirstResult(0).setMaxResults(1);
            List objectList = query.getResultList();
            List<Object> listResult = convertToEntity(objectList, classOfT);
            this.entityManager.close();
            return listResult.size() > 0 ? listResult.get(0) : null;
        } catch (Exception var9) {
            LOGGER.error(var9);
            return null;
        }
    }

    public Object getOnlyOneValueFirstData(StringBuilder queryString, List<Object> arrParams) {
        try {
            Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
            int countParams = 1;
            if (arrParams != null) {
                for (Iterator var5 = arrParams.iterator(); var5.hasNext(); ++countParams) {
                    Object arrParam = var5.next();
                    query.setParameter(countParams, arrParam);
                }
            }

            query.setFirstResult(0).setMaxResults(1);
            List<Tuple> listResult = query.getResultList();
            this.entityManager.close();
            if (listResult != null && listResult.size() > 0) {
                Tuple tuple = (Tuple) listResult.get(0);
                return tuple.get(((TupleElement) tuple.getElements().stream().findFirst().get()).getAlias());
            } else {
                return null;
            }
        } catch (Exception var7) {
            LOGGER.error(var7);
            return null;
        }
    }

    public Object getOnlyOneValueFirstData(StringBuilder queryString, HashMap<String, Object> hmapParams) {
        try {
            Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
            if (hmapParams != null) {
                Set set = hmapParams.entrySet();
                Iterator var5 = set.iterator();

                while (var5.hasNext()) {
                    Object o = var5.next();
                    Map.Entry mentry = (Map.Entry) o;
                    query.setParameter(mentry.getKey().toString(), mentry.getValue());
                }
            }

            query.setFirstResult(0).setMaxResults(1);
            List<Tuple> listResult = query.getResultList();
            this.entityManager.close();
            if (listResult != null && listResult.size() > 0) {
                Tuple tuple = (Tuple) listResult.get(0);
                return tuple.get(((TupleElement) tuple.getElements().stream().findFirst().get()).getAlias());
            } else {
                return null;
            }
        } catch (Exception var8) {
            LOGGER.error(var8);
            return null;
        }
    }

    public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams,
            Integer startPage, Integer pageLoad, Class<?> classOfT) {
        try {
            // Xây dựng truy vấn với phân trang
            StringBuilder sqlPage = new StringBuilder(queryString);
            if (startPage != null && pageLoad != null) {
                sqlPage.append(" LIMIT ").append(pageLoad).append(" OFFSET ").append(startPage);
            }

            // Tạo truy vấn và đặt tham số
            Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
            if (hmapParams != null) {
                for (Map.Entry<String, Object> entry : hmapParams.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
                }
            }

            // Thực thi truy vấn và xử lý kết quả
            List<?> objectList = query.getResultList();
            ResultSelectEntity result = new ResultSelectEntity();
            if (objectList != null) {
                List<Object> listResult = Collections
                        .singletonList(convertToEntity((List<Tuple>) objectList, classOfT));
                result.setListData(listResult);
            }

            // Lấy tổng số bản ghi
            result.setCount(this.getCountDataInSelect(queryString, hmapParams));
            this.entityManager.close();
            return result;
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            return null;
        }
    }

    public ResultSelectEntity getListDataAndCount(StringBuilder queryString, List<Object> arrParams, Integer startPage,
            Integer pageLoad, Class<?> classOfT) {
        try {
            // Xây dựng truy vấn với phân trang cho PostgreSQL
            StringBuilder sqlPage = new StringBuilder(queryString);
            if (startPage != null && pageLoad != null) {
                sqlPage.append(" LIMIT ").append(pageLoad).append(" OFFSET ").append(startPage);
            }

            // Tạo truy vấn và đặt tham số
            Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
            int countParams = 1;
            if (arrParams != null) {
                for (Object arrParam : arrParams) {
                    query.setParameter(countParams++, arrParam);
                }
            }
            System.out.println(query);
            // Thực thi truy vấn và xử lý kết quả
            List<?> objectList = query.getResultList();
            ResultSelectEntity result = new ResultSelectEntity();
            if (objectList != null) {
                List<Object> listResult = Collections
                        .singletonList(convertToEntity((List<Tuple>) objectList, classOfT));
                result.setListData(listResult);
            }

            // Lấy tổng số bản ghi
            result.setCount(this.getCountDataInSelect(queryString, arrParams));
            this.entityManager.close();
            return result;
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            return null;
        }
    }

    private int getCountDataInSelect(StringBuilder queryString, List<Object> arrParams) {
        StringBuilder strBuild = new StringBuilder();
        String strReplace = queryString.toString().trim().replaceAll(" +", " ");
        String strSql = this.removeOrderBy(strReplace);
        strBuild.append("Select count(1) as count From (");
        strBuild.append(strSql);
        strBuild.append(") tbcount");
        Query query = this.entityManager.createNativeQuery(strBuild.toString());
        int countParams = 1;
        Object value;
        if (arrParams != null) {
            for (Iterator var8 = arrParams.iterator(); var8.hasNext(); ++countParams) {
                value = var8.next();
                query.setParameter(countParams, value);
            }
        }

        List resultQr = query.getResultList();
        if (resultQr != null && resultQr.size() > 0) {
            value = resultQr.get(0);
            String result = String.valueOf(value);
            this.entityManager.close();
            return Integer.parseInt(result);
        } else {
            return 0;
        }
    }

    public int getCountData(StringBuilder queryString, List<Object> arrParams) {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("Select count(1) as count From (");
        strBuild.append(queryString);
        strBuild.append(") tbcount");
        Query query = this.entityManager.createNativeQuery(strBuild.toString());
        int countParams = 1;
        Object value;
        if (arrParams != null) {
            for (Iterator var6 = arrParams.iterator(); var6.hasNext(); ++countParams) {
                value = var6.next();
                query.setParameter(countParams, value);
            }
        }

        List resultQr = query.getResultList();
        if (resultQr != null && resultQr.size() > 0) {
            value = resultQr.get(0);
            String result = String.valueOf(value);
            this.entityManager.close();
            return Integer.valueOf(result);
        } else {
            return 0;
        }
    }

    public int getCountData(StringBuilder queryString, HashMap<String, Object> hmapParams) {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("Select count(1) as count From (");
        strBuild.append(queryString);
        strBuild.append(") tbcount");
        Query query = this.entityManager.createNativeQuery(strBuild.toString());
        if (hmapParams != null) {
            Set set = hmapParams.entrySet();

            Map.Entry mentry;
            Object value;
            for (Iterator iterator = set.iterator(); iterator.hasNext(); query.setParameter(mentry.getKey().toString(),
                    value)) {
                mentry = (Map.Entry) iterator.next();
                value = mentry.getValue();
                if (value == null) {
                    value = "";
                }
            }
        }

        List resultQr = query.getResultList();
        if (resultQr != null && resultQr.size() > 0) {
            Object value = resultQr.get(0);
            String result = String.valueOf(value);
            this.entityManager.close();
            return Integer.valueOf(result);
        } else {
            return 0;
        }
    }

    private int getCountDataInSelect(StringBuilder queryString, HashMap<String, Object> hmapParams) {
        StringBuilder strBuild = new StringBuilder();
        String strReplace = queryString.toString().trim().replaceAll(" +", " ");
        String strSql = this.removeOrderBy(strReplace);
        strBuild.append("Select count(1) as count From (");
        strBuild.append(strSql);
        strBuild.append(") tbcount");
        Query query = this.entityManager.createNativeQuery(strBuild.toString());
        if (hmapParams != null) {
            Set set = hmapParams.entrySet();

            Map.Entry mentry;
            Object value;
            for (Iterator var8 = set.iterator(); var8.hasNext(); query
                    .setParameter(mentry.getKey().toString().toLowerCase(), value)) {
                Object o = var8.next();
                mentry = (Map.Entry) o;
                value = mentry.getValue();
                if (value == null) {
                    value = "";
                }
            }
        }

        List resultQr = query.getResultList();
        if (resultQr != null && resultQr.size() > 0) {
            Object value = resultQr.get(0);
            String result = String.valueOf(value);
            this.entityManager.close();
            return Integer.parseInt(result);
        } else {
            return 0;
        }
    }

    public Boolean excuteSqlDatabase(StringBuilder queryString, List<Object> arrParams) {
        boolean result = true;

        try {
            Query query = this.entityManager.createNativeQuery(queryString.toString());
            int countParams = 1;
            if (arrParams != null && arrParams.size() > 0) {
                for (Iterator var6 = arrParams.iterator(); var6.hasNext(); ++countParams) {
                    Object arrParam = var6.next();
                    query.setParameter(countParams, arrParam);
                }
            }

            int var9 = query.executeUpdate();
        } catch (Exception var8) {
            LOGGER.error(var8);
            result = false;
        }

        this.entityManager.close();
        return result;
    }

    @Transactional
    public Boolean excuteSqlDatabase(StringBuilder queryString, HashMap<String, Object> hmapParams) {
        boolean result = true;

        try {
            Query query = this.entityManager.createNativeQuery(queryString.toString());
            if (hmapParams != null) {
                Set set = hmapParams.entrySet();

                Map.Entry mentry;
                Object value;
                for (Iterator var6 = set.iterator(); var6.hasNext(); query.setParameter(mentry.getKey().toString(),
                        value)) {
                    Object o = var6.next();
                    mentry = (Map.Entry) o;
                    value = mentry.getValue();
                    if (value == null) {
                        value = "";
                    }
                }
            }

            int var11 = query.executeUpdate();
        } catch (Exception var10) {
            LOGGER.error(var10);
            result = false;
        }

        this.entityManager.close();
        return result;
    }

    private String removeOrderBy(String strReplace) {
        String strResult = strReplace.toLowerCase();
        int indexLast = strResult.lastIndexOf("order by");
        int indexLastCm = strResult.lastIndexOf(")");
        if (indexLast > 0 && indexLastCm < indexLast) {
            strResult = strResult.substring(0, indexLast);
        }

        return strResult;
    }

    public static List<?> convertToEntity(List<Tuple> input, Class<?> dtoClass) {
        List<Object> arrayList = new ArrayList();
        input.stream().forEach((tuple) -> {
            Map<String, Object> temp = new HashMap();
            tuple.getElements().stream().forEach((tupleElement) -> {
                Object value = tuple.get(tupleElement.getAlias());
                temp.put(tupleElement.getAlias().toLowerCase(), value);
            });
            ObjectMapper map = new ObjectMapper();
            map.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                String mapToString = map.writeValueAsString(temp);
                arrayList.add(map.readValue(mapToString, dtoClass));
            } catch (JsonProcessingException var6) {
                throw new RuntimeException(var6.getMessage());
            }
        });
        return arrayList;
    }
}
