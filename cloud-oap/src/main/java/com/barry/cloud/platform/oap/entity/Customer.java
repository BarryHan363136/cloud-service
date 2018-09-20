package com.barry.cloud.platform.oap.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 10:43
 */
@Data
@Entity
@Table(name = "spark_customer")
public class Customer {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "accout", nullable = false)
    private String accout;

    @Column(name = "password", nullable = false)
    private String password;

}