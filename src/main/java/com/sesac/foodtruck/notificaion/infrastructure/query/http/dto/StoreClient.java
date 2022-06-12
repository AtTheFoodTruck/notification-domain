package com.sesac.foodtruck.notificaion.infrastructure.query.http.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("STORE-SERVICE")
public interface StoreClient {
    @GetMapping("/api/v1/store/{storeId}")
    Result<GetStoreResponse> getStore(@RequestHeader(value="Authorization", required = true) String authorizationHeader, @PathVariable(value = "storeId") Long storeId);
}
