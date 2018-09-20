package com.barry.cloud.platform.oauth2.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 10:43
 */
@Data
@Entity
@Table(name = "spark_user")
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "status")
    private Integer status;



}