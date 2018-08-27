package com.barry.cloud.platform.security.entity;

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

    //@ManyToMany(mappedBy = "roles")
    /**
     * @Transient表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性；
     * 如果一个属性并非数据库表的字段映射，就务必将其标示为@Transient，否则ORM框架默认其注解为@Basic；
     * 表示该字段在数据库表中没有
     * */
    @Transient
    private List<User> users;

}