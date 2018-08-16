package com.barry.cloud.platform.shiro.entity;

import lombok.Data;

@Data
public class SysRole {

    private Long id;

    private Byte available;

    private String description;

    private String role;


}