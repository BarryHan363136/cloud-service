package com.barry.cloud.platform.jpa.entity;

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
@Table(name = "spark_user")
public class User {

    @Id
    @Column(name = "id")
    //@SequenceGenerator(name = "city_generator", sequenceName = "city_sequence", initialValue = 23)
    //@GeneratedValue(generator = "city_generator")
    private Long id;

    @Column(name = "name", unique = true, nullable = true)
    private String name;

    @Column(name = "password", nullable = true)
    private String password;

    @ManyToMany(cascade={CascadeType.REFRESH}, fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinTable(name = "spark_user_role",
            joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "rid", referencedColumnName = "id")})
    private List<Role> roles;

}