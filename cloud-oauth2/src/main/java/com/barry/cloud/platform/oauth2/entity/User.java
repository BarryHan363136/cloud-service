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

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "age")
    private Integer age;



}