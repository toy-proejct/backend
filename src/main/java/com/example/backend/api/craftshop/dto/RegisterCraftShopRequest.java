package com.example.backend.api.craftshop.dto;

import com.example.backend.api.craftshop.domain.BusinessDay;
import com.example.backend.api.craftshop.domain.BusinessHour;
import com.example.backend.api.craftshop.domain.CraftShop;
import com.example.backend.api.craftshop.domain.Location;
import com.example.backend.api.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "공방 등록을 요청하는 객체")
public class RegisterCraftShopRequest {
    @NotEmpty(message = "공방이름은 필수 입력값입니다.")
    @ApiModelProperty(value = "닉네임")
    private String name;

    @NotEmpty(message = "전화번호는 필수 입력값입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식에 맞지 않습니다.")
    @ApiModelProperty(value = "전화번호")
    private String phone;

    @ApiModelProperty(value = "공방소개")
    private String introduce;

    private List<BusinessDayRequest> businessDayRequests = new ArrayList<>();

    private LocationRequest locationRequest;

    public CraftShop toCraftShopEntity(Member member) {
        return new CraftShop(name, phone, introduce, member.getId(), locationRequest.toEntity());
    }

    public List<BusinessDay> toBusinessDaysEntity(CraftShop craftShop) {
        return businessDayRequests.stream()
                .map(it -> it.toEntity(craftShop))
                .collect(Collectors.toList());
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "공방 운영시간을 입력하는 객체")
    public static class BusinessDayRequest {
        @NotEmpty(message = "영업일은 필수 입력값입니다.")
        @ApiModelProperty(value = "영업일")
        private DayOfWeek dayOfWeek;

        @NotEmpty(message = "오픈시간은 필수 입력값입니다.")
        @ApiModelProperty(value = "종료시간")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        private LocalTime businessHourFrom;

        @NotEmpty(message = "마감시간은 필수 입력값입니다.")
        @ApiModelProperty(value = "종료시간")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        private LocalTime businessHourTo;

        public BusinessDay toEntity(CraftShop craftShop) {
            return new BusinessDay(dayOfWeek, craftShop, new BusinessHour(businessHourFrom, businessHourTo));
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "공방 위치정보를 입력하는 객체")
    public static class LocationRequest {
        @ApiModelProperty(value = "위치정보")
        private String location;

        public Location toEntity() {
            return new Location(location);
        }
    }
}
