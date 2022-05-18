package com.example.backend.fixtures;

import com.example.backend.api.craftshop.dto.RegisterCraftShopRequest;

import java.time.LocalTime;

public class CraftShopFixture {

    public static RegisterCraftShopRequest.RegisterCraftShopRequestBuilder aRegisterCraftShopRequest() {
        return RegisterCraftShopRequest.builder()
                .name("test craftShop")
                .phone("010-0000-0000")
                .introduce("this is craftShop")
                .businessHourRequest(aBusinessHour().build())
                .locationRequest(aLocationRequest().build())
                ;
    }

    public static RegisterCraftShopRequest.BusinessHourRequest.BusinessHourRequestBuilder aBusinessHour() {
        return RegisterCraftShopRequest.BusinessHourRequest
                .builder()
                .businessHourFrom(LocalTime.of(9, 0, 0))
                .businessHourTo(LocalTime.of(18, 0, 0));
    }

    private static RegisterCraftShopRequest.LocationRequest.LocationRequestBuilder aLocationRequest() {
        return RegisterCraftShopRequest.LocationRequest.builder()
                .location("location");
    }
}
