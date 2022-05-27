package com.example.backend.api.member.dto.naver;

import lombok.Data;

@Data
public class Response {
    private String id;
    private String email;
    private String nickname;
    private String name;
    private String gender;
    private String age;
    private String birthday;
    private String birthyear;
    private String profile_image;
    private String mobile;
}
