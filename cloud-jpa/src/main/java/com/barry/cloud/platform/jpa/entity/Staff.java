package com.barry.cloud.platform.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 13:49
 */

@Data
@Table(name = "spark_staff")
@Entity
public class Staff implements Serializable {

    private static final long serialVersionUID = 5593560516862449870L;

    @Id
    @SequenceGenerator(name = "staff_generator", sequenceName = "staff_sequence", initialValue = 100)
    @GeneratedValue(generator = "staff_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String realName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer gender;

    public Staff() {

    }

    public Staff(String userName, String realName, String password, Integer gender) {
        this.userName = userName;
        this.realName = realName;
        this.password = password;
        this.gender= gender;
    }

}