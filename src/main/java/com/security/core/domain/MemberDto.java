package com.security.core.domain;

import lombok.Data;

@Data
public class MemberDto {

    private String username;
    private String password;
    private String email;
    private String age;
}
