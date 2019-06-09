package com.barry.cloud.platform.easyexcel.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Author:
 * @email:
 * @Description: 用户实体类
 * @Date: 2019/5/13/0013 15:28
 */
@Data
public class UserReader implements Serializable {

    private static final long serialVersionUID = -6937412768185068015L;

    private String name;

    private String password;

    private Integer age;

    private Date birthTime;

}