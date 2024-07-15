package com.fresher.fresherserivce.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fresher.fresherserivce.vo.enums.CoreErrorApp;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntity {
    Object data;
    MessEntity mess;

    public ResponseEntity() {
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MessEntity getMess() {
        return this.mess;
    }

    public void setMess(MessEntity mess) {
        this.mess = mess;
    }

    public static Object responseToClient(Object itemObject) {
        ResponseEntity responseEntity = new ResponseEntity();
        CoreErrorApp errorApp;
        if (itemObject == null) {
            errorApp = CoreErrorApp.DATAEMTY;
        } else {
            errorApp = CoreErrorApp.SUCCESS;
            responseEntity.setData(itemObject);
        }

        MessEntity itemEntity = new MessEntity();
        itemEntity.setCode(errorApp.getCode());
        itemEntity.setDescription(errorApp.getDescription());
        responseEntity.setMess(itemEntity);
        return responseEntity;
    }

    public static Object responseToClient(CoreErrorApp errorApp, Object itemObject) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (itemObject != null) {
            responseEntity.setData(itemObject);
        }
        MessEntity itemEntity = new MessEntity();
        itemEntity.setCode(errorApp.getCode());
        itemEntity.setDescription(errorApp.getDescription());
        responseEntity.setMess(itemEntity);
        return responseEntity;
    }

    public static Object responseToClient(int status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;
    }

    public static Object responseToClient(CoreErrorApp errorApp) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", errorApp.getCode());
        response.put("message", errorApp.getDescription());
        return response;
    }
}
