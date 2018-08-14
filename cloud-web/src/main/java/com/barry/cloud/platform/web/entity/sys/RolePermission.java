package com.barry.cloud.platform.web.entity.sys;

import lombok.Data;

@Data
public class RolePermission {

    private Long id;

    private Long roleId;

    private Long permissionId;

}