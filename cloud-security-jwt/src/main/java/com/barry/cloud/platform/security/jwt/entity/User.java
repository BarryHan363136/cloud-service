package com.barry.cloud.platform.security.jwt.entity;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="spark_user")
public class User {

    private Long id;

    private String userName;

    private String userPwd;

    private String realName;


}