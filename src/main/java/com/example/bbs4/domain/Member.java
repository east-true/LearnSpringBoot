package com.example.bbs4.domain;

import lombok.Data;

import java.util.Date;

// Data어노테이션 (롬복 : lomboc?) getter/setter를 알아서 처리
@Data
public class Member {
    private long mno;
    private String id;
    private String password;
    private String name;
    private String type;
    private Date regDate;
}
