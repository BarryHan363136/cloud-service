package com.barry.cloud.platform.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 10:47
 */
@Data
@Entity
@Table(name = "spark_role")
public class Role {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}