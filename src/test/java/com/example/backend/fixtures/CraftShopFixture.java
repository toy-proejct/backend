package com.example.backend.fixtures;

import com.example.backend.api.craftshop.dto.RegisterCraftShopRequest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class CraftShopFixture {

    public static RegisterCraftShopRequest.RegisterCraftShopRequestBuilder aRegisterCraftShopRequest() {
        return RegisterCraftShopRequest.builder()
                .name("test craftShop")
                .phone("010-0000-0000")
                .introduce("this is craftShop")
                .businessDayRequests(List.of(aBusinessDay().build()))
                .locationRequest(aLocationRequest().build());
    }

    public static RegisterCraftShopRequest.BusinessDayRequest.BusinessDayRequestBuilder aBusinessDay() {
        return RegisterCraftShopRequest.BusinessDayRequest
                .builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .businessHourFrom(LocalTime.of(9, 0, 0))
                .businessHourTo(LocalTime.of(18, 0, 0));
    }

    private static RegisterCraftShopRequest.LocationRequest.LocationRequestBuilder aLocationRequest() {
        return RegisterCraftShopRequest.LocationRequest.builder()
                .location("location");
    }
}
