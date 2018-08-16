package com.barry.cloud.platform.shiro.entity;

import lombok.Data;

@Data
public class SysPermission {

    private Long id;

    private Byte available;

    private String name;

    private Long parentId;

    private String parentIds;

    private String permission;

    private String resourceType;

    private String url;


}