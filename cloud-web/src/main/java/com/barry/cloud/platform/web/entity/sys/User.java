package com.barry.cloud.platform.web.entity.sys;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long userId;

    private String userName;

    private String userPwd;

    private String nick;

    private String realName;

    private Boolean sex;

    private Integer age;

    private String email;

    private String mobile;

    private String position;

    private String salt;

    private Integer state;

    private Date createTime;

    private Date lastLoginTime;

    private String remark;


}