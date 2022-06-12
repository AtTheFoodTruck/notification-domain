package com.sesac.foodtruck.notificaion.infrastructure.query.http.dto;

import lombok.Getter;

@Getter
public class GetStoreResponse {
    private Long storeId;
    private String storeName;
    private String phoneNum;
}
