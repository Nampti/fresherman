package com.fresher.fresherserivce.vo.response;

import lombok.Data;

@Data
public class AccountPaymentResponse {

    private Long userId;

    private Long status; // 2: active, 0: lock

    private Long amount;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;
}
