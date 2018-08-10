package com.barry.cloud.platform.redis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/10 15:06
 */
@Data
public class TestEntity implements Serializable {

    private static final long serialVersionUID = -1377342389793265424L;

    private String id;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String remark;


}
